package com.github.ArnorDorian.cp.common;

import com.github.ArnorDorian.cp.entities.EntityLoader;
import com.github.ArnorDorian.cp.items.ItemLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by arnor on 2016/6/5 0005.
 */
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        new ItemLoader(event);
    }

    public void init(FMLInitializationEvent event) {
        new EntityLoader();
    }

    public void postInit(FMLPostInitializationEvent event) {

    }

}
