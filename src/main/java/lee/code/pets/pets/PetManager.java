package lee.code.pets.pets;

import lee.code.pets.pets.pet.animal.*;
import lee.code.pets.pets.pet.animal.CatPet;
import lee.code.pets.pets.pet.fish.CodPet;
import lee.code.pets.pets.pet.fish.DolphinPet;
import lee.code.pets.pets.pet.mob.*;
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
      case WOLF -> spawn(player.getWorld(), new WolfPet(player, Boolean.parseBoolean(data[0]), data[1], data[2]));
      case CAT -> spawn(player.getWorld(), new CatPet(player, Boolean.parseBoolean(data[0]), data[1], data[2], data[3]));
      case VILLAGER -> spawn(player.getWorld(), new VillagerPet(player, Boolean.parseBoolean(data[0]), data[1], data[2], data[3], data[4]));
      case BEE -> spawn(player.getWorld(), new BeePet(player, Boolean.parseBoolean(data[0]), data[1]));
      case ZOMBIE -> spawn(player.getWorld(), new ZombiePet(player, Boolean.parseBoolean(data[0]), data[1]));
      case BLAZE -> spawn(player.getWorld(), new BlazePet(player, data[0]));
      case SNIFFER -> spawn(player.getWorld(), new SnifferPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case RABBIT -> spawn(player.getWorld(), new RabbitPet(player, Boolean.parseBoolean(data[0]), data[1], data[2]));
      case SLIME -> spawn(player.getWorld(), new SlimePet(player, data[0], data[1]));
      case MAGMA_CUBE -> spawn(player.getWorld(), new MagmaCubePet(player, data[0], data[1]));
      case RAVAGER -> spawn(player.getWorld(), new RavagerPet(player, data[0]));
      case CREEPER -> spawn(player.getWorld(), new CreeperPet(player, data[0], Boolean.parseBoolean(data[1])));
      case SKELETON -> spawn(player.getWorld(), new SkeletonPet(player, data[0]));
      case HORSE -> spawn(player.getWorld(), new HorsePet(player, Boolean.parseBoolean(data[0]), data[1], data[2], data[3]));
      case COD -> spawn(player.getWorld(), new CodPet(player, data[0]));
      case DOLPHIN -> spawn(player.getWorld(), new DolphinPet(player, data[0]));
      case GHAST -> spawn(player.getWorld(), new GhastPet(player, data[0]));
      case BAT -> spawn(player.getWorld(), new BatPet(player, data[0]));
      case ALLAY -> spawn(player.getWorld(), new AllayPet(player, data[0]));
      case STRIDER -> spawn(player.getWorld(), new StriderPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case SNOWMAN -> spawn(player.getWorld(), new SnowGolemPet(player, data[0], Boolean.parseBoolean(data[1])));
      case IRON_GOLEM -> spawn(player.getWorld(), new IronGolemPet(player, data[0]));
    }
  }

  private void spawn(World world, Entity entity) {
    ((CraftWorld) world).getHandle().addFreshEntity(entity, CreatureSpawnEvent.SpawnReason.CUSTOM);
  }
}
