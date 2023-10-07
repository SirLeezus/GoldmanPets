package lee.code.pets.events;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class RideEvent extends Event implements Cancellable {
  private static final HandlerList handlers = new HandlerList();
  @Getter Player player;
  @Getter Entity entity;
  @Setter @Getter boolean cancelled;

  public RideEvent(Player player, Entity entity) {
    this.player = player;
    this.entity = entity;
  }

  @Override
  public HandlerList getHandlers() {
    return handlers;
  }

  public static HandlerList getHandlerList() {
    return handlers;
  }
}
