package de.tzerr.muckenwebapp.game.api;

import de.tzerr.muckenwebapp.util.JsonUtil;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class LocalGameControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Nested
  class CreateLocalGame {

    private static Stream<Set<String>> malformedPlayers() {
      return Stream.of(
        null,
        Set.of(),
        Set.of("Thömel", "Lea", "Marcel"),
        Set.of("Thömel", "Lea", "Marcel", "Flo", "Gabi"),
        Set.of("", "Lea", "Marcel", "Flo"),
        Set.of(" ", "Lea", "Marcel", "Flo")
      );
    }

    @Test
    void shouldReturnUnauthorizedBecauseUserIsNotAuthenticated() throws Exception {
      mockMvc.perform(
        post("/api/v1/games/local/create-game")
          .contentType(MediaType.APPLICATION_JSON)
          .content(JsonUtil.toJson(new CreateGameDto(Set.of("Thömel", "Lea", "Marcel", "Flo"))))
      ).andExpect(status().isUnauthorized());
    }

    @ParameterizedTest
    @MethodSource("malformedPlayers")
    @WithMockUser(username = "Trainer Red")
    void shouldReturnBadRequestBecausePlayersIsMalformed(Set<String> players) throws Exception {
      mockMvc.perform(
        post("/api/v1/games/local/create-game")
          .contentType(MediaType.APPLICATION_JSON)
          .content(JsonUtil.toJson(new CreateGameDto(players)))
      ).andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "Trainer Blue")
    void shouldCreateNewGame() throws Exception {
      mockMvc.perform(
        post("/api/v1/games/local/create-game")
          .contentType(MediaType.APPLICATION_JSON)
          .content(JsonUtil.toJson(new CreateGameDto(Set.of("Thömel", "Lea", "Marcel", "Flo"))))
      ).andExpect(status().isOk());

      throw new RuntimeException("Match expected initial events");
    }

    @Test
    @WithMockUser(username = "Trainer Green")
    void shouldNotCreateGameBecauseUserAlreadyHasGame() throws Exception {
      mockMvc.perform(
        post("/api/v1/games/local/create-game")
          .contentType(MediaType.APPLICATION_JSON)
          .content(JsonUtil.toJson(new CreateGameDto(Set.of("Thömel", "Lea", "Marcel", "Flo"))))
      ).andExpect(status().isOk());

      mockMvc.perform(
          post("/api/v1/games/local/create-game")
            .contentType(MediaType.APPLICATION_JSON)
            .content(JsonUtil.toJson(new CreateGameDto(Set.of("Thömel", "Lea", "Marcel", "Flo"))))
        ).andExpect(status().isOk())
        .andExpect(jsonPath("$.ok").isEmpty())
        .andExpect(jsonPath("$.error").value("LocalGameAlreadyExists"));
    }
  }
}