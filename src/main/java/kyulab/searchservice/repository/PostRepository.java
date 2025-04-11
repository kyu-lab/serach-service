package kyulab.searchservice.repository;

import kyulab.searchservice.document.PostsDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends ElasticsearchRepository<PostsDocument, String> {

	List<PostsDocument> findBySubjectContaining(String query);

}
