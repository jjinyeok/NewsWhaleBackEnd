package demo3.demo3.repository;

import demo3.demo3.entity.ArticleKeyword;
import demo3.demo3.entity.Keyword;
import demo3.demo3.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleKeywordRepository extends JpaRepository<ArticleKeyword, Long> {

    List<ArticleKeyword> findAllByKeywordOrderByIdDesc(Keyword keyword);

    List<ArticleKeyword> findAllByArticle(Article article);
}
