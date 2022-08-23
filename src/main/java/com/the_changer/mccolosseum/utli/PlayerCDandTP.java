package com.the_changer.mccolosseum.utli;

import com.the_changer.mccolosseum.block.ModBlocks;
import com.the_changer.mccolosseum.entities.ModEntities;
import com.the_changer.mccolosseum.entities.entity.WeakWarriorEntity;
import com.the_changer.mccolosseum.mccolosseum;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.server.command.SummonCommand;
import net.minecraft.server.command.TeleportCommand;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Optional;
import java.util.function.Predicate;

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
            //do the countdown
            Thread.sleep(3000);
            player.sendMessage(Text.of("3"), false);
            Thread.sleep(1000);
            player.sendMessage(Text.of("2"), false);
            Thread.sleep(1000);
            player.sendMessage(Text.of("1"), false);
            Thread.sleep(1000);

            BlockCheckRange = 120;
            found = false;
            for (int x = player.getBlockX() - BlockCheckRange; x < player.getBlockX() + BlockCheckRange; x++) {
                for (int y = player.getBlockY() - 1; y < player.getBlockY() + 1; y++) {
                    for (int z = player.getBlockZ() - BlockCheckRange; z < player.getBlockZ() + BlockCheckRange; z++) {
                        //spawn the enemy at the block
                        Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
                        this.world.getServer().sendMessage(Text.of(String.valueOf(new BlockPos(x, y, z))));
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

                            player.sendMessage(Text.of("FIGHT!!!"), false);
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
