package de.tzerr.muckenwebapp.auth.api;

import de.tzerr.muckenwebapp.auth.model.Account;
import de.tzerr.muckenwebapp.auth.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class AccountControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private AccountRepository accountRepository;

  @Test
  @WithMockUser(username = "Trainer Red")
  void shouldReturnAuthenticatedAccount() throws Exception {
    var account = new Account();
    account.setUsername("Trainer Red");
    account.setEmail("test@test.com");

    var savedAccount = accountRepository.save(account);

    this.mockMvc.perform(get("/api/v1/accounts/read-me"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.error").isEmpty())
      .andExpect(jsonPath("$.ok.id").value(savedAccount.getId().toString()))
      .andExpect(jsonPath("$.ok.username").value(savedAccount.getUsername()))
      .andExpect(jsonPath("$.ok.email").value(savedAccount.getEmail()));
  }

  @Test
  @WithMockUser(username = "unknownUser")
  void shouldReturnErrorBecauseAuthenticatedUserIsNotInDatabase() throws Exception {
    this.mockMvc.perform(get("/api/v1/accounts/read-me"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.error").value("AccountNotFound"))
      .andExpect(jsonPath("$.ok").isEmpty());
  }

  @Test
  void shouldReturnUnauthorizedBecauseUserIsNotAuthenticated() throws Exception {
    this.mockMvc.perform(get("/api/v1/accounts/read-me"))
      .andExpect(status().isUnauthorized());
  }
}