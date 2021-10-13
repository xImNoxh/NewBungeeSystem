package de.kyoro.bansystem.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Config {

    private String address;
    private String passwort;
    private String user;
    private String database;
    private int port;
    private String prefix;
}
