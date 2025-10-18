package de.tzerr.muckenwebapp.auth.api;

import de.tzerr.muckenwebapp.auth.model.User;
import de.tzerr.muckenwebapp.auth.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("read-me")
  public User readMe() {
    var email = ((DefaultOAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAttributes().get("email").toString();
    return userRepository.findByEmail(email).orElseGet(() -> {
      var newUser = User.fromEmail(email);
      userRepository.save(newUser);
      return newUser;
    });
  }
}
