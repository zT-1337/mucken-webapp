package de.tzerr.muckenwebapp.auth.repository;

import de.tzerr.muckenwebapp.auth.model.Account;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface AccountRepository extends Repository<Account, String> {

  Account save(Account account);
  Optional<Account> findByEmail(String email);
  Optional<Account> findByUsername(String username);
}
