package de.tzerr.muckenwebapp.auth.api;

import de.tzerr.muckenwebapp.auth.query.ReadAccountByUsername;
import de.tzerr.muckenwebapp.auth.util.SecurityUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

  private final ReadAccountByUsername readAccountByUsername;

  public AccountController(ReadAccountByUsername readAccountByUsername) {
    this.readAccountByUsername = readAccountByUsername;
  }

  @GetMapping("read-me")
  public AccountDto readMe() {
    return AccountDto.from(
      readAccountByUsername.query(
        new ReadAccountByUsername.Filter(SecurityUtil.getUsernameOfAuthenticatedUser())
      )
    );
  }
}
