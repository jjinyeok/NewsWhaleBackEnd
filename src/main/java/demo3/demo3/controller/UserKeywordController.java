package demo3.demo3.controller;

import demo3.demo3.entity.User;
import demo3.demo3.entity.UserKeyword;
import demo3.demo3.repository.UserKeywordRepository;
import demo3.demo3.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserKeywordController {

    UserRepository userRepository;
    UserKeywordRepository userKeywordRepository;

    public UserKeywordController(UserRepository userRepository, UserKeywordRepository userKeywordRepository) {
        this.userRepository = userRepository;
        this.userKeywordRepository = userKeywordRepository;
    }

    @GetMapping("/keywords")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String getKeywords(@RequestParam String userId) {
        Optional<User> user = userRepository.findById(Long.parseLong(userId));
        if(user.isEmpty()) {
            return "사용자가 등록한 키워드가 없습니다.";
        } else {
            List<UserKeyword> userKeywords = userKeywordRepository.findAllByUser(user);
            String result = "";
            for (UserKeyword userKeyword : userKeywords) {
                result += userKeyword.getKeyword().getKeywordName();
            }
            return result;
        }
    }
}
