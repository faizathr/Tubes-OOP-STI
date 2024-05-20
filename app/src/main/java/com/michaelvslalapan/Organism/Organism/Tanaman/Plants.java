package com.michaelvslalapan.Organism.Tanaman;

import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip;

import com.michaelvslalapan.Organism.Organism;
import com.michaelvslalapan.Organism.Zombie.Zombie;
import com.michaelvslalapan.ADT.Point;
import com.michaelvslalapan.Game.Game;
import com.michaelvslalapan.Game.Pea;
import com.michaelvslalapan.Game.Sun;

public class Plants<PlantID> extends Organism {
    private PlantID ID;
    private int cost, range, LaneX, LaneY; // [9][6]
    private boolean isThreaten = false, isIdle = true;

    private static boolean[][] isSlotFilled = new boolean[9][6];
    private static Point[][] MapSlot = new Point[9][6];

    private static boolean[][] isLilypadSlotFilled = new boolean[9][6];
    private static Point[][] LilypadMapSlot = new Point[9][6];

    private double cooldown;
    private Timer peaTimer;
    private Timer repeaterPeaTimer;
    private Timer sunDropTimer;
    private double attackDamage;
    private int plantHeight = 66, plantWidth = 62;
    Thread threadToExplode;
    private boolean isExploded = false;
    private boolean isEnlarging = false;

    private Plants<Integer> newPlant = new Sunflower(0, 0);

    public Plants(PlantID ID, String name, int cost, double health, double attackDamage, double attackSpeed, int range, double cooldown, Boolean is_aquatic, int LaneX, int LaneY) {
        super(name, health, attackDamage, attackSpeed, is_aquatic);
        this.ID = ID;  
        this.cost = cost;
        this.range = range;
        this.cooldown = cooldown;
        this.attackDamage = attackDamage;
        if (ID.equals(7)) {
            threadToExplode = new Thread(new waitToExplode());
        } else if (ID.equals(8)) {
            threadToExplode = new Thread(new waitToExplode());
            plantHeight = 74;
            plantWidth = 76;
        }
    }

    /*
    public void setNewPlants(int PlantID, int LaneX, int LaneY) {
        Plants<Integer>[] plantConstructor = new Plants<Integer>[] {
            new Sunflower(LaneX, LaneY),
            new Peashooter(LaneX, LaneY),
            new Wallnut(LaneX, LaneY),
            new SnowPea(LaneX, LaneY),
            new Squash(LaneX, LaneY),
            new Lilypad(LaneX, LaneY),
            new Repeater(LaneX, LaneY),
            new ExplodeONut(LaneX, LaneY),
            new CherryBomb(LaneX, LaneY),
            new Puffshroom(LaneX, LaneY),
        };
        newPlant = plantConstructor[PlantID];
    }
     */


    public void setNewPlants(int PlantID, int LaneX, int LaneY) {
        switch (PlantID) {
            case 0:
                newPlant = new Sunflower(LaneX, LaneY);
            case 1:
                newPlant = new Peashooter(LaneX, LaneY);
            case 2:
                newPlant = new Wallnut(LaneX, LaneY);
            case 3:
                newPlant = new SnowPea(LaneX, LaneY);
            case 4:
                newPlant = new Squash(LaneX, LaneY);
            case 5:
                newPlant = new Lilypad(LaneX, LaneY);
            case 6:
                newPlant = new Repeater(LaneX, LaneY);
            case 7:
                newPlant = new ExplodeONut(LaneX, LaneY);
            case 8:
                newPlant = new CherryBomb(LaneX, LaneY);
            case 9:
                newPlant = new Puffshroom(LaneX, LaneY);
        }
    }
    

    // Getter Methods
    public int getCost() {
        return cost;
    }

    public int getRange() {
        return range;
    }

    public double getCooldown() {
        return cooldown;
    }

    public PlantID getPlantID() {
        return ID;
    }

    public boolean getIsExploded(){
        return isExploded;
    }

    public boolean getIsEnlarging(){
        return isEnlarging;
    }

    public int getLaneX() {
        return LaneX;
    }
    public int getLaneY() {
        return LaneY;
    }

    public int getPlantHeight() {
        return plantHeight;
    }
    public int getPlantWidth() {
        return plantWidth;
    }

    public boolean getIsThreaten() {
        return isThreaten;
    }

    public boolean getIsIdle() {
        return isIdle;
    }

    // Setter Methods
    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public void zombie_Attack_Plants(Zombie z) {
        z.decreaseHealth(this.get_Attack_Damage());
    } // buat plant melakukan attack ke zombie 

    public void explodePlant(){ // tanaman meledak, setelah meledak, tanaman mati
        this.isExploded = true;
    }

    public static boolean getIsSlotFilled(int x, int y) {
        return isSlotFilled[x][y];
    }
    public static Point getMapSlot(int x, int y) {
        return MapSlot[x][y];
    }

    public static void emptySlot(int x, int y){
        isSlotFilled[x][y] = false;
    }
    public static void setMapSlot(int x, int y){
        MapSlot[x][y] = new Point(265 + 82 * x, 50 + 88 * y);
    }

    public static boolean getIsLilypadSlotFilled(int x, int y) {
        return isLilypadSlotFilled[x][y];
    }
    public static Point getLilypadMapSlot(int x, int y) {
        return LilypadMapSlot[x][y];
    }

    public static void emptyLilypadSlot(int x, int y){
        isLilypadSlotFilled[x][y] = false;
    }
    public static void setLilypadMapSlot(int x, int y){
        LilypadMapSlot[x][y] = new Point(265 + 82 * x, 75 + 88 * y);
    }

    public void setIsThreaten(boolean isThreaten) {
        this.isThreaten = isThreaten;
    }

    public boolean plant(int LaneX, int LaneY, PlantID ID){
        if(!isSlotFilled[LaneX][LaneY]){
            isSlotFilled[LaneX][LaneY] = true;
            setNewPlants((int) ID, LaneX, LaneY);
            Game.plants.add(newPlant);
            return true;
        } else return false;
    }

    public boolean plantLilypad(int LaneX, int LaneY){
        if(!isLilypadSlotFilled[LaneX][LaneY]){
            isLilypadSlotFilled[LaneX][LaneY] = true;
            Game.plants.add(new Lilypad(LaneX, LaneY));
            return true;
        } else return false;
    }

    public void attack(){
        peaTimer.start();
        if (ID.equals(6)) repeaterPeaTimer.start();
        isIdle = false;
    }

    public void enlargeExplodingPlant(){
        isEnlarging = true;
        plantWidth+=1; plantHeight+=1;
    }

    public void startProducingSun() {
        sunDropTimer.start();
    }

    public void stop(){
        peaTimer.stop();
        repeaterPeaTimer.stop();
        sunDropTimer.stop();
        isIdle = true;
    }

    public class waitToExplode implements Runnable { 
        public void run() { 
            try{
                Thread.sleep(800);
            } catch (InterruptedException e) {}
        }
    } 
    public void startTimer(){
        threadToExplode.start();
    }
    public boolean isthreadToExplodeAlive(){
        return threadToExplode.isAlive();
    }

    //initialization block
    {
        peaTimer = new Timer(2000, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                Game.peas.add(new Pea(LaneX, LaneY, attackDamage, 1));
            }
        });
        
        repeaterPeaTimer = new Timer(2000, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                Game.peas.add(new Pea(LaneX, LaneY, attackDamage, 6));
            }
        });
        repeaterPeaTimer.setInitialDelay(2200);

        sunDropTimer = new Timer(10000, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                Game.suns.add(new Sun(LaneX, LaneY));
            }
        });
    }
}