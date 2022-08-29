package com.the_changer.mccolosseum.entities.client;

import com.the_changer.mccolosseum.entities.entity.StabbyMcstabbyEntity;
import com.the_changer.mccolosseum.entities.entity.WeakWarriorEntity;
import com.the_changer.mccolosseum.mccolosseum;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class StabbyMcstabbyRenderer extends GeoEntityRenderer<StabbyMcstabbyEntity> {
    public StabbyMcstabbyRenderer(EntityRendererFactory.Context ctx) {

        super(ctx, new StabbyMcStabbyModel());
    }

    //point the entity to the texture file
    @Override
    public Identifier getTextureResource(StabbyMcstabbyEntity instance) {
        return new Identifier(mccolosseum.MOD_ID, "textures/stabby_mcstabby.png");
    }
}