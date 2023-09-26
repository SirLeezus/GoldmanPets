package lee.code.pets.pets.pet.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.animal.CatVariant;

@AllArgsConstructor
public enum CatVariantUtil {
  WHITE(BuiltInRegistries.CAT_VARIANT.getOrThrow(CatVariant.WHITE)),
  JELLIE(BuiltInRegistries.CAT_VARIANT.getOrThrow(CatVariant.JELLIE)),
  BLACK(BuiltInRegistries.CAT_VARIANT.getOrThrow(CatVariant.BLACK)),
  RED(BuiltInRegistries.CAT_VARIANT.getOrThrow(CatVariant.RED)),
  ALL_BLACK(BuiltInRegistries.CAT_VARIANT.getOrThrow(CatVariant.ALL_BLACK)),
  BRITISH_SHORTHAIR(BuiltInRegistries.CAT_VARIANT.getOrThrow(CatVariant.BRITISH_SHORTHAIR)),
  CALICO(BuiltInRegistries.CAT_VARIANT.getOrThrow(CatVariant.CALICO)),
  RAGDOLL(BuiltInRegistries.CAT_VARIANT.getOrThrow(CatVariant.RAGDOLL)),
  TABBY(BuiltInRegistries.CAT_VARIANT.getOrThrow(CatVariant.TABBY)),
  PERSIAN(BuiltInRegistries.CAT_VARIANT.getOrThrow(CatVariant.PERSIAN)),
  SIAMESE(BuiltInRegistries.CAT_VARIANT.getOrThrow(CatVariant.SIAMESE)),

  ;
  @Getter private final CatVariant catVariant;
}
