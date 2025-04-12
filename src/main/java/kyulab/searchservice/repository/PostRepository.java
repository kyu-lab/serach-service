package kyulab.searchservice.repository;

import kyulab.searchservice.document.PostsDocument;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PostRepository extends ReactiveElasticsearchRepository<PostsDocument, String> {

	Flux<PostsDocument> findBySubjectContaining(String query);

}
