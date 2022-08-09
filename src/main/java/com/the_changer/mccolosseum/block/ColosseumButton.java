package com.the_changer.mccolosseum.block;

import com.the_changer.mccolosseum.mccolosseum;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.WoodenButtonBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ColosseumButton extends WoodenButtonBlock {
    public ColosseumButton(Settings settings) {
        super(settings);
    }

    //run when the player press the button
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        //do nothing if this is on the client side
        if (world.isClient) {
            return ActionResult.SUCCESS;
        } else {
            //check if the button is not jammed
            if (!state.get(ColosseumButton.POWERED)) {
                //check if the player inventory is empty
                if (!player.getInventory().isEmpty() && hand == Hand.MAIN_HAND) {
                    //tell the player what to do before starting the match
                    player.sendMessage(Text.of("Empty everything inside your inventory and set your spawn point before pressing the button."), false);
                } else {
                    //prepare the player that the fights will start soon.
                    player.sendMessage(Text.of("DON'T MOVE A MUSCLE. The fight will start soon, and moving could cause you to miss the opportunity to fight."), false);
                    player.sendMessage(Text.of("Also, Don't try to smuggle a item into the fight. You will lose the item."), false);
                    //jammed the button, so it can't be pressed again.
                    world.setBlockState(pos, state.with(ColosseumButton.POWERED, true), NOTIFY_ALL);

                    //start the countdown for the fight
                    PlayerCDandTP thread = new PlayerCDandTP(player, world);
                    thread.start();
                }
                return ActionResult.CONSUME;
            } else {
                //tell the player that the button is jammed, not allowing you to be able to fight at this arena
                player.sendMessage(Text.of("It seems like the button is jammed. Guess you got to find a new Colosseum to be able to fight, or you can watch the ongoing fight, if there is one."), false);
                return ActionResult.FAIL;
            }
        }
    }
}

//the thread that will do the countdown and teleport the player inside the event
class PlayerCDandTP extends Thread {
    //save the player and world that is pass through
    PlayerEntity player;
    World world;
    public PlayerCDandTP(PlayerEntity Player, World world1) {

        player = Player;
        world = world1;
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


            //get the closest block to teleport to
            for (int x = player.getBlockX()-10; x < player.getBlockX()+10; x++)
            {
                for (int y = player.getBlockY()-10; y < player.getBlockY()+10; y++)
                {
                    for (int z = player.getBlockZ()-10; z < player.getBlockZ()+10; z++)
                    {
                        //teleport the player to the block
                        Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
                        if (block == ModBlocks.Colosseum_Stripped_Oak_Wood) {
                            player.sendMessage(Text.of("FIGHT!!!"), false);
                            player.teleport(x, y+1, z);
                        }

                        //give the player there weapon, food, shield, and armor.
                        player.getInventory().clear();
                        player.getInventory().setStack(0, new ItemStack(Items.WOODEN_SWORD));
                        player.getInventory().setStack(1, new ItemStack(Items.COOKED_BEEF, 2));
                        player.getInventory().offHand.set(0, new ItemStack(Items.SHIELD));
                        player.getInventory().armor.set(3, new ItemStack(Items.LEATHER_HELMET));
                        player.getInventory().armor.set(2, new ItemStack(Items.LEATHER_CHESTPLATE));
                        player.getInventory().armor.set(1, new ItemStack(Items.LEATHER_LEGGINGS));
                        player.getInventory().armor.set(0, new ItemStack(Items.LEATHER_BOOTS));
                    }
                }
            }
        } catch (InterruptedException e) {
            mccolosseum.LOGGER.warn(e.getLocalizedMessage());
        }
    }
}