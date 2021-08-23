package br.com.nanosync.limitthismob.memories.api;

import br.com.nanosync.limitthismob.database.api.DatabaseAPI;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.HashSet;
public class MemoriesAPI {

    static HashMap<String, Integer> memoriesMobsAPI = new HashMap<>();
    static HashSet<String> memoriesWorldsAPI = new HashSet<>();

    public void load(){
        DatabaseAPI databaseAPI = new DatabaseAPI();
        memoriesMobsAPI.putAll(databaseAPI.listMobChunk());
        memoriesWorldsAPI.addAll(databaseAPI.listWprlds());
    }

    public void reload(){
        memoriesMobsAPI.clear();
        memoriesWorldsAPI.clear();
        load();
    }

    public static HashSet<String> getMemoriesWorldsAPI(){
        return memoriesWorldsAPI;
    }

    public static HashMap<String, Integer> getMemoriesMobsAPI(){
        return memoriesMobsAPI;
    }
}
