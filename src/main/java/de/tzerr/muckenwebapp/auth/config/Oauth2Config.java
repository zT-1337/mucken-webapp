package de.tzerr.muckenwebapp.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.HttpStatusAccessDeniedHandler;

@Configuration
public class Oauth2Config {

  @Bean
  public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    return http
      .csrf(AbstractHttpConfigurer::disable)
      .authorizeHttpRequests(
        authorize -> authorize
          .requestMatchers("/login", "/assets/**").permitAll()
          .anyRequest().authenticated()
      )
      .oauth2Login(oauth2 -> oauth2
        .loginPage("/login")
        .defaultSuccessUrl("/", true)
      )
      .logout(logout -> logout
        .logoutUrl("/logout")
        .deleteCookies("JSESSIONID")
        .invalidateHttpSession(true)
        .clearAuthentication(true)
        .logoutSuccessUrl("/login")
        .permitAll()
      )
      .exceptionHandling(exception -> exception
        .authenticationEntryPoint((request, response, authException) -> {
          if (request.getRequestURI().startsWith("/api")) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
          } else {
            response.sendRedirect("/login");
          }
        })
        .accessDeniedHandler(new HttpStatusAccessDeniedHandler(HttpStatus.FORBIDDEN))
      )
      .build();
  }
}
