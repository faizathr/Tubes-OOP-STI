package com.michaelvslalapan.SaveAndLoad;
 
import java.io.File;  // Import the File class
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.michaelvslalapan.Main;
import com.michaelvslalapan.Game.Game;
import com.michaelvslalapan.Game.Pea;
import com.michaelvslalapan.Game.Sun;
import com.michaelvslalapan.Organism.Tanaman.Plants;
import com.michaelvslalapan.Organism.Zombie.Zombie;
import java.util.Map;
import java.util.List;

public class Load {
    
    public static void loadGame() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        mapper.registerModule(module);

        String directory = System.getProperty("user.dir") + "/src/main/resources/SaveFiles/SaveFile.json";

        Map<String, Object> gameData = mapper.readValue(new File(directory), new TypeReference<Map<String, Object>>() {});

        Game.plants = mapper.convertValue(gameData.get("plants"), new TypeReference<List<Plants<Integer>>>() {});
        Main.maingame.setPlantDeck(mapper.convertValue(gameData.get("plantDeck"), new TypeReference<int[]>() {}));
        Game.zombies = mapper.convertValue(gameData.get("zombies"), new TypeReference<List<Zombie>>() {});
        Game.suns = mapper.convertValue(gameData.get("suns"), new TypeReference<List<Sun>>() {});
        Game.peas = mapper.convertValue(gameData.get("peas"), new TypeReference<List<Pea>>() {});
        Main.maingame.setSunCredits(mapper.convertValue(gameData.get("sunCredits"), Integer.class));
        // Main.maingame.setGameTimer(mapper.convertValue(gameData.get("gameTimer"), Timer.class));
        Game.setZombieInMapCount(mapper.convertValue(gameData.get("zombieInMap"), Integer.class));
        Game.setWave(mapper.convertValue(gameData.get("zombieWave"), Integer.class));
        Main.maingame.setIsNight(mapper.convertValue(gameData.get("isNight"), Boolean.class));
        Game.setRealCoolDownList(mapper.convertValue(gameData.get("realCooldownPlantList"), new TypeReference<List<Double>>() {}));
        Game.setSecondsTime(mapper.convertValue(gameData.get("secondsTime"), Integer.class));

    }

    public static void main(String args[]) {
    }
}
