package demo3.demo3.service;

import demo3.demo3.dto.GetNewsDto;
import demo3.demo3.dto.ResponseNews;
import demo3.demo3.entity.*;
import demo3.demo3.repository.KeywordRepository;
import demo3.demo3.repository.NewsKeywordRepository;
import demo3.demo3.repository.UserKeywordRepository;
import demo3.demo3.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NewsService {

    private final UserRepository userRepository;
    private final UserKeywordRepository userKeywordRepository;
    private final KeywordRepository keywordRepository;
    private final NewsKeywordRepository newsKeywordRepository;

    public NewsService(UserRepository userRepository,
                       UserKeywordRepository userKeywordRepository,
                       KeywordRepository keywordRepository,
                       NewsKeywordRepository newsKeywordRepository) {
        this.userRepository = userRepository;
        this.userKeywordRepository = userKeywordRepository;
        this.keywordRepository = keywordRepository;
        this.newsKeywordRepository = newsKeywordRepository;
    }

    public GetNewsDto getNews(String userId) {

        // 0. return 내부 값 생성
        Long count = 0l;
        List<ResponseNews> responseNewsList = new ArrayList<>();

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

            // 5. Keyword로부터 NewsKeyword 리스트 find
            List<NewsKeyword> newsKeywordsByKeyword = newsKeywordRepository.findAllByKeyword(keyword);

            // 6. NewsKeyword 리스트로부터 NewsKeyword를 하나씩 꺼내,
            //
            for (NewsKeyword newsKeyword : newsKeywordsByKeyword) {

                // 7. NewsKeyword로부터 News를 get
                News news = newsKeyword.getNews();

                Boolean duplicateChecker = false;
                for(ResponseNews duplicateResponseNews : responseNewsList) {
                    if(news.getNewsId() == duplicateResponseNews.getNewsId()) {
                        duplicateChecker = true;
                        break;
                    }
                }
                if(duplicateChecker == false) {
                    // 8. ResponseNews 객체 생성 (뉴스 제목, 뉴스 기자, 언론사, 뉴스 URL, 언론사 URL)
                    ResponseNews responseNews = ResponseNews.builder()
                            .newsId(news.getNewsId())
                            .newsTitle(news.getNewsTitle())
                            .newsReporter(news.getNewsReporter())
                            .newsMedia(news.getNewsMedia())
                            .newsUrl(news.getNewsUrl())
                            .mediaUrl(news.getMediaUrl())
                            .build();

                    // 9. News로부터 NewsKeyword 리스트 find
                    List<NewsKeyword> newsKeywordsByNews = newsKeywordRepository.findAllByNews(news);

                    // 10. ResponseNews 객체 완성 (키워드1, 키워드2, 키워드3)
                    responseNews.setKeyword1(newsKeywordsByNews.get(0).getKeyword().getKeywordName());
                    responseNews.setKeyword2(newsKeywordsByNews.get(1).getKeyword().getKeywordName());
                    responseNews.setKeyword3(newsKeywordsByNews.get(2).getKeyword().getKeywordName());

                    // 11. return 내부 값 계산
                    responseNewsList.add(responseNews);
                    count++;
                }
            }
        }

        return GetNewsDto.builder()
                .count(count)
                .newsList(responseNewsList)
                .build();
    }

}
