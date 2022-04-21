package demo3.demo3.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class TokenProvider implements InitializingBean {

    private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);
    private static final String AUTHORITIES_KEY = "auth";

    private final String secret; // bmV3cy13aGFsZS1iYWNrZW5kLXdpdGgtc3ByaW5nYm9vdC1mb3ItbG9naW4tYXV0aG9yaXphdGlvbi1hbmQtYXV0aGVudGljYXRpb24tand0LXNlY3VyaXR5LWtleQ==
    private final long tokenValidityInMilliseconds; // 86400 * 1000

    private Key key;

    public TokenProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds
    ) {
        this.secret = secret;
        this.tokenValidityInMilliseconds = tokenValidityInSeconds * 1000;
    }

    // 주입받은 secret 값을 BASE64 decode 한 다음 key 변수에 할당하기 위함
    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        //System.out.println("keyBytes = " + keyBytes);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        //System.out.println("this.key = " + this.key);
    }

    // Authentication Interface (현재 접근하는 주체의 권한을 담는 인터페이스)의 권한 정보를 이용해서 JWT Token을 생성하고 return
    public String createToken(Authentication authentication) {
        // 사용자의 권한 목록
        String authorities = authentication.getAuthorities().stream() // 현재 사용자의 권한 목록을 가져옴
                .map(GrantedAuthority::getAuthority) // String으로 바꿈
                .collect(Collectors.joining(",")); // ','를 써서 하나의 문자로 묶음

        // Token 만료시간
        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInMilliseconds);

        return Jwts.builder()
                .setSubject(authentication.getName()) // 토큰 용도
                .claim(AUTHORITIES_KEY, authorities) // JWT body: claim이 호출되면 Claims 인스턴스에 key-value쌍의 데이타가 추가되며 기존에 있는 key-value는 덮어쓴다. (ex) "auth": "authority1"
                .signWith(key, SignatureAlgorithm.HS512) // sign-key 지정, key: secret: bmV3cy13aGFsZS1iYWNrZW5kLXdpdGgtc3ByaW5nYm9vdC1mb3ItbG9naW4tYXV0aG9yaXphdGlvbi1hbmQtYXV0aGVudGljYXRpb24tand0LXNlY3VyaXR5LWtleQ== , key에 허용된 안전한 알고리즘
                .setExpiration(validity) // 토큰 만료 기간
                .compact(); // 토큰 생성
    }

    // Token에 담겨 있는 정보를 이용해 UsernamePasswordAuthenticationToken 객체를 생성하고 return
    public Authentication getAuthentication(String token) {
        // Token으로 Claim (JWT의 Body) 생성
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        // Token으로부터 authorities를 받아옴
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(",")) // claim에서 "auth": 에 맞는 값들을 꺼냄
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        // 이를 이용해 User 객체 생성
        User principal = new User(claims.getSubject(), "", authorities); // 내가 만든 User 엔티티가 아니고, Spring Security에서 사용하는 User 객체임

        // 최종적으로 UsernamePasswordAuthenticationToken 객체 return
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    // Token 유효성 검사
    // Token을 parameter로 받아 파싱하고 exception을 캐치하여
    // 문제 있으면 false, 없으면 true return
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            logger.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            logger.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            logger.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            logger.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

}
