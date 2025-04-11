package kyulab.searchservice.document;

import kyulab.searchservice.dto.kafka.PostDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;

@Getter
@Document(indexName = "posts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostsDocument {
	@Id
	private String id;

	@Field(type = FieldType.Text)
	private String subject;

	@Field(type = FieldType.Date)
	private LocalDate createdAt;

	public PostsDocument(PostDto postDto) {
		this.id = String.valueOf(postDto.getPostId());
		this.subject = postDto.getSubject();
		this.createdAt = postDto.getCreatedAt();
	}
}
