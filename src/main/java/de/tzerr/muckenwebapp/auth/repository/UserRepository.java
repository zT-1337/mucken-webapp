package de.tzerr.muckenwebapp.auth.repository;

import de.tzerr.muckenwebapp.auth.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

  private static final List<User> users = new ArrayList<>();

  public Optional<User> findByEmail(String email) {
    return users.stream().filter(user -> user.getEmail().equals(email)).findFirst();
  }

  public void save(User user) {
    users.add(user);
  }
}
