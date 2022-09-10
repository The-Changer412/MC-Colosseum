package com.the_changer.mccolosseum.utli;

import com.the_changer.mccolosseum.block.ModBlocks;
import com.the_changer.mccolosseum.entities.ModEntities;
import net.minecraft.block.Block;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class RoundTwoThread extends Thread {
    PlayerEntity player;
    World world;

    public RoundTwoThread(PlayerEntity Player, World World) {
        player = Player;
        world = World;
    }


    public void run() {
        try {
            //talk to the players
            Thread.sleep(1000);
            UsefulFunctions.TalkToEveryone(player, "Announcer: And the warrior is down.");
            Thread.sleep(1000);
            UsefulFunctions.TalkToEveryone(player, "Announcer: But who is surprised about that?");
            Thread.sleep(1000);
            UsefulFunctions.TalkToEveryone(player, "Announcer: That was just a random hobo that we paid 10 bucks to fight the player.");
            Thread.sleep(2000);
            UsefulFunctions.TalkToEveryone(player, "Announcer: We just call him strong to scare the player away.");
            Thread.sleep(2000);
            UsefulFunctions.TalkToEveryone(player, "Announcer: Hopefully, the next guy can scare you away with his strength.");
            Thread.sleep(2000);
            UsefulFunctions.TalkToEveryone(player, "Announcer: Or kill you. That also works.");
            Thread.sleep(2000);
            UsefulFunctions.TalkToEveryone(player, "Announcer: Anyway, Let the next fight begin in 3...");
            Thread.sleep(2000);
            UsefulFunctions.TalkToEveryone(player, "Announcer: 2...");
            Thread.sleep(1000);
            UsefulFunctions.TalkToEveryone(player, "Announcer: 1...");
            Thread.sleep(1000);

            int BlockCheckRange = 120;
            boolean found = false;
            for (int x = player.getBlockX() - BlockCheckRange; x < player.getBlockX() + BlockCheckRange; x++) {
                for (int y = player.getBlockY() - 1; y < player.getBlockY() + 1; y++) {
                    for (int z = player.getBlockZ() - BlockCheckRange; z < player.getBlockZ() + BlockCheckRange; z++) {
                        //spawn the enemy at the block
                        Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
                        if (block == ModBlocks.Colosseum_Stripped_Oak_Wood_Enemy) {
                            ModEntities.STABBYMCSTABBY.spawn(this.world.getServer().getWorld(this.world.getRegistryKey()),  null, null, null, new BlockPos(x, y+1, z), SpawnReason.TRIGGERED, true, true);
                            found = true;
                            break;
                        }
                    }
                    if (found) {break;}
                }
                if (found) {break;}
            }

            //give the player there weapon, food, shield, armor, and reset their stats.
            player.getInventory().clear();
            player.getInventory().setStack(0, new ItemStack(Items.STONE_SWORD));
            player.getInventory().setStack(1, new ItemStack(Items.COOKED_BEEF, 4));
            player.getInventory().offHand.set(0, new ItemStack(Items.SHIELD));
            player.getInventory().armor.set(3, new ItemStack(Items.CHAINMAIL_HELMET));
            player.getInventory().armor.set(2, new ItemStack(Items.CHAINMAIL_CHESTPLATE));
            player.getInventory().armor.set(1, new ItemStack(Items.CHAINMAIL_LEGGINGS));
            player.getInventory().armor.set(0, new ItemStack(Items.CHAINMAIL_BOOTS));

            //set the player to max hp
            player.clearStatusEffects();
            player.heal(30);

            UsefulFunctions.TalkToEveryone(player, "Announcer: FIGHT!!!");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
