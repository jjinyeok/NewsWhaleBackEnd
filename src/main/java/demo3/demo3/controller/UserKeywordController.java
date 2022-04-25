package demo3.demo3.controller;

import demo3.demo3.dto.GetKeywordsDto;
import demo3.demo3.dto.PostKeywordsDto;
import demo3.demo3.entity.Keyword;
import demo3.demo3.entity.User;
import demo3.demo3.entity.UserKeyword;
import demo3.demo3.repository.KeywordRepository;
import demo3.demo3.repository.UserKeywordRepository;
import demo3.demo3.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserKeywordController {

    UserRepository userRepository;
    UserKeywordRepository userKeywordRepository;
    KeywordRepository keywordRepository;

    public UserKeywordController(UserRepository userRepository, UserKeywordRepository userKeywordRepository, KeywordRepository keywordRepository) {
        this.userRepository = userRepository;
        this.userKeywordRepository = userKeywordRepository;
        this.keywordRepository = keywordRepository;
    }

    @GetMapping("/keywords")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<GetKeywordsDto> getKeywords(@RequestParam String userId) {
        Optional<User> user = userRepository.findById(Long.parseLong(userId));
        List<UserKeyword> userKeywords = userKeywordRepository.findAllByUser(user);
        List<String> userKeywordNames = new ArrayList<>();
        for (UserKeyword userKeyword : userKeywords) {
            userKeywordNames.add(userKeyword.getKeyword().getKeywordName());
        }
        GetKeywordsDto getKeywordsDto = GetKeywordsDto.builder()
                .count(Long.valueOf(userKeywords.size()))
                .keywordName(userKeywordNames)
                .build();
        return ResponseEntity.ok(getKeywordsDto);
    }

    @PostMapping("/keywords")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Transactional
    public ResponseEntity<PostKeywordsDto> postKeywords(@RequestBody PostKeywordsDto postKeywordsDto) {
        UserKeyword userKeyword = new UserKeyword();
        User user = userRepository.getById(postKeywordsDto.getUserId());
        Keyword keyword = new Keyword();
        keyword.setKeywordName(postKeywordsDto.getKeywordName());
        userKeyword.setUser(user);
        userKeyword.setKeyword(keyword);
        keywordRepository.save(keyword);
        userKeywordRepository.save(userKeyword);
        return ResponseEntity.ok(postKeywordsDto);
    }

    @DeleteMapping("/keywords")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Transactional
    public void deleteKeywords(@RequestParam("userId") String userId, @RequestParam("keywordName") String keywordName) {
        Optional<User> user = userRepository.findById(Long.parseLong(userId));
        Keyword keyword = keywordRepository.findByKeywordName(keywordName);
        UserKeyword userKeyword = userKeywordRepository.findByUserAndKeyword(user.get(), keyword);
        userKeywordRepository.delete(userKeyword);
    }
}
