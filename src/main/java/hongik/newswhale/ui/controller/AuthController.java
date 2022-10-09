/*
package hongik.newswhale.ui.controller;

import hongik.newswhale.dto.DuplicateCheckDto;
import hongik.newswhale.dto.SignInDto;
import hongik.newswhale.dto.SignUpDto;
import hongik.newswhale.dto.UserDto;
import hongik.newswhale.infrastructure.persistance.jpa.entity.UserEntity;
import hongik.newswhale.security.jwt.JwtFilter;
import hongik.newswhale.security.jwt.TokenProvider;
import hongik.newswhale.infrastructure.persistance.jpa.repository.UserRepository;
import hongik.newswhale.application.service.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AuthService authService;

    private final UserRepository userRepository;

    public AuthController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, AuthService authService, UserRepository userRepository) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.authService = authService;
        this.userRepository = userRepository;
    }

    // Security 연습 예제 basic: 401 Unauthorized
    @GetMapping("/hello")
    public ResponseEntity<String> aaa() {
        return ResponseEntity.ok("hello");
    }

    // 회원가입 컨트롤러
    @PostMapping("/signup")
    public ResponseEntity<UserEntity> signUp(@Valid @RequestBody SignUpDto signUpDto) {
        return ResponseEntity.ok(authService.signUp(signUpDto));
    }

    // 로그인 컨트롤러
    @PostMapping("/signin")
    public ResponseEntity<UserDto> signIn(@Valid @RequestBody SignInDto signInDto) {

        Optional<UserEntity> user = userRepository.findByUsername(signInDto.getUsername());
        Long userId = user.get().getUserId();

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(signInDto.getUsername(), signInDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 생성한 Authentication 객체를 SecurityContext에 저장
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Authentication(현재 접근하는 주체의 권한을 담는 인터페이스)를 통해 JWT 생성
        String jwt = tokenProvider.createToken(authentication);
        System.out.println("jwt = " + jwt);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(
                UserDto.builder()
                        .userId(userId)
                        .token(jwt)
                        .build()
                , httpHeaders, HttpStatus.OK);
    }

    // 아이디 중복 확인 컨트롤러
    @PostMapping("/duplicatecheck")
    public ResponseEntity<Boolean> duplicateCheck(@Valid @RequestBody DuplicateCheckDto duplicateCheckDto) {
        System.out.println("Controller 통과");
        return ResponseEntity.ok(authService.duplicateCheck(duplicateCheckDto));
    }
}
*/
