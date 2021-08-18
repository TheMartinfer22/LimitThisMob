package br.com.nanosync.limitthismob.commands;

import br.com.nanosync.limitthismob.Main;
import br.com.nanosync.limitthismob.database.DatabaseAPI;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;

public class LimitMobPerChunkCommand implements CommandExecutor, TabExecutor {
    private final String prefix = "§6[LimitThisMob] ";

    @SuppressWarnings("all")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission(Main.getInstance().getConfig().getString("PermissionCommand"))){
            sender.sendMessage(prefix + Main.getInstance().getConfig().getString("NoPermissionPhrase").replace("&", "§"));
            return true;
        }

        if (args.length < 1){
            sender.sendMessage(prefix + Main.getInstance().getConfig().getString("Help").replace("&", "§"));
            return true;
        }

        DatabaseAPI databaseAPI = new DatabaseAPI();
//        Set<Map.Entry<String, Integer>> memDataMob = databaseAPI.listMobChunk().entrySet();
//        Set<Map.Entry<String, String>> memDataWorld = databaseAPI.listWprlds().entrySet();

        switch (args[0]){
            case "add":
                if (args.length < 2) {
                    sender.sendMessage(prefix + Main.getInstance().getConfig().getString("MobAddedErrorNoArgs").replace("&", "§"));
                    return true;
                }

                if (args.length < 3){
                    sender.sendMessage(prefix + Main.getInstance().getConfig().getString("MobAddedErrorNoArgs2").replace("&", "§"));
                    return true;
                }
                try {
                    databaseAPI.addMobChunk(args[1], parseInt(args[2]));
                } catch (NumberFormatException e){
                    sender.sendMessage(prefix + Main.getInstance().getConfig().getString("MobAddedErrorOnlyNumber").replace("&", "§"));
                    return true;
                }
                sender.sendMessage(prefix + Main.getInstance().getConfig().getString("MobAddedSuccess").replace("&", "§"));
                break;

            case "list":
                sender.sendMessage(prefix + Main.getInstance().getConfig().getString("MobList").replace("&", "§"));
                sender.sendMessage("");
                sender.sendMessage("Mobs");
                sender.sendMessage("");
                databaseAPI.listMobChunk().forEach((mob, quantity) -> {
                    TextComponent delete = new TextComponent();
                    delete.setText(" [X] ");
                    delete.setBold(true);
                    delete.setColor(net.md_5.bungee.api.ChatColor.RED);
                    delete.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.RED + "Clique aqui para deletar.").create()));
                    delete.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/teste mobremove " + mob));
                    sender.spigot().sendMessage(new TextComponent(ChatColor.GREEN + "Mob: " + ChatColor.GOLD + mob + ChatColor.GREEN + " Limit: " + ChatColor.GOLD + quantity), delete);
                });
                sender.sendMessage("");
                sender.sendMessage("Worlds");
                sender.sendMessage("");
                databaseAPI.listWprlds().forEach(world -> {
                    TextComponent delete = new TextComponent();
                    delete.setText(" [X] ");
                    delete.setBold(true);
                    delete.setColor(net.md_5.bungee.api.ChatColor.RED);
                    delete.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.RED + "Clique aqui para deletar.").create()));
                    delete.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/teste worldremove " + world));
                    sender.spigot().sendMessage(new TextComponent(ChatColor.GREEN + "World: " + ChatColor.GOLD + world), delete);
                });
                break;

            case "worldremove":
                if (args.length < 1) return true;
                databaseAPI.removeWorld(args[1]);
                sender.sendMessage(prefix + Main.getInstance().getConfig().getString("WorldRemovedSuccess").replace("&", "§"));
                Bukkit.getPlayer(sender.getName()).performCommand("teste list");
                break;

            case "mobremove":
                if (args.length < 1) return true;
                databaseAPI.removeMobChunk(args[1]);
                sender.sendMessage(prefix + Main.getInstance().getConfig().getString("MobRemovedSuccess").replace("&", "§"));
                Bukkit.getPlayer(sender.getName()).performCommand("teste list");
                break;

            case "who":
                sender.sendMessage("");
                Arrays.stream(Bukkit.getPlayer(sender.getName()).getLocation().getChunk().getEntities()).forEach(entityFound -> {
                    if (entityFound instanceof Player) return;
                    if (entityFound instanceof Item) return;
                    TextComponent text = new TextComponent();
                    TextComponent copy = new TextComponent();
                    text.setText(ChatColor.GREEN + "Mob: " + ChatColor.GOLD + entityFound.getName());
                    copy.setText(" [Add] ");
                    copy.setBold(true);
                    copy.setColor(net.md_5.bungee.api.ChatColor.DARK_PURPLE);
                    copy.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GREEN + "Clique para adicionar a lista de limitação.").create()));
                    copy.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/teste add " + entityFound.getName() + " LIMIT_HERE"));
                    sender.spigot().sendMessage(text, copy);
                });
                sender.sendMessage("");
                break;

            case "world":
                if (args.length < 2){
                    sender.sendMessage(prefix + Main.getInstance().getConfig().getString("WorldArgsInvalid").replace("&", "§"));
                    return true;
                }

                if (args[1].equals("add")){
                    if (args.length < 3){
                        sender.sendMessage(prefix + Main.getInstance().getConfig().getString("WorldArgsInvalid2").replace("&", "§"));
                        return true;
                    }
                    sender.sendMessage(prefix + Main.getInstance().getConfig().getString("WorldAddedSuccess").replace("&", "§"));
                    databaseAPI.addWorld(args[2]);
                    return true;
                }

                if (args[1].equals("addall")){
                    sender.sendMessage(prefix + Main.getInstance().getConfig().getString("WorldAddAll").replace("&", "§"));
                    databaseAPI.purgeAllWorlds();
                    Bukkit.getWorlds().forEach(existingWorld -> {
                        databaseAPI.addWorld(existingWorld.getName());
                    });
                    return true;
                }

                sender.sendMessage(prefix + Main.getInstance().getConfig().getString("WorldArgsInvalid").replace("&", "§"));
                break;

            default: sender.sendMessage(prefix + Main.getInstance().getConfig().getString("Help").replace("&", "§"));
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return Arrays.asList("add", "list", "world", "who");
    }
}
