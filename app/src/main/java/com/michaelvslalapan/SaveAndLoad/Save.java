package com.michaelvslalapan.SaveAndLoad;
 
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URL;
import java.io.File;
import java.nio.file.Paths;
import java.io.FileWriter;
import com.michaelvslalapan.Main;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Save {
    public void saveGame() throws JsonProcessingException, IOException  {
        ObjectMapper mapper = new ObjectMapper();

        String JSONsave = mapper.writeValueAsString(Main.maingame);

        FileWriter writer = new FileWriter("SaveFile.json");

        writer.write(JSONsave);

        writer.close();
    }

    public static void main(String args[]) {
        
    }
}
