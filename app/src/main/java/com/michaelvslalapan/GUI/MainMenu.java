package com.michaelvslalapan.GUI;

import javax.swing.*;
import java.awt.*;

public class MainMenu {
    public MainMenu(){
        JFrame frameMainMenu = new JFrame();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Cari Screen Size device (buat pastiin ratio window ke screen size ya sama di semua device)
        int height = (int) (screenSize.getWidth()/3);

        ImageIcon startPopUpImage = new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\com\\michaelvslalapan\\Assets\\StartPopUpMenu.png");
        ImageIcon scaledStartPopUpImage = new ImageIcon(startPopUpImage.getImage().getScaledInstance(height, height, Image.SCALE_DEFAULT));


        JLabel startPopUpLabel = new JLabel(scaledStartPopUpImage);

        frameMainMenu.setSize(height,height);
        frameMainMenu.add(startPopUpLabel);
        frameMainMenu.pack();

        JButton button = new JButton();
        button.setBounds(100,100,(int) (height*48/28/5), (int) (height*28/48/5));

        button.setIcon(new ImageIcon ((new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\com\\michaelvslalapan\\Assets\\Start.gif")).getImage().getScaledInstance((int) (height*48/28/5), (int) (height*28/48/5), Image.SCALE_DEFAULT)));
        

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
