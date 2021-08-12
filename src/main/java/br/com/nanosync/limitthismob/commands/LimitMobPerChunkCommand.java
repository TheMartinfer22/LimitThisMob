package br.com.nanosync.limitthismob.commands;

import br.com.nanosync.limitthismob.Main;
import br.com.nanosync.limitthismob.config.MobsConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Long.parseLong;

public class LimitMobPerChunkCommand implements CommandExecutor {
    MobsConfig mobsConfig = new MobsConfig();

    @SuppressWarnings("all")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission(Main.getInstance().getConfig().getString("PermissionCommand"))){
            sender.sendMessage("[LimitThisMob] " + Main.getInstance().getConfig().getString("NoPermissionPhrase").replace("&", "§"));
            return true;
        }

        if (args.length < 1){
            sender.sendMessage("[LimitThisMob] " + Main.getInstance().getConfig().getString("Help").replace("&", "§"));
            return true;
        }

        switch (args[0]){
            case "add":
                if (args.length < 2) {
                    sender.sendMessage("[LimitThisMob] " + Main.getInstance().getConfig().getString("MobAddedErrorNoArgs").replace("&", "§"));
                    return true;
                }

                if (args.length < 3){
                    sender.sendMessage("[LimitThisMob] " + Main.getInstance().getConfig().getString("MobAddedErrorNoArgs2").replace("&", "§"));
                    return true;
                }

                HashMap<String, Long> mobList = new HashMap<>();
                mobList.putAll((Map<? extends String, ? extends Long>) mobsConfig.getChunkMobs());
                try {
                    mobList.put(args[1], parseLong(args[2]));
                } catch (Exception e){
                    sender.sendMessage("[LimitThisMob] " + Main.getInstance().getConfig().getString("MobAddedErrorOnlyLong").replace("&", "§"));
                }
                mobsConfig.saveChunkMobsConfig("ListMobQuantity", mobList);
                sender.sendMessage("[LimitThisMob] " + Main.getInstance().getConfig().getString("MobAddedSuccess").replace("&", "§"));
        }
        return false;
    }
}
