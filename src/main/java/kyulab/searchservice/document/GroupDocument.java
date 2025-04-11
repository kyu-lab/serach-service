package kyulab.searchservice.document;

import kyulab.searchservice.dto.kafka.GroupDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Document(indexName = "group")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GroupDocument {
	@Id
	private String id;

	@Field(type = FieldType.Text)
	private String name;

	public GroupDocument(GroupDto groupDto) {
		this.id = String.valueOf(groupDto.getId());
		this.name = groupDto.getName();
	}
}
