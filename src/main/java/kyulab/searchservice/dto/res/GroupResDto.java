package kyulab.searchservice.dto.res;

import kyulab.searchservice.document.GroupDocument;

public record GroupResDto(String id, String name) {
	public static GroupResDto from(GroupDocument groupDocument) {
		return new GroupResDto(
				groupDocument.getId(),
				groupDocument.getName()
		);
	}
}
