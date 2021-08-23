package br.com.nanosync.limitthismob;

import br.com.nanosync.limitthismob.commands.LimitMobPerChunkCommand;
import br.com.nanosync.limitthismob.config.MobsConfig;
import br.com.nanosync.limitthismob.events.LimitMobChunkEvent;
import br.com.nanosync.limitthismob.memories.api.MemoriesAPI;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @SneakyThrows
    @Override
    public void onEnable() {
        Class.forName("org.h2.Driver");
        saveDefaultConfig();
        new MemoriesAPI().load();
        new MobsConfig().createConfigs();
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new LimitMobChunkEvent(), this);
        getCommand("mob").setExecutor(new LimitMobPerChunkCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getInstance(){
        return getPlugin(Main.class);
    }
}
