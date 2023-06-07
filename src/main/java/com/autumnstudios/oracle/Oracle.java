package com.autumnstudios.oracle;

import com.autumnstudios.oracle.api.annotations.OraclePlugin;
import com.autumnstudios.oracle.api.plugin.PluginOracle;
import org.bukkit.plugin.java.JavaPlugin;

@OraclePlugin
public final class Oracle extends JavaPlugin implements PluginOracle {

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    @Override
    public void registerCommands() {

    }

    @Override
    public void registerModules() {

    }

    @Override
    public JavaPlugin getJavaPlugin() {
        return this;

    }
}
