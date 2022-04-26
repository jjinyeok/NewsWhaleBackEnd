package demo3.demo3.repository;

import demo3.demo3.entity.Keyword;
import demo3.demo3.entity.News;
import demo3.demo3.entity.NewsKeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsKeywordRepository extends JpaRepository<NewsKeyword, Long> {

    List<NewsKeyword> findAllByKeyword(Keyword keyword);

    List<NewsKeyword> findAllByNews(News news);
}
