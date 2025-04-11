package kyulab.searchservice.dto.res;

import java.util.List;

public record SearchResDto(
		List<UsersResDto> users,
		List<PostResDto> posts,
		List<GroupResDto> group) {
}
