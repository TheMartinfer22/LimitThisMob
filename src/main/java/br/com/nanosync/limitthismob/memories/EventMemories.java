package br.com.nanosync.limitthismob.memories;

import br.com.nanosync.limitthismob.database.DatabaseAPI;

import java.util.HashMap;
import java.util.HashSet;

public class EventMemories {
    public HashSet<String> updateAllWorlds(){
        return new DatabaseAPI().listWprlds();
    }

    public HashMap<String, Integer> updateAllMobs(){
        return new DatabaseAPI().listMobChunk();
    }
}
