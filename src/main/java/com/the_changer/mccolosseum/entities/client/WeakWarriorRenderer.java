package com.the_changer.mccolosseum.entities.client;

import com.the_changer.mccolosseum.entities.entity.WeakWarriorEntity;
import com.the_changer.mccolosseum.mccolosseum;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class WeakWarriorRenderer extends GeoEntityRenderer<WeakWarriorEntity> {
    //create the model
    public WeakWarriorRenderer(EntityRendererFactory.Context ctx) {

        super(ctx, new WeakWarriorModel());
    }

    //point the entity to the texture file
    @Override
    public Identifier getTextureResource(WeakWarriorEntity instance) {
        return new Identifier(mccolosseum.MOD_ID, "textures/weak_warrior.png");
    }
}