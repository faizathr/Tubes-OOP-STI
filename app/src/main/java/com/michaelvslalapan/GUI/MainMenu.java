package com.michaelvslalapan.GUI;

import javax.swing.*;
import java.awt.*;

public class MainMenu {
    public MainMenu(){
        JFrame frameMainMenu = new JFrame();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Cari Screen Size device (buat pastiin ratio window ke screen size ya sama di semua device)

        ImageIcon startPopUpImage = new ImageIcon(getClass().getResource("/app/src/main/java/com/michaelvslalapan/Assets/StartPopUpMenu.png").toString());
        
        JLabel startPopUpLabel = new JLabel(startPopUpImage);
        startPopUpLabel.setSize((int) screenSize.getWidth()/3, (int) (screenSize.getWidth()/3));
        frameMainMenu.setSize((int) screenSize.getWidth()/3, (int) (screenSize.getWidth()/3));
        frameMainMenu.add(startPopUpLabel);

        JButton button = new JButton("Start Playing");
        button.setBounds(130,100,100,40);
        button.setIcon(new ImageIcon(""));

        frameMainMenu.add(button);
        frameMainMenu.setLayout(null);
        frameMainMenu.setVisible(true);
        frameMainMenu.setResizable(false);
        frameMainMenu.setAlwaysOnTop(true);
        frameMainMenu.setLocationRelativeTo(null);
        frameMainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        MainMenu test = new MainMenu();
    }
}
