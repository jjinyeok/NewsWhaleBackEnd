package hongik.newswhale.infrastructure.persistance.jpa.repository;

import hongik.newswhale.infrastructure.persistance.jpa.entity.KeywordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KeywordRepository extends JpaRepository<KeywordEntity, Long> {

    Optional<KeywordEntity> findByKeywordName(String keywordName);

    List<KeywordEntity> findTop10ByOrderByPoliticsCountDesc();
    List<KeywordEntity> findTop10ByOrderByEconomyCountDesc();
    List<KeywordEntity> findTop10ByOrderBySocietyCountDesc();
    List<KeywordEntity> findTop10ByOrderByCultureCountDesc();
    List<KeywordEntity> findTop10ByOrderByInternationalCountDesc();
    List<KeywordEntity> findTop10ByOrderByLocalCountDesc();
    List<KeywordEntity> findTop10ByOrderBySportsCountDesc();
    List<KeywordEntity> findTop10ByOrderByItScienceCountDesc();

    List<KeywordEntity> findTop5ByOrderByPoliticsCountDesc();
    List<KeywordEntity> findTop5ByOrderByEconomyCountDesc();
    List<KeywordEntity> findTop5ByOrderBySocietyCountDesc();
    List<KeywordEntity> findTop5ByOrderByCultureCountDesc();
    List<KeywordEntity> findTop5ByOrderByInternationalCountDesc();
    List<KeywordEntity> findTop5ByOrderByLocalCountDesc();
    List<KeywordEntity> findTop5ByOrderBySportsCountDesc();
    List<KeywordEntity> findTop5ByOrderByItScienceCountDesc();

    List<KeywordEntity> findTop3ByOrderByPoliticsCountDesc();
    List<KeywordEntity> findTop3ByOrderByEconomyCountDesc();
    List<KeywordEntity> findTop3ByOrderBySocietyCountDesc();
    List<KeywordEntity> findTop3ByOrderByCultureCountDesc();
    List<KeywordEntity> findTop3ByOrderByInternationalCountDesc();
    List<KeywordEntity> findTop3ByOrderByLocalCountDesc();
    List<KeywordEntity> findTop3ByOrderBySportsCountDesc();
    List<KeywordEntity> findTop3ByOrderByItScienceCountDesc();
}
