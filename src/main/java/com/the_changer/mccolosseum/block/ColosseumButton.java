package com.the_changer.mccolosseum.block;

import com.the_changer.mccolosseum.utli.PlayerCDandTP;
import net.minecraft.block.BlockState;
import net.minecraft.block.WoodenButtonBlock;
import net.minecraft.entity.player.PlayerEntity;
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

