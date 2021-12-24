package com.rob.asuTwit.config;

import com.rob.asuTwit.models.User;
import com.rob.asuTwit.repo.UserDetailsRepo;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.time.LocalDateTime;


@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").authenticated() // Block this
                .antMatchers().permitAll() // Allow this for all
                .anyRequest().authenticated();

    }

    @Bean
    public PrincipalExtractor principalExtractor(UserDetailsRepo userDetailsRepo) {
        return map -> {
            String id = (String) map.get("sub");
            User user = userDetailsRepo.findById(id).orElseGet(() -> {
                User newUser = User.builder()
                        .id(id).name((String) map.get("name"))
                        .email((String) map.get("email"))
                        .gender((String) map.get("gender"))
                        .locale((String) map.get("locale"))
                        .userpic((String) map.get("picture"))
                        .lastVisit(LocalDateTime.now())
                        .build();
                return newUser;
            });
            user.setLastVisit(LocalDateTime.now());
            return userDetailsRepo.save(user);
        };
    }
}
