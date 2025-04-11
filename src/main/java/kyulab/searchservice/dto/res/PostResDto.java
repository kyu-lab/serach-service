package kyulab.searchservice.dto.res;

import kyulab.searchservice.document.PostsDocument;

public record PostResDto(String id, String subject) {
	public static PostResDto from(PostsDocument postsDocument) {
		return new PostResDto(
				postsDocument.getId(),
				postsDocument.getSubject()
		);
	}
}
