package com.the_changer.mccolosseum.block;

import com.the_changer.mccolosseum.mccolosseum;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {
    //create the block
    public static final Block Colosseum_Button = registerBlock("colosseum_button",
            new ColosseumButton(FabricBlockSettings.of(Material.DECORATION)
                    .strength(9999999999999999f, 9999999999999999f).requiresTool().noCollision()), null);
    public static final Block Colosseum_Stripped_Oak_Wood = registerBlock("colosseum_stripped_oak_wood",
            new RotateBlock(FabricBlockSettings.of(Material.DECORATION)), null);
    public static final Block Colosseum_Stripped_Oak_Wood_Enemy = registerBlock("colosseum_stripped_oak_wood_enemy",
            new RotateBlock(FabricBlockSettings.of(Material.DECORATION)), null);

    //register a new block item
    private static Item registerBlockItem(String name, Block block, ItemGroup group){
        return Registry.register(Registry.ITEM, new Identifier(mccolosseum.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(group)));
    }

    //register a block
    private static Block registerBlock(String name, Block block, ItemGroup group){
        registerBlockItem(name, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(mccolosseum.MOD_ID, name), block);
    }

    //register the modded blocks to the mod
    public static void registerModBlocks(){
        mccolosseum.LOGGER.info("Registering Mod Blocks for "+ mccolosseum.MOD_ID);
    }

}
