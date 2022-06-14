package demo3.demo3.repository;

import demo3.demo3.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Long> {

    Optional<Keyword> findByKeywordName(String keywordName);

    List<Keyword> findTop10ByOrderByPoliticsCountDesc();
    List<Keyword> findTop10ByOrderByEconomyCountDesc();
    List<Keyword> findTop10ByOrderBySocietyCountDesc();
    List<Keyword> findTop10ByOrderByCultureCountDesc();
    List<Keyword> findTop10ByOrderByInternationalCountDesc();
    List<Keyword> findTop10ByOrderByLocalCountDesc();
    List<Keyword> findTop10ByOrderBySportsCountDesc();
    List<Keyword> findTop10ByOrderByItScienceCountDesc();

    List<Keyword> findTop5ByOrderByPoliticsCountDesc();
    List<Keyword> findTop5ByOrderByEconomyCountDesc();
    List<Keyword> findTop5ByOrderBySocietyCountDesc();
    List<Keyword> findTop5ByOrderByCultureCountDesc();
    List<Keyword> findTop5ByOrderByInternationalCountDesc();
    List<Keyword> findTop5ByOrderByLocalCountDesc();
    List<Keyword> findTop5ByOrderBySportsCountDesc();
    List<Keyword> findTop5ByOrderByItScienceCountDesc();

    List<Keyword> findTop3ByOrderByPoliticsCountDesc();
    List<Keyword> findTop3ByOrderByEconomyCountDesc();
    List<Keyword> findTop3ByOrderBySocietyCountDesc();
    List<Keyword> findTop3ByOrderByCultureCountDesc();
    List<Keyword> findTop3ByOrderByInternationalCountDesc();
    List<Keyword> findTop3ByOrderByLocalCountDesc();
    List<Keyword> findTop3ByOrderBySportsCountDesc();
    List<Keyword> findTop3ByOrderByItScienceCountDesc();
}
