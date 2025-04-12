package kyulab.searchservice.repository;

import kyulab.searchservice.document.GroupDocument;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface GroupRepository extends ReactiveElasticsearchRepository<GroupDocument, String> {

	Flux<GroupDocument> findByNameContaining(String query);

}
