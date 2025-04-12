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
import reactor.core.publisher.Flux;
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
		return Mono.zip(
				usersRepository.findByNameContaining(query).map(UsersResDto::from).collectList(),
				postRepository.findBySubjectContaining(query).map(PostResDto::from).collectList(),
				groupRepository.findByNameContaining(query).map(GroupResDto::from).collectList()
		).map(zip -> new SearchResDto(zip.getT1(), zip.getT2(), zip.getT3()));
	}

	public Flux<UsersResDto> searchUsers(String query) {
		return usersRepository.findByNameContaining(query)
				.map(UsersResDto::from);
	}

	public Flux<PostResDto> searchPosts(String query) {
		return postRepository.findBySubjectContaining(query)
				.map(PostResDto::from);
	}

	public Flux<GroupResDto> searchGroup(String query) {
		return groupRepository.findByNameContaining(query)
				.map(GroupResDto::from);
	}

	public Mono<Void> saveUsersData(UsersDto usersDto) {
		return usersRepository.save(new UsersDocument(usersDto))
				.doOnError(e -> log.error("사용자 데이터 저장 실패: {}", e.getMessage()))
				.then();
	}

	public Mono<Void> savePostData(PostDto postDto) {
		return postRepository.save(new PostsDocument(postDto))
				.doOnError(e -> log.error("게시글 데이터 저장 실패: {}", e.getMessage()))
				.then();
	}

	public Mono<Void> saveGroupData(GroupDto groupDto) {
		return groupRepository.save(new GroupDocument(groupDto))
				.doOnError(e -> log.error("그룹 데이터 저장 실패: {}", e.getMessage()))
				.then();
	}

}
