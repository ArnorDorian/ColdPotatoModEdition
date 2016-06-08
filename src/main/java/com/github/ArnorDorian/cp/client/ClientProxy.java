package com.github.ArnorDorian.cp.client;

import com.github.ArnorDorian.cp.common.CommonProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by arnor on 2016/6/5 0005.
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        new EntityRenderLoader();
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        new ItemRenderLoader();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

}
