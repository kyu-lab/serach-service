package kyulab.searchservice.dto.res;

import kyulab.searchservice.document.UsersDocument;

public record UsersResDto(String id, String name) {
	public static UsersResDto from(UsersDocument usersDocument) {
		return new UsersResDto(
				usersDocument.getId(),
				usersDocument.getName()
		);
	}
}
