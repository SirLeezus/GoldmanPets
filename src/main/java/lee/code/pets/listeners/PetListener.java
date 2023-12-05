package lee.code.pets.listeners;

import lee.code.pets.Pets;
import lee.code.pets.enums.PettingSound;
import lee.code.pets.events.RideEvent;
import lee.code.pets.lang.Lang;
import lee.code.pets.pets.PetManager;
import lee.code.pets.utils.CoreUtil;
import org.bukkit.*;
import org.bukkit.entity.Camel;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;
import org.spigotmc.event.entity.EntityDismountEvent;

public class PetListener implements Listener {
  private final Pets pets;

  public PetListener(Pets pets) {
    this.pets = pets;
  }

  @EventHandler
  public void onRidePet(RideEvent e) {
    final Entity entity = e.getEntity();
    final Player player = e.getPlayer();
    if (pets.getDelayManager().isOnDelayOrSchedule(player.getUniqueId(), 1000)) return;
    final ItemStack handItem = player.getInventory().getItemInMainHand();
    final ItemMeta itemMeta = handItem.getItemMeta();
    if (itemMeta != null) {
      if (itemMeta.hasCustomModelData() && itemMeta.getCustomModelData() == 1) return;
    }
    if (handItem.getType().isAir() && player.isSneaking()) {
      final Sound petSound = PettingSound.valueOf(entity.getType().name()).getSound();
      final BoundingBox boundingBox = entity.getBoundingBox();
      final Location headLocation = boundingBox.getCenter().toLocation(entity.getWorld()).add(new Vector(0, boundingBox.getHeight() / 2, 0));
      CoreUtil.spawnLoveAroundLocation(headLocation);
      player.getWorld().playSound(entity, petSound, (float) 1, (float) 1);
      return;
    }
    if (!pets.getPetManager().isPetOwner(player.getUniqueId(), entity)) {
      if (entity instanceof Camel camel) {
        if (camel.getPassengers().size() > 1 || camel.getPassengers().size() == 0) return;
      } else return;
    }
    entity.addPassenger(player);
  }

  @EventHandler
  public void onPlayerPetInteractAtEntity(PlayerInteractAtEntityEvent e) {
    if (!pets.getPetManager().isPet(e.getRightClicked())) return;
    e.setCancelled(true);
    Bukkit.getServer().getPluginManager().callEvent(new RideEvent(e.getPlayer(), e.getRightClicked()));
  }

  @EventHandler
  public void onPlayerInteractWithPet(PlayerInteractEntityEvent e) {
    if (!pets.getPetManager().isPet(e.getRightClicked())) return;
    e.setCancelled(true);
    Bukkit.getServer().getPluginManager().callEvent(new RideEvent(e.getPlayer(), e.getRightClicked()));
  }

  @EventHandler
  public void onPlayerPetDismount(EntityDismountEvent e) {
    if (!pets.getPetManager().isPet(e.getDismounted())) return;
    if (!(e.getEntity() instanceof Player player)) return;
    if (!(e.getDismounted() instanceof Camel)) return;
    if (!pets.getPetManager().isPetOwner(player.getUniqueId(), e.getDismounted())) return;
    for (Entity entity : e.getDismounted().getPassengers()) {
      if (entity != player) e.getDismounted().removePassenger(entity);
    }
  }

  @EventHandler (priority = EventPriority.MONITOR)
  public void onDamageWithLead(EntityDamageByEntityEvent e) {
    if (e.isCancelled()) return;
    final PetManager petManager = pets.getPetManager();
    if (!(e.getDamager() instanceof Player player)) return;
    if (e.getEntity() instanceof Player) return;
    if (petManager.isPet(e.getEntity())) return;
    final ItemStack handItem = player.getInventory().getItemInMainHand();
    final ItemMeta itemMeta = handItem.getItemMeta();
    if (itemMeta == null) return;
    if (!itemMeta.hasCustomModelData() || itemMeta.getCustomModelData() != 1) return;
    final int[] health = CoreUtil.getEntityHealth(e.getEntity());
    if (health == null) return;
    CoreUtil.sendHealthProgress(player, health[0], health[1]);
  }

  @EventHandler (priority = EventPriority.MONITOR)
  public void onCapturePet(PlayerInteractEntityEvent e) {
    if (e.isCancelled()) return;
    if (pets.getPetManager().isPet(e.getRightClicked())) return;
    final Player player = e.getPlayer();
    final ItemStack handItem = player.getInventory().getItemInMainHand();
    final ItemMeta itemMeta = handItem.getItemMeta();
    if (itemMeta == null) return;
    if (!itemMeta.hasCustomModelData() || itemMeta.getCustomModelData() != 1) return;
    e.setCancelled(true);
    final Entity entity = e.getRightClicked();
    if (pets.getDelayManager().isOnDelayOrSchedule(player.getUniqueId(), 500)) return;
    if (!pets.getPetManager().canCaptureNewPet(player)) {
      player.sendMessage(Lang.PREFIX.getComponent(null).append(Lang.ERROR_MAX_PETS.getComponent(new String[]{String.valueOf(pets.getPetManager().getMaxPets(player))})));
      return;
    }
    if (!pets.getPetManager().getSupportedPets().contains(e.getRightClicked().getType())) {
      player.sendActionBar(Lang.ERROR_ENTITY_NOT_SUPPORTED.getComponent(new String[]{CoreUtil.capitalize(e.getRightClicked().getType().name())}));
      return;
    }
    final int[] health = CoreUtil.getEntityHealth(entity);
    if (health == null) return;
    final double threshold = health[1] * 0.3;
    CoreUtil.sendHealthProgress(player, health[0], health[1]);
    if (health[0] <= threshold || player.getGameMode().equals(GameMode.CREATIVE)) {
      entity.getWorld().playEffect(entity.getLocation(), Effect.ENDER_SIGNAL, 1);
      entity.getWorld().playSound(entity.getBoundingBox().getCenter().toLocation(entity.getWorld()), Sound.ENTITY_ENDERMAN_TELEPORT, (float) 1, (float) 1);
      pets.getPetManager().capturePet(player, entity);
      player.sendMessage(Lang.PREFIX.getComponent(null).append(Lang.CAPTURE_SUCCESSFUL.getComponent(new String[]{CoreUtil.capitalize(entity.getType().name())})));
    }
  }

  @EventHandler
  public void onPetDamage(EntityDamageEvent e) {
    if (!pets.getPetManager().isPet(e.getEntity())) return;
    e.setCancelled(true);
  }

  @EventHandler (priority = EventPriority.MONITOR)
  public void onTeleportWithPetActive(PlayerTeleportEvent e) {
    if (e.isCancelled()) return;
    pets.getPetManager().respawnActivePet(e.getPlayer(), e.getTo());
  }

  @EventHandler
  public void onDeathWithPet(PlayerDeathEvent e) {
    pets.getPetManager().removeActivePet(e.getPlayer());
  }

  @EventHandler
  public void onQuitPetActive(PlayerQuitEvent e) {
    pets.getPetManager().removeActivePet(e.getPlayer());
  }

  @EventHandler (priority = EventPriority.MONITOR)
  public void onPlayerPortal(PlayerPortalEvent e) {
    if (e.isCancelled()) return;
    pets.getPetManager().removeActivePet(e.getPlayer());
  }

  @EventHandler (priority = EventPriority.MONITOR)
  public void onEntityPortal(EntityPortalEvent e) {
    if (e.isCancelled()) return;
    if (pets.getPetManager().isPet(e.getEntity())) e.setCancelled(true);
  }

  @EventHandler
  public void onDamagePlayerOnPet(EntityDamageByEntityEvent e) {
    if (e.getDamager() instanceof Player attacker && e.getEntity() instanceof Player victim) {
      if (attacker.getVehicle() != null) {
        if (pets.getPetManager().isPet(attacker.getVehicle())) e.setCancelled(true);
      }
      if (victim.getVehicle() != null) {
        if (pets.getPetManager().isPet(victim.getVehicle())) e.setCancelled(true);
      }
    }
  }

  @EventHandler
  public void onShootPlayerOnPet(ProjectileHitEvent e) {
    if (e.getEntity().getShooter() instanceof Player attacker && e.getHitEntity() instanceof Player victim) {
      if (attacker.getVehicle() != null) {
        if (pets.getPetManager().isPet(attacker.getVehicle())) e.setCancelled(true);
      }
      if (victim.getVehicle() != null) {
        if (pets.getPetManager().isPet(victim.getVehicle())) e.setCancelled(true);
      }
    }
  }
}
