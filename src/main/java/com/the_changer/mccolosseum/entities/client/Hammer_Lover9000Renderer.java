package com.the_changer.mccolosseum.entities.client;

import com.the_changer.mccolosseum.entities.entity.Hammer_Lover9000Entity;
import com.the_changer.mccolosseum.entities.entity.WeakWarriorEntity;
import com.the_changer.mccolosseum.mccolosseum;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class Hammer_Lover9000Renderer extends GeoEntityRenderer<Hammer_Lover9000Entity> {
    //create the model of the entity
    public Hammer_Lover9000Renderer(EntityRendererFactory.Context ctx) {

        super(ctx, new Hammer_Lover9000Model());
    }

    //point the entity to the texture file
    @Override
    public Identifier getTextureResource(Hammer_Lover9000Entity instance) {
        return new Identifier(mccolosseum.MOD_ID, "textures/hammer_lover9000.png");
    }
}