package lee.code.pets.pets;

import lee.code.pets.pets.pet.ChickenPet;
import lee.code.pets.pets.pet.PhantomPet;
import lee.code.pets.pets.pet.SheepPet;
import lee.code.pets.pets.pet.WolfPet;
import net.minecraft.world.entity.Entity;
import org.bukkit.DyeColor;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class PetManager  {

  public void spawnSheep(Player player, String name, DyeColor dyeColor) {
    spawn(player.getWorld(), new SheepPet(player, player.getLocation(), name, dyeColor.name()));
  }

  public void spawnChicken(Player player, String name) {
    spawn(player.getWorld(), new ChickenPet(player, player.getLocation(), name));
  }

  public void spawnWolf(Player player, String name) {
    spawn(player.getWorld(), new WolfPet(player, player.getLocation(), name));
  }

  public void spawnPhantom(Player player, String name) {
    spawn(player.getWorld(), new PhantomPet(player, player.getLocation(), name));
  }

  private void spawn(World world, Entity entity) {
    ((CraftWorld) world).getHandle().addFreshEntity(entity, CreatureSpawnEvent.SpawnReason.CUSTOM);
  }

}
