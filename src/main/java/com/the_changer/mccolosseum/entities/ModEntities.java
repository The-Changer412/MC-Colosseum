package com.the_changer.mccolosseum.entities;

import com.the_changer.mccolosseum.entities.entity.Hammer_Lover9000Entity;
import com.the_changer.mccolosseum.entities.entity.StabbyMcstabbyEntity;
import com.the_changer.mccolosseum.entities.entity.WeakWarriorEntity;
import com.the_changer.mccolosseum.mccolosseum;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEntities {
    //register the entities
    public static final EntityType<WeakWarriorEntity> WEAKWARRIOR = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(mccolosseum.MOD_ID, "weakwarrior"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, WeakWarriorEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());

    public static final EntityType<StabbyMcstabbyEntity> STABBYMCSTABBY = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(mccolosseum.MOD_ID, "stabbymcstabby"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, StabbyMcstabbyEntity::new)
                    .dimensions(EntityDimensions.fixed(0.7f, 2.18f)).build());

    public static final EntityType<Hammer_Lover9000Entity> HAMMER_LOVER9000 = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(mccolosseum.MOD_ID, "hammer_lover9000"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, Hammer_Lover9000Entity::new)
                    .dimensions(EntityDimensions.fixed(1.2f, 2.75f)).build());
}