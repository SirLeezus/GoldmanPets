package lee.code.pets.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.apache.commons.lang3.text.WordUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CoreUtil {
  private final static Pattern colorPattern = Pattern.compile(Pattern.compile("\\&#[a-fA-F0-9]{6}").pattern() + "|" + Pattern.compile("\\&[0-9a-frklmno]").pattern());

  public static Component parseColorComponent(String text) {
    final LegacyComponentSerializer serializer = LegacyComponentSerializer.legacyAmpersand();
    return (Component.empty().decoration(TextDecoration.ITALIC, false)).append(serializer.deserialize(text));
  }

  public static String serializeColorComponentJson(String text) {
    final GsonComponentSerializer serializer = GsonComponentSerializer.gson();
    final Component component = parseColorComponent(text);
    return serializer.serialize(component);
  }

  @SuppressWarnings("deprecation")
  public static  String capitalize(String message) {
    final String format = message.toLowerCase().replaceAll("_", " ");
    return WordUtils.capitalize(format);
  }

  public static String trimToMaxCharacters(String input, int maxCharacters) {
    if (input == null || input.isBlank()) return "";
    final Matcher colorMatcher = colorPattern.matcher(input);
    int subtract = 0;
    while (colorMatcher.find()) subtract += colorMatcher.group().length();
    if (input.length() - subtract > maxCharacters) return input.substring(0, maxCharacters + subtract);
    return input;
  }
}
