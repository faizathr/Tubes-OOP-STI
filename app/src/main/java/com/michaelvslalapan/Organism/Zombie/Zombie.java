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
import com.michaelvslalapan.ADT.Point;
import com.michaelvslalapan.Game.AudioManager;

public class Zombie extends Organism implements Comparable<Zombie> {
    private static Timer zombieSpawningTimer;
    private Timer zombieAttackTimer;
    private Timer zombieSlowedTimer = new Timer(3000, new ActionListener(){
        public void actionPerformed(ActionEvent e) {
            isSlowed = false;
            zombieSlowedTimer.stop();
        }
    });
    private static boolean isReachedHouse = false;

    private int ZombieID;
    private int zombieWidth = 73;
    private int zombieHeight = 119;
    private int LaneX = 9, CoordY;
    private final int LaneY;
    private double timePerLaneMove = 10;
    private float CoordX = 1000f;
    private boolean isSlowed;
    private boolean isPoleVaultingUsed = false;
    private boolean haveTargettoJump = false;
    private boolean isDolphinJumped = false;
    private int jumpHeight = 0;
    private int jumpDisplacement = 0;
    private Point jumpKillTarget;

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
        zombieAttackTimer = new Timer(((int) get_Attack_Speed()) * 1000, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                for(Plants<Integer> plant: Game.plants){
                    if (plant.getLaneX() == LaneX && plant.getLaneY() == LaneY) {
                        if (LaneY == 2 || LaneY == 3) {
                            if (Plants.getIsSlotFilled(LaneX, LaneY) && plant.getPlantID().equals(5)) continue;
                        }
                        if(!AudioManager.isEating() && !isReachedHouse){
                            AudioManager.eat();
                        }
                        plant.decreaseHealth(get_Attack_Damage());
                    }
                }
            }
        });
        //zombieAttackTimer.setInitialDelay(200);
    }

    public Zombie(@JsonProperty("zombieID")int ZombieID, @JsonProperty("_name")String name, @JsonProperty("_Health")double health, @JsonProperty("_Attack_Damage")double attackDamage, @JsonProperty("_Attack_Speed")double attackSpeed, @JsonProperty("_is_aquatic")boolean is_aquatic, @JsonProperty("coordY") int coordY, @JsonProperty("isSlowed") boolean slowed, @JsonProperty("coordX") float coordX, @JsonProperty("laneX")int laneX, @JsonProperty("laneY")int laneY) {
        super(name, health, attackDamage, attackSpeed, is_aquatic);
        this.LaneY = laneY;
        CoordY = coordY;
        CoordX = coordX;
        this.ZombieID = ZombieID;
        isSlowed = slowed;
        zombieAttackTimer = new Timer(((int) get_Attack_Speed()) * 1000, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                for(Plants<Integer> plant: Game.plants){
                    if (plant.getLaneX() == LaneX && plant.getLaneY() == LaneY) {
                        if (LaneY == 2 || LaneY == 3) {
                            if (Plants.getIsSlotFilled(LaneX, LaneY) && plant.getPlantID().equals(5)) continue;
                        }
                        if(!AudioManager.isEating() && !isReachedHouse){
                            AudioManager.eat();
                        }
                        plant.decreaseHealth(get_Attack_Damage());
                    }
                }
            }
        });
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

    public void setCoordX(float CoordX) {
        this.CoordX = CoordX;
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
            new NormalZombie(LaneY),
            new ConeheadZombie(LaneY),
            new PoleVaultingZombie(LaneY),
            new BucketheadZombie(LaneY),
            new FootballZombie(LaneY),
            new WallnutZombie(LaneY),
            new ScreenDoorZombie(LaneY)
        };
        Zombie[] AquaticZombieInventory = new Zombie[] {
            new DuckyTubeZombie(LaneY),
            new DolphinRiderZombie(LaneY),
            new SnorkelZombie(LaneY)
        };
        if (LaneY <= 1 || LaneY >= 4) {
            int randSeed = (int)(Math.random() * NonAquaticZombieInventory.length);
            zombie = NonAquaticZombieInventory[randSeed];
        } else {
            int randSeed = (int)(Math.random() * AquaticZombieInventory.length);
            zombie = AquaticZombieInventory[randSeed];
        }
        return zombie;
    }
    @JsonIgnore
    public static void startSpawning() {
        zombieSpawningTimer = new Timer(3 * 1000, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if (Game.getSecondsTime() >= 20 && Game.getSecondsTime() <= 160) {
                    if (Game.getZombieInMapCount() < 10) {
                        for (int LaneYSpawn = 0; LaneYSpawn < 6; LaneYSpawn++) {
                            if ((int)(Math.random() * 10) < 3) {
                                Game.zombies.add(genrateRandomZombie(LaneYSpawn));
                                Game.addZombieInMapCount();
                                Collections.sort(Game.zombies);
                            }   
                        }
                    }
                }
            }
        });
        if(Game.isUsingPreviousGame){
            zombieSpawningTimer.setInitialDelay((Game.getSecondsTime() - 20*1000 < 0) ? 0 : (Game.getSecondsTime() - 20*1000));
        }else{
            zombieSpawningTimer.setInitialDelay(20 * 1000);
        }
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

    public boolean getHaveTargettoJump() {
        return haveTargettoJump;
    }

    public void setHaveTargettJump() {
        this.haveTargettoJump = true;
    }

    public void removeHaveTargettJump() {
        this.haveTargettoJump = false;
    }

    public void setJumpKillTarget(int x, int y) {
        jumpKillTarget = new Point(x, y);
    }

    public Point getJumpKillTarget() {
        return jumpKillTarget;
    }

    public void attackOrMove() {
        LaneX = this.getLaneX();
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

    public static void stopSpawning() {
        zombieSpawningTimer.stop();
    }

    public void startAttackingPlant() {
        zombieAttackTimer.start();
    }

    public boolean isAttackingPlantStarted() {
        return zombieAttackTimer.isRunning();
    }

    public void stopAttackingPlant() {
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
}
