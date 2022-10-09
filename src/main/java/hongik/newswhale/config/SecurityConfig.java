package hongik.newswhale.config;

import hongik.newswhale.security.jwt.JwtAccessDeniedHandler;
import hongik.newswhale.security.jwt.JwtAuthenticationEntryPoint;
import hongik.newswhale.security.jwt.JwtSecurityConfig;
import hongik.newswhale.security.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    public SecurityConfig(TokenProvider tokenProvider,
                          JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                          JwtAccessDeniedHandler jwtAccessDeniedHandler) {
        this.tokenProvider = tokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("my-sql/**",
                        "/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()

                // 만들었던 예외처리 추가
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                // My-SQL 콘솔
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                // 세션을 사용하지 않음
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // 요청들에 대한 제한 설정
                .and()
                .authorizeRequests() // 사용하는 요청들에 대한 접근 제한을 설정함
                .antMatchers("/auth/**").permitAll() // '/auth/*'에 대한 요청은 인증 없이 접근을 허용함
                .anyRequest().authenticated() // 나머지 요청들은 모두 인증되어야 함

                // TokenProvider와 JwtFilter를 SecurityConfig에 적용
                .and()
                .apply(new JwtSecurityConfig(tokenProvider));
    }
}
