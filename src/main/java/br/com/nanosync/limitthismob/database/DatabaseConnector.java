package br.com.nanosync.limitthismob.database;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DatabaseConnector {

    @SneakyThrows
    public DatabaseConnector(){
        Connection conn = new ConnectionFactory().getConnection();

        String chunks =  "CREATE TABLE IF NOT EXISTS LIMITMOBCHUNK (ID SERIAL PRIMARY KEY, MOB varchar(255), LIMITE INTEGER)";
        String worlds =  "CREATE TABLE IF NOT EXISTS WORLDS (ID SERIAL PRIMARY KEY, NAME varchar(255), LIMITED varchar(255))";

        PreparedStatement preparedStatementChunks = conn.prepareStatement(chunks);
        PreparedStatement preparedStatementWorlds = conn.prepareStatement(worlds);

        preparedStatementChunks.execute();
        preparedStatementWorlds.execute();

        preparedStatementChunks.close();
        preparedStatementWorlds.close();

        conn.close();
    }
}
