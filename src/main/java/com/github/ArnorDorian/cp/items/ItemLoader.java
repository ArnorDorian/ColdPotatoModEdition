package com.github.ArnorDorian.cp.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by arnor on 2016/6/6 0006.
 */
public class ItemLoader {

    public static Item scalpel = new ItemScalpel();
    public static ItemSword unnamed_sword = new ItemUnnamedSword();
    public static Item grenade = new ItemCheekGrenade();

    public ItemLoader(FMLPreInitializationEvent event) {
        register(scalpel, "scalpel");
        register(unnamed_sword, "unnamed_sword");
        register(grenade, "cheek_grenade");
    }

    @SideOnly(Side.CLIENT)
    public static void registerRenders() {
        registerRender(scalpel, 0);
        registerRender(unnamed_sword, 0);
        registerRender(grenade, 0);
    }

    private void register(Item item, String name) {
        GameRegistry.registerItem(item, name);
    }

    @SideOnly(Side.CLIENT)
    private static void registerRender(Item item, int metadata) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, metadata, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

}
