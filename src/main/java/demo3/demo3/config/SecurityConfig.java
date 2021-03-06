package demo3.demo3.config;

import demo3.demo3.jwt.JwtAccessDeniedHandler;
import demo3.demo3.jwt.JwtAuthenticationEntryPoint;
import demo3.demo3.jwt.JwtSecurityConfig;
import demo3.demo3.jwt.TokenProvider;
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

                // ???????????? ???????????? ??????
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                // My-SQL ??????
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                // ????????? ???????????? ??????
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // ???????????? ?????? ?????? ??????
                .and()
                .authorizeRequests() // ???????????? ???????????? ?????? ?????? ????????? ?????????
                .antMatchers("/auth/**").permitAll() // '/auth/*'??? ?????? ????????? ?????? ?????? ????????? ?????????
                .anyRequest().authenticated() // ????????? ???????????? ?????? ??????????????? ???

                // TokenProvider??? JwtFilter??? SecurityConfig??? ??????
                .and()
                .apply(new JwtSecurityConfig(tokenProvider));
    }
}
