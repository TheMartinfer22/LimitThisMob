package br.com.nanosync.limitthismob.database;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;

public class DatabaseAPI {

    public static

    // Mob Chunk Scope
    @SneakyThrows
    public void addMobChunk(String mob, Integer limite){
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection conn  = connectionFactory.getConnection();
        Statement statement = conn.createStatement();
        String sql = "INSERT INTO LIMITMOBCHUNK (MOB, LIMITE) VALUES ('" + mob + "', " + limite + ")";
        statement.execute(sql);
        statement.close();
    }

    @SneakyThrows
    public void removeMobChunk(String mob){
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection conn  = connectionFactory.getConnection();
        Statement statement = conn.createStatement();
        String sql = "DELETE FROM LIMITMOBCHUNK where MOB = '" + mob + "'";
        statement.execute(sql);
        conn.close();
        statement.close();
    }

    @SneakyThrows
    public HashMap<String, Integer> listMobChunk(){
        HashMap<String, Integer> memlist = new HashMap<>();
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection conn  = connectionFactory.getConnection();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM LIMITMOBCHUNK");
        while (resultSet.next()){
            memlist.put(resultSet.getString("MOB"), resultSet.getInt("LIMITE"));
        }
        conn.close();
        statement.close();
        return memlist;
    }

    // World Scope

    @SneakyThrows
    public void addWorld(String worldname){
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection conn  = connectionFactory.getConnection();
        Statement statement = conn.createStatement();
        statement.execute("INSERT INTO WORLDS (NAME) VALUES ('" + worldname + "')");
        conn.close();
        statement.close();
    }

    @SneakyThrows
    public void removeWorld(String worldname){
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection conn  = connectionFactory.getConnection();
        Statement statement = conn.createStatement();
        statement.execute("DELETE FROM WORLDS WHERE NAME = '" + worldname + "'");
        conn.close();
        statement.close();
    }

    @SneakyThrows
    public HashSet<String> listWprlds(){
        HashSet<String> memList = new HashSet<>();
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection conn  = connectionFactory.getConnection();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM WORLDS");
        while (resultSet.next()){
            memList.add(resultSet.getString("NAME"));
        }
        conn.close();
        statement.close();
        return memList;
    }

    @SneakyThrows
    public void purgeAllWorlds(){
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection conn  = connectionFactory.getConnection();
        Statement statement = conn.createStatement();
        statement.execute("DELETE FROM WORLDS");
        conn.close();
        statement.close();
    }
}
