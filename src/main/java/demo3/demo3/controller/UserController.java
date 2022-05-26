package demo3.demo3.controller;

import demo3.demo3.dto.GetUserRecommendKeywordDto;
import demo3.demo3.dto.GetUserTendencyDto;
import demo3.demo3.repository.UserRepository;
import demo3.demo3.service.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    public UserController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/tendency")
    public ResponseEntity<GetUserTendencyDto> getUserTendency(@RequestParam String userId) {
        return ResponseEntity.ok(userService.getUserTendency(userId));
    }

    @GetMapping("/recommendKeyword")
    public ResponseEntity<GetUserRecommendKeywordDto> getUserRecommendKeyword(@RequestParam String userId) {
        return ResponseEntity.ok(userService.getUserRecommendKeyword(userId));
    }

}
