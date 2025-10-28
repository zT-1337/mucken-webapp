package de.tzerr.muckenwebapp.game.api.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import de.tzerr.muckenwebapp.game.model.event.GameEvent;
import lombok.RequiredArgsConstructor;

@JsonTypeInfo(
  use = JsonTypeInfo.Id.SIMPLE_NAME,
  include = JsonTypeInfo.As.EXISTING_PROPERTY,
  property = "type",
  visible = true
)
@JsonSubTypes({
  @JsonSubTypes.Type(value = BetAcceptedDto.class),
  @JsonSubTypes.Type(value = BetFinishedDto.class),
  @JsonSubTypes.Type(value = BetShoutedDto.class),
  @JsonSubTypes.Type(value = CardPlayedDto.class),
  @JsonSubTypes.Type(value = DealtHandDto.class),
  @JsonSubTypes.Type(value = GameFinishedDto.class),
  @JsonSubTypes.Type(value = WonStingDto.class)
})
@RequiredArgsConstructor
public abstract class GameEventDto {
  private final int eventId;

  public static GameEventDto from(GameEvent gameEvent) {
    throw new RuntimeException("Not implemented yet");
  }
}
