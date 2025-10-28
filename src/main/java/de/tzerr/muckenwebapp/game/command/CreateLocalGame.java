package de.tzerr.muckenwebapp.game.command;

import de.tzerr.muckenwebapp.game.model.GameEvent;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Set;

@Service
@Validated
public class CreateLocalGame {

  public List<GameEvent> execute(@NonNull @Valid Args args) {
    throw new UnsupportedOperationException("Not implemented - CreateLocalGame");
  }

  public record Args(@NotNull @Size(min = 4, max = 4) Set<@NotBlank String> players,
                     @NotBlank String gameId) {
  }
}
