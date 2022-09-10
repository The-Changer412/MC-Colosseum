package com.the_changer.mccolosseum.utli;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.VillagerData;
import net.minecraft.village.VillagerProfession;
import net.minecraft.village.VillagerType;
import net.minecraft.world.World;

public class SpectatorsSpawnThread extends Thread {
    PlayerEntity player;
    World world;
    int BlockCheckRange = 40;

    public SpectatorsSpawnThread(PlayerEntity Player, World World) {
        player = Player;
        world = World;
    }

    @Override
    public void run() {
        BlockCheckRange = 140;
        for (int x = player.getBlockX() - BlockCheckRange; x < player.getBlockX() + BlockCheckRange; x++) {
            for (int y = player.getBlockY() - 10; y < player.getBlockY() + 10; y++) {
                for (int z = player.getBlockZ() - BlockCheckRange; z < player.getBlockZ() + BlockCheckRange; z++) {

                    Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
                    if (block == Blocks.SMOOTH_SANDSTONE_STAIRS) {
                        if (Random.create().nextInt(7) == 0) {

                            switch (Random.createLocal().nextInt(7)) {
                                case 0, 1, 2, 3:
                                    VillagerEntity villager = EntityType.VILLAGER.spawn(this.world.getServer().getWorld(this.world.getRegistryKey()), null, null, null, new BlockPos(x, y + 1, z), SpawnReason.TRIGGERED, true, true);

                                    VillagerType type = null;
                                    switch (Random.createLocal().nextInt(7)) {
                                        case 0:
                                            type = VillagerType.PLAINS;
                                        break;
                                        case 1:
                                            type = VillagerType.DESERT;
                                            break;
                                        case 2:
                                            type = VillagerType.JUNGLE;
                                            break;
                                        case 3:
                                            type = VillagerType.SAVANNA;
                                            break;
                                        case 4:
                                            type = VillagerType.SNOW;
                                            break;
                                        case 5:
                                            type = VillagerType.SWAMP;
                                            break;
                                        case 6:
                                            type = VillagerType.TAIGA;
                                            break;
                                    }

                                villager.setVillagerData(new VillagerData(type, VillagerProfession.NONE, 1));
                                break;
                                case 4:
                                    EntityType.WANDERING_TRADER.spawn(this.world.getServer().getWorld(this.world.getRegistryKey()), null, null, null, new BlockPos(x, y + 1, z), SpawnReason.TRIGGERED, true, true);;
                                break;
                                case 5:
                                    EntityType.IRON_GOLEM.spawn(this.world.getServer().getWorld(this.world.getRegistryKey()), null, null, null, new BlockPos(x, y + 1, z), SpawnReason.TRIGGERED, true, true);
                                break;
                                case 6:
                                    EntityType.ARMOR_STAND.spawn(this.world.getServer().getWorld(this.world.getRegistryKey()), null, null, null, new BlockPos(x, y + 1, z), SpawnReason.TRIGGERED, true, true);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
