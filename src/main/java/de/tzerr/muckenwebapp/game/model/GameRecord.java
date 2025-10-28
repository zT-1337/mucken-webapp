package de.tzerr.muckenwebapp.game.model;

import de.tzerr.mucken.core.game.Game;
import de.tzerr.muckenwebapp.game.model.event.GameEvent;

import java.util.ArrayList;
import java.util.List;

public class GameRecord {
  private final Game game;
  private final List<GameEvent> eventLog = new ArrayList<>();

  public GameRecord(Game game) {
    this.game = game;
  }
}
