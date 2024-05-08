package com.michaelvslalapan.Organism.Zombie;

import java.lang.Math;
import javax.swing.Timer;

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

    public void setZombieSpeed (double zombieSpeed){
        this.zombieSpeed = zombieSpeed;
    }

    // No additional attributes needed for basic Zombie

    // Getter and Setter methods are inherited from Organism class

    private static Timer zombieSpawningTimer, zombieAttackTimer;
    private static boolean isReachedHouse = false;
    private float CoordX;
    private int CoordY;

    private int getCoordYbyLaneY(int LaneY) {

    }

    public float getZombieSpeed(double timePerLaneMove) {
        return (float) (1 / timePerLaneMove);
    }

    public Zombie genrateRandomZombie(int LaneY) {
        Zombie[] ZombieInventory = new Zombie[] {
            new BucketheadZombie(LaneY),    
            new DuckyTubeZombie(LaneY),
            new ConeheadZombie(LaneY),
            new NormalZombie(LaneY),
            new DolphinRiderZombie(LaneY),
            new PoleVaultingZombie(LaneY)
        };
        Zombie zombie = ZombieInventory[(int)(Math.random() * ZombieInventory.length)];
        return zombie;
    }

    public static void startSpawning(){
        zombieSpawningTimer = new Timer(1000, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                //if (getZombieCount() < maxZombie){
                for (int lane = 0; lane < 6; lane++) {
                    if ((int)(Math.random() * 3) == 1) {
                        increaseZombieCount();
                        Game.zombies.add(genrateRandomZombie(lane));
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
        zombieAttackTimer = new Timer(this.get_Attack_Speed() * 1000, new ActionListener(){
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

    public void attack(){
        LaneXEat = getLaneXEat();
        if (Plants.getIsSlotFilled(LaneXEat, LaneY) != false){
            IterateGamePlants: for(Plants plant: Game.plants){
                if (plant.getLaneX() == LaneXEat && plant.getLaneY() == LaneY){
                    zombieAttackTimer.start();
                    if (plant.isDead()){
                        plant.stop();
                        Plant.emptySlot(LaneXEat, LaneY);
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
