package demo3.demo3.controller;

import demo3.demo3.dto.GetArticleDto;
import demo3.demo3.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public ResponseEntity<GetArticleDto> getArticle(@RequestParam String userId) {
        return ResponseEntity.ok(articleService.getArticle(userId));
    }
}
