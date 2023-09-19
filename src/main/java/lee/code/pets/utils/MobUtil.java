package lee.code.pets.utils;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Flying;
import org.bukkit.entity.WaterMob;

public class MobUtil {

  public static boolean canFly(Entity entity) {
    return entity instanceof Flying;
  }

  public static boolean canSwim(Entity entity) {
    return entity instanceof WaterMob;
  }
}
