package lee.code.pets.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.EntityType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

@AllArgsConstructor
public enum PetEffects {
  COW(EntityType.COW, new PotionEffect(PotionEffectType.LUCK, 20 * 20, 0), PotionType.LUCK),
  SHEEP(EntityType.SHEEP, new PotionEffect(PotionEffectType.NIGHT_VISION, 20 * 20, 0), PotionType.NIGHT_VISION),
  ALLAY(EntityType.ALLAY, new PotionEffect(PotionEffectType.REGENERATION, 20 * 20, 1), PotionType.REGEN),
  AXOLOTL(EntityType.AXOLOTL, new PotionEffect(PotionEffectType.REGENERATION, 20 * 20, 0), PotionType.REGEN),
  BAT(EntityType.BAT, new PotionEffect(PotionEffectType.NIGHT_VISION, 20 * 20, 0), PotionType.NIGHT_VISION),
  CAT(EntityType.CAT, new PotionEffect(PotionEffectType.SPEED, 20 * 20, 0), PotionType.SPEED),
  CAMEL(EntityType.CAMEL, new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 20, 0), PotionType.LONG_STRENGTH),
  CHICKEN(EntityType.CHICKEN, new PotionEffect(PotionEffectType.SLOW_FALLING, 20 * 20, 0), PotionType.SLOW_FALLING),
  COD(EntityType.COD, new PotionEffect(PotionEffectType.WATER_BREATHING, 20 * 20, 0), PotionType.WATER_BREATHING),
  DONKEY(EntityType.DONKEY, new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 20, 0), PotionType.INSTANT_DAMAGE),
  FOX(EntityType.FOX, new PotionEffect(PotionEffectType.JUMP, 20 * 20, 0), PotionType.JUMP),
  FROG(EntityType.FROG, new PotionEffect(PotionEffectType.JUMP, 20 * 20, 0), PotionType.JUMP),
  GLOW_SQUID(EntityType.GLOW_SQUID, new PotionEffect(PotionEffectType.NIGHT_VISION, 20 * 20, 0), PotionType.NIGHT_VISION),
  HORSE(EntityType.HORSE, new PotionEffect(PotionEffectType.SPEED, 20 * 20, 1), PotionType.SPEED),
  MUSHROOM_COW(EntityType.MUSHROOM_COW, new PotionEffect(PotionEffectType.REGENERATION, 20 * 20, 1), PotionType.REGEN),
  MULE(EntityType.MULE, new PotionEffect(PotionEffectType.JUMP, 20 * 20, 0), PotionType.JUMP),
  OCELOT(EntityType.OCELOT, new PotionEffect(PotionEffectType.SLOW_FALLING, 20 * 20, 0), PotionType.SLOW_FALLING),
  PARROT(EntityType.PARROT, new PotionEffect(PotionEffectType.HEALTH_BOOST, 20 * 20, 1), PotionType.INSTANT_HEAL),
  PIG(EntityType.PIG, new PotionEffect(PotionEffectType.REGENERATION, 20 * 20, 0), PotionType.REGEN),
  PUFFERFISH(EntityType.PUFFERFISH, new PotionEffect(PotionEffectType.WATER_BREATHING, 20 * 20, 0), PotionType.WATER_BREATHING),
  RABBIT(EntityType.RABBIT, new PotionEffect(PotionEffectType.JUMP, 20 * 20, 1), PotionType.JUMP),
  SALMON(EntityType.SALMON, new PotionEffect(PotionEffectType.WATER_BREATHING, 20 * 20, 1), PotionType.WATER_BREATHING),
  SNIFFER(EntityType.SNIFFER, new PotionEffect(PotionEffectType.FAST_DIGGING, 20 * 20, 1), PotionType.SPEED),
  SNOWMAN(EntityType.SNOWMAN, new PotionEffect(PotionEffectType.SLOW_FALLING, 20 * 20, 0), PotionType.SLOW_FALLING),
  SQUID(EntityType.SQUID, new PotionEffect(PotionEffectType.WATER_BREATHING, 20 * 20, 0), PotionType.WATER_BREATHING),
  STRIDER(EntityType.STRIDER, new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20 * 20, 0), PotionType.FIRE_RESISTANCE),
  TADPOLE(EntityType.TADPOLE, new PotionEffect(PotionEffectType.LUCK, 20 * 20, 0), PotionType.LUCK),
  TROPICAL_FISH(EntityType.TROPICAL_FISH, new PotionEffect(PotionEffectType.WATER_BREATHING, 20 * 20, 0), PotionType.WATER_BREATHING),
  TURTLE(EntityType.TURTLE, new PotionEffect(PotionEffectType.WATER_BREATHING, 20 * 20, 0), PotionType.WATER_BREATHING),
  VILLAGER(EntityType.VILLAGER, new PotionEffect(PotionEffectType.HEAL, 20 * 20, 1), PotionType.INSTANT_HEAL),
  WANDERING_TRADER(EntityType.WANDERING_TRADER, new PotionEffect(PotionEffectType.INVISIBILITY, 20 * 20, 0), PotionType.INVISIBILITY),
  CAVE_SPIDER(EntityType.CAVE_SPIDER, new PotionEffect(PotionEffectType.NIGHT_VISION, 20 * 20, 0), PotionType.NIGHT_VISION),
  DOLPHIN(EntityType.DOLPHIN, new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 20 * 20, 0), PotionType.WATER_BREATHING),
  ENDERMAN(EntityType.ENDERMAN, new PotionEffect(PotionEffectType.NIGHT_VISION, 20 * 20, 0), PotionType.NIGHT_VISION),
  GOAT(EntityType.GOAT, new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 20, 0), PotionType.INSTANT_DAMAGE),
  IRON_GOLEM(EntityType.IRON_GOLEM, new PotionEffect(PotionEffectType.HEALTH_BOOST, 20 * 20, 1), PotionType.INSTANT_HEAL),
  LLAMA(EntityType.LLAMA, new PotionEffect(PotionEffectType.REGENERATION, 20 * 20, 0), PotionType.REGEN),
  PANDA(EntityType.PANDA, new PotionEffect(PotionEffectType.HEAL, 20 * 20, 0), PotionType.INSTANT_HEAL),
  PIGLIN(EntityType.PIGLIN, new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 20, 0), PotionType.INSTANT_DAMAGE),
  PIGLIN_BRUTE(EntityType.PIGLIN_BRUTE, new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 20, 0), PotionType.INSTANT_DAMAGE),
  ZOMBIFIED_PIGLIN(EntityType.ZOMBIFIED_PIGLIN, new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 20, 0), PotionType.INSTANT_DAMAGE),
  POLAR_BEAR(EntityType.POLAR_BEAR, new PotionEffect(PotionEffectType.REGENERATION, 20 * 20, 0), PotionType.REGEN),
  SKELETON_HORSE(EntityType.SKELETON_HORSE, new PotionEffect(PotionEffectType.SPEED, 20 * 20, 0), PotionType.SPEED),
  SPIDER(EntityType.SPIDER, new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20 * 20, 1), PotionType.FIRE_RESISTANCE),
  TRADER_LLAMA(EntityType.TRADER_LLAMA, new PotionEffect(PotionEffectType.LUCK, 20 * 20, 0), PotionType.LUCK),
  WOLF(EntityType.WOLF, new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 20, 0), PotionType.INSTANT_DAMAGE),
  CREEPER(EntityType.CREEPER, new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 20, 1), PotionType.INSTANT_DAMAGE),
  DROWNED(EntityType.DROWNED, new PotionEffect(PotionEffectType.WATER_BREATHING, 20 * 20, 0), PotionType.WATER_BREATHING),
  ELDER_GUARDIAN(EntityType.ELDER_GUARDIAN, new PotionEffect(PotionEffectType.CONDUIT_POWER, 20 * 20, 1), PotionType.WATER_BREATHING),
  ENDERMITE(EntityType.ENDERMITE, new PotionEffect(PotionEffectType.SPEED, 20 * 20, 0), PotionType.SPEED),
  ILLUSIONER(EntityType.ILLUSIONER, new PotionEffect(PotionEffectType.INVISIBILITY, 20 * 20, 0), PotionType.INVISIBILITY),
  WITHER(EntityType.WITHER, new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 20, 1), PotionType.INSTANT_DAMAGE),
  BLAZE(EntityType.BLAZE, new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20 * 20, 1), PotionType.FIRE_RESISTANCE),
  EVOKER(EntityType.EVOKER, new PotionEffect(PotionEffectType.HEAL, 20 * 20, 1), PotionType.INSTANT_HEAL),
  GHAST(EntityType.GHAST, new PotionEffect(PotionEffectType.SLOW_FALLING, 20 * 20, 0), PotionType.SLOW_FALLING),
  GUARDIAN(EntityType.GUARDIAN, new PotionEffect(PotionEffectType.WATER_BREATHING, 20 * 20, 0), PotionType.WATER_BREATHING),
  HOGLIN(EntityType.HOGLIN, new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20 * 20, 1), PotionType.FIRE_RESISTANCE),
  ZOGLIN(EntityType.ZOGLIN, new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 20, 1), PotionType.INSTANT_DAMAGE),
  HUSK(EntityType.HUSK, new PotionEffect(PotionEffectType.REGENERATION, 20 * 20, 1), PotionType.REGEN),
  MAGMA_CUBE(EntityType.MAGMA_CUBE, new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20 * 20, 1), PotionType.FIRE_RESISTANCE),
  PHANTOM(EntityType.PHANTOM, new PotionEffect(PotionEffectType.SLOW_FALLING, 20 * 20, 0), PotionType.SLOW_FALLING),
  PILLAGER(EntityType.PILLAGER, new PotionEffect(PotionEffectType.REGENERATION, 20 * 20, 1), PotionType.REGEN),
  RAVAGER(EntityType.RAVAGER, new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 20, 2), PotionType.INSTANT_DAMAGE),
  SHULKER(EntityType.SHULKER, new PotionEffect(PotionEffectType.LEVITATION, 20 * 20, 1), PotionType.JUMP),
  SILVERFISH(EntityType.SILVERFISH, new PotionEffect(PotionEffectType.SPEED, 20 * 20, 0), PotionType.SPEED),
  SKELETON(EntityType.SKELETON, new PotionEffect(PotionEffectType.SPEED, 20 * 20, 0), PotionType.SPEED),
  SLIME(EntityType.SLIME, new PotionEffect(PotionEffectType.JUMP, 20 * 20, 1), PotionType.JUMP),
  STRAY(EntityType.STRAY, new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 20, 1), PotionType.INSTANT_DAMAGE),
  VEX(EntityType.VEX, new PotionEffect(PotionEffectType.REGENERATION, 20 * 20, 1), PotionType.REGEN),
  VINDICATOR(EntityType.VINDICATOR, new PotionEffect(PotionEffectType.NIGHT_VISION, 20 * 20, 0), PotionType.NIGHT_VISION),
  WARDEN(EntityType.WARDEN, new PotionEffect(PotionEffectType.HEALTH_BOOST, 20 * 20, 2), PotionType.INSTANT_HEAL),
  WITCH(EntityType.WITCH, new PotionEffect(PotionEffectType.LUCK, 20 * 20, 1), PotionType.LUCK),
  WITHER_SKELETON(EntityType.WITHER_SKELETON, new PotionEffect(PotionEffectType.SPEED, 20 * 20, 1), PotionType.SPEED),
  ZOMBIE(EntityType.ZOMBIE, new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 20, 0), PotionType.INSTANT_DAMAGE),
  ZOMBIE_VILLAGER(EntityType.ZOMBIE_VILLAGER, new PotionEffect(PotionEffectType.REGENERATION, 20 * 20, 0), PotionType.REGEN),
  ENDER_DRAGON(EntityType.ENDER_DRAGON, new PotionEffect(PotionEffectType.HEALTH_BOOST, 20 * 20, 5), PotionType.INSTANT_HEAL),
  ZOMBIE_HORSE(EntityType.ZOMBIE_HORSE, new PotionEffect(PotionEffectType.HEALTH_BOOST, 20 * 20, 1), PotionType.INSTANT_HEAL),
  BEE(EntityType.BEE, new PotionEffect(PotionEffectType.SPEED, 20 * 20, 1), PotionType.SPEED)
  ;

  @Getter private final EntityType entityType;
  @Getter private final PotionEffect potionEffect;
  @Getter private final PotionType potionType;
}

