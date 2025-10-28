package de.tzerr.muckenwebapp.game.command;

import de.tzerr.muckenwebapp.game.model.GameEvent;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.NonNull;

import java.util.List;
import java.util.Set;

public class CreateLocalGame {

  public List<GameEvent> execute(@NonNull @Valid Args args) {
    throw new RuntimeException("Not implemented");
  }

  public record Args(@NonNull @Size(min = 4, max = 4) Set<@NotNull @NotBlank String> playerNames) {
  }
}
