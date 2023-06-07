package com.autumnstudios.oracle.api.commands;

import com.autumnstudios.oracle.api.annotations.Alias;
import com.autumnstudios.oracle.api.annotations.Command;
import com.autumnstudios.oracle.api.annotations.OraclePlugin;
import com.autumnstudios.oracle.api.annotations.Permission;
import com.autumnstudios.oracle.api.plugin.PluginOracle;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CommandProcesser {

    public static void processMultible(PluginOracle plugin, Class<?>...commandCls) {
        for (Class<?> cls : commandCls) {
            process(cls, plugin);
        }
    }

    public static void process(Class<?> commandCls, PluginOracle plugin) {
        for (Method m : commandCls.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Command.class)) {
                Command command = m.getAnnotation(Command.class);
                String label = command.label();
                JavaPlugin jPlugin = plugin.getJavaPlugin();

                register(command, m, commandCls, jPlugin);
            }
        }
    }

    public static void register(Command cmd, Method m, Class<?> commandCls, JavaPlugin plugin) {
        CommandMap map = null;
        Field field;
        try {
            field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            map = (CommandMap) field.get(Bukkit.getServer());
        }
        catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }



        assert map != null;
        org.bukkit.command.Command command = map.getCommand(cmd.label());
        command.setName(cmd.label());
        command.setLabel(cmd.label());

        Permission permission = null;
        if (commandCls.isAnnotationPresent(Permission.class)) {permission = commandCls.getAnnotation(Permission.class); }
        if (permission != null) {
            command.setPermission(permission.permission());
            command.setPermissionMessage(permission.permissionMessage());
        }

        Alias alias = null;
        if (commandCls.isAnnotationPresent(Alias.class)) {alias = commandCls.getAnnotation(Alias.class); }
        if (alias != null) {
            command.setAliases(new ArrayList<>(List.of(alias.aliases())));
        }

        OracleCommandExecuter executer = new OracleCommandExecuter(command.getLabel());
        executer.setOracleCommandExecuter(m, commandCls);
        map.register(cmd.label(), executer);


    }


}
