package lee.code.pets.menus.menu.menudata.options;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum OptionSelector {
  COW(new String[] {Option.NAME.name(), Option.BABY.name()}),
  SHEEP(new String[] {Option.NAME.name(), Option.BABY.name(), Option.COLOR.name()}),
  PARROT(new String[] {Option.NAME.name(), Option.VARIANT.name()}),
  ALLAY(new String[] {Option.NAME.name()}),
  BAT(new String[] {Option.NAME.name()}),
  BEE(new String[] {Option.NAME.name(), Option.BABY.name(), Option.ANGRY.name(), Option.NECTAR.name(), Option.STUNG.name()}),
  CAMEL(new String[] {Option.NAME.name(), Option.BABY.name(), Option.SADDLE.name()}),
  CAT(new String[] {Option.NAME.name(), Option.BABY.name(), Option.VARIANT.name(), Option.COLOR.name()}),
  CHICKEN(new String[] {Option.NAME.name(), Option.BABY.name()}),
  DONKEY(new String[] {Option.NAME.name(), Option.BABY.name(), Option.CHEST.name(), Option.SADDLE.name()}),
  FOX(new String[] {Option.NAME.name(), Option.BABY.name(), Option.VARIANT.name()}),
  GOAT(new String[] {Option.NAME.name(), Option.BABY.name(), Option.HORNS.name()}),
  HOGLIN(new String[] {Option.NAME.name(), Option.BABY.name()}),
  HORSE(new String[] {Option.NAME.name(), Option.BABY.name(), Option.SADDLE.name(), Option.VARIANT.name(), Option.MARKING.name()}),
  IRON_GOLEM(new String[] {Option.NAME.name()}),
  LLAMA(new String[] {Option.NAME.name(), Option.BABY.name(), Option.CHEST.name(), Option.VARIANT.name(), Option.SADDLE.name(), Option.COLOR.name()}),
  MUSHROOM_COW(new String[] {Option.NAME.name(), Option.BABY.name(), Option.VARIANT.name()}),
  OCELOT(new String[] {Option.NAME.name(), Option.BABY.name()}),
  PANDA(new String[] {Option.NAME.name(), Option.BABY.name(), Option.MAIN_GENE.name(), Option.HIDDEN_GENE.name()}),
  PIG(new String[] {Option.NAME.name(), Option.BABY.name()}),
  POLAR_BEAR(new String[] {Option.NAME.name(), Option.BABY.name()}),
  RABBIT(new String[] {Option.NAME.name(), Option.BABY.name(), Option.VARIANT.name()}),
  SKELETON_HORSE(new String[] {Option.NAME.name(), Option.BABY.name(), Option.SADDLE.name()}),
  SNIFFER(new String[] {Option.NAME.name(), Option.BABY.name()}),
  SNOWMAN(new String[] {Option.NAME.name(), Option.PUMPKIN.name()}),
  STRIDER(new String[] {Option.NAME.name(), Option.BABY.name(), Option.SADDLE.name()}),
  TRADER_LLAMA(new String[] {Option.NAME.name(), Option.BABY.name(), Option.CHEST.name(), Option.VARIANT.name(), Option.SADDLE.name(), Option.COLOR.name()}),
  VILLAGER(new String[] {Option.NAME.name(), Option.BABY.name(), Option.TYPE.name(), Option.PROFESSION.name(), Option.LEVEL.name()}),
  WANDERING_TRADER(new String[] {Option.NAME.name()}),
  WOLF(new String[] {Option.NAME.name(), Option.BABY.name(), Option.COLLAR.name(), Option.COLOR.name(), Option.ANGRY.name()}),
  ZOGLIN(new String[] {Option.NAME.name(), Option.BABY.name()}),
  ZOMBIE_HORSE(new String[] {Option.NAME.name(), Option.BABY.name(), Option.SADDLE.name()}),
  AXOLOTL(new String[] {Option.NAME.name(), Option.BABY.name(), Option.VARIANT.name()}),
  COD(new String[] {Option.NAME.name()}),
  DOLPHIN(new String[] {Option.NAME.name()}),
  ELDER_GUARDIAN(new String[] {Option.NAME.name()}),
  FROG(new String[] {Option.NAME.name(), Option.VARIANT.name()}),
  GLOW_SQUID(new String[] {Option.NAME.name()}),
  GUARDIAN(new String[] {Option.NAME.name()}),
  PUFFERFISH(new String[] {Option.NAME.name()}),
  SALMON(new String[] {Option.NAME.name()}),
  SQUID(new String[] {Option.NAME.name()}),
  TADPOLE(new String[] {Option.NAME.name()}),
  TROPICAL_FISH(new String[] {Option.NAME.name(), Option.VARIANT.name(), Option.BODY_COLOR.name(), Option.PATTERN_COLOR.name()}),
  TURTLE(new String[] {Option.NAME.name(), Option.BABY.name()}),
  BLAZE(new String[] {Option.NAME.name()}),
  CAVE_SPIDER(new String[] {Option.NAME.name()}),
  CREEPER(new String[] {Option.NAME.name(), Option.POWERED.name()}),
  DROWNED(new String[] {Option.NAME.name(), Option.BABY.name()}),
  ENDERMAN(new String[] {Option.NAME.name()}),
  EVOKER(new String[] {Option.NAME.name()}),
  GHAST(new String[] {Option.NAME.name()}),
  HUSK(new String[] {Option.NAME.name(), Option.BABY.name()}),
  ILLUSIONER(new String[] {Option.NAME.name()}),
  MAGMA_CUBE(new String[] {Option.NAME.name(), Option.SIZE.name()})
  ;

  @Getter private final String[] options;
}
