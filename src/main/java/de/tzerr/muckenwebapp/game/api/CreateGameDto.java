package de.tzerr.muckenwebapp.game.api;

import de.tzerr.muckenwebapp.game.command.CreateLocalGame;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class CreateGameDto {
  private Set<String> players;

  public CreateLocalGame.Args toArgs(String gameId) {
    return new CreateLocalGame.Args(players, gameId);
  }
}
