/*
package hongik.newswhale.application.service;

import hongik.newswhale.dto.GetArticleDto;
import hongik.newswhale.dto.RecommendArticle;
import hongik.newswhale.dto.ResponseArticle;
import hongik.newswhale.entity.*;
import hongik.newswhale.infrastructure.persistance.jpa.entity.*;
import hongik.newswhale.infrastructure.persistance.jpa.repository.KeywordRepository;
import hongik.newswhale.infrastructure.persistance.jpa.repository.ArticleKeywordRepository;
import hongik.newswhale.infrastructure.persistance.jpa.repository.UserKeywordRepository;
import hongik.newswhale.infrastructure.persistance.jpa.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArticleService {

    private final UserRepository userRepository;
    private final UserKeywordRepository userKeywordRepository;
    private final KeywordRepository keywordRepository;
    private final ArticleKeywordRepository articleKeywordRepository;

    public ArticleService(UserRepository userRepository,
                          UserKeywordRepository userKeywordRepository,
                          KeywordRepository keywordRepository,
                          ArticleKeywordRepository articleKeywordRepository) {
        this.userRepository = userRepository;
        this.userKeywordRepository = userKeywordRepository;
        this.keywordRepository = keywordRepository;
        this.articleKeywordRepository = articleKeywordRepository;
    }

    public GetArticleDto getArticle(String userId) {

        // 0. return 내부 값 생성
        Long count = 0l;
        List<ResponseArticle> responseArticleList = new ArrayList<>();

        // 1. userId로부터 User find
        Optional<UserEntity> optionalUser = userRepository.findById(Long.parseLong(userId));
        UserEntity userEntity = new UserEntity();
        if(optionalUser.isEmpty()) {
            throw new RuntimeException("사용자가 없습니다. 뉴스를 조회할 수 없습니다.");
        } else {
            userEntity = optionalUser.get();
        }

        // 2. User로부터 UserKeyword 리스트 find
        List<UserKeywordEntity> userKeywordEntities = userKeywordRepository.findAllByUser(userEntity);

        // 3. UserKeyword 리스트에서 UserKeyword를 하나씩 꺼내,
        //
        for (UserKeywordEntity userKeywordEntity : userKeywordEntities) {

            // 4. UserKeyword로부터 Keyword get
            KeywordEntity keywordEntity = userKeywordEntity.getKeywordEntity();

            // 5. Keyword로부터 ArticleKeyword 리스트 find
            List<ArticleKeywordEntity> articleKeywordsByKeywordEntity = articleKeywordRepository.findAllByKeywordOrderByIdDesc(keywordEntity);

            // 6. ArticleKeyword 리스트로부터 ArticleKeyword를 하나씩 꺼내,
            for (ArticleKeywordEntity articleKeywordEntity : articleKeywordsByKeywordEntity) {

                // 7. ArticleKeyword로부터 Article를 get
                ArticleEntity articleEntity = articleKeywordEntity.getArticleEntity();

                Boolean duplicateChecker = false;
                for(ResponseArticle duplicateResponseArticle : responseArticleList) {
                    if(articleEntity.getArticleId() == duplicateResponseArticle.getArticleId()) {
                        duplicateChecker = true;
                        break;
                    }
                }
                if(duplicateChecker == false) {
                    // 8. ResponseArticle 객체 생성 (뉴스 제목, 뉴스 기자, 언론사, 뉴스 URL, 언론사 URL)
                    ResponseArticle responseArticle = ResponseArticle.builder()
                            .articleId(articleEntity.getArticleId())
                            .articleTitle(articleEntity.getArticleTitle())
                            .articleReporter(articleEntity.getArticleReporter())
                            .articleUrl(articleEntity.getArticleUrl())
                            .articleMediaName(articleEntity.getArticleMediaName())
                            .articleMediaUrl(articleEntity.getArticleMediaUrl())
                            .articleMediaImageSrc(articleEntity.getArticleMediaImageSrc())
                            .articleLastModifiedTime(articleEntity.getArticleLastModifiedDate())
                            .build();

                    // 9. Article로부터 ArticleKeyword 리스트 find
                    List<ArticleKeywordEntity> articleKeywordsByArticleEntity = articleKeywordRepository.findAllByArticle(articleEntity);

                    // 10. ResponseArticle 객체 완성 (키워드1, 키워드2, 키워드3)
                    responseArticle.setKeyword1(articleKeywordsByArticleEntity.get(0).getKeywordEntity().getKeywordName());
                    responseArticle.setKeyword2(articleKeywordsByArticleEntity.get(1).getKeywordEntity().getKeywordName());
                    responseArticle.setKeyword3(articleKeywordsByArticleEntity.get(2).getKeywordEntity().getKeywordName());

                    // 11. return 내부 값 계산
                    responseArticleList.add(responseArticle);
                    responseArticleList.sort(new ResponseArticleComparator());
                    count++;
                    if (count == 301) {
                        break;
                    }
                }
                if (count == 301) {
                    break;
                }
            }
        }

        TreeMap<Long, String> userTendencyDictionary = new TreeMap<Long, String>();
        userTendencyDictionary.put(userEntity.getPoliticsScore() * (-1), "PoliticsScore");
        userTendencyDictionary.put(userEntity.getEconomyScore() * (-1), "EconomyScore");
        userTendencyDictionary.put(userEntity.getSocietyScore() * (-1), "SocietyScore");
        userTendencyDictionary.put(userEntity.getCultureScore() * (-1), "CultureScore");
        userTendencyDictionary.put(userEntity.getInternationalScore() * (-1), "InternationalScore");
        userTendencyDictionary.put(userEntity.getLocalScore() * (-1), "LocalScore");
        userTendencyDictionary.put(userEntity.getSportsScore() * (-1), "SportsScore");
        userTendencyDictionary.put(userEntity.getItScienceScore() * (-1), "ItScienceScore");

        List<KeywordEntity> userTendencyKeywordEntities = new ArrayList<>();
        if(userTendencyDictionary.firstEntry().getValue() == "PoliticsScore") {
            userTendencyKeywordEntities = keywordRepository.findTop10ByOrderByPoliticsCountDesc();
        }
        else if(userTendencyDictionary.firstEntry().getValue() == "EconomyScore") {
            userTendencyKeywordEntities = keywordRepository.findTop10ByOrderByEconomyCountDesc();
        }
        else if(userTendencyDictionary.firstEntry().getValue() == "SocietyScore") {
            userTendencyKeywordEntities = keywordRepository.findTop10ByOrderBySocietyCountDesc();
        }
        else if(userTendencyDictionary.firstEntry().getValue() == "CultureScore") {
            userTendencyKeywordEntities = keywordRepository.findTop10ByOrderByCultureCountDesc();
        }
        else if(userTendencyDictionary.firstEntry().getValue() == "InternationalScore") {
            userTendencyKeywordEntities = keywordRepository.findTop10ByOrderByInternationalCountDesc();
        }
        else if(userTendencyDictionary.firstEntry().getValue() == "LocalScore") {
            userTendencyKeywordEntities = keywordRepository.findTop10ByOrderByLocalCountDesc();
        }
        else if(userTendencyDictionary.firstEntry().getValue() == "SportsScore") {
            userTendencyKeywordEntities = keywordRepository.findTop10ByOrderBySportsCountDesc();
        }
        else if(userTendencyDictionary.firstEntry().getValue() == "ItScienceScore") {
            userTendencyKeywordEntities = keywordRepository.findTop10ByOrderByItScienceCountDesc();
        }

        int recommendNewsCount = 0;
        List<RecommendArticle> recommendArticleList = new ArrayList<>();
        for (KeywordEntity userTendencyKeywordEntity : userTendencyKeywordEntities) {
            List<ArticleKeywordEntity> articleKeywordEntities = articleKeywordRepository.findAllByKeywordOrderByIdDesc(userTendencyKeywordEntity);
            for (ArticleKeywordEntity articleKeywordEntity : articleKeywordEntities) {
                RecommendArticle recommendArticle = RecommendArticle.builder()
                        .articleTitle(articleKeywordEntity.getArticleEntity().getArticleTitle())
                        .articleUrl(articleKeywordEntity.getArticleEntity().getArticleUrl())
                        .build();
                if (!recommendArticleList.contains(recommendArticle)) {
                    recommendArticleList.add(recommendArticle);
                    recommendNewsCount += 1;
                }
                if(recommendNewsCount == 5){
                    break;
                }
            }
            if(recommendNewsCount == 5){
                break;
            }
        }

        return GetArticleDto.builder()
                .count(count)
                .articleList(responseArticleList)
                .recommendArticleList(recommendArticleList)
                .build();
    }

}

*/
