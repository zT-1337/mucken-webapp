package de.tzerr.muckenwebapp.game.api;

import de.tzerr.muckenwebapp.game.api.command.GameCommandDto;
import de.tzerr.muckenwebapp.game.api.event.GameEventDto;
import de.tzerr.muckenwebapp.util.ApiResult;
import jakarta.validation.Valid;
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

  @GetMapping("/read-event-log")
  public ApiResult<List<GameEventDto>, String> readEventLog() {
    throw new RuntimeException("Not implemented");
  }

  @PostMapping("/create-game")
  public ApiResult<List<GameEventDto>, String> createGame(@Valid @NonNull @RequestBody CreateGameDto createGameDto) {
    throw new RuntimeException("Not implemented");
  }

  @PostMapping("/execute-command")
  public ApiResult<List<GameEventDto>, String> executeCommand(@Valid @NonNull @RequestBody GameCommandDto gameCommandDto) {
    throw new RuntimeException("Not implemented");
  }
}
