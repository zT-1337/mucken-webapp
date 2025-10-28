package de.tzerr.muckenwebapp.game.query;

import de.tzerr.muckenwebapp.game.model.GameEvent;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;

import java.util.List;

public class ReadGameEventLog {

  public List<GameEvent> query(@NonNull @Valid Filter filter) {
    throw new RuntimeException("Not implemented yet");
  }

  public record Filter(@NonNull @NotBlank String gameId) {
  }
}
