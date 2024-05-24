package com.michaelvslalapan.Organism.Tanaman;

import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;
import javax.swing.Timer;
import com.michaelvslalapan.Organism.Organism;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.michaelvslalapan.ADT.Point;
import com.michaelvslalapan.Game.Game;
import com.michaelvslalapan.Game.Pea;
import com.michaelvslalapan.Game.Sun;
import com.michaelvslalapan.Organism.Zombie.Zombie;

public class Plants<PlantID> extends Organism {
    private PlantID ID;
    private int cost, range, LaneX, LaneY; // [9][6]
    private boolean isThreaten = false, isIdle = true;

    private static boolean[][] isSlotFilled = new boolean[10][6];
    private static Point[][] MapSlot = new Point[9][6];

    private static boolean[][] isLilypadSlotFilled = new boolean[10][6];
    private static Point[][] LilypadMapSlot = new Point[10][6];

    private double cooldown;
    private Timer peaTimer;
    private Timer repeaterPeaTimer;
    private Timer sunDropTimer;
    private double attackDamage;
    protected int plantHeight = 66, plantWidth = 62;
    private int jumpHeight = 0;
    private int jumpDisplacement = 0;
    private boolean isSquashJumped = false;
    
    @JsonIgnore
    Thread threadToKill;

    private boolean isExploded = false;

    public Plants(PlantID ID, String name, int cost, double health, double attackDamage, double attackSpeed, int range, double cooldown, Boolean is_aquatic, int LaneX, int LaneY) {
        super(name, health, attackDamage, attackSpeed, is_aquatic);
        this.ID = ID;  
        this.cost = cost;
        this.range = range;
        this.cooldown = cooldown;
        this.attackDamage = attackDamage;
        if (ID.equals(4)) {
            threadToKill = new Thread(new waitToKill());
        } else if (ID.equals(7)) {
            threadToKill = new Thread(new waitToKill());
        } else if (ID.equals(8)) {
            threadToKill = new Thread(new waitToKill());
            plantHeight = 74;
            plantWidth = 76;
        }
        this.LaneX = LaneX;
        this.LaneY = LaneY;
    }

    public Plants(@JsonProperty("plantID")PlantID ID, @JsonProperty("_name")String name, @JsonProperty("cost")int cost, @JsonProperty("_Health")double health, @JsonProperty("_Attack_Damage")double attackDamage, @JsonProperty("_Attack_Speed")double attackSpeed, @JsonProperty("range")int range, @JsonProperty("cooldown")double cooldown, @JsonProperty("_is_aquatic")Boolean is_aquatic, @JsonProperty("laneX")int LaneX, @JsonProperty("laneY")int LaneY, @JsonProperty("isExploded")Boolean isexploded) {
        super(name, health, attackDamage, attackSpeed, is_aquatic);
        this.ID = ID;  
        this.cost = cost;
        this.range = range;
        this.cooldown = cooldown;
        this.attackDamage = attackDamage;
        if (ID.equals(7)) {
            threadToKill = new Thread(new waitToKill());
        } else if (ID.equals(8)) {
            threadToKill = new Thread(new waitToKill());
            plantHeight = 74;
            plantWidth = 76;
        }
        this.LaneX = LaneX;
        this.LaneY = LaneY;
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

    public static void printArrayIsSlotFilled(){
        for (int i = 0; i < 6; i++){
            for(int j = 0; j < 10; j++){
                System.out.printf(String.valueOf(isSlotFilled[j][i])+", ");
            }
            System.out.println("");
        }
    }

    public static void fillPlantSlot(){
        for(Plants<Integer> p : Game.plants){
            isSlotFilled[p.getLaneX()][p.getLaneY()] = true;
        }
    }

    public static boolean getIsSlotFilled(int x, int y) {
        return isSlotFilled[x][y];
    }
    public static Point getMapSlot(int x, int y) {
        return MapSlot[x][y];
    }

    public static void emptySlot(int x, int y) {
        isSlotFilled[x][y] = false;
    }
    public static void setMapSlot(int x, int y) {
        MapSlot[x][y] = new Point(265 + 82 * x, 50 + 88 * y);
    }

    public static boolean getIsLilypadSlotFilled(int x, int y) {
        return isLilypadSlotFilled[x][y];
    }
    public static Point getLilypadMapSlot(int x, int y) {
        return LilypadMapSlot[x][y];
    }

    public static void emptyLilypadSlot(int x, int y) {
        isLilypadSlotFilled[x][y] = false;
    }
    public static void setLilypadMapSlot(int x, int y) {
        LilypadMapSlot[x][y] = new Point(265 + 82 * x, 75 + 88 * y);
    }

    public void setIsThreaten(boolean isThreaten) {
        this.isThreaten = isThreaten;
    }

    public boolean plant(int LaneX, int LaneY, int PlantID){
        if(!isSlotFilled[LaneX][LaneY]){
            isSlotFilled[LaneX][LaneY] = true;
            switch (PlantID) {
                case 0:
                    Game.plants.add(new Sunflower(LaneX, LaneY));
                    break;
                case 1:
                    Game.plants.add(new Peashooter(LaneX, LaneY));
                    break;
                case 2:
                    Game.plants.add(new Wallnut(LaneX, LaneY));
                    break;
                case 3:
                    Game.plants.add(new SnowPea(LaneX, LaneY));
                    break;
                case 4:
                    Game.plants.add(new Squash(LaneX, LaneY));
                    break;
                case 5:
                    Game.plants.add(new Lilypad(LaneX, LaneY));
                    break;
                case 6:
                    Game.plants.add(new Repeater(LaneX, LaneY));
                    break;
                case 7:
                    Game.plants.add(new ExplodeONut(LaneX, LaneY));
                    break;
                case 8:
                    Game.plants.add(new CherryBomb(LaneX, LaneY));
                    break;
                case 9:
                    Game.plants.add(new Puffshroom(LaneX, LaneY));
                    break;
            }
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

    public void setIsIdle(boolean condition){
        isIdle = condition;
    }

    public class waitToKill implements Runnable { 
        public void run() { 
            try{
                Thread.sleep(800);
            } catch (InterruptedException e) {}
        }
    } 
    public void startTimer(){
        threadToKill.start();
    }

    @JsonIgnore
    public boolean isthreadToKillAlive(){
        return threadToKill.isAlive();
    }

    public int getJumpHeight() {
        return jumpHeight;
    }

    public void addJumpHeight() {
        jumpHeight += 2;
    }
    
    public void reduceJumpHeight() {
        jumpHeight -= 2;
    }

    public int getJumpDisplacement() {
        return jumpDisplacement;
    }

    public void addJumpDisplacement() {
        jumpDisplacement += 2;
    }

    public boolean getIsSquashJumped() {
        return isSquashJumped;
    }

    public void setIsSquashJumped() {
        isSquashJumped = true;
    }

    //initialization block
    {
        peaTimer = new Timer(2000, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                Game.peas.add(new Pea(LaneX, LaneY, attackDamage, (int) ID));
            }
        });
        
        repeaterPeaTimer = new Timer(2000, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                Game.peas.add(new Pea(LaneX, LaneY, attackDamage, 6));
            }
        });
        repeaterPeaTimer.setInitialDelay(2200);

        sunDropTimer = new Timer(3 * 1000, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                Game.suns.add(new Sun(LaneX, LaneY));
            }
        });
    }
}