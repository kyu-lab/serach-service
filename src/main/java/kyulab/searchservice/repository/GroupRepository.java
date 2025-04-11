package kyulab.searchservice.repository;

import kyulab.searchservice.document.GroupDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends ElasticsearchRepository<GroupDocument, String> {

	List<GroupDocument> findByNameContaining(String query);

}
