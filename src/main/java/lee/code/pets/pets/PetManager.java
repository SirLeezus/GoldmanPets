package lee.code.pets.pets;

import lee.code.pets.pets.pet.animal.ChickenPet;
import lee.code.pets.pets.pet.animal.WolfPet;
import lee.code.pets.pets.pet.mob.PhantomPet;
import lee.code.pets.pets.pet.animal.SheepPet;
import net.minecraft.world.entity.Entity;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class PetManager  {

  public void spawn(Player player, EntityType entityType, String[] data) {
    switch (entityType) {
      case SHEEP -> spawn(player.getWorld(), new SheepPet(player, Boolean.parseBoolean(data[0]), data[1], data[2]));
      case CHICKEN -> spawn(player.getWorld(), new ChickenPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case PHANTOM -> spawn(player.getWorld(), new PhantomPet(player, data[0]));
      case WOLF -> spawn(player.getWorld(), new WolfPet(player, Boolean.parseBoolean(data[0]), data[1]));
    }
  }

  private void spawn(World world, Entity entity) {
    ((CraftWorld) world).getHandle().addFreshEntity(entity, CreatureSpawnEvent.SpawnReason.CUSTOM);
  }
}
