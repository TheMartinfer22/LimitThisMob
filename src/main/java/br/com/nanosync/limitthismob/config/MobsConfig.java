package br.com.nanosync.limitthismob.config;

import lombok.SneakyThrows;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Map;

public class MobsConfig {
//    final File worldMobsFile = new File("plugins/LimitThisMob/MobsWorld.yml");
    final File chunkMobsFile = new File("plugins/LimitThisMob/MobsChunk.yml");
//    final YamlConfiguration worldMobsYML = YamlConfiguration.loadConfiguration(worldMobsFile);
    final YamlConfiguration chunkMobsYML = YamlConfiguration.loadConfiguration(chunkMobsFile);

    @SneakyThrows
    public void createConfigs(){
//        if (!worldMobsFile.exists()){
//            worldMobsFile.createNewFile();
//            worldMobsYML.set("Enabled", true);
//            worldMobsYML.set("ListMobQuantity", "");
//            worldMobsYML.save(worldMobsFile);
//        }

        if (!chunkMobsFile.exists()){
            chunkMobsFile.createNewFile();
//            chunkMobsYML.set("Enabled", true);
            chunkMobsYML.set("ListMobQuantity", "");
            chunkMobsYML.save(chunkMobsFile);
        }
        System.out.println("[+] LimitThisMob foi carregado com sucesso.");
    }

    public Map<?, ?> getChunkMobs(){
        return chunkMobsYML.getConfigurationSection("ListMobQuantity").getValues(true);
    }

//    @SneakyThrows
//    public void saveWorldMobsConfig(String key, Object value){
//        worldMobsYML.set(key, value);
//        worldMobsYML.save(worldMobsFile);
//    }

    @SneakyThrows
    public void saveChunkMobsConfig(String key, Object value){
        chunkMobsYML.set(key, value);
        chunkMobsYML.save(chunkMobsFile);
    }
}
