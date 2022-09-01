package com.the_changer.mccolosseum.entities.client;

import com.the_changer.mccolosseum.entities.entity.Hammer_Lover9000Entity;
import com.the_changer.mccolosseum.entities.entity.WeakWarriorEntity;
import com.the_changer.mccolosseum.mccolosseum;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class Hammer_Lover9000Model extends AnimatedGeoModel<Hammer_Lover9000Entity> {
    //point the entity to the model file
    @Override
    public Identifier getModelResource(Hammer_Lover9000Entity object) {
        return new Identifier(mccolosseum.MOD_ID, "geo/hammer_lover9000.geo.json");
    }

    //point the entity to the texture file
    @Override
    public Identifier getTextureResource(Hammer_Lover9000Entity object) {
        return new Identifier(mccolosseum.MOD_ID, "textures/hammer_lover9000.png");
    }
    //point the entity to the animation file
    @Override
    public Identifier getAnimationResource(Hammer_Lover9000Entity animatable) {
        return new Identifier(mccolosseum.MOD_ID, "animations/hammer_lover9000.animation.json");
    }
}