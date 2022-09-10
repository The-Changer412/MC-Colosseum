package com.the_changer.mccolosseum.utli;

import com.the_changer.mccolosseum.block.ModBlocks;
import com.the_changer.mccolosseum.entities.ModEntities;
import com.the_changer.mccolosseum.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class WinnerThread extends Thread {
    PlayerEntity player;
    World world;

    public WinnerThread(PlayerEntity Player, World World) {
        player = Player;
        world = World;
    }


    public void run() {
        try {
            int range = 150;
            Thread.sleep(1000);
            UsefulFunctions.TalkToEveryone(player, "Announcer: And Hammer Lover9000 is down.");
            Thread.sleep(1000);
            UsefulFunctions.TalkToEveryone(player, "Announcer: Which means that the player has killed all of the opponents in this colosseum.");
            Thread.sleep(3000);
            UsefulFunctions.TalkToEveryone(player, "Announcer: His price for winning the matches will be given to him shortly.");
            Thread.sleep(2000);
            UsefulFunctions.TalkToEveryone(player, "Announcer: Which means that the player has killed all of the opponents in this colosseum.");
            Thread.sleep(2000);
            UsefulFunctions.TalkToEveryone(player, "Announcer: But first, this colosseum is going to have to shutdown due to the fact that the player has killed all of our opponents.");
            Thread.sleep(5000);
            UsefulFunctions.TalkToEveryone(player, "Announcer: But don't worry people, we have many more colosseums for you to watch, and for the player to get different types of rewards at.");
            Thread.sleep(5000);
            UsefulFunctions.TalkToEveryone(player, "Announcer: Now, here's your reward.");
            Thread.sleep(1000);


            ItemStack buff = null;
            switch (Random.create().nextInt(7)) {
                case 0:
                    buff = new ItemStack(ModItems.Strength_Item);
                break;
                case 1:
                    buff = new ItemStack(ModItems.Regeneration_Item);
                break;
                case 2:
                    buff = new ItemStack(ModItems.Resistance_Item);
                break;
                case 3:
                    buff = new ItemStack(ModItems.Speed_Item);
                break;
                case 4:
                    buff = new ItemStack(ModItems.Fire_Resistance_Item);
                break;
                case 5:
                    buff = new ItemStack(ModItems.Jump_Boost_Item);
                break;
                case 6:
                    buff = new ItemStack(ModItems.Absorption_Item);
                break;
            }
            player.getInventory().clear();
            player.getInventory().insertStack(buff);
            BlockPos spawn = player.getServer().getPlayerManager().getPlayer(player.getUuid()).getSpawnPointPosition();
            player.teleport(spawn.getX(), spawn.getY(), spawn.getZ());

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
