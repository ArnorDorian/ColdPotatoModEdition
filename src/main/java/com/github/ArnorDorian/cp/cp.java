package com.github.ArnorDorian.cp;

import com.github.ArnorDorian.cp.common.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

/**
 * Created by arnor on 2016/6/5 0005.
 */
@Mod(modid = cp.MODID, name = cp.NAME, version = cp.VERSION, acceptedMinecraftVersions = "[1.9,)")
public class cp {

    public static final String MODID = "cp";
    public static final String NAME = "Cold Potato";
    public static final String VERSION = "0.1.2";

    @SidedProxy(serverSide = "com.github.ArnorDorian.cp.common.CommonProxy", clientSide = "com.github.ArnorDorian.cp.client.ClientProxy")
    public static CommonProxy proxy;

    @Mod.Instance(cp.MODID)
    public static cp instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
