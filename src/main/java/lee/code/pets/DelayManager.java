package lee.code.pets;

import org.bukkit.Bukkit;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class DelayManager {
  private final Pets pets;
  private final ConcurrentHashMap<UUID, Long> playersOnDelay = new ConcurrentHashMap<>();

  public DelayManager(Pets pets) {
    this.pets = pets;
  }

  public boolean isOnDelayOrSchedule(UUID uuid, long delay) {
    if (isOnDelay(uuid)) return true;
    setOnDelay(uuid, delay);
    return false;
  }

  public boolean isOnDelay(UUID uuid) {
    return playersOnDelay.containsKey(uuid);
  }

  public void setOnDelay(UUID uuid, long delay) {
    playersOnDelay.put(uuid, System.currentTimeMillis() + delay);
    scheduleDelay(uuid, delay);
  }

  private void scheduleDelay(UUID uuid, long delay) {
    Bukkit.getServer().getAsyncScheduler().runDelayed(pets, scheduledTask ->
      playersOnDelay.remove(uuid), delay, TimeUnit.MILLISECONDS);
  }
}
