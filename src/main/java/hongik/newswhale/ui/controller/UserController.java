/*
package hongik.newswhale.ui.controller;

import hongik.newswhale.dto.GetUserRecommendKeywordDto;
import hongik.newswhale.dto.GetUserTendencyDto;
import hongik.newswhale.application.service.UserService;
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
*/
