package com.github.ArnorDorian.cp.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.server.FMLServerHandler;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by arnor on 2016/6/14 0014.
 */
public class CommandTeamTell extends CommandBase {

    @Override
    public List<String> getCommandAliases() {
        return Arrays.<String>asList(new String[]{" ", "tt"});
    }

    @Override
    public String getCommandName() {
        return "teamtell";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "commands.teamtell.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

        if (args.length < 1) {
            throw new WrongUsageException("commands.teamtell.usage", new Object[0]);
        } else {

            ITextComponent iTextComponent = getChatComponentFromNthArg(sender, args, 0, !(sender instanceof EntityPlayer));
            Object[] objects = new Object[]{sender.getName(), iTextComponent.createCopy()};
            TextComponentTranslation textComponentTranslation = new TextComponentTranslation("commands.teamtell.display.null", objects);

            if (((EntityPlayer) sender).getTeam().getChatFormat() == TextFormatting.BLUE)
                textComponentTranslation = new TextComponentTranslation("commands.teamtell.display.blue", objects);
            if (((EntityPlayer) sender).getTeam().getChatFormat() == TextFormatting.RED)
                textComponentTranslation = new TextComponentTranslation("commands.teamtell.display.red", objects);

            if (((EntityPlayer) sender).getTeam() != null) {
                Collection<String> teamMembersCollection = ((EntityPlayer) sender).getTeam().getMembershipCollection();
                String[] teamMembers = teamMembersCollection.toArray(new String[teamMembersCollection.size()]);

                for (String name : teamMembers) {
                    EntityPlayer member = server.getPlayerList().getPlayerByUsername(name);
                    if (member != null) member.addChatMessage(textComponentTranslation);
                }
            }
            if (((EntityPlayer) sender).getTeam() == null) {
                String[] allPlayerNames = server.getPlayerList().getAllUsernames();

                for (String name : allPlayerNames) {
                    EntityPlayer player = server.getPlayerList().getPlayerByUsername(name);
                    if (player != null && player.getTeam() == null) player.addChatMessage(textComponentTranslation);
                }
            }
        }
    }

}
