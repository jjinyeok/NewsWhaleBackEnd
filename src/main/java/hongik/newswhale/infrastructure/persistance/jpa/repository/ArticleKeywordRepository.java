package hongik.newswhale.infrastructure.persistance.jpa.repository;

import hongik.newswhale.infrastructure.persistance.jpa.entity.ArticleKeywordEntity;
import hongik.newswhale.infrastructure.persistance.jpa.entity.KeywordEntity;
import hongik.newswhale.infrastructure.persistance.jpa.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleKeywordRepository extends JpaRepository<ArticleKeywordEntity, Long> {

    List<ArticleKeywordEntity> findAllByKeywordOrderByIdDesc(KeywordEntity keywordEntity);

    List<ArticleKeywordEntity> findAllByArticle(ArticleEntity articleEntity);
}
