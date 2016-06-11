package com.github.ArnorDorian.cp.entities;

import com.github.ArnorDorian.cp.cp;
import com.github.ArnorDorian.cp.items.ItemLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by arnor on 2016/6/6 0006.
 */
public class EntityLoader {

    public EntityLoader() {
        registerEntity(EntityScalpel.class, "Scalpel", 64, 10, true);
        registerEntity(EntityCheekGrenade.class, "CheekGrenade", 64, 10, true);
    }

    public static void registerEntity(Class<? extends Entity> entityClass, String name, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
        int entityID = EntityRegistry.findGlobalUniqueEntityId();

        EntityRegistry.registerGlobalEntityID(entityClass, name, entityID);
        EntityRegistry.registerModEntity(entityClass, name, entityID, cp.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
    }

    @SideOnly(Side.CLIENT)
    public static void registerRenders() {
        registerEntityRender(EntityScalpel.class, new IRenderFactory<EntityScalpel>() {
            @Override
            public Render<? super EntityScalpel> createRenderFor(RenderManager manager) {
                return new RenderSnowball<EntityScalpel>(manager, ItemLoader.scalpel, Minecraft.getMinecraft().getRenderItem());
            }
        });
        registerEntityRender(EntityCheekGrenade.class, new IRenderFactory() {
            @Override
            public Render createRenderFor(RenderManager manager) {
                return new RenderSnowball<EntityCheekGrenade>(manager, ItemLoader.grenade, Minecraft.getMinecraft().getRenderItem());
            }
        });
    }

    @SideOnly(Side.CLIENT)
    private static void registerEntityRender(Class<? extends Entity> entityClass, IRenderFactory renderer) {
        RenderingRegistry.registerEntityRenderingHandler(entityClass, renderer);
    }

}
