package de.tzerr.muckenwebapp.auth.api;

import de.tzerr.muckenwebapp.auth.repository.AccountRepository;
import de.tzerr.muckenwebapp.auth.util.SecurityUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

  private final AccountRepository accountRepository;

  public AccountController(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @GetMapping("read-me")
  public AccountDto readMe() {
    var account = accountRepository
      .findByUsername(SecurityUtil.getUsernameOfAuthenticatedUser())
      .orElseThrow(AccountNotFound::new);

    return AccountDto.from(account);
  }
}
