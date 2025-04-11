package kyulab.searchservice.service;

import kyulab.searchservice.document.GroupDocument;
import kyulab.searchservice.document.PostsDocument;
import kyulab.searchservice.document.UsersDocument;
import kyulab.searchservice.dto.kafka.GroupDto;
import kyulab.searchservice.dto.kafka.PostDto;
import kyulab.searchservice.dto.kafka.UsersDto;
import kyulab.searchservice.dto.res.GroupResDto;
import kyulab.searchservice.dto.res.PostResDto;
import kyulab.searchservice.dto.res.SearchResDto;
import kyulab.searchservice.dto.res.UsersResDto;
import kyulab.searchservice.repository.GroupRepository;
import kyulab.searchservice.repository.PostRepository;
import kyulab.searchservice.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {

	private final UsersRepository usersRepository;
	private final PostRepository postRepository;
	private final GroupRepository groupRepository;

	public Mono<SearchResDto> search(String query) {
		Mono<List<UsersResDto>> users = searchUsers(query);
		Mono<List<PostResDto>> posts = searchPosts(query);
		Mono<List<GroupResDto>> group = searchGroup(query);

		return Mono.zip(users, posts, group)
				.map(zip -> new SearchResDto(zip.getT1(), zip.getT2(), zip.getT3()));
	}

	public Mono<List<UsersResDto>> searchUsers(String query) {
		return Mono.fromCallable(() ->
				usersRepository.findByNameContaining(query).stream()
						.map(UsersResDto::from)
						.toList()
		).subscribeOn(Schedulers.boundedElastic());
	}

	public Mono<List<PostResDto>> searchPosts(String query) {
		return Mono.fromCallable(() ->
				postRepository.findBySubjectContaining(query).stream()
						.map(PostResDto::from)
						.toList()
		).subscribeOn(Schedulers.boundedElastic());
	}

	public Mono<List<GroupResDto>> searchGroup(String query) {
		return Mono.fromCallable(() ->
				groupRepository.findByNameContaining(query).stream()
						.map(GroupResDto::from)
						.toList()
		).subscribeOn(Schedulers.boundedElastic());
	}

	public void saveUsersData(UsersDto usersDto) {
		UsersDocument usersDocument = new UsersDocument(usersDto);
		try {
			usersRepository.save(usersDocument);
		} catch (Exception e) {
			log.error("사용자 데이터 저장 실패 : {}", e.getMessage());
		}
	}

	public void savePostData(PostDto postDto) {
		PostsDocument postsDocument = new PostsDocument(postDto);
		try {
			postRepository.save(postsDocument);
		} catch (Exception e) {
			log.error("게시글 데이터 저장 실패 : {}", e.getMessage());
		}
	}

	public void saveGroupData(GroupDto groupDto) {
		GroupDocument groupDocument = new GroupDocument(groupDto);
		try {
			groupRepository.save(groupDocument);
		} catch (Exception e) {
			log.error("그룹 데이터 저장 실패 : {}", e.getMessage());
		}
	}

}
