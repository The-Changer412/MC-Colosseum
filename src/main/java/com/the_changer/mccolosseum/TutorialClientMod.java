package com.the_changer.mccolosseum;

import com.the_changer.mccolosseum.entity.ModEntities;
import com.the_changer.mccolosseum.entity.client.RaccoonRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class TutorialClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.RACCOON, RaccoonRenderer::new);

    }
}