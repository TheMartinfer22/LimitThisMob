package br.com.nanosync.limitthismob.events;

import br.com.nanosync.limitthismob.database.DatabaseAPI;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.Arrays;

public class LimitMobChunkEvent implements Listener {

    @EventHandler
    public void limit(EntitySpawnEvent event){
        DatabaseAPI databaseAPI = new DatabaseAPI();
        databaseAPI.listWprlds().forEach(entryWorld -> {
            if (event.getLocation().getWorld().getName().equals(entryWorld)){
                databaseAPI.listMobChunk().forEach((mob, quantity) -> {
                    if  (event.getEntity().getName().equals(mob)){
                        int quantidade = (int) Arrays.stream(event.getLocation().getChunk().getEntities()).filter(e -> e.getName().equals(mob)).count();
                        if (quantidade >= quantity){
                            event.setCancelled(true);
                        }
                    }
                });
            }
        });
    }
}
