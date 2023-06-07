package com.autumnstudios.oracle.systems.worlds.commands;

import com.autumnstudios.oracle.api.annotations.Alias;
import com.autumnstudios.oracle.api.annotations.Command;
import com.autumnstudios.oracle.api.annotations.Permission;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class OracleWorld {

    @Command(label="oracleworld")
    @Permission(permission = "oracle.worlds")
    @Alias(aliases = {"oworld", "worlds", "ow", "oraclew"})
    public void oracleWorldCommand(CommandSender sender, String[] args) {

    }
}
