package kyulab.searchservice.repository;

import kyulab.searchservice.document.UsersDocument;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UsersRepository extends ReactiveElasticsearchRepository<UsersDocument, String> {

	Flux<UsersDocument> findByNameContaining(String query);

}
