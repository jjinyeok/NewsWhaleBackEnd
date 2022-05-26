package demo3.demo3.service;

import demo3.demo3.dto.GetArticleDto;
import demo3.demo3.dto.ResponseArticle;
import demo3.demo3.entity.*;
import demo3.demo3.repository.KeywordRepository;
import demo3.demo3.repository.ArticleKeywordRepository;
import demo3.demo3.repository.UserKeywordRepository;
import demo3.demo3.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
            List<ArticleKeyword> articleKeywordsByKeyword = articleKeywordRepository.findAllByKeyword(keyword);

            // 6. ArticleKeyword 리스트로부터 ArticleKeyword를 하나씩 꺼내,
            //
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
                }
            }
        }

        return GetArticleDto.builder()
                .count(count)
                .articleList(responseArticleList)
                .build();
    }

}

