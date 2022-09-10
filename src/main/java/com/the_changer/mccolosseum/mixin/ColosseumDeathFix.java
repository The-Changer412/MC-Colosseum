package com.the_changer.mccolosseum.mixin;

import com.the_changer.mccolosseum.mccolosseum;
import com.the_changer.mccolosseum.utli.DeathMessageThread;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@org.spongepowered.asm.mixin.Mixin(ServerPlayerEntity.class)
public class ColosseumDeathFix {
	@Inject(at = @At("TAIL"), method = "onDeath")
	public void onDeath(DamageSource damageSource, CallbackInfo info) {
		//check if any colosseum boss was alive when the player dies
		if (mccolosseum.ColosseumBossAlive) {
			ServerWorld world = damageSource.getSource().getServer().getOverworld();
			ServerPlayerEntity player = world.getPlayers().get(0);

			DeathMessageThread Thread = new DeathMessageThread(player, world);
			Thread.run();

			mccolosseum.LOGGER.warn("DEATH TO ALL BOSSES!");
		}
	}
}
