package kyulab.searchservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kyulab.searchservice.dto.kafka.GroupDto;
import kyulab.searchservice.dto.kafka.PostDto;
import kyulab.searchservice.dto.kafka.UsersDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaService {

	private final SearchService searchService;
	private final ObjectMapper objectMapper;

	@KafkaListener(
			topics = "users-search",
			groupId = "search-group",
			clientIdPrefix = "users-consumer"
	)
	public Mono<Void> consumeUsers(ConsumerRecord<String, String> record) {
		UsersDto dto;
		try {
			dto = objectMapper.readValue(record.value(), UsersDto.class);
		} catch (Exception e) {
			log.error("사용자 검색 데이터 파싱 오류 : {}", record.value());
			log.error("에러 스택 : {}" , e.getMessage());
			throw new RuntimeException("카프카 파싱 오류");
		}
		return searchService.saveUsersData(dto);
	}

	@KafkaListener(
			topics = "post-search",
			groupId = "search-group",
			clientIdPrefix = "post-consumer"
	)
	public Mono<Void> consumePost(ConsumerRecord<String, String> record) {
		PostDto dto;
		try {
			dto = objectMapper.readValue(record.value(), PostDto.class);
		} catch (Exception e) {
			log.error("게시글 검색 데이터 파싱 오류 : {}", record.value());
			log.error("에러 스택 : {}" , e.getMessage());
			throw new RuntimeException("카프카 파싱 오류");
		}
		return searchService.savePostData(dto);
	}

	@KafkaListener(
			topics = "group-search",
			groupId = "search-group",
			clientIdPrefix = "group-consumer"
	)
	public Mono<Void> consumeGroup(ConsumerRecord<String, String> record) {
		GroupDto dto;
		try {
			dto = objectMapper.readValue(record.value(), GroupDto.class);
		} catch (Exception e) {
			log.error("그룹 검색 데이터 파싱 오류 : {}", record.value());
			log.error("에러 스택 : {}" , e.getMessage());
			throw new RuntimeException("카프카 파싱 오류");
		}
		return searchService.saveGroupData(dto);
	}

}
