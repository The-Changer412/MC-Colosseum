package com.the_changer.mccolosseum.utli;

import com.the_changer.mccolosseum.mccolosseum;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class DeathMessageThread extends Thread {
    ServerPlayerEntity player;
    ServerWorld world;

    public DeathMessageThread(ServerPlayerEntity Player, ServerWorld World) {
        player = Player;
        world = World;
    }

    public void run() {
        try {

            Thread.sleep(3000);
            //kill the boss
            if ( mccolosseum.WeakWarriorUUID != null) {world.getEntity(mccolosseum.WeakWarriorUUID).kill();}
            if ( mccolosseum.StabbyMcstabbyUUID != null) {world.getEntity(mccolosseum.StabbyMcstabbyUUID).kill();}
            if ( mccolosseum.Hammer_Lover9000UUID != null) {world.getEntity(mccolosseum.Hammer_Lover9000UUID).kill();}

            //talk to the players
            UsefulFunctions.TalkToEveryone(player, "Announcer: And the player is down.");
            Thread.sleep(1000);
            UsefulFunctions.TalkToEveryone(player, "Announcer: Turns out, the player isn't as strong as we thought he is.");
            Thread.sleep(2000);
            UsefulFunctions.TalkToEveryone(player, "Announcer: Maybe the player will do better next time.");
            Thread.sleep(1000);
            UsefulFunctions.TalkToEveryone(player, "Announcer: Tune in next time to see if the player will do better next time.");
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

