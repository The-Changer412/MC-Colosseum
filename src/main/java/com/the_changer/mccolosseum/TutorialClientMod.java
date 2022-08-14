package com.the_changer.mccolosseum;

import com.the_changer.mccolosseum.entities.ModEntities;
import com.the_changer.mccolosseum.entities.client.WeakWarriorRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class TutorialClientMod implements ClientModInitializer {

    //make the client render the entities
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.WEAKWARRIOR, WeakWarriorRenderer::new);

    }
}