package com.autumnstudios.oracle.api.plugin;

import org.bukkit.plugin.java.JavaPlugin;

public interface PluginOracle {

    void registerCommands();

    void registerModules();

    JavaPlugin getJavaPlugin();
}
