package demo3.demo3.controller;

import demo3.demo3.dto.GetArticleDto;
import demo3.demo3.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping("/keyword")
    public ResponseEntity<GetArticleDto> getKeywordArticle(@RequestParam String keyword) {
        return ResponseEntity.ok(articleService.getKeywordArticle(keyword));
    }

}
