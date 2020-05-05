package com.frontend;

import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;


@CrossOrigin
@RestController
public class WebController extends WebSecurityConfigurerAdapter  {

    @GetMapping("/user_info")
    public String user(@AuthenticationPrincipal OAuth2User principal) {
        return principal.getAttribute("id").toString();
    }

    @RequestMapping("/is_logged")
    public Boolean is_logged(@AuthenticationPrincipal OAuth2User principal) {
        return principal != null;
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .cors()
                .and()
                .authorizeRequests(a -> a
                        .antMatchers("/","/is_logged","/js/**","/css/**","/fonts/**", "/error", "/webjars/**").permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(e -> e
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )
                .csrf(c -> c
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                .logout(l -> l
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/").permitAll()
                )
                .oauth2Login();
        // @formatter:on
    }
}
