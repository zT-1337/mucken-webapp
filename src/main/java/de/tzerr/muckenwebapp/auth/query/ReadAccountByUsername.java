package de.tzerr.muckenwebapp.auth.query;

import de.tzerr.muckenwebapp.auth.api.AccountNotFound;
import de.tzerr.muckenwebapp.auth.model.Account;
import de.tzerr.muckenwebapp.auth.repository.AccountRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class ReadAccountByUsername {

  private final AccountRepository accountRepository;

  public ReadAccountByUsername(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public Account query(@NonNull Filter filter) {
    return accountRepository.findByUsername(filter.username)
      .orElseThrow(AccountNotFound::new);
  }

  public record Filter(@NonNull String username) {
  }
}
