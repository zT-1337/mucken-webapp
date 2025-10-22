package de.tzerr.muckenwebapp.auth.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false, unique = true)
  private String username = UUID.randomUUID().toString();

  @Column(nullable = false, unique = true)
  private String email;

  public static Account fromEmail(String email) {
    var user = new Account();
    user.email = email;

    return user;
  }
}
