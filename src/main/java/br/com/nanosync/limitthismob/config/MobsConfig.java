package br.com.nanosync.limitthismob.config;

import br.com.nanosync.limitthismob.database.DatabaseConnector;
import lombok.SneakyThrows;

public class MobsConfig {
    @SneakyThrows
    public void createConfigs(){
        new DatabaseConnector();
        System.out.println("[+] LimitThisMob foi carregado com sucesso.");
    }
}
