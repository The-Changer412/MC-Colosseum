package com.the_changer.mccolosseum.item;

import com.the_changer.mccolosseum.mccolosseum;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    public static final Item Strength_Item = registerItem("strength_item",
            new StrengthItem(new FabricItemSettings().group(null).maxCount(1)));
    public static final Item Absorption_Item = registerItem("absorption_item",
            new AbsorptionItem(new FabricItemSettings().group(null).maxCount(1)));
    public static final Item Fire_Resistance_Item = registerItem("fire_resistance_item",
            new FireResistanceItem(new FabricItemSettings().group(null).maxCount(1)));
    public static final Item Jump_Boost_Item = registerItem("jump_boost_item",
            new JumpBoostItem(new FabricItemSettings().group(null).maxCount(1)));
    public static final Item Regeneration_Item = registerItem("regeneration_item",
            new RegenerationItem(new FabricItemSettings().group(null).maxCount(1)));
    public static final Item Resistance_Item = registerItem("resistance_item",
            new ResistanceItem(new FabricItemSettings().group(null).maxCount(1)));
    public static final Item Speed_Item = registerItem("speed_item",
            new SpeedItem(new FabricItemSettings().group(null).maxCount(1)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(mccolosseum.MOD_ID, name), item);
    }

    public static void registerModItems() {
        mccolosseum.LOGGER.debug("Registering Mod Items for " + mccolosseum.MOD_ID);
    }
}
