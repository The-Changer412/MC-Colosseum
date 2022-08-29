package com.the_changer.mccolosseum.entities.client;

import com.the_changer.mccolosseum.entities.entity.StabbyMcstabbyEntity;
import com.the_changer.mccolosseum.entities.entity.WeakWarriorEntity;
import com.the_changer.mccolosseum.mccolosseum;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class StabbyMcStabbyModel extends AnimatedGeoModel<StabbyMcstabbyEntity> {
    //point the entity to the model file
    @Override
    public Identifier getModelResource(StabbyMcstabbyEntity object) {
        return new Identifier(mccolosseum.MOD_ID, "geo/stabby_mcstabby.geo.json");
    }

    //point the entity to the texture file
    @Override
    public Identifier getTextureResource(StabbyMcstabbyEntity object) {
        return new Identifier(mccolosseum.MOD_ID, "textures/stabby_mcstabby.png");
    }
    //point the entity to the animation file
    @Override
    public Identifier getAnimationResource(StabbyMcstabbyEntity animatable) {
        return new Identifier(mccolosseum.MOD_ID, "animations/stabby_mcstabby.animation.json");
    }
}