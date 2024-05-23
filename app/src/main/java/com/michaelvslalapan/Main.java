package com.michaelvslalapan;

import javax.swing.JFrame;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.michaelvslalapan.Game.Game;
import com.michaelvslalapan.SaveAndLoad.Load;
import com.michaelvslalapan.SaveAndLoad.Save;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main{
    public static Game maingame;
    public static void main(String[] args){
        int inset=38;
        if(System.getProperty("os.name").equals("Mac OS X")){
            inset=22;
        }else if(System.getProperty("os.name").equals("Linux")){
            inset=37;
        }
        
        JFrame frame = new JFrame();

        String directory = System.getProperty("user.dir") + "/src/main/resources/SaveFiles/SaveFile.json";
        
        File previousGame = new File(directory);
        
        maingame = new Game(previousGame.exists());

        try {    
            Load.loadGame();
            System.out.println("Starting a Previous Game !");
        } catch (FileNotFoundException e) {
            System.out.println("Starting a New Game");
        } catch (Exception e){
            e.printStackTrace();
        }
        frame.add(maingame);
        frame.setTitle("Michael vs Lalapan");
        frame.setBounds(127, 0, 1024, 625+inset);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Scanner input = new Scanner(System.in);

        int a = input.nextInt();

        try {
            Save.saveGame();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
