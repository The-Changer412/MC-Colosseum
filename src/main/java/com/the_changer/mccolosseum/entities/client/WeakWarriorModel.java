package com.the_changer.mccolosseum.entities.client;

import com.the_changer.mccolosseum.entities.entity.WeakWarriorEntity;
import com.the_changer.mccolosseum.mccolosseum;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class WeakWarriorModel extends AnimatedGeoModel<WeakWarriorEntity> {
    @Override
    public Identifier getModelResource(WeakWarriorEntity object) {
        return new Identifier(mccolosseum.MOD_ID, "geo/weak_warrior.geo.json");
    }

    @Override
    public Identifier getTextureResource(WeakWarriorEntity object) {
        return new Identifier(mccolosseum.MOD_ID, "textures/weak_warrior.png");
    }
    @Override
    public Identifier getAnimationResource(WeakWarriorEntity animatable) {
        return new Identifier(mccolosseum.MOD_ID, "animations/weak_warrior.animation.json");
    }
}