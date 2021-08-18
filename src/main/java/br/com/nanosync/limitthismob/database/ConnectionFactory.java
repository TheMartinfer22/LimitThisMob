package br.com.nanosync.limitthismob.database;

import br.com.nanosync.limitthismob.Main;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    @SneakyThrows
    public Connection getConnection(){
        return DriverManager.getConnection("jdbc:h2:" + Main.getInstance().getDataFolder().getAbsolutePath() + "/data/database", "sa", "");
    }
}
