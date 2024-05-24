package com.michaelvslalapan;

import javax.swing.JFrame;
import com.michaelvslalapan.Game.Game;
import com.michaelvslalapan.Game.Pea;
import com.michaelvslalapan.Game.Sun;
import com.michaelvslalapan.Organism.Tanaman.Plants;
import com.michaelvslalapan.Organism.Zombie.Zombie;

import java.io.File;
import java.util.ArrayList;

public class Main{
    public static Game maingame;
    public static JFrame frame;
    public static File previousGameFile;
    private static String directory;

    public static void restartGame(){
        Main.maingame = new Game();
        Main.frame.setContentPane(maingame);
        Main.frame.repaint();
        Main.frame.revalidate();
        Main.previousGameFile = new File(directory);
    }
    public static void main(String[] args){
        int inset=38;
        if(System.getProperty("os.name").equals("Mac OS X")){
            inset=22;
        }else if(System.getProperty("os.name").equals("Linux")){
            inset=37;
        }
        
        frame = new JFrame();

        directory = System.getProperty("user.dir") + "/src/main/resources/SaveFiles/SaveFile.json";
        previousGameFile = new File(directory);

        maingame = new Game();

        frame.add(maingame);
        frame.setTitle("Michael vs Lalapan");
        frame.setBounds(127, 0, 1024, 625+inset);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);
        frame.setLocationRelativeTo(null);
    }
}
