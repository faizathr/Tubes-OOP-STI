package com.michaelvslalapan.SaveAndLoad;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.michaelvslalapan.Game.Game;
import com.michaelvslalapan.Main;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Save {
    public static void saveGame() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        // Register any necessary modules (e.g., custom serializers if needed)
        SimpleModule module = new SimpleModule();
        mapper.registerModule(module);

        String directory = System.getProperty("user.dir") + "/src/main/resources/SaveFiles/SaveFile.json";

        // Configure the pretty printer
        DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
        DefaultPrettyPrinter.Indenter indenter = new DefaultIndenter("  ", "\n");
        prettyPrinter.indentArraysWith(indenter);
        prettyPrinter.indentObjectsWith(indenter);

        // Create a map to hold all lists
        Map<String, Object> gameData = new HashMap<>();
        gameData.put("plants", Game.plants);
        gameData.put("zombies", Game.zombies);
        gameData.put("suns", Game.suns);
        gameData.put("peas", Game.peas);
        gameData.put("sunCredits", Main.maingame.getSunCredits());
        gameData.put("secondsTime", Game.getSecondsTime());
        // gameData.put("gameTimer", Main.maingame.getGameTimer());
        gameData.put("zombieInMap", Game.getZombieInMapCount());
        gameData.put("zombieWave", Game.getWave());
        gameData.put("isNight", Main.maingame.getIsNight());
        gameData.put("plantDeck", Main.maingame.getPlantDeck());
        gameData.put("realCooldownPlantList", Game.getRealCoolDownList());

        // Serialize the map and write to the file
        mapper.writer(prettyPrinter).writeValue(new File(directory), gameData);
    }
}
