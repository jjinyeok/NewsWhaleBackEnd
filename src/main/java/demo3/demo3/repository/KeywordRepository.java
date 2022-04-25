package demo3.demo3.repository;

import demo3.demo3.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Long> {

    Keyword findByKeywordName(String keywordName);

}
