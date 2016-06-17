package com.github.ArnorDorian.cp.items;

import com.github.ArnorDorian.cp.cp;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arnor on 2016/6/6 0006.
 */
public class ItemLoader {

    public static Item scalpel = new ItemScalpel();
    public static ItemSword unnamed_sword = new ItemUnnamedSword();
    public static Item grenade = new ItemCheekGrenade();
    public static ItemArrow arrow = new ItemCPArrow();

    public ItemLoader(FMLPreInitializationEvent event) {
        register(scalpel, "scalpel");
        register(unnamed_sword, "unnamed_sword");
        register(grenade, "cheek_grenade");
        register(arrow, "arrow");
    }

    @SideOnly(Side.CLIENT)
    public static void registerRenders() {
        registerRender(scalpel, 0, "scalpel");
        registerRender(unnamed_sword, 0, "unnamed_sword");
        registerRender(grenade, 0, "cheek_grenade");
        registerRender(arrow, 0, "windy_arrow");
        registerRender(arrow, 1, "icy_arrow");
       // registerModelVariant(arrow, arrow.getUnlocalizedName(new ItemStack(arrow, 1, 0)));
        //registerModelVariant(arrow, arrow.getUnlocalizedName(new ItemStack(arrow, 1, 1)));
        //registerModelVariant(arrow,arrow.getUnlocalizedName());
    }

    private void register(Item item, String name) {
        GameRegistry.registerItem(item, name);
    }

    @SideOnly(Side.CLIENT)
    private static void registerRender(Item item, int metadata, String name) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, metadata, new ModelResourceLocation(cp.MODID + ":" + name, "inventory"));
    }

    private static void registerModelVariant(Item item, String... names) {
        if (FMLCommonHandler.instance().getSide().isClient()) {
            int i = names.length;
            while (--i >= 0) {
                names[i] = cp.MODID + ":" + names[i];
            }
            List<ResourceLocation> list = new ArrayList<ResourceLocation>();
            for (String name : names) {
                list.add(new ResourceLocation(name));
            }
            ResourceLocation[] resourceLocations = list.toArray(new ResourceLocation[list.size()]);
            ModelLoader.registerItemVariants(item, resourceLocations);
        }
    }

}
