package kyulab.searchservice.repository;

import kyulab.searchservice.document.UsersDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends ElasticsearchRepository<UsersDocument, String> {

	List<UsersDocument> findByNameContaining(String query);

}
