package com.autumnstudios.oracle.api.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.List;

public class OracleCommandExecuter extends Command {

    Method method;
    Class<?> commandCls;

    boolean canExecute;

    protected OracleCommandExecuter(@NotNull String name) {
        super(name);
    }

    public void setOracleCommandExecuter(Method method, Class<?> commandCls) {


        canExecute = true;
        Class<?>[] params = method.getParameterTypes();

        this.commandCls = commandCls;
        if (params.length == 2) {
            if (params[0].getTypeName().equals("org.bukkit.command.CommandSender") && params[1].getTypeName().equals("java.lang.String[]")) {
                canExecute = true;

            } else {
                canExecute = false;
                Bukkit.getLogger().severe("Error loading command in Oracle Command Executor");
            }
        }

        this.method = method;
    }


    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String[] strings) {
        if (canExecute) {
            try {
                method.invoke(commandCls, commandSender, strings);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        return super.tabComplete(sender, alias, args);
    }
}
