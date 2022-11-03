package demo3.demo3.service;

import demo3.demo3.dto.GetArticleDto;
import demo3.demo3.dto.RecommendArticle;
import demo3.demo3.dto.ResponseArticle;
import demo3.demo3.entity.*;
import demo3.demo3.repository.KeywordRepository;
import demo3.demo3.repository.ArticleKeywordRepository;
import demo3.demo3.repository.UserKeywordRepository;
import demo3.demo3.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.security.Key;
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
        Optional<User> optionalUser = userRepository.findById(Long.parseLong(userId));
        User user = new User();
        if(optionalUser.isEmpty()) {
            throw new RuntimeException("사용자가 없습니다. 뉴스를 조회할 수 없습니다.");
        } else {
            user = optionalUser.get();
        }

        // 2. User로부터 UserKeyword 리스트 find
        List<UserKeyword> userKeywords = userKeywordRepository.findAllByUser(user);

        // 3. UserKeyword 리스트에서 UserKeyword를 하나씩 꺼내,
        //
        for (UserKeyword userKeyword : userKeywords) {

            // 4. UserKeyword로부터 Keyword get
            Keyword keyword = userKeyword.getKeyword();

            // 5. Keyword로부터 ArticleKeyword 리스트 find
            List<ArticleKeyword> articleKeywordsByKeyword = articleKeywordRepository.findAllByKeywordOrderByIdDesc(keyword);

            // 6. ArticleKeyword 리스트로부터 ArticleKeyword를 하나씩 꺼내,
            for (ArticleKeyword articleKeyword : articleKeywordsByKeyword) {

                // 7. ArticleKeyword로부터 Article를 get
                Article article = articleKeyword.getArticle();

                Boolean duplicateChecker = false;
                for(ResponseArticle duplicateResponseArticle : responseArticleList) {
                    if(article.getArticleId() == duplicateResponseArticle.getArticleId()) {
                        duplicateChecker = true;
                        break;
                    }
                }
                if(duplicateChecker == false) {
                    // 8. ResponseArticle 객체 생성 (뉴스 제목, 뉴스 기자, 언론사, 뉴스 URL, 언론사 URL)
                    ResponseArticle responseArticle = ResponseArticle.builder()
                            .articleId(article.getArticleId())
                            .articleTitle(article.getArticleTitle())
                            .articleReporter(article.getArticleReporter())
                            .articleUrl(article.getArticleUrl())
                            .articleMediaName(article.getArticleMediaName())
                            .articleMediaUrl(article.getArticleMediaUrl())
                            .articleMediaImageSrc(article.getArticleMediaImageSrc())
                            .articleLastModifiedTime(article.getArticleLastModifiedDate())
                            .build();

                    // 9. Article로부터 ArticleKeyword 리스트 find
                    List<ArticleKeyword> articleKeywordsByArticle = articleKeywordRepository.findAllByArticle(article);

                    // 10. ResponseArticle 객체 완성 (키워드1, 키워드2, 키워드3)
                    responseArticle.setKeyword1(articleKeywordsByArticle.get(0).getKeyword().getKeywordName());
                    responseArticle.setKeyword2(articleKeywordsByArticle.get(1).getKeyword().getKeywordName());
                    responseArticle.setKeyword3(articleKeywordsByArticle.get(2).getKeyword().getKeywordName());

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
        userTendencyDictionary.put(user.getPoliticsScore() * (-1), "PoliticsScore");
        userTendencyDictionary.put(user.getEconomyScore() * (-1), "EconomyScore");
        userTendencyDictionary.put(user.getSocietyScore() * (-1), "SocietyScore");
        userTendencyDictionary.put(user.getCultureScore() * (-1), "CultureScore");
        userTendencyDictionary.put(user.getInternationalScore() * (-1), "InternationalScore");
        userTendencyDictionary.put(user.getLocalScore() * (-1), "LocalScore");
        userTendencyDictionary.put(user.getSportsScore() * (-1), "SportsScore");
        userTendencyDictionary.put(user.getItScienceScore() * (-1), "ItScienceScore");

        List<Keyword> userTendencyKeywords = new ArrayList<>();
        if(userTendencyDictionary.firstEntry().getValue() == "PoliticsScore") {
            userTendencyKeywords = keywordRepository.findTop10ByOrderByPoliticsCountDesc();
        }
        else if(userTendencyDictionary.firstEntry().getValue() == "EconomyScore") {
            userTendencyKeywords = keywordRepository.findTop10ByOrderByEconomyCountDesc();
        }
        else if(userTendencyDictionary.firstEntry().getValue() == "SocietyScore") {
            userTendencyKeywords = keywordRepository.findTop10ByOrderBySocietyCountDesc();
        }
        else if(userTendencyDictionary.firstEntry().getValue() == "CultureScore") {
            userTendencyKeywords = keywordRepository.findTop10ByOrderByCultureCountDesc();
        }
        else if(userTendencyDictionary.firstEntry().getValue() == "InternationalScore") {
            userTendencyKeywords = keywordRepository.findTop10ByOrderByInternationalCountDesc();
        }
        else if(userTendencyDictionary.firstEntry().getValue() == "LocalScore") {
            userTendencyKeywords = keywordRepository.findTop10ByOrderByLocalCountDesc();
        }
        else if(userTendencyDictionary.firstEntry().getValue() == "SportsScore") {
            userTendencyKeywords = keywordRepository.findTop10ByOrderBySportsCountDesc();
        }
        else if(userTendencyDictionary.firstEntry().getValue() == "ItScienceScore") {
            userTendencyKeywords = keywordRepository.findTop10ByOrderByItScienceCountDesc();
        }

        int recommendNewsCount = 0;
        List<RecommendArticle> recommendArticleList = new ArrayList<>();
        for (Keyword userTendencyKeyword : userTendencyKeywords) {
            List<ArticleKeyword> articleKeywords = articleKeywordRepository.findAllByKeywordOrderByIdDesc(userTendencyKeyword);
            for (ArticleKeyword articleKeyword : articleKeywords) {
                RecommendArticle recommendArticle = RecommendArticle.builder()
                        .articleTitle(articleKeyword.getArticle().getArticleTitle())
                        .articleUrl(articleKeyword.getArticle().getArticleUrl())
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

    public GetArticleDto getKeywordArticle(String keywordName) {

        // 0. return 내부 값 생성
        Long count = 0l;
        List<ResponseArticle> responseArticleList = new ArrayList<>();

        // 1. keyword(이름)로부터 Keyword find
        Optional<Keyword> optionalKeyword = keywordRepository.findByKeywordName(keywordName);
        Keyword keyword = new Keyword();
        if(optionalKeyword.isEmpty()) {
            // throw new RuntimeException("키워드가 없습니다. 키워드 기반 뉴스를 조회할 수 없습니다.");
            List<ArticleKeyword> articleKeywordsByKeyword = new ArrayList<>();
        } else {
            keyword = optionalKeyword.get();

            // 2. keyword로부터 ArticleKeyword 리스트 find
            List<ArticleKeyword> articleKeywordsByKeyword = articleKeywordRepository.findAllByKeywordOrderByIdDesc(keyword);

            // 3. articleKeywordsByKeyword 리스트로부터 ArticleKeyword를 하나씩 꺼내,
            for (ArticleKeyword articleKeyword : articleKeywordsByKeyword) {

                // 4. ArticleKeyword로부터 Article를 get
                Article article = articleKeyword.getArticle();

                Boolean duplicateChecker = false;
                for(ResponseArticle duplicateResponseArticle : responseArticleList) {
                    if(article.getArticleId() == duplicateResponseArticle.getArticleId()) {
                        duplicateChecker = true;
                        break;
                    }
                }
                if(duplicateChecker == false) {

                    // 5. ResponseArticle 객체 생성 (뉴스 제목, 뉴스 기자, 언론사, 뉴스 URL, 언론사 URL)
                    ResponseArticle responseArticle = ResponseArticle.builder()
                            .articleId(article.getArticleId())
                            .articleTitle(article.getArticleTitle())
                            .articleReporter(article.getArticleReporter())
                            .articleUrl(article.getArticleUrl())
                            .articleMediaName(article.getArticleMediaName())
                            .articleMediaUrl(article.getArticleMediaUrl())
                            .articleMediaImageSrc(article.getArticleMediaImageSrc())
                            .articleLastModifiedTime(article.getArticleLastModifiedDate())
                            .build();

                    // 6. Article로부터 ArticleKeyword 리스트 find
                    List<ArticleKeyword> articleKeywordsByArticle = articleKeywordRepository.findAllByArticle(article);

                    // 7. ResponseArticle 객체 완성 (키워드1, 키워드2, 키워드3)
                    responseArticle.setKeyword1(articleKeywordsByArticle.get(0).getKeyword().getKeywordName());
                    responseArticle.setKeyword2(articleKeywordsByArticle.get(1).getKeyword().getKeywordName());
                    responseArticle.setKeyword3(articleKeywordsByArticle.get(2).getKeyword().getKeywordName());

                    // 8. return 내부 값 계산
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

        return GetArticleDto.builder()
                .count(count)
                .articleList(responseArticleList)
                .build();
    }

}

