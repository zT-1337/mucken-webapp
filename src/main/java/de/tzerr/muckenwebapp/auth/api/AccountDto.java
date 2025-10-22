package de.tzerr.muckenwebapp.auth.api;

import de.tzerr.muckenwebapp.auth.model.Account;
import lombok.Data;

@Data
public class AccountDto {
  private String id;
  private String username;
  private String email;

  public static AccountDto from(Account account) {
    AccountDto dto = new AccountDto();
    dto.id = account.getId().toString();
    dto.username = account.getUsername();
    dto.email = account.getEmail();

    return dto;
  }
}
