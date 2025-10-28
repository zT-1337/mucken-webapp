package de.tzerr.muckenwebapp.game.repository;

import de.tzerr.muckenwebapp.game.model.GameRecord;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class LocalGameRepository {

  private final Map<String, GameRecord> games = new ConcurrentHashMap<>();

  public synchronized void save(String gameId, GameRecord gameRecord) {
    if (games.containsKey(gameId)) {
      throw new LocalGameAlreadyExists();
    }

    games.put(gameId, gameRecord);
  }
}
