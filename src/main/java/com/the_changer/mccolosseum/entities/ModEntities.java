package com.the_changer.mccolosseum.entities;

import com.the_changer.mccolosseum.entities.entity.WeakWarriorEntity;
import com.the_changer.mccolosseum.mccolosseum;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEntities {
    //register the weak warrior entity
    public static final EntityType<WeakWarriorEntity> WEAKWARRIOR = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(mccolosseum.MOD_ID, "weakwarrior"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, WeakWarriorEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());
}