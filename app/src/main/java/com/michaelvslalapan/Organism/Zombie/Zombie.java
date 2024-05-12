package com.michaelvslalapan.Organism.Zombie;

import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;
import java.lang.Math;
import javax.swing.Timer;

import com.michaelvslalapan.Game.Game;
import com.michaelvslalapan.Organism.Organism;
import com.michaelvslalapan.Organism.Tanaman.Plants;

public abstract class Zombie extends Organism {
    private int LaneXEat, LaneY, CoordY;
    private double timePerLaneMove = 5;
    private float CoordX;

    public Zombie(String name, double health, double attackDamage, double attackSpeed, boolean is_aquatic, int LaneY) {
        super(name, health, attackDamage, attackSpeed, is_aquatic);
        this.LaneY = LaneY;
    }

    public void zombie_Attack_Plants(Plants p) {
        p.decreaseHealth(this.get_Attack_Damage());
    } // buat zombie melakukan attack ke tanaman

    public void settimePerLaneMove(double timePerLaneMove){
        this.timePerLaneMove = timePerLaneMove;
    }

    // No additional attributes needed for basic Zombie

    // Getter and Setter methods are inherited from Organism class

    private static Timer zombieSpawningTimer, zombieAttackTimer;
    private static boolean isReachedHouse = false;
    //public final static int maxZombie = 50;

    private int getCoordYbyLaneY(int LaneY) {
        
    }

    public float getZombieSpeed(double timePerLaneMove) {
        return (float) (1 / timePerLaneMove);
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
        if (LaneY <= 1 && LaneY >= 4) {
            zombie = NonAquaticZombieInventory[(int)(Math.random() * NonAquaticZombieInventory.length)];
        } else {
            // if (LaneY >= 2 && LaneY <= 3) {
            zombie = AquaticZombieInventory[(int)(Math.random() * AquaticZombieInventory.length)];
        }
        return zombie;
    }

    public static void startSpawning(){
        zombieSpawningTimer = new Timer(1000, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                //if (getZombieCount() < maxZombie){
                for (int LaneY = 0; LaneY < 6; LaneY++) {
                    if ((int)(Math.random() * 3) == 1) {
                        zombieCount++;
                        Game.zombies.add(genrateRandomZombie(LaneY));
                    }   
                }
            }
        });
        zombieSpawningTimer.start();
        //zombieSpawningTimer.setDelay(1000);
    }

    public int getLaneX() {

    }

    public int getLaneXEat() {

    }

    // Initialization Block
    {
        zombieAttackTimer = new Timer((int) (this.get_Attack_Speed() * 1000), new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                for(Plants plant: Game.plants){
                    if (plant.getLaneX() == LaneXEat && plant.getLaneY() == LaneY){
                        if(!AudioManager.isEating() && !isReachedHouse){
                            AudioManager.eat();
                        }
                        zombie_Attack_Plants(plant);
                    }
                }
            }
        });
        zombieAttackTimer.setInitialDelay(200);
    }

    public void attackOrMove(){
        LaneXEat = getLaneXEat();
        if (Plants.getIsSlotFilled(LaneXEat, LaneY) != false){
            IterateGamePlants: for(Plants plant: Game.plants){
                if (plant.getLaneX() == LaneXEat && plant.getLaneY() == LaneY){
                    zombieAttackTimer.start();
                    if (plant.isDead()){
                        plant.stop();
                        Plants.emptySlot(LaneXEat, LaneY);
                        zombieAttackTimer.stop();
                        Game.plants.remove(plant);
                        break IterateGamePlants;
                    }
                }
            }
        }else{
            move();
        }
    }
    public void move(){
        CoordX -= getZombieSpeed(timePerLaneMove);
    }
}
