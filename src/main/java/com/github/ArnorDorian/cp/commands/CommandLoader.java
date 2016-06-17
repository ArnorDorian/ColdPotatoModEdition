package com.github.ArnorDorian.cp.commands;

import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

/**
 * Created by arnor on 2016/6/14 0014.
 */
public class CommandLoader {

    public CommandLoader(FMLServerStartingEvent event){
        event.registerServerCommand(new CommandTeamTell());
    }

}
