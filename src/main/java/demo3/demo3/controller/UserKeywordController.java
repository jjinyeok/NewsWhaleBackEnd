package demo3.demo3.controller;

import demo3.demo3.dto.DeleteKeywordDto;
import demo3.demo3.dto.GetKeywordsDto;
import demo3.demo3.dto.PostKeywordDto;
import demo3.demo3.service.UserKeywordService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/keywords")
public class UserKeywordController {

    private final UserKeywordService userKeywordService;

    public UserKeywordController(
            UserKeywordService userKeywordService) {
        this.userKeywordService = userKeywordService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<GetKeywordsDto> getKeywords(
            @RequestParam String userId) {
        return ResponseEntity.ok(userKeywordService.getKeywords(userId));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Transactional
    public ResponseEntity<PostKeywordDto> postKeywords(
            @RequestBody PostKeywordDto postKeywordsDto) {
        return ResponseEntity.ok(userKeywordService.postKeyword(postKeywordsDto));
    }

    @DeleteMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Transactional
    public ResponseEntity<DeleteKeywordDto> deleteKeywords(
            @RequestParam("userId") String userId,
            @RequestParam("keywordName") String keywordName) {
        return ResponseEntity.ok(userKeywordService.deleteKeyword(userId, keywordName));
    }
}
