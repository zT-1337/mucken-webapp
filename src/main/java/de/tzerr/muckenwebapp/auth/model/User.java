package de.tzerr.muckenwebapp.auth.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

  private final String name;
  private final String email;

  public static User fromEmail(String email) {
    return new User(UUID.randomUUID().toString(), email);
  }
}
