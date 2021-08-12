package br.com.nanosync.limitthismob;

import br.com.nanosync.limitthismob.commands.LimitMobPerChunkCommand;
import br.com.nanosync.limitthismob.config.MobsConfig;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        new MobsConfig().createConfigs();
        getCommand("teste").setExecutor(new LimitMobPerChunkCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getInstance(){
        return getPlugin(Main.class);
    }
}
