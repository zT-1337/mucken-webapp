package de.tzerr.muckenwebapp.game.command;

import de.tzerr.mucken.core.game.DefaultGame;
import de.tzerr.mucken.core.game.Player;
import de.tzerr.mucken.core.game.deck.SimpleRandomShuffler;
import de.tzerr.muckenwebapp.game.model.event.GameEvent;
import de.tzerr.muckenwebapp.game.model.GameRecord;
import de.tzerr.muckenwebapp.game.repository.LocalGameRepository;
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

  private final LocalGameRepository localGameRepository;

  public CreateLocalGame(LocalGameRepository localGameRepository) {
    this.localGameRepository = localGameRepository;
  }

  public List<GameEvent> execute(@NonNull @Valid Args args) {
    var gameBuilder = new DefaultGame.Builder()
      .withDeckShuffler(new SimpleRandomShuffler(5, System.currentTimeMillis()));
    args.players.forEach(player -> gameBuilder.withPlayer(new Player(player)));

    var gameRecord = new GameRecord(gameBuilder.build());
    localGameRepository.save(args.gameId, gameRecord);

    throw new UnsupportedOperationException("Not implemented - CreateLocalGame");
  }

  public record Args(@NotNull @Size(min = 4, max = 4) Set<@NotBlank String> players,
                     @NotBlank String gameId) {
  }
}
