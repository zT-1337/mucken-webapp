package de.tzerr.muckenwebapp.auth.config;

import de.tzerr.muckenwebapp.auth.model.Account;
import de.tzerr.muckenwebapp.auth.repository.AccountRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class Oauth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

  private final AccountRepository accountRepository;
  private final DefaultOAuth2UserService delegate;

  public Oauth2UserService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
    this.delegate = new DefaultOAuth2UserService();
  }

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    var oauthUser = delegate.loadUser(userRequest);
    var email = Objects.requireNonNull(oauthUser.getAttribute("email")).toString();

    var account = accountRepository.findByEmail(email).orElseGet(() -> {
      var newAccount = Account.fromEmail(email);
      return accountRepository.save(newAccount);
    });

    var userInfo = OidcUserInfo.builder()
      .name(account.getUsername())
      .email(account.getEmail())
      .claim("id", account.getId())
      .build();

    return new DefaultOAuth2User(Set.of(), userInfo.getClaims(), "name");
  }
}
