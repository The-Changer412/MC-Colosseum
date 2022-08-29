package com.the_changer.mccolosseum.utli;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.List;

public class UsefulFunctions {
    //function that will talk to everyone in a certain range
    public static void TalkToEveryone(PlayerEntity player, String msg){
        List<ServerPlayerEntity> Players = player.getServer().getPlayerManager().getPlayerList();
        for (ServerPlayerEntity OtherPlayer : Players) {
            OtherPlayer.sendMessage(Text.of(msg), false);
        }
    }
}
