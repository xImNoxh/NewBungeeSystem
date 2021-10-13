package de.kyoro.bansystem.config;

import com.google.gson.Gson;
import lombok.Getter;

import java.io.*;

@Getter
public class ConfigReader {

    private Gson gson = new Gson();

    private final File file = new File("plugins//KyoroAPI//config.json");

    public void saveConfig() {

        Config config = new Config("localhost", "passwort", "user", "database", 3306, "§8┃ §e§lKyoroDE §8┃ ");



        try (FileWriter fileWriter = new FileWriter(file)) {
            if(!file.exists()) file.createNewFile();
            fileWriter.write(gson.toJson(config));

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Config readConfig() {

        if(!file.exists()) saveConfig();

        try {
            Config config = gson.fromJson(new FileReader(file), Config.class);
            return config;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }
}
