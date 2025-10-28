package de.tzerr.muckenwebapp.game.command;

import de.tzerr.muckenwebapp.game.model.GameCommand;
import de.tzerr.muckenwebapp.game.model.GameEvent;
import jakarta.validation.Valid;
import lombok.NonNull;

import java.util.List;

public class ExecuteGameCommand {

  public List<GameEvent> execute(@NonNull @Valid Args args) {
    throw new RuntimeException("Not implemented");
  }

  public record Args(@NonNull @Valid GameCommand command) {
  }
}
