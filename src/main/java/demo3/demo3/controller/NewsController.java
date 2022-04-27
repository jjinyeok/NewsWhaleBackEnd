package demo3.demo3.controller;

import demo3.demo3.dto.GetNewsDto;
import demo3.demo3.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;

    public NewsController (NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public ResponseEntity<GetNewsDto> getNews(@RequestParam String userId) {
        return ResponseEntity.ok(newsService.getNews(userId));
    }
}
