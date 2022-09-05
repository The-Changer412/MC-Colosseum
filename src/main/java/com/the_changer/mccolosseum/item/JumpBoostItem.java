package com.the_changer.mccolosseum.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class JumpBoostItem extends Item {
    public JumpBoostItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient) {
            PlayerEntity player = world.getClosestPlayer(entity, 20f);
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 1, 0), entity);
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }
}
