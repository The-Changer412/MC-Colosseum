package com.the_changer.mccolosseum.entity.client;

import com.the_changer.mccolosseum.entity.custom.RaccoonEntity;
import com.the_changer.mccolosseum.mccolosseum;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RaccoonRenderer extends GeoEntityRenderer<RaccoonEntity> {
    public RaccoonRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new RaccoonModel());
    }

    @Override
    public Identifier getTextureResource(RaccoonEntity instance) {
        return new Identifier(mccolosseum.MOD_ID, "textures/entity/raccoon/raccoon.png");
    }
}