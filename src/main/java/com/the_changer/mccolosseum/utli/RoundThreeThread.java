package com.the_changer.mccolosseum.utli;

import com.the_changer.mccolosseum.block.ModBlocks;
import com.the_changer.mccolosseum.entities.ModEntities;
import net.minecraft.block.Block;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RoundThreeThread extends Thread {
    PlayerEntity player;
    World world;

    public RoundThreeThread(PlayerEntity Player, World World) {
        player = Player;
        world = World;
    }


    public void run() {
        try {
            int range = 150;
            Thread.sleep(1000);
            UsefulFunctions.TalkToEveryone(player, "Announcer: And Stabby is down.");
            Thread.sleep(1000);
            UsefulFunctions.TalkToEveryone(player, "Announcer: Ironic isn't it. The guy that likes to stab people has been stabbed to death by the player.");
            Thread.sleep(3000);
            UsefulFunctions.TalkToEveryone(player, "Announcer: If the player can't be scared or stabbed to death, can the player be smashed to death?");
            Thread.sleep(3000);
            UsefulFunctions.TalkToEveryone(player, "Announcer: Hopefully, the next guy can scare the player so much, that they can't move around, and smash the player to death.");
            Thread.sleep(6000);
            UsefulFunctions.TalkToEveryone(player, "Announcer: Let's find out if the next guy can do that in 3...");
            Thread.sleep(2000);
            player.sendMessage(Text.of("Announcer: 2..."), false);
            Thread.sleep(1000);
            player.sendMessage(Text.of("Announcer: 1..."), false);
            Thread.sleep(1000);

            int BlockCheckRange = 120;
            boolean found = false;
            for (int x = player.getBlockX() - BlockCheckRange; x < player.getBlockX() + BlockCheckRange; x++) {
                for (int y = player.getBlockY() - 1; y < player.getBlockY() + 1; y++) {
                    for (int z = player.getBlockZ() - BlockCheckRange; z < player.getBlockZ() + BlockCheckRange; z++) {
                        //spawn the enemy at the block
                        Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
                        if (block == ModBlocks.Colosseum_Stripped_Oak_Wood_Enemy) {
                            ModEntities.HAMMER_LOVER9000.spawn(this.world.getServer().getWorld(this.world.getRegistryKey()),  null, null, null, new BlockPos(x, y+1, z), SpawnReason.TRIGGERED, true, true);
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
            player.getInventory().setStack(0, new ItemStack(Items.IRON_SWORD));
            player.getInventory().setStack(1, new ItemStack(Items.COOKED_BEEF, 6));
            player.getInventory().offHand.set(0, new ItemStack(Items.SHIELD));
            player.getInventory().armor.set(3, new ItemStack(Items.DIAMOND_HELMET));
            player.getInventory().armor.set(2, new ItemStack(Items.DIAMOND_CHESTPLATE));
            player.getInventory().armor.set(1, new ItemStack(Items.DIAMOND_LEGGINGS));
            player.getInventory().armor.set(0, new ItemStack(Items.DIAMOND_BOOTS));

            player.clearStatusEffects();
            player.heal(30);
            player.getHungerManager().setFoodLevel(30);
            player.getHungerManager().setSaturationLevel(30);
            player.getHungerManager().setFoodLevel(30);

            UsefulFunctions.TalkToEveryone(player, "Announcer: FIGHT!!!");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
