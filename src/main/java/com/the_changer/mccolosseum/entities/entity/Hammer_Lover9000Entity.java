package com.the_changer.mccolosseum.entities.entity;

import com.the_changer.mccolosseum.block.ModBlocks;
import com.the_changer.mccolosseum.mccolosseum;
import com.the_changer.mccolosseum.utli.WinnerThread;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.List;

public class Hammer_Lover9000Entity extends PathAwareEntity implements IAnimatable {
    //create the variables
    private AnimationFactory factory = new AnimationFactory(this);
    public boolean attacked = false;
    public boolean CommandKill = false;
    public ServerBossBar BB = null;
    public Hammer_Lover9000Entity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
        BB = new ServerBossBar(this.getName(), BossBar.Color.WHITE, BossBar.Style.PROGRESS);
    }

    //set the stats for the entity
    public static DefaultAttributeContainer.Builder setAttributes() {
        return AnimalEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 140f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.20f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 14.5f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 1.1f)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 2f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 200f);
    }

    //set the AI for the entity
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new MeleeAttackGoal(this, this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_SPEED), true));
        this.targetSelector.add(2, new ActiveTargetGoal(this, PlayerEntity.class, true));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
    }

    //make the animation state machine for movement
    private <E extends IAnimatable> PlayState movePredicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("hammer_lover9000.walk", true));
            event.getController().setAnimationSpeed(1.5f);
            attacked = false;
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("hammer_lover9000.idle", true));
            event.getController().setAnimationSpeed(1f);
        }
        return PlayState.CONTINUE;
    }

    //make the animation state machine for attacks
    private <E extends IAnimatable> PlayState attackPredicate(AnimationEvent<E> event) {
        //check if the entity is close enough to attack
        PlayerEntity player = this.world.getClosestPlayer(this, 100);
        if (player != null) {
            double dis = this.getBlockPos().getSquaredDistance(player.getPos());
            if (dis < 5 && !attacked) {
                if (event.getController().getAnimationState() == AnimationState.Stopped) {
                    event.getController().markNeedsReload();
                }
                event.getController().setAnimation(new AnimationBuilder().addAnimation("hammer_lover9000.attack", false));
                event.getController().setAnimationSpeed(1.85f);
                attacked = true;
                return PlayState.CONTINUE;
            } else {
                return  PlayState.STOP;
            }
        } else {
            return  PlayState.STOP;
        }
    }

    //assign the state machine to the entity
    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "movecontroller", 0, this::movePredicate));
        animationData.addAnimationController(new AnimationController(this, "attackcontroller", 0, this::attackPredicate));
    }

    //make it only target the player
    public boolean canTarget(EntityType<?> type) {
        if (type == EntityType.PLAYER) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void tick() {
        super.tick();
        mccolosseum.Hammer_Lover9000UUID = this.uuid;

        //slow down any nearby players
        if (!this.world.isClient) {
            List<ServerPlayerEntity> Players = this.getServer().getPlayerManager().getPlayerList();
            for (ServerPlayerEntity player : Players) {
                double dis = this.getBlockPos().getSquaredDistance(player.getPos());
                if (dis < 50 && !player.isCreative() && !player.isSpectator()) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 2, 1), this);
                }
            }

            //set the boss bar of the boss
            if (!this.dead) {
                mccolosseum.ColosseumBossAlive = true;
                //only let ppl close to the boss see the health bar
                for (ServerPlayerEntity player : Players) {
                    double dis = this.getBlockPos().getSquaredDistance(player.getPos());
                    if (dis < 10000) {
                        BB.addPlayer(player);
                    } else {
                        BB.removePlayer(player);
                    }
                }
                //set the stats of the boss bar
                BB.setPercent(this.getHealth()/this.getMaxHealth());
                BB.setColor(BossBar.Color.RED);
                BB.setDarkenSky(true);
                BB.setVisible(true);
            } else {
                BB.clearPlayers();
                mccolosseum.ColosseumBossAlive = false;
            }
        }

    }

    //make it not despawn
    public void checkDespawn() {
        this.despawnCounter = 0;
    }

    //set the amount of xp it drops
    @Override
    public int getXpToDrop() {return  this.random.nextBetween(130, 180);}

    //check if the entity was killed by command
    @Override
    public boolean damage(DamageSource source, float amount) {
        if (source.isOutOfWorld()) {
            CommandKill = true;
        }
        return super.damage(source, amount);
    }

    @Override
    public void remove(RemovalReason reason) {

        super.remove(reason);
        //start the thread for the winner round after the boss's death as long as he wasn't killed by the command
        if (this.isDead() && !this.world.isClient && !CommandKill) {
            PlayerEntity player = this.world.getClosestPlayer(this, 100);
            WinnerThread Thread = new WinnerThread(player, this.getServer().getWorld(this.world.getRegistryKey()));
            Thread.run();
        }
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    //add the sound for the ambient
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_PILLAGER_AMBIENT;
    }
    //add the sound for getting hurt
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_PILLAGER_HURT;
    }
    //add the sound for dying
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_PILLAGER_DEATH;
    }
    //add the sound for stepping on blocks
    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        if (state.getBlock() == Blocks.SAND) {
            this.playSound(SoundEvents.BLOCK_SAND_STEP, 0.15f, 1.0f);
        } else if (state.getBlock() == Blocks.STRIPPED_OAK_WOOD || state.getBlock() == ModBlocks.Colosseum_Stripped_Oak_Wood) {
            this.playSound(SoundEvents.BLOCK_WOOD_STEP, 0.15f, 1.0f);
        }
    }
}