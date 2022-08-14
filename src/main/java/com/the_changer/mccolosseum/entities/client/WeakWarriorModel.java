package com.the_changer.mccolosseum.entities.client;

import com.the_changer.mccolosseum.entities.entity.WeakWarriorEntity;
import com.the_changer.mccolosseum.mccolosseum;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class WeakWarriorModel extends AnimatedGeoModel<WeakWarriorEntity> {
    //point the entity to the model file
    @Override
    public Identifier getModelResource(WeakWarriorEntity object) {
        return new Identifier(mccolosseum.MOD_ID, "geo/weak_warrior.geo.json");
    }

    //point the entity to the texture file
    @Override
    public Identifier getTextureResource(WeakWarriorEntity object) {
        return new Identifier(mccolosseum.MOD_ID, "textures/weak_warrior.png");
    }
    //point the entity to the animation file
    @Override
    public Identifier getAnimationResource(WeakWarriorEntity animatable) {
        return new Identifier(mccolosseum.MOD_ID, "animations/weak_warrior.animation.json");
    }
}