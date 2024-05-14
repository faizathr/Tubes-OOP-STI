package com.michaelvslalapan.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.FlowLayout.*;

public class GameMainMenu {
    public GameMainMenu(){
        JFrame frameMainMenu = new JFrame("Michael vs Lalapan (Plants vs Zombies)");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Cari Screen Size device (buat pastiin ratio window ke screen size ya sama di semua device)
        int height = (int) (screenSize.getWidth()/3);
        frameMainMenu.setSize(height,height);
        frameMainMenu.setLayout(new GridLayout(7,3, 5 , 20));
        frameMainMenu.setVisible(true);
        frameMainMenu.setResizable(false);
        frameMainMenu.setAlwaysOnTop(true);
        frameMainMenu.setLocationRelativeTo(null);
        frameMainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int i = 7;
        int j = 3;
        JPanel[][] panelHolder = new JPanel[i][j];    

        for(int m = 0; m < i; m++) {
        for(int n = 0; n < j; n++) {
            panelHolder[m][n] = new JPanel();
            frameMainMenu.add(panelHolder[m][n]);
   }
}

        JButton buttonStart = new JButton(new ImageIcon ((new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\com\\michaelvslalapan\\Assets\\StartGameButton.gif")).getImage().getScaledInstance((int) (height*800/427/11.5), (int) (height/11.5), Image.SCALE_DEFAULT)));
        JButton buttonPlantsList = new JButton(new ImageIcon ((new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\com\\michaelvslalapan\\Assets\\PlantsListButton.png")).getImage().getScaledInstance((int) (height*1024/348/14), (int) (height/14), Image.SCALE_DEFAULT)));
        JButton buttonZombieList = new JButton(new ImageIcon ((new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\com\\michaelvslalapan\\Assets\\ZombieListButton.png")).getImage().getScaledInstance((int) (height*1024/349/14), (int) (height/14), Image.SCALE_DEFAULT)));
        JButton buttonExit = new JButton(new ImageIcon ((new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\com\\michaelvslalapan\\Assets\\ExitButton.png")).getImage().getScaledInstance((int) (height*1023/680/12), (int) (height/12), Image.SCALE_DEFAULT)));
        JButton buttonHelp = new JButton(new ImageIcon ((new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\com\\michaelvslalapan\\Assets\\HelpButton.png")).getImage().getScaledInstance((int) (height*1024/550/13), (int) (height/13), Image.SCALE_DEFAULT)));

        buttonStart.setBackground(Color.WHITE);
        buttonStart.setOpaque(true);
        buttonStart.setBorderPainted(false);
        buttonPlantsList.setBackground(Color.WHITE);
        buttonPlantsList.setOpaque(true);
        buttonPlantsList.setBorderPainted(false);
        buttonZombieList.setBackground(Color.WHITE);
        buttonZombieList.setOpaque(true);
        buttonZombieList.setBorderPainted(false);
        buttonExit.setBackground(Color.WHITE);
        buttonExit.setOpaque(true);
        buttonExit.setBorderPainted(false);
        buttonHelp.setBackground(Color.WHITE);
        buttonHelp.setOpaque(true);
        buttonHelp.setBorderPainted(false);

        panelHolder[1][1].add(buttonStart);
        panelHolder[2][1].add(buttonPlantsList);
        panelHolder[3][1].add(buttonZombieList);
        panelHolder[4][1].add(buttonExit);
        panelHolder[5][1].add(buttonHelp);

    }  
}
