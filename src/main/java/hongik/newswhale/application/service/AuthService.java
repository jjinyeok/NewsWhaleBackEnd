/*
package hongik.newswhale.application.service;

import hongik.newswhale.dto.DuplicateCheckDto;
import hongik.newswhale.dto.SignUpDto;
import hongik.newswhale.infrastructure.persistance.jpa.entity.AuthorityEntity;
import hongik.newswhale.infrastructure.persistance.jpa.entity.UserEntity;
import hongik.newswhale.infrastructure.persistance.jpa.repository.UserRepository;
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
    public UserEntity signUp(SignUpDto signUpDto) {

        AuthorityEntity authorityEntity = AuthorityEntity.builder()
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

        UserEntity userEntity = UserEntity.builder()
                .username(signUpDto.getUsername())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .nickname(nickname)
                .email(signUpDto.getEmail())
                .activated(true)
                .authorities(Collections.singleton(authorityEntity))
                .politicsScore(0L)
                .economyScore(0L)
                .societyScore(0L)
                .cultureScore(0L)
                .internationalScore(0L)
                .localScore(0L)
                .sportsScore(0L)
                .itScienceScore(0L)
                .build();

        return userRepository.save(userEntity);
    }

    // 아이디 중복 체크 서비스
    public Boolean duplicateCheck(DuplicateCheckDto duplicateCheckDto) {
        System.out.println("Controller 통과");
        Optional<UserEntity> registeredUser = userRepository.findByUsername(duplicateCheckDto.getUsername());
        if(registeredUser.isEmpty()) {
            return Boolean.FALSE; // 아이디 중복이 없는 경우
        }
        else {
            return Boolean.TRUE; // 아이디 중복이 있는 경우
        }
    }
}
*/
