package demo3.demo3.service;

import demo3.demo3.dto.DuplicateCheckDto;
import demo3.demo3.dto.SignUpDto;
import demo3.demo3.entity.Authority;
import demo3.demo3.entity.User;
import demo3.demo3.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // passwordEncoder 구조체는 SecurityConfig에 존재

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 회원가입 서비스
    @Transactional
    public User signUp(SignUpDto signUpDto) {

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        List<String> whales = new ArrayList<>(10);
        whales.add("흰수염고래");
        whales.add("범고래");
        whales.add("밍크고래");
        whales.add("향유고래");
        whales.add("돌고래");
        whales.add("혹등고래");
        whales.add("참고래");
        whales.add("벨루가");
        whales.add("귀신고래");
        whales.add("상괭이");

        String nickname;
        String whale;

        Random random = new Random();
        whale = whales.get(random.nextInt(10));

        if(signUpDto.getNickname() == "") {
            nickname = whale;
        } else {
            nickname = signUpDto.getNickname();
        }

        User user = User.builder()
                .username(signUpDto.getUsername())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .nickname(nickname)
                .email(signUpDto.getEmail())
                .activated(true)
                .authorities(Collections.singleton(authority))
                .build();

        return userRepository.save(user);
    }

    // 아이디 중복 체크 서비스
    public Boolean duplicateCheck(DuplicateCheckDto duplicateCheckDto) {
        System.out.println("Controller 통과");
        Optional<User> registeredUser = userRepository.findByUsername(duplicateCheckDto.getUsername());
        if(registeredUser.isEmpty()) {
            return Boolean.FALSE; // 아이디 중복이 없는 경우
        }
        else {
            return Boolean.TRUE; // 아이디 중복이 있는 경우
        }
    }
}
