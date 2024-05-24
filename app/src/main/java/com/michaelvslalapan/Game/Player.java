package com.michaelvslalapan.Game;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GraphicsEnvironment;
import javax.swing.JOptionPane;

public class Player {
    private int sunCredits, sunChanges;
    private boolean isUsingShovel = false;
    private Font font;
    private int selectedPlant = -1;

    private String[] PlantID = new String[]{
        "Sunflower",
        "Peashooter",
        "Wallnut",
        "SnowPea",
        "Squash",
        "Lilypad",
        "Repeater",
        "ExplodeONut",
        "Cherrybomb",
        "FumeShroom"
    };
    private int[] PlantCost = new int[]{
        50, 100, 50, 175, 50, 25, 200, 100, 25, 100
    };

    public int getPlantID(String PlantName) {
        for (int i = 0; i < PlantID.length; i++) {
            if (PlantName.equals(PlantID[i])) return i;
        }
        return -1;
    }

    public Player() {
        sunCredits = 25;
        sunChanges = sunCredits;
        loadFont();
    }

    private void loadFont() {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("../Assets/font/Chalkboard.ttc").openStream()).deriveFont(Font.BOLD, 20f);
            GraphicsEnvironment GUIEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GUIEnv.registerFont(font);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Unable to open font!");
        }
    }

    public void draw(Graphics2D GUI){
        GUI.setFont(font); 
        GUI.setColor(Color.BLACK);
        FontMetrics metrics = GUI.getFontMetrics(font);
        if (sunCredits == sunChanges) {
            GUI.drawString(Integer.toString(sunChanges), 91-(metrics.stringWidth(Integer.toString(sunChanges))/2), 136);
        } else if(sunCredits < sunChanges) {
            sunChanges -= 5;
            GUI.drawString(Integer.toString(sunChanges), 91-(metrics.stringWidth(Integer.toString(sunChanges))/2), 136);
        } else {
            sunChanges += 5;
            GUI.drawString(Integer.toString(sunChanges), 91-(metrics.stringWidth(Integer.toString(sunChanges))/2), 136);
        }
    }

    public int getSunCredits() {
        return sunCredits;
    }

    public void addSunCredits() {
        sunCredits += 25;
    }

    public void resetSunCredits() {
        sunCredits = 25;
    }

    public void spendSunCredits(int spendValue) {
        sunCredits -= spendValue;
    }

    public int getSelectedPlant() {
        return selectedPlant;
    }

    public void selectPlant(int PlantID) {
        selectedPlant = PlantID;
    }

    public boolean isUsingShovel() {
        return isUsingShovel;
    }

    public void setShovel(boolean isUsingShovel) {
        this.isUsingShovel = isUsingShovel;
    }

    public void plantSelected() {
        spendSunCredits(PlantCost[selectedPlant]);
    }

    public void emptyPlantSelection() {
        selectedPlant = -1;
    }
}
