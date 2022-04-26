package demo3.demo3.controller;

import demo3.demo3.dto.GetNewsDto;
import demo3.demo3.dto.ResponseNews;
import demo3.demo3.entity.*;
import demo3.demo3.repository.KeywordRepository;
import demo3.demo3.repository.NewsKeywordRepository;
import demo3.demo3.repository.UserKeywordRepository;
import demo3.demo3.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class NewsController {

    private final UserRepository userRepository;
    private final UserKeywordRepository userKeywordRepository;
    private final KeywordRepository keywordRepository;
    private final NewsKeywordRepository newsKeywordRepository;

    public NewsController (
            UserRepository userRepository,
            UserKeywordRepository userKeywordRepository,
            KeywordRepository keywordRepository,
            NewsKeywordRepository newsKeywordRepository)
    {
        this.userRepository = userRepository;
        this.userKeywordRepository = userKeywordRepository;
        this.keywordRepository = keywordRepository;
        this.newsKeywordRepository = newsKeywordRepository;
    }

    @GetMapping("/news")
    public ResponseEntity<GetNewsDto> getNews(@RequestParam String userId) {
        GetNewsDto response = new GetNewsDto();
        List<ResponseNews> responseNewsList = new ArrayList<>();
        Optional<User> user = userRepository.findById(Long.parseLong(userId));
        List<UserKeyword> userKeywords = userKeywordRepository.findAllByUser(user.get());
        Long count = 0l;
        for (UserKeyword userKeyword : userKeywords) {
            Keyword keyword = userKeyword.getKeyword();
            List<NewsKeyword> newsKeywords = newsKeywordRepository.findAllByKeyword(keyword);
            for (NewsKeyword newsKeyword : newsKeywords) {
                ResponseNews responseNews = ResponseNews.builder()
                        .newsTitle(newsKeyword.getNews().getNewsTitle())
                        .newsReporter(newsKeyword.getNews().getNewsReporter())
                        .newsMedia(newsKeyword.getNews().getNewsMedia())
                        .newsUrl(newsKeyword.getNews().getNewsUrl())
                        .mediaUrl(newsKeyword.getNews().getMediaUrl())
                        .build();
                News news = newsKeyword.getNews();
                List<NewsKeyword> allByNews = newsKeywordRepository.findAllByNews(news);
                responseNews.setKeyword1(allByNews.get(0).getKeyword().getKeywordName());
                responseNews.setKeyword2(allByNews.get(1).getKeyword().getKeywordName());
                responseNews.setKeyword3(allByNews.get(2).getKeyword().getKeywordName());
                responseNewsList.add(responseNews);
                count++;
            }
        }
        response.setCount(count);
        response.setNewsList(responseNewsList);
        return ResponseEntity.ok(response);
    }
}
