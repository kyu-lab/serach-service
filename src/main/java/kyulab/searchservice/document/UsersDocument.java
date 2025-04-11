package kyulab.searchservice.document;

import kyulab.searchservice.dto.kafka.UsersDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Document(indexName = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UsersDocument {
	@Id
	private String id;

	@Field(type = FieldType.Text)
	private String name;

	public UsersDocument(UsersDto usersDto) {
		this.id = String.valueOf(usersDto.getId());
		this.name = usersDto.getName();
	}
}
