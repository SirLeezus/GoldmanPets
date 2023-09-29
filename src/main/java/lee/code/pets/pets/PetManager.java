package lee.code.pets.pets;

import lee.code.pets.Pets;
import lee.code.pets.pets.pet.animal.*;
import lee.code.pets.pets.pet.animal.CatPet;
import lee.code.pets.pets.pet.fish.*;
import lee.code.pets.pets.pet.monster.*;
import lee.code.pets.utils.CoreUtil;
import lee.code.pets.utils.PetDataUtil;
import net.minecraft.world.entity.Entity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PetManager  {
  private final Pets pets;
  private final ConcurrentHashMap<Integer, UUID> petTracker = new ConcurrentHashMap<>();
  private final ConcurrentHashMap<UUID, Integer> activePetTracker = new ConcurrentHashMap<>();
  
  public PetManager(Pets pets) {
    this.pets = pets;
  }

  public void spawn(Player player, int id, EntityType entityType, String[] data) {
    switch (entityType) {
      case SHEEP -> spawn(player, id, new SheepPet(player, data));
      case CHICKEN -> spawn(player, id, new ChickenPet(player, data));
      case PHANTOM -> spawn(player, id, new PhantomPet(player, data));
      case WOLF -> spawn(player, id, new WolfPet(player,data));
      case CAT -> spawn(player, id, new CatPet(player, data));
      case VILLAGER -> spawn(player, id, new VillagerPet(player, data));
      case BEE -> spawn(player, id, new BeePet(player, data));
      case ZOMBIE -> spawn(player, id, new ZombiePet(player, data));
      case BLAZE -> spawn(player, id, new BlazePet(player, data));
      case SNIFFER -> spawn(player, id, new SnifferPet(player, data));
      case RABBIT -> spawn(player, id, new RabbitPet(player, data));
      case SLIME -> spawn(player, id, new SlimePet(player, data));
      case MAGMA_CUBE -> spawn(player, id, new MagmaCubePet(player, data));
      case RAVAGER -> spawn(player, id, new RavagerPet(player, data));
      case CREEPER -> spawn(player, id, new CreeperPet(player, data));
      case SKELETON -> spawn(player, id, new SkeletonPet(player, data));
      case HORSE -> spawn(player, id, new HorsePet(player, data));
      case COD -> spawn(player, id,  new CodPet(player, data));
      case DOLPHIN -> spawn(player, id, new DolphinPet(player, data));
      case GHAST -> spawn(player, id, new GhastPet(player, data));
      case BAT -> spawn(player, id, new BatPet(player, data));
      case ALLAY -> spawn(player, id, new AllayPet(player, data));
      case STRIDER -> spawn(player, id, new StriderPet(player, data));
      case SNOWMAN -> spawn(player, id, new SnowGolemPet(player, data));
      case IRON_GOLEM -> spawn(player, id, new IronGolemPet(player, data));
      case ZOGLIN -> spawn(player, id, new ZoglinPet(player, data));
      case TURTLE -> spawn(player, id, new TurtlePet(player, data));
      case AXOLOTL -> spawn(player, id, new AxolotlPet(player, data));
      case WITCH -> spawn(player, id, new WitchPet(player, data));
      case COW -> spawn(player, id, new CowPet(player, data));
      case MUSHROOM_COW -> spawn(player, id, new MushroomCowPet(player, data));
      case WARDEN -> spawn(player, id, new WardenPet(player, data));
      case WITHER -> spawn(player, id, new WitherPet(player, data));
      case ENDERMAN -> spawn(player, id, new EndermanPet(player, data));
      case CAMEL -> spawn(player, id, new CamelPet(player, data));
      case DONKEY -> spawn(player, id, new DonkeyPet(player, data));
      case FROG -> spawn(player, id, new FrogPet(player, data));
      case FOX -> spawn(player, id, new FoxPet(player, data));
      case SQUID -> spawn(player, id, new SquidPet(player, data));
      case GLOW_SQUID -> spawn(player, id, new GlowSquidPet(player, data));
      case OCELOT -> spawn(player, id, new OcelotPet(player, data));
      case PARROT -> spawn(player, id, new ParrotPet(player, data));
      case PIG -> spawn(player, id, new PigPet(player, data));
      case PUFFERFISH -> spawn(player, id, new PufferFishPet(player, data));
      case SALMON -> spawn(player, id, new SalmonPet(player, data));
      case SKELETON_HORSE -> spawn(player, id, new SkeletonHorsePet(player, data));
      case ZOMBIE_HORSE -> spawn(player, id, new ZombieHorsePet(player, data));
      case TADPOLE -> spawn(player, id, new TadpolePet(player, data));
      case TROPICAL_FISH -> spawn(player, id, new TropicalFishPet(player, data));
      case WANDERING_TRADER -> spawn(player, id, new WanderingTraderPet(player, data));
      case SPIDER -> spawn(player, id, new SpiderPet(player, data));
      case LLAMA -> spawn(player, id, new LlamaPet(player, data));
      case GOAT -> spawn(player, id, new GoatPet(player, data));
      case CAVE_SPIDER -> spawn(player, id, new CaveSpiderPet(player, data));
      case PANDA -> spawn(player, id, new PandaPet(player, data));
      case PIGLIN -> spawn(player, id, new PiglinPet(player, data));
      case ZOMBIFIED_PIGLIN -> spawn(player, id, new ZombifiedPiglinPet(player, data));
      case POLAR_BEAR -> spawn(player, id, new PolarBearPet(player, data));
      case HUSK -> spawn(player, id, new HuskPet(player, data));
      case SILVERFISH -> spawn(player, id, new SilverfishPet(player, data));
      case ENDERMITE -> spawn(player, id, new EndermitePet(player, data));
      case PILLAGER -> spawn(player, id, new PillagerPet(player, data));
      case ELDER_GUARDIAN -> spawn(player, id, new ElderGuardianPet(player, data));
      case GUARDIAN -> spawn(player, id, new GuardianPet(player, data));
      case VEX -> spawn(player, id, new VexPet(player, data));
      case DROWNED -> spawn(player, id, new DrownedPet(player, data));
      case HOGLIN -> spawn(player, id, new HoglinPet(player, data));
      case PIGLIN_BRUTE -> spawn(player, id, new PiglinBrutePet(player, data));
      case SHULKER -> spawn(player, id, new ShulkerPet(player, data));
      case STRAY -> spawn(player, id, new StrayPet(player, data));
      case VINDICATOR -> spawn(player, id, new VindicatorPet(player, data));
      case WITHER_SKELETON -> spawn(player, id, new WitherSkeletonPet(player, data));
      case ZOMBIE_VILLAGER -> spawn(player, id, new ZombieVillagerPet(player, data));
      case EVOKER -> spawn(player, id, new EvokerPet(player, data));
      case ILLUSIONER -> spawn(player, id, new IllusionerPet(player, data));
      case TRADER_LLAMA -> spawn(player, id, new TraderLlamaPet(player, data));
    }
  }

  private void spawn(Player player, int id, Entity entity) {
    final CraftEntity craftEntity = entity.getBukkitEntity();
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

  public boolean isPet(org.bukkit.entity.Entity entity) {
    return petTracker.containsValue(entity.getUniqueId());
  }

  public void removeActivePet(Player player) {
    if (hasActivePet(player.getUniqueId())) removePet(player);
  }

  public void removePet(Player player) {
    final int activePet = getActivePetID(player.getUniqueId());
    final org.bukkit.entity.Entity entity = player.getWorld().getEntity(getActivePetUUID(activePet));
    if (entity != null) entity.remove();
    removeFromPetTracker(activePet, player.getUniqueId());
  }

  public void capturePet(Player player, org.bukkit.entity.Entity entity) {
    pets.getCacheManager().getCachePets().createNewPet(player.getUniqueId(), PetDataUtil.serializePetData(entity));
    entity.remove();
  }

  public boolean isPetOwner(UUID uuid, org.bukkit.entity.Entity entity) {
    if (!activePetTracker.containsKey(uuid)) return false;
    return getActivePetUUID(getActivePetID(uuid)).equals(entity.getUniqueId());
  }

  public int getMaxPets(Player player) {
    if (player.isOp()) return 100;
    return CoreUtil.getHighestPermission(player, "pets.max.", 100);
  }

  public boolean canCaptureNewPet(Player player) {
    final int count = pets.getCacheManager().getCachePets().getPlayerPetData().getPetCount(player.getUniqueId());
    return count + 1 <= getMaxPets(player);
  }
}
