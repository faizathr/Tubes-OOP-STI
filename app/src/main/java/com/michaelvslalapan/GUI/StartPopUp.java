package com.michaelvslalapan.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartPopUp {
    public StartPopUp(){
        JFrame frameStartPopUp = new JFrame("Michael vs Lalapan (Plants vs Zombies)");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Cari Screen Size device (buat pastiin ratio window ke screen size ya sama di semua device)
        int height = (int) (screenSize.getWidth()/3);

        ImageIcon startPopUpImage = new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\com\\michaelvslalapan\\Assets\\StartPopUpMenu.png");
        ImageIcon scaledStartPopUpImage = new ImageIcon(startPopUpImage.getImage().getScaledInstance(height, height, Image.SCALE_DEFAULT));

        JLabel startPopUpLabel = new JLabel(scaledStartPopUpImage);

        frameStartPopUp.setSize(height,height);
        frameStartPopUp.add(startPopUpLabel);
        frameStartPopUp.pack();

        JButton button = new JButton(new ImageIcon ((new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\com\\michaelvslalapan\\Assets\\Start.gif")).getImage().getScaledInstance((int) (height*800/427/5), (int) (height*427/800/5), Image.SCALE_DEFAULT)));
        button.setBounds(height/3,height/3,(int) (height*48/28/5), (int) (height*28/48/5));
        button.setBackground(Color.WHITE);
        button.setOpaque(true);
        button.setBorderPainted(true);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 6));

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new GameMainMenu();
                frameStartPopUp.dispatchEvent(new WindowEvent(frameStartPopUp, WindowEvent.WINDOW_CLOSING));

            }
        });

        frameStartPopUp.add(button);
        frameStartPopUp.setLayout(null);
        frameStartPopUp.setVisible(true);
        frameStartPopUp.setResizable(false);
        frameStartPopUp.setAlwaysOnTop(true);
        frameStartPopUp.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new StartPopUp();
    }
}
