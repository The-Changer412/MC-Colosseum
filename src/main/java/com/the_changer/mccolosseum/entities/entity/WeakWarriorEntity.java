package com.the_changer.mccolosseum.entities.entity;

import com.the_changer.mccolosseum.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class WeakWarriorEntity extends PathAwareEntity implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);

    public WeakWarriorEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    //set the stats for the entity
    public static DefaultAttributeContainer.Builder setAttributes() {
        return AnimalEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.5f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 1f)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0f);
    }

    //set the ai for the entity
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new MeleeAttackGoal(this, this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_SPEED), false));
        this.targetSelector.add(2, new ActiveTargetGoal(this, PlayerEntity.class, true));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
    }

    //make the animation state machine
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("weak_warrior.walk", true));
            event.getController().setAnimationSpeed(1.6f);
            return PlayState.CONTINUE;
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("weak_warrior.idle", true));
            event.getController().setAnimationSpeed(1f);
            return PlayState.CONTINUE;
        }
    }

    //assign the state machine to the entity
    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
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
    public void tickMovement() {
        super.tickMovement();
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