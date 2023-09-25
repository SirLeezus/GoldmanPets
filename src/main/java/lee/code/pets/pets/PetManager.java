package lee.code.pets.pets;

import lee.code.pets.Pets;
import lee.code.pets.pets.pet.animal.*;
import lee.code.pets.pets.pet.animal.CatPet;
import lee.code.pets.pets.pet.fish.*;
import lee.code.pets.pets.pet.monster.*;
import net.minecraft.world.entity.Entity;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PetManager  {
  private final Pets pets;
  private final ConcurrentHashMap<UUID, Integer> petTracker = new ConcurrentHashMap<>();
  
  public PetManager(Pets pets) {
    this.pets = pets;
  }

  public void spawn(Player player, int id, EntityType entityType, String[] data) {
    switch (entityType) {
      case SHEEP -> spawn(player.getLocation(), id, new SheepPet(player, Boolean.parseBoolean(data[0]), data[1], data[2]));
      case CHICKEN -> spawn(player.getLocation(), id, new ChickenPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case PHANTOM -> spawn(player.getLocation(), id, new PhantomPet(player, data[0]));
      case WOLF -> spawn(player.getLocation(), id, new WolfPet(player, Boolean.parseBoolean(data[0]), data[1], data[2]));
      case CAT -> spawn(player.getLocation(), id, new CatPet(player, Boolean.parseBoolean(data[0]), data[1], data[2], data[3]));
      case VILLAGER -> spawn(player.getLocation(), id, new VillagerPet(player, Boolean.parseBoolean(data[0]), data[1], data[2], data[3], data[4]));
      case BEE -> spawn(player.getLocation(), id, new BeePet(player, Boolean.parseBoolean(data[0]), data[1]));
      case ZOMBIE -> spawn(player.getLocation(), id, new ZombiePet(player, Boolean.parseBoolean(data[0]), data[1]));
      case BLAZE -> spawn(player.getLocation(), id, new BlazePet(player, data[0]));
      case SNIFFER -> spawn(player.getLocation(), id, new SnifferPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case RABBIT -> spawn(player.getLocation(), id, new RabbitPet(player, Boolean.parseBoolean(data[0]), data[1], data[2]));
      case SLIME -> spawn(player.getLocation(), id, new SlimePet(player, data[0], data[1]));
      case MAGMA_CUBE -> spawn(player.getLocation(), id, new MagmaCubePet(player, data[0], data[1]));
      case RAVAGER -> spawn(player.getLocation(), id, new RavagerPet(player, data[0]));
      case CREEPER -> spawn(player.getLocation(), id, new CreeperPet(player, data[0], Boolean.parseBoolean(data[1])));
      case SKELETON -> spawn(player.getLocation(), id, new SkeletonPet(player, data[0]));
      case HORSE -> spawn(player.getLocation(), id, new HorsePet(player, Boolean.parseBoolean(data[0]), Boolean.parseBoolean(data[1]), data[2], data[3], data[4]));
      case COD -> spawn(player.getLocation(), id,  new CodPet(player, data[0]));
      case DOLPHIN -> spawn(player.getLocation(), id, new DolphinPet(player, data[0]));
      case GHAST -> spawn(player.getLocation(), id, new GhastPet(player, data[0]));
      case BAT -> spawn(player.getLocation(), id, new BatPet(player, data[0]));
      case ALLAY -> spawn(player.getLocation(), id, new AllayPet(player, data[0]));
      case STRIDER -> spawn(player.getLocation(), id, new StriderPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case SNOWMAN -> spawn(player.getLocation(), id, new SnowGolemPet(player, data[0], Boolean.parseBoolean(data[1])));
      case IRON_GOLEM -> spawn(player.getLocation(), id, new IronGolemPet(player, data[0]));
      case ZOGLIN -> spawn(player.getLocation(), id, new ZoglinPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case TURTLE -> spawn(player.getLocation(), id, new TurtlePet(player, Boolean.parseBoolean(data[0]), data[1]));
      case AXOLOTL -> spawn(player.getLocation(), id, new AxolotlPet(player, Boolean.parseBoolean(data[0]), data[1], data[2]));
      case WITCH -> spawn(player.getLocation(), id, new WitchPet(player, data[0]));
      case COW -> spawn(player.getLocation(), id, new CowPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case MUSHROOM_COW -> spawn(player.getLocation(), id, new MushroomCowPet(player, Boolean.parseBoolean(data[0]), data[1], data[2]));
      case WARDEN -> spawn(player.getLocation(), id, new WardenPet(player, data[0]));
      case WITHER -> spawn(player.getLocation(), id, new WitherPet(player, data[0]));
      case ENDERMAN -> spawn(player.getLocation(), id, new EndermanPet(player, data[0]));
      case CAMEL -> spawn(player.getLocation(), id, new CamelPet(player, Boolean.parseBoolean(data[0]), Boolean.parseBoolean(data[1]), data[2]));
      case DONKEY -> spawn(player.getLocation(), id, new DonkeyPet(player, Boolean.parseBoolean(data[0]), data[1], Boolean.parseBoolean(data[2])));
      case FROG -> spawn(player.getLocation(), id, new FrogPet(player, data[0], data[1]));
      case FOX -> spawn(player.getLocation(), id, new FoxPet(player, Boolean.parseBoolean(data[0]), data[1], data[2]));
      case SQUID -> spawn(player.getLocation(), id, new SquidPet(player, data[0]));
      case GLOW_SQUID -> spawn(player.getLocation(), id, new GlowSquidPet(player, data[0]));
      case OCELOT -> spawn(player.getLocation(), id, new OcelotPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case PARROT -> spawn(player.getLocation(), id, new ParrotPet(player, data[0], data[1]));
      case PIG -> spawn(player.getLocation(), id, new PigPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case PUFFERFISH -> spawn(player.getLocation(), id, new PufferFishPet(player, data[0]));
      case SALMON -> spawn(player.getLocation(), id, new SalmonPet(player, data[0]));
      case SKELETON_HORSE -> spawn(player.getLocation(), id, new SkeletonHorsePet(player, Boolean.parseBoolean(data[0]), Boolean.parseBoolean(data[1]), data[2]));
      case ZOMBIE_HORSE -> spawn(player.getLocation(), id, new ZombieHorsePet(player, Boolean.parseBoolean(data[0]), Boolean.parseBoolean(data[1]), data[2]));
      case TADPOLE -> spawn(player.getLocation(), id, new TadpolePet(player, data[0]));
      case TROPICAL_FISH -> spawn(player.getLocation(), id, new TropicalFishPet(player, data[0], data[1], data[2], data[3]));
      case WANDERING_TRADER -> spawn(player.getLocation(), id, new WanderingTraderPet(player, data[0]));
      case SPIDER -> spawn(player.getLocation(), id, new SpiderPet(player, data[0]));
      case LLAMA -> spawn(player.getLocation(), id, new LlamaPet(player, Boolean.parseBoolean(data[0]), Boolean.parseBoolean(data[1]), data[2], data[3], data[4]));
      case GOAT -> spawn(player.getLocation(), id, new GoatPet(player, Boolean.parseBoolean(data[0]), data[1], Boolean.parseBoolean(data[2])));
      case CAVE_SPIDER -> spawn(player.getLocation(), id, new CaveSpiderPet(player, data[0]));
      case PANDA -> spawn(player.getLocation(), id, new PandaPet(player, Boolean.parseBoolean(data[0]), data[1], data[2], data[3]));
      case PIGLIN -> spawn(player.getLocation(), id, new PiglinPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case ZOMBIFIED_PIGLIN -> spawn(player.getLocation(), id, new ZombifiedPiglinPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case POLAR_BEAR -> spawn(player.getLocation(), id, new PolarBearPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case HUSK -> spawn(player.getLocation(), id, new HuskPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case SILVERFISH -> spawn(player.getLocation(), id, new SilverfishPet(player, data[0]));
      case ENDERMITE -> spawn(player.getLocation(), id, new EndermitePet(player, data[0]));
      case PILLAGER -> spawn(player.getLocation(), id, new PillagerPet(player, data[0]));
      case ELDER_GUARDIAN -> spawn(player.getLocation(), id, new ElderGuardianPet(player, data[0]));
      case GUARDIAN -> spawn(player.getLocation(), id, new GuardianPet(player, data[0]));
      case VEX -> spawn(player.getLocation(), id, new VexPet(player, data[0]));
      case DROWNED -> spawn(player.getLocation(), id, new DrownedPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case HOGLIN -> spawn(player.getLocation(), id, new HoglinPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case PIGLIN_BRUTE -> spawn(player.getLocation(), id, new PiglinBrutePet(player, Boolean.parseBoolean(data[0]), data[1]));
      case SHULKER -> spawn(player.getLocation(), id, new ShulkerPet(player, data[0], data[1]));
      case STRAY -> spawn(player.getLocation(), id, new StrayPet(player, data[0]));
      case VINDICATOR -> spawn(player.getLocation(), id, new VindicatorPet(player, data[0]));
      case WITHER_SKELETON -> spawn(player.getLocation(), id, new WitherSkeletonPet(player, data[0]));
      case ZOMBIE_VILLAGER -> spawn(player.getLocation(), id, new ZombieVillagerPet(player, Boolean.parseBoolean(data[0]), data[1], data[2], data[3], data[4]));
      case EVOKER -> spawn(player.getLocation(), id, new EvokerPet(player, data[0]));
      case ILLUSIONER -> spawn(player.getLocation(), id, new IllusionerPet(player, data[0]));
      case TRADER_LLAMA -> spawn(player.getLocation(), id, new TraderLlamaPet(player, Boolean.parseBoolean(data[0]), Boolean.parseBoolean(data[1]), data[2], data[3], data[4]));
    }
  }

  private void spawn(Location location, int id, Entity entity) {
    final CraftEntity craftEntity = entity.getBukkitEntity();
    storePetMetaData(craftEntity, id);
    addToPetTracker(craftEntity.getUniqueId(), id);
    craftEntity.spawnAt(location, CreatureSpawnEvent.SpawnReason.CUSTOM);
  }

  private void addToPetTracker(UUID entity, int id) {
    petTracker.put(entity, id);
  }

  private void removeFromPetTracker(UUID entity) {
    petTracker.remove(entity);
  }

  private void storePetMetaData(CraftEntity entity, int id) {
    final NamespacedKey key = new NamespacedKey(pets, "pet");
    final PersistentDataContainer container = entity.getPersistentDataContainer();
    container.set(key, PersistentDataType.INTEGER, id);
  }

  public int getPetID(org.bukkit.entity.Entity entity) {
    final PersistentDataContainer container = entity.getPersistentDataContainer();
    final NamespacedKey key = new NamespacedKey(pets, "pet");
    return container.getOrDefault(key, PersistentDataType.INTEGER, 0);
  }

  public boolean isPet(org.bukkit.entity.Entity entity) {
    return getPetID(entity) != 0;
  }
}
