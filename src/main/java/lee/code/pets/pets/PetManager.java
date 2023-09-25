package lee.code.pets.pets;

import lee.code.pets.Pets;
import lee.code.pets.pets.pet.animal.*;
import lee.code.pets.pets.pet.animal.CatPet;
import lee.code.pets.pets.pet.fish.*;
import lee.code.pets.pets.pet.monster.*;
import lee.code.pets.utils.PetDataUtil;
import net.minecraft.world.entity.Entity;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.UUID;

public class PetManager  {
  private final Pets pets;
  private final HashMap<Integer, UUID> petTracker = new HashMap<>();
  private final HashMap<UUID, Integer> activePetTracker = new HashMap<>();
  
  public PetManager(Pets pets) {
    this.pets = pets;
  }

  public void spawn(Player player, int id, EntityType entityType, String[] data) {
    switch (entityType) {
      case SHEEP -> spawn(player, id, new SheepPet(player, Boolean.parseBoolean(data[0]), data[1], data[2]));
      case CHICKEN -> spawn(player, id, new ChickenPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case PHANTOM -> spawn(player, id, new PhantomPet(player, data[0]));
      case WOLF -> spawn(player, id, new WolfPet(player, Boolean.parseBoolean(data[0]), data[1], data[2]));
      case CAT -> spawn(player, id, new CatPet(player, Boolean.parseBoolean(data[0]), data[1], data[2], data[3]));
      case VILLAGER -> spawn(player, id, new VillagerPet(player, Boolean.parseBoolean(data[0]), data[1], data[2], data[3], data[4]));
      case BEE -> spawn(player, id, new BeePet(player, Boolean.parseBoolean(data[0]), data[1]));
      case ZOMBIE -> spawn(player, id, new ZombiePet(player, Boolean.parseBoolean(data[0]), data[1]));
      case BLAZE -> spawn(player, id, new BlazePet(player, data[0]));
      case SNIFFER -> spawn(player, id, new SnifferPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case RABBIT -> spawn(player, id, new RabbitPet(player, Boolean.parseBoolean(data[0]), data[1], data[2]));
      case SLIME -> spawn(player, id, new SlimePet(player, data[0], data[1]));
      case MAGMA_CUBE -> spawn(player, id, new MagmaCubePet(player, data[0], data[1]));
      case RAVAGER -> spawn(player, id, new RavagerPet(player, data[0]));
      case CREEPER -> spawn(player, id, new CreeperPet(player, data[0], Boolean.parseBoolean(data[1])));
      case SKELETON -> spawn(player, id, new SkeletonPet(player, data[0]));
      case HORSE -> spawn(player, id, new HorsePet(player, Boolean.parseBoolean(data[0]), Boolean.parseBoolean(data[1]), data[2], data[3], data[4]));
      case COD -> spawn(player, id,  new CodPet(player, data[0]));
      case DOLPHIN -> spawn(player, id, new DolphinPet(player, data[0]));
      case GHAST -> spawn(player, id, new GhastPet(player, data[0]));
      case BAT -> spawn(player, id, new BatPet(player, data[0]));
      case ALLAY -> spawn(player, id, new AllayPet(player, data[0]));
      case STRIDER -> spawn(player, id, new StriderPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case SNOWMAN -> spawn(player, id, new SnowGolemPet(player, data[0], Boolean.parseBoolean(data[1])));
      case IRON_GOLEM -> spawn(player, id, new IronGolemPet(player, data[0]));
      case ZOGLIN -> spawn(player, id, new ZoglinPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case TURTLE -> spawn(player, id, new TurtlePet(player, Boolean.parseBoolean(data[0]), data[1]));
      case AXOLOTL -> spawn(player, id, new AxolotlPet(player, Boolean.parseBoolean(data[0]), data[1], data[2]));
      case WITCH -> spawn(player, id, new WitchPet(player, data[0]));
      case COW -> spawn(player, id, new CowPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case MUSHROOM_COW -> spawn(player, id, new MushroomCowPet(player, Boolean.parseBoolean(data[0]), data[1], data[2]));
      case WARDEN -> spawn(player, id, new WardenPet(player, data[0]));
      case WITHER -> spawn(player, id, new WitherPet(player, data[0]));
      case ENDERMAN -> spawn(player, id, new EndermanPet(player, data[0]));
      case CAMEL -> spawn(player, id, new CamelPet(player, Boolean.parseBoolean(data[0]), Boolean.parseBoolean(data[1]), data[2]));
      case DONKEY -> spawn(player, id, new DonkeyPet(player, Boolean.parseBoolean(data[0]), data[1], Boolean.parseBoolean(data[2])));
      case FROG -> spawn(player, id, new FrogPet(player, data[0], data[1]));
      case FOX -> spawn(player, id, new FoxPet(player, Boolean.parseBoolean(data[0]), data[1], data[2]));
      case SQUID -> spawn(player, id, new SquidPet(player, data[0]));
      case GLOW_SQUID -> spawn(player, id, new GlowSquidPet(player, data[0]));
      case OCELOT -> spawn(player, id, new OcelotPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case PARROT -> spawn(player, id, new ParrotPet(player, data[0], data[1]));
      case PIG -> spawn(player, id, new PigPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case PUFFERFISH -> spawn(player, id, new PufferFishPet(player, data[0]));
      case SALMON -> spawn(player, id, new SalmonPet(player, data[0]));
      case SKELETON_HORSE -> spawn(player, id, new SkeletonHorsePet(player, Boolean.parseBoolean(data[0]), Boolean.parseBoolean(data[1]), data[2]));
      case ZOMBIE_HORSE -> spawn(player, id, new ZombieHorsePet(player, Boolean.parseBoolean(data[0]), Boolean.parseBoolean(data[1]), data[2]));
      case TADPOLE -> spawn(player, id, new TadpolePet(player, data[0]));
      case TROPICAL_FISH -> spawn(player, id, new TropicalFishPet(player, data[0], data[1], data[2], data[3]));
      case WANDERING_TRADER -> spawn(player, id, new WanderingTraderPet(player, data[0]));
      case SPIDER -> spawn(player, id, new SpiderPet(player, data[0]));
      case LLAMA -> spawn(player, id, new LlamaPet(player, Boolean.parseBoolean(data[0]), Boolean.parseBoolean(data[1]), data[2], data[3], data[4]));
      case GOAT -> spawn(player, id, new GoatPet(player, Boolean.parseBoolean(data[0]), data[1], Boolean.parseBoolean(data[2])));
      case CAVE_SPIDER -> spawn(player, id, new CaveSpiderPet(player, data[0]));
      case PANDA -> spawn(player, id, new PandaPet(player, Boolean.parseBoolean(data[0]), data[1], data[2], data[3]));
      case PIGLIN -> spawn(player, id, new PiglinPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case ZOMBIFIED_PIGLIN -> spawn(player, id, new ZombifiedPiglinPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case POLAR_BEAR -> spawn(player, id, new PolarBearPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case HUSK -> spawn(player, id, new HuskPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case SILVERFISH -> spawn(player, id, new SilverfishPet(player, data[0]));
      case ENDERMITE -> spawn(player, id, new EndermitePet(player, data[0]));
      case PILLAGER -> spawn(player, id, new PillagerPet(player, data[0]));
      case ELDER_GUARDIAN -> spawn(player, id, new ElderGuardianPet(player, data[0]));
      case GUARDIAN -> spawn(player, id, new GuardianPet(player, data[0]));
      case VEX -> spawn(player, id, new VexPet(player, data[0]));
      case DROWNED -> spawn(player, id, new DrownedPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case HOGLIN -> spawn(player, id, new HoglinPet(player, Boolean.parseBoolean(data[0]), data[1]));
      case PIGLIN_BRUTE -> spawn(player, id, new PiglinBrutePet(player, Boolean.parseBoolean(data[0]), data[1]));
      case SHULKER -> spawn(player, id, new ShulkerPet(player, data[0], data[1]));
      case STRAY -> spawn(player, id, new StrayPet(player, data[0]));
      case VINDICATOR -> spawn(player, id, new VindicatorPet(player, data[0]));
      case WITHER_SKELETON -> spawn(player, id, new WitherSkeletonPet(player, data[0]));
      case ZOMBIE_VILLAGER -> spawn(player, id, new ZombieVillagerPet(player, Boolean.parseBoolean(data[0]), data[1], data[2], data[3], data[4]));
      case EVOKER -> spawn(player, id, new EvokerPet(player, data[0]));
      case ILLUSIONER -> spawn(player, id, new IllusionerPet(player, data[0]));
      case TRADER_LLAMA -> spawn(player, id, new TraderLlamaPet(player, Boolean.parseBoolean(data[0]), Boolean.parseBoolean(data[1]), data[2], data[3], data[4]));
    }
  }

  private void spawn(Player player, int id, Entity entity) {
    final CraftEntity craftEntity = entity.getBukkitEntity();
    storePetMetaData(craftEntity, id);
    addToPetTracker(id, craftEntity.getUniqueId(), player.getUniqueId());
    craftEntity.spawnAt(player.getLocation(), CreatureSpawnEvent.SpawnReason.CUSTOM);
  }

  private void addToPetTracker(int id, UUID entity, UUID player) {
    petTracker.put(id, entity);
    activePetTracker.put(player, id);
  }

  private void removeFromPetTracker(int id, UUID player) {
    petTracker.remove(id);
    activePetTracker.remove(player);
  }

  public int getActivePetID(UUID uuid) {
    return activePetTracker.get(uuid);
  }

  private UUID getActivePetUUID(int id) {
    return petTracker.get(id);
  }

  public boolean hasActivePet(UUID uuid) {
    return activePetTracker.containsKey(uuid);
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
    return petTracker.containsValue(entity.getUniqueId());
  }

  public void removePet(Player player, int id) {
    final org.bukkit.entity.Entity entity = player.getWorld().getEntity(getActivePetUUID(id));
    if (entity != null) entity.getScheduler().run(pets, task -> entity.remove(), null);
    removeFromPetTracker(id, player.getUniqueId());
  }

  public void capturePet(Player player, org.bukkit.entity.Entity entity) {
    pets.getCacheManager().getCachePets().createNewPet(player.getUniqueId(), PetDataUtil.serializePetData(entity));
    entity.getScheduler().run(pets, task -> entity.remove(), null);
  }
}
