package kyulab.searchservice.controller;

import kyulab.searchservice.dto.res.SearchResDto;
import kyulab.searchservice.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {

	private final SearchService searchService;

	@GetMapping
	public Mono<SearchResDto> search(@RequestParam(name = "q") String query) {
		return searchService.search(query);
	}

}
