package com.the_changer.mccolosseum.utli;

import com.the_changer.mccolosseum.entities.ModEntities;
import com.the_changer.mccolosseum.entities.entity.WeakWarriorEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;

public class ModRegistries {
    public static void registerModStuffs() {
        registerAttributes();
    }

    private static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(ModEntities.WEAKWARRIOR, WeakWarriorEntity.setAttributes());
    }
}