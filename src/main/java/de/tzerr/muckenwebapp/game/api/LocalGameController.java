package de.tzerr.muckenwebapp.game.api;

import de.tzerr.muckenwebapp.auth.util.SecurityUtil;
import de.tzerr.muckenwebapp.game.api.command.GameCommandDto;
import de.tzerr.muckenwebapp.game.api.event.GameEventDto;
import de.tzerr.muckenwebapp.game.command.CreateLocalGame;
import de.tzerr.muckenwebapp.util.ApiResult;
import lombok.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/games/local")
public class LocalGameController {

  private final CreateLocalGame createLocalGame;

  public LocalGameController(CreateLocalGame createLocalGame) {
    this.createLocalGame = createLocalGame;
  }

  @GetMapping("/read-event-log")
  public ApiResult<List<GameEventDto>, String> readEventLog() {
    throw new RuntimeException("Not implemented");
  }

  @PostMapping("/create-game")
  public ApiResult<List<GameEventDto>, String> createGame(@NonNull @RequestBody CreateGameDto createGameDto) {
    return ApiResult.fromLambda(
      () -> createLocalGame.execute(
          createGameDto.toArgs(SecurityUtil.getUsernameOfAuthenticatedUser())
        ).stream()
        .map(GameEventDto::from)
        .toList()
    );
  }

  @PostMapping("/execute-command")
  public ApiResult<List<GameEventDto>, String> executeCommand(@NonNull @RequestBody GameCommandDto gameCommandDto) {
    throw new RuntimeException("Not implemented");
  }
}
