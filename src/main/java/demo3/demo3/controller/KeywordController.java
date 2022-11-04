package demo3.demo3.controller;

import demo3.demo3.dto.MostKeywordsDto;
import demo3.demo3.service.KeywordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/keyword")
public class KeywordController {

    private final KeywordService keywordService;
    public KeywordController (KeywordService keywordService) {
        this.keywordService = keywordService;
    }

    @GetMapping("/most")
    public ResponseEntity<MostKeywordsDto> getMostKeywords() {
        return ResponseEntity.ok(keywordService.getMostKeywords());
    }
}
