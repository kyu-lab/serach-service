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
	public void consumeUsers(ConsumerRecord<String, String> record) {
		try {
			UsersDto dto = objectMapper.readValue(record.value(), UsersDto.class);
			searchService.saveUsersData(dto);
		} catch (Exception e) {
			log.error("사용자 검색 데이터 파싱 오류 : {}", record.value());
			log.error("에러 스택 : {}" , e.getMessage());
		}
	}

	@KafkaListener(
			topics = "post-search",
			groupId = "search-group",
			clientIdPrefix = "post-consumer"
	)
	public void consumePost(ConsumerRecord<String, String> record) {
		try {
			PostDto dto = objectMapper.readValue(record.value(), PostDto.class);
			searchService.savePostData(dto);
		} catch (Exception e) {
			log.error("게시글 검색 데이터 파싱 오류 : {}", record.value());
			log.error("에러 스택 : {}" , e.getMessage());
		}
	}

	@KafkaListener(
			topics = "group-search",
			groupId = "search-group",
			clientIdPrefix = "group-consumer"
	)
	public void consumeGroup(ConsumerRecord<String, String> record) {
		try {
			GroupDto dto = objectMapper.readValue(record.value(), GroupDto.class);
			searchService.saveGroupData(dto);
		} catch (Exception e) {
			log.error("그룹 검색 데이터 파싱 오류 : {}", record.value());
			log.error("에러 스택 : {}" , e.getMessage());
		}
	}

}
