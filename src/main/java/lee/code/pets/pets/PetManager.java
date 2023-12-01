package lee.code.pets.pets;

import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import lee.code.pets.Pets;
import lee.code.pets.database.cache.CachePets;
import lee.code.pets.pets.pet.animal.*;
import lee.code.pets.pets.pet.animal.CatPet;
import lee.code.pets.pets.pet.fish.*;
import lee.code.pets.pets.pet.monster.*;
import lee.code.pets.utils.CoreUtil;
import lee.code.pets.utils.PetDataUtil;
import lee.code.pets.utils.PetEffects;
import lee.code.playerdata.PlayerDataAPI;
import net.minecraft.world.entity.Entity;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_20_R2.entity.CraftEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class PetManager  {
  private final Pets pets;
  private final ConcurrentHashMap<Integer, UUID> petTracker = new ConcurrentHashMap<>();
  private final ConcurrentHashMap<UUID, Integer> activePetTracker = new ConcurrentHashMap<>();
  private final ConcurrentHashMap<UUID, ScheduledTask> petEffectTasks = new ConcurrentHashMap<>();
  
  public PetManager(Pets pets) {
    this.pets = pets;
  }

  public void spawn(Player player, Location location, int id, EntityType entityType, String[] data) {
    switch (entityType) {
      case SHEEP -> spawn(player, location, id, new SheepPet(player, data));
      case CHICKEN -> spawn(player, location, id, new ChickenPet(player, data));
      case PHANTOM -> spawn(player, location, id, new PhantomPet(player, data));
      case WOLF -> spawn(player, location, id, new WolfPet(player,data));
      case CAT -> spawn(player, location, id, new CatPet(player, data));
      case VILLAGER -> spawn(player, location, id, new VillagerPet(player, data));
      case BEE -> spawn(player, location, id, new BeePet(player, data));
      case ZOMBIE -> spawn(player, location, id, new ZombiePet(player, data));
      case BLAZE -> spawn(player, location, id, new BlazePet(player, data));
      case SNIFFER -> spawn(player, location, id, new SnifferPet(player, data));
      case RABBIT -> spawn(player, location, id, new RabbitPet(player, data));
      case SLIME -> spawn(player, location, id, new SlimePet(player, data));
      case MAGMA_CUBE -> spawn(player, location, id, new MagmaCubePet(player, data));
      case RAVAGER -> spawn(player, location, id, new RavagerPet(player, data));
      case CREEPER -> spawn(player, location, id, new CreeperPet(player, data));
      case SKELETON -> spawn(player, location, id, new SkeletonPet(player, data));
      case HORSE -> spawn(player, location, id, new HorsePet(player, data));
      case COD -> spawn(player, location, id,  new CodPet(player, data));
      case DOLPHIN -> spawn(player, location, id, new DolphinPet(player, data));
      case GHAST -> spawn(player, location, id, new GhastPet(player, data));
      case BAT -> spawn(player, location, id, new BatPet(player, data));
      case ALLAY -> spawn(player, location, id, new AllayPet(player, data));
      case STRIDER -> spawn(player, location, id, new StriderPet(player, data));
      case SNOWMAN -> spawn(player, location, id, new SnowGolemPet(player, data));
      case IRON_GOLEM -> spawn(player, location, id, new IronGolemPet(player, data));
      case ZOGLIN -> spawn(player, location, id, new ZoglinPet(player, data));
      case TURTLE -> spawn(player, location, id, new TurtlePet(player, data));
      case AXOLOTL -> spawn(player, location, id, new AxolotlPet(player, data));
      case WITCH -> spawn(player, location, id, new WitchPet(player, data));
      case COW -> spawn(player, location, id, new CowPet(player, data));
      case MUSHROOM_COW -> spawn(player, location, id, new MushroomCowPet(player, data));
      case WARDEN -> spawn(player, location, id, new WardenPet(player, data));
      case WITHER -> spawn(player, location, id, new WitherPet(player, data));
      case ENDERMAN -> spawn(player, location, id, new EndermanPet(player, data));
      case CAMEL -> spawn(player, location, id, new CamelPet(player, data));
      case DONKEY -> spawn(player, location, id, new DonkeyPet(player, data));
      case FROG -> spawn(player, location, id, new FrogPet(player, data));
      case FOX -> spawn(player, location, id, new FoxPet(player, data));
      case SQUID -> spawn(player, location, id, new SquidPet(player, data));
      case GLOW_SQUID -> spawn(player, location, id, new GlowSquidPet(player, data));
      case OCELOT -> spawn(player, location, id, new OcelotPet(player, data));
      case PARROT -> spawn(player, location, id, new ParrotPet(player, data));
      case PIG -> spawn(player, location, id, new PigPet(player, data));
      case PUFFERFISH -> spawn(player, location, id, new PufferFishPet(player, data));
      case SALMON -> spawn(player, location, id, new SalmonPet(player, data));
      case SKELETON_HORSE -> spawn(player, location, id, new SkeletonHorsePet(player, data));
      case ZOMBIE_HORSE -> spawn(player, location, id, new ZombieHorsePet(player, data));
      case TADPOLE -> spawn(player, location, id, new TadpolePet(player, data));
      case TROPICAL_FISH -> spawn(player, location, id, new TropicalFishPet(player, data));
      case WANDERING_TRADER -> spawn(player, location, id, new WanderingTraderPet(player, data));
      case SPIDER -> spawn(player, location, id, new SpiderPet(player, data));
      case LLAMA -> spawn(player, location, id, new LlamaPet(player, data));
      case GOAT -> spawn(player, location, id, new GoatPet(player, data));
      case CAVE_SPIDER -> spawn(player, location, id, new CaveSpiderPet(player, data));
      case PANDA -> spawn(player, location, id, new PandaPet(player, data));
      case PIGLIN -> spawn(player, location, id, new PiglinPet(player, data));
      case ZOMBIFIED_PIGLIN -> spawn(player, location, id, new ZombifiedPiglinPet(player, data));
      case POLAR_BEAR -> spawn(player, location, id, new PolarBearPet(player, data));
      case HUSK -> spawn(player, location, id, new HuskPet(player, data));
      case SILVERFISH -> spawn(player, location, id, new SilverfishPet(player, data));
      case ENDERMITE -> spawn(player, location, id, new EndermitePet(player, data));
      case PILLAGER -> spawn(player, location, id, new PillagerPet(player, data));
      case ELDER_GUARDIAN -> spawn(player, location, id, new ElderGuardianPet(player, data));
      case GUARDIAN -> spawn(player, location, id, new GuardianPet(player, data));
      case VEX -> spawn(player, location, id, new VexPet(player, data));
      case DROWNED -> spawn(player, location, id, new DrownedPet(player, data));
      case HOGLIN -> spawn(player, location, id, new HoglinPet(player, data));
      case PIGLIN_BRUTE -> spawn(player, location, id, new PiglinBrutePet(player, data));
      case SHULKER -> spawn(player, location, id, new ShulkerPet(player, data));
      case STRAY -> spawn(player, location, id, new StrayPet(player, data));
      case VINDICATOR -> spawn(player, location, id, new VindicatorPet(player, data));
      case WITHER_SKELETON -> spawn(player, location, id, new WitherSkeletonPet(player, data));
      case ZOMBIE_VILLAGER -> spawn(player, location, id, new ZombieVillagerPet(player, data));
      case EVOKER -> spawn(player, location, id, new EvokerPet(player, data));
      case ILLUSIONER -> spawn(player, location, id, new IllusionerPet(player, data));
      case TRADER_LLAMA -> spawn(player, location, id, new TraderLlamaPet(player, data));
    }
  }

  private void spawn(Player player, Location location, int id, Entity entity) {
    final CraftEntity craftEntity = entity.getBukkitEntity();
    addToPetTracker(id, craftEntity.getUniqueId(), player.getUniqueId());
    craftEntity.spawnAt(location, CreatureSpawnEvent.SpawnReason.CUSTOM);
    startEffectTimer(player.getUniqueId(), craftEntity.getType(), id);
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

  public void respawnActivePet(Player player, Location location) {
    if (!hasActivePet(player.getUniqueId())) return;
    final int petID = getActivePetID(player.getUniqueId());
    final CachePets cachePets = pets.getCacheManager().getCachePets();
    removeActivePet(player);
    spawn(player, location, petID, cachePets.getPetEntityType(petID), cachePets.getPetData(petID));
  }

  public void removePet(Player player) {
    final int activePet = getActivePetID(player.getUniqueId());
    final org.bukkit.entity.Entity entity = player.getWorld().getEntity(getActivePetUUID(activePet));
    if (entity != null) entity.remove();
    removeFromPetTracker(activePet, player.getUniqueId());
    stopEffectTimer(player.getUniqueId());
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

  private void startEffectTimer(UUID uuid, EntityType entityType, int petID) {
    stopEffectTimer(uuid);
    if (!pets.getCacheManager().getCachePets().getPetEffect(petID)) return;
    petEffectTasks.put(uuid, Bukkit.getAsyncScheduler().runAtFixedRate(pets, scheduledTask -> {
      final Player player = PlayerDataAPI.getOnlinePlayer(uuid);
      if (player == null) {
        stopEffectTimer(uuid);
        return;
      }
      player.getScheduler().run(pets, task -> player.addPotionEffect(PetEffects.valueOf(entityType.name()).getPotionEffect()), null);
    }, 0, 3, TimeUnit.SECONDS));
  }

  public void stopEffectTimer(UUID uuid) {
    if (petEffectTasks.containsKey(uuid)) {
      petEffectTasks.get(uuid).cancel();
      petEffectTasks.remove(uuid);
    }
  }
}
