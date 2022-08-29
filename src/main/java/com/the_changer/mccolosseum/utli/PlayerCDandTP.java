package com.the_changer.mccolosseum.utli;

import com.the_changer.mccolosseum.block.ModBlocks;
import com.the_changer.mccolosseum.entities.ModEntities;
import com.the_changer.mccolosseum.mccolosseum;
import net.minecraft.block.Block;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

//the thread that will do the countdown and teleport the player inside the event
public class PlayerCDandTP extends Thread {
    //save the player and world that is pass through
    PlayerEntity player;
    World world;
    int BlockCheckRange = 40;
    boolean found = false;

    public PlayerCDandTP(PlayerEntity Player, World World) {
        player = Player;
        world = World;
    }

    public void run() {
        try {
            int range = 1500;
            //make the announcer talk to the player
            Thread.sleep(3000);
            UsefulFunctions.TalkToEveryone(player, "Announcer: Welcome to today's fight.");
            Thread.sleep(1000);
            UsefulFunctions.TalkToEveryone(player, "Announcer: On the left side of the colosseum, we have some random player that volunteer himself to die for your entertainment.");
            Thread.sleep(2200);
            UsefulFunctions.TalkToEveryone(player, "Announcer: And on the right side of the colosseum, we have one a strong warrior that will easily kill the player.");
            Thread.sleep(2000);
            UsefulFunctions.TalkToEveryone(player, "Announcer: This is a fight that you do not want to miss.");
            Thread.sleep(1200);
            UsefulFunctions.TalkToEveryone(player, "Announcer: Release the Fighters in 3...");
            Thread.sleep(1000);
            player.sendMessage(Text.of("Announcer: 2..."), false);
            Thread.sleep(1000);
            player.sendMessage(Text.of("Announcer: 1..."), false);
            Thread.sleep(1000);


            BlockCheckRange = 120;
            found = false;
            for (int x = player.getBlockX() - BlockCheckRange; x < player.getBlockX() + BlockCheckRange; x++) {
                for (int y = player.getBlockY() - 1; y < player.getBlockY() + 1; y++) {
                    for (int z = player.getBlockZ() - BlockCheckRange; z < player.getBlockZ() + BlockCheckRange; z++) {
                        //spawn the enemy at the block
                        Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
                        if (block == ModBlocks.Colosseum_Stripped_Oak_Wood_Enemy) {
                            ModEntities.WEAKWARRIOR.spawn(this.world.getServer().getWorld(this.world.getRegistryKey()),  null, null, null, new BlockPos(x, y+1, z), SpawnReason.TRIGGERED, true, true);
                            found = true;
                            break;
                        }
                    }
                    if (found) {break;}
                }
                if (found) {break;}
            }

            //get the closest block to teleport the player at
            BlockCheckRange = 10;
            found = false;
            for (int x = player.getBlockX() - BlockCheckRange; x < player.getBlockX() + BlockCheckRange; x++) {
                for (int y = player.getBlockY() - 1; y < player.getBlockY() + 1; y++) {
                    for (int z = player.getBlockZ() - BlockCheckRange; z < player.getBlockZ() + BlockCheckRange; z++) {

                        //teleport the player to the block
                        Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
                        if (block == ModBlocks.Colosseum_Stripped_Oak_Wood) {
                            player.teleport(x, y + 1, z);

                            //give the player there weapon, food, shield, and armor.
                            player.getInventory().clear();
                            player.getInventory().setStack(0, new ItemStack(Items.WOODEN_SWORD));
                            player.getInventory().setStack(1, new ItemStack(Items.COOKED_BEEF, 2));
                            player.getInventory().offHand.set(0, new ItemStack(Items.SHIELD));
                            player.getInventory().armor.set(3, new ItemStack(Items.LEATHER_HELMET));
                            player.getInventory().armor.set(2, new ItemStack(Items.LEATHER_CHESTPLATE));
                            player.getInventory().armor.set(1, new ItemStack(Items.LEATHER_LEGGINGS));
                            player.getInventory().armor.set(0, new ItemStack(Items.LEATHER_BOOTS));

                            player.sendMessage(Text.of("Announcer: FIGHT!!!"), false);
                            found = true;
                            break;
                        }
                    }
                    if (found) {break;}
                }
                if (found) {break;}
            }
        } catch (InterruptedException e) {
            mccolosseum.LOGGER.warn(e.getLocalizedMessage());
        }
    }
}
