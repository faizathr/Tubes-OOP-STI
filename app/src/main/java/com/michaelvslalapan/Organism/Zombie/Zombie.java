package com.michaelvslalapan.Organism.Zombie;

import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;
import java.util.Collections;
import java.lang.Math;
import javax.swing.Timer;

import com.michaelvslalapan.Game.Game;
import com.michaelvslalapan.Organism.Organism;
import com.michaelvslalapan.Organism.Tanaman.Plants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.michaelvslalapan.Game.AudioManager;

public class Zombie extends Organism implements Comparable<Zombie> {
    private static Timer zombieSpawningTimer, zombieAttackTimer, zombieSlowedTimer;
    private static boolean isReachedHouse = false;

    private int ZombieID;
    private int zombieWidth = 73;
    private int zombieHeight = 119;
    private int LaneX, LaneY, CoordY;
    private double timePerLaneMove = 10;
    private float CoordX = 1000f;
    private boolean isSlowed;
    private boolean isPoleVaultingUsed = false;
    private boolean isDolphinJumped = false;

    private String[] ZombieIDCatalog = new String[]{
        "NormalZombie",
        "ConeheadZombie",
        "PoleVaultingZombie",
        "BucketheadZombie",
        "DuckyTubeZombie",
        "DolphinRiderZombie",
        "FootballZombie",
        "SnorkelZombie",
        "WallnutZombie",
        "ScreendoorZombie",
    };

    public Zombie(int ZombieID, String name, double health, double attackDamage, double attackSpeed, boolean is_aquatic, int LaneY) {
        super(name, health, attackDamage, attackSpeed, is_aquatic);
        this.LaneY = LaneY;
        CoordY = getLaneYCoord(LaneY);
        this.ZombieID = ZombieID;
    }


    public Zombie(@JsonProperty("zombieID")int ZombieID, @JsonProperty("_name")String name, @JsonProperty("_Health")double health, @JsonProperty("_Attack_Damage")double attackDamage, @JsonProperty("_Attack_Speed")double attackSpeed, @JsonProperty("_is_aquatic")boolean is_aquatic, @JsonProperty("coordY")int coordY, @JsonProperty("isSlowed") boolean slowed, @JsonProperty("coordX")int coordX, @JsonProperty("laneX")int laneX, @JsonProperty("laneY")int laneY) {
        super(name, health, attackDamage, attackSpeed, is_aquatic);
        this.LaneY = laneY;
        CoordY = coordY;
        CoordX = coordX;
        this.ZombieID = ZombieID;
        isSlowed = slowed;
        this.LaneX = laneX;
    }
    

    public void settimePerLaneMove(double timePerLaneMove){
        this.timePerLaneMove = timePerLaneMove;
    }

    public float getZombieSpeed(double timePerLaneMove) {
        return (float) (1 / timePerLaneMove);
    }

    public int getCoordY() {
        return CoordY;
    }

    public float getCoordX() {
        return CoordX;
    }

    public int getLaneY() {
        return LaneY;
    }

    public boolean getIsSlowed() {
        return isSlowed;
    }

    public int getZombieID() {
        return ZombieID;
    }
    @JsonIgnore
    public int getWidth() {
        return zombieWidth;
    }
    @JsonIgnore
    public int getHeight() {
        return zombieHeight;
    }

    public static Zombie genrateRandomZombie(int LaneY) {
        Zombie zombie;
        Zombie[] NonAquaticZombieInventory = new Zombie[] {
            new BucketheadZombie(LaneY), 
            new ConeheadZombie(LaneY),
            new NormalZombie(LaneY),
            new PoleVaultingZombie(LaneY)
        };
        Zombie[] AquaticZombieInventory = new Zombie[] {
            new DuckyTubeZombie(LaneY),
            new DolphinRiderZombie(LaneY)
        };
        //System.out.println("LaneY: " + LaneY);
        if (LaneY <= 1 || LaneY >= 4) {
            int randSeed = (int)(Math.random() * NonAquaticZombieInventory.length);
            //System.out.println("NonAquaticZombieInventory randSeed: " + randSeed);
            zombie = NonAquaticZombieInventory[randSeed];
        } else {
            // if (LaneY >= 2 && LaneY <= 3) {
            int randSeed = (int)(Math.random() * AquaticZombieInventory.length);
            //System.out.println("AquaticZombieInventory randSeed: " + randSeed);
            zombie = AquaticZombieInventory[randSeed];
        }
        return zombie;
    }

    public static void startSpawning() {
        zombieSpawningTimer = new Timer(3 * 1000, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if (Game.getSecondsTime() >= 20 && Game.getSecondsTime() <= 160) {
                    if (Game.getZombieInMapCount() < 10) {
                        for (int LaneY = 0; LaneY < 6; LaneY++) {
                            if ((int)(Math.random() * 10) < 3) {
                                Game.zombies.add(genrateRandomZombie(LaneY));
                                Game.addZombieInMapCount();
                                Collections.sort(Game.zombies);
                            }   
                        }
                    }
                }
            }
        });
        zombieSpawningTimer.setInitialDelay(20 * 1000);
        zombieSpawningTimer.start();
    }
    public int getLaneX() {
        int calculateLaneX = 9;
        int[] fieldArr = {296,377,458,539,620,701,782,863,944};
        for (int i = fieldArr.length - 1; i >= 1; i--) {
            if (CoordX <= fieldArr[i] && CoordX > fieldArr[i-1]) {
                calculateLaneX = i;
                break;
            } else if (CoordX <= fieldArr[0]) {
                calculateLaneX = 0;
            }
        }
        return calculateLaneX;
    }

    public void slowed() {
        if (!isSlowed) {
            isSlowed = true;
            zombieSlowedTimer = new Timer(3000, new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    isSlowed = false;
                    zombieSlowedTimer.stop();
                }
            });
            zombieSlowedTimer.start();
        } else {
            zombieSlowedTimer.restart();
        }
    }

    public boolean getIsPoleVaultingUsed(){
        return isPoleVaultingUsed;
    }

    public void usePoleVault(){
        this.isPoleVaultingUsed = true;
    }

    public boolean getIsDolphinJumped(){
        return isDolphinJumped;
    }

    public void useDolphin(){
        this.isDolphinJumped = true;
    }

    public void attackOrMove() {
        LaneX = getLaneX();
        if (Plants.getIsSlotFilled(LaneX, LaneY) != false){
            IterateGamePlants: for(Plants<Integer> plant: Game.plants){
                if (plant.getLaneX() == LaneX && plant.getLaneY() == LaneY && !plant.getPlantID().equals(5)){
                    zombieAttackTimer.start();
                    if (plant.isDead()) {
                        plant.stop();
                        Plants.emptySlot(LaneX, LaneY);
                        zombieAttackTimer.stop();
                        Game.plants.remove(plant);
                        break IterateGamePlants;
                    }
                }
            }
        } else if (Plants.getIsLilypadSlotFilled(LaneX, LaneY) != false) {
            IterateLilypad: for(Plants<Integer> plant: Game.plants){
                if (plant.getLaneX() == LaneX && plant.getLaneY() == LaneY && plant.getPlantID().equals(5)){
                    zombieAttackTimer.start();
                    if (plant.isDead()){
                        plant.stop();
                        Plants.emptyLilypadSlot(LaneX, LaneY);
                        zombieAttackTimer.stop();
                        Game.plants.remove(plant);
                        break IterateLilypad;
                    }
                }
            }
        } else {
            move();
        }
    }
    
    public void move(){
        CoordX -= getZombieSpeed(timePerLaneMove) * (isSlowed ? 0.5f : 1f);
    }

    public static void stopAttackingPlant() {
        zombieAttackTimer.stop();
    }

    @Override
	public int compareTo(Zombie z) {
		return LaneY - z.getLaneY();
	}
    @JsonIgnore
    public boolean isGameOver() {
        if (CoordX < 210) {
            isReachedHouse = true;
            return true;
        } else return false;
    }

    public static void resetGameOver() {
        isReachedHouse = false;
    }

    private int getLaneYCoord(int LaneY) {
        return 2 + 88 * LaneY;
    }

    public static void startWave() {
        AudioManager.wave();
        zombieSpawningTimer.setInitialDelay(4000);
        zombieSpawningTimer.start();
        Game.setWave(1);
    }

    // Initialization Block
    {
        zombieAttackTimer = new Timer((int) (this.get_Attack_Speed() * 1000), new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                for(Plants<Integer> plant: Game.plants){
                    if (plant.getLaneX() == LaneX && plant.getLaneY() == LaneY && !(plant.getPlantID().equals(5) && Plants.getIsSlotFilled(LaneX, LaneY))){
                        if(!AudioManager.isEating() && !isReachedHouse){
                            AudioManager.eat();
                        }
                        plant.decreaseHealth(get_Attack_Damage());
                    }
                }
            }
        });
        zombieAttackTimer.setInitialDelay(200);
    }
}
