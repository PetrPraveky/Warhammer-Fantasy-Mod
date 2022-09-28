package net.pravekypetr.wh;

import net.minecraft.ChatFormatting;

public class _ModItemQualities {
    // Common items
    public static ChatFormatting COMMON = ChatFormatting.GRAY;

    // Uncommon items
    public static ChatFormatting UNCOMMON = ChatFormatting.AQUA;

    // Rare items
    public static ChatFormatting RARE = ChatFormatting.GREEN;

    // Epic items
    public static ChatFormatting EPIC = ChatFormatting.LIGHT_PURPLE;

    // Relic items
    public static ChatFormatting RELIC = ChatFormatting.GOLD;


    public static ChatFormatting getColor(String quality) {
        if (quality.toLowerCase().equals("common")) {
            return _ModItemQualities.COMMON;
        } else if (quality.toLowerCase().equals("uncommon")) {
            return _ModItemQualities.UNCOMMON;
        } else if (quality.toLowerCase().equals("rare")) {
            return _ModItemQualities.RARE;
        } else if (quality.toLowerCase().equals("epic")) {
            return _ModItemQualities.EPIC;
        } else if (quality.toLowerCase().equals("relic")) {
            return _ModItemQualities.RELIC;
        } else {
            return ChatFormatting.WHITE;
        }
    }
}
