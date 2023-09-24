package lee.code.pets.pets;

import lee.code.pets.pets.pet.animal.*;
import lee.code.pets.pets.pet.animal.CatPet;
import lee.code.pets.pets.pet.fish.*;
import lee.code.pets.pets.pet.monster.*;
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
      case HORSE -> spawn(player.getWorld(), new HorsePet(player, Boolean.parseBoolean(data[0]), Boolean.parseBoolean(data[1]), data[2], data[3], data[4]));
      case COD -> spawn(player.getWorld(), new CodPet(player, data[0]));
      case DOLPHIN -> spawn(player.getWorld(), new DolphinPet(player, data[0]));
      case GHAST -> spawn(player.getWorld(), new GhastPet(player, data[0]));
      case BAT -> spawn(player.getWorld(), new BatPet(player, data[0]));
      case ALLAY -> spawn(player.getWorld(), new AllayPet(player, data[0]));
      case STRIDER -> spawn(player.getWorld(), new StriderPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case SNOWMAN -> spawn(player.getWorld(), new SnowGolemPet(player, data[0], Boolean.parseBoolean(data[1])));
      case IRON_GOLEM -> spawn(player.getWorld(), new IronGolemPet(player, data[0]));
      case ZOGLIN -> spawn(player.getWorld(), new ZoglinPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case TURTLE -> spawn(player.getWorld(), new TurtlePet(player, Boolean.parseBoolean(data[0]), data[1]));
      case AXOLOTL -> spawn(player.getWorld(), new AxolotlPet(player, Boolean.parseBoolean(data[0]), data[1], data[2]));
      case WITCH -> spawn(player.getWorld(), new WitchPet(player, data[0]));
      case COW -> spawn(player.getWorld(), new CowPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case MUSHROOM_COW -> spawn(player.getWorld(), new MushroomCowPet(player, Boolean.parseBoolean(data[0]), data[1], data[2]));
      case WARDEN -> spawn(player.getWorld(), new WardenPet(player, data[0]));
      case WITHER -> spawn(player.getWorld(), new WitherPet(player, data[0]));
      case ENDERMAN -> spawn(player.getWorld(), new EndermanPet(player, data[0]));
      case CAMEL -> spawn(player.getWorld(), new CamelPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case DONKEY -> spawn(player.getWorld(), new DonkeyPet(player, Boolean.parseBoolean(data[0]), data[1], Boolean.parseBoolean(data[2])));
      case FROG -> spawn(player.getWorld(), new FrogPet(player, data[0], data[1]));
      case FOX -> spawn(player.getWorld(), new FoxPet(player, Boolean.parseBoolean(data[0]), data[1], data[2]));
      case SQUID -> spawn(player.getWorld(), new SquidPet(player, data[0]));
      case GLOW_SQUID -> spawn(player.getWorld(), new GlowSquidPet(player, data[0]));
      case OCELOT -> spawn(player.getWorld(), new OcelotPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case PARROT -> spawn(player.getWorld(), new ParrotPet(player, data[0], data[1]));
      case PIG -> spawn(player.getWorld(), new PigPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case PUFFERFISH -> spawn(player.getWorld(), new PufferFishPet(player, data[0]));
      case SALMON -> spawn(player.getWorld(), new SalmonPet(player, data[0]));
      case SKELETON_HORSE -> spawn(player.getWorld(), new SkeletonHorsePet(player, Boolean.parseBoolean(data[0]), Boolean.parseBoolean(data[1]), data[2]));
      case ZOMBIE_HORSE -> spawn(player.getWorld(), new ZombieHorsePet(player, Boolean.parseBoolean(data[0]), Boolean.parseBoolean(data[1]), data[2]));
      case TADPOLE -> spawn(player.getWorld(), new TadpolePet(player, data[0]));
      case TROPICAL_FISH -> spawn(player.getWorld(), new TropicalFishPet(player, data[0], data[1], data[2], data[3]));
      case WANDERING_TRADER -> spawn(player.getWorld(), new WanderingTraderPet(player, data[0]));
      case SPIDER -> spawn(player.getWorld(), new SpiderPet(player, data[0]));
      case LLAMA -> spawn(player.getWorld(), new LlamaPet(player, Boolean.parseBoolean(data[0]), Boolean.parseBoolean(data[1]), data[2], data[3], data[4]));
      case GOAT -> spawn(player.getWorld(), new GoatPet(player, Boolean.parseBoolean(data[0]), data[1], Boolean.parseBoolean(data[2])));
      case CAVE_SPIDER -> spawn(player.getWorld(), new CaveSpiderPet(player, data[0]));
      case PANDA -> spawn(player.getWorld(), new PandaPet(player, Boolean.parseBoolean(data[0]), data[1], data[2], data[3]));
      case PIGLIN -> spawn(player.getWorld(), new PiglinPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case ZOMBIFIED_PIGLIN -> spawn(player.getWorld(), new ZombifiedPiglinPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case POLAR_BEAR -> spawn(player.getWorld(), new PolarBearPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case HUSK -> spawn(player.getWorld(), new HuskPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case SILVERFISH -> spawn(player.getWorld(), new SilverfishPet(player, data[0]));
      case ENDERMITE -> spawn(player.getWorld(), new EndermitePet(player, data[0]));
      case PILLAGER -> spawn(player.getWorld(), new PillagerPet(player, data[0]));
      case ELDER_GUARDIAN -> spawn(player.getWorld(), new ElderGuardianPet(player, data[0]));
      case GUARDIAN -> spawn(player.getWorld(), new GuardianPet(player, data[0]));
      case VEX -> spawn(player.getWorld(), new VexPet(player, data[0]));
      case DROWNED -> spawn(player.getWorld(), new DrownedPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case HOGLIN -> spawn(player.getWorld(), new HoglinPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case PIGLIN_BRUTE -> spawn(player.getWorld(), new PiglinBrutePet(player, Boolean.parseBoolean(data[0]), data[1]));
      case SHULKER -> spawn(player.getWorld(), new ShulkerPet(player, data[0], data[1]));
      case STRAY -> spawn(player.getWorld(), new StrayPet(player, data[0]));
      case VINDICATOR -> spawn(player.getWorld(), new VindicatorPet(player, data[0]));
      case WITHER_SKELETON -> spawn(player.getWorld(), new WitherSkeletonPet(player, data[0]));
    }
  }

  private void spawn(World world, Entity entity) {
    ((CraftWorld) world).getHandle().addFreshEntity(entity, CreatureSpawnEvent.SpawnReason.CUSTOM);
  }
}
