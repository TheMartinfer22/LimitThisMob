package br.com.nanosync.limitthismob.events;

import br.com.nanosync.limitthismob.memories.api.MemoriesAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import java.util.Arrays;

public class LimitMobChunkEvent implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void limit(EntitySpawnEvent event){

        MemoriesAPI.getMemoriesWorldsAPI().forEach(entryWorld -> {
            if (event.getLocation().getWorld().getName().equals(entryWorld)){
                MemoriesAPI.getMemoriesMobsAPI().forEach((mob, quantity) -> {
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
