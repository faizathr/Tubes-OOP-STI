package com.michaelvslalapan.Organism.Zombie;

import java.awt.event.ActionListener;
import java.lang.Math;
import javax.swing.Timer;

import com.michaelvslalapan.Organism.Organism;
import com.michaelvslalapan.Organism.Tanaman.Plants;

public abstract class Zombie extends Organism {

    private double zombieSpeed = 5;

    public Zombie(String name, double health, double attackDamage, double attackSpeed, Boolean is_aquatic) {
        super(name, health, attackDamage, attackSpeed, is_aquatic);
    }

    public void zombie_Attack_Plants(Plants p) {
        p.decreaseHealth(this.get_Attack_Damage());
    } // buat zombie melakukan attack ke tanaman

    public double getZombieSpeed(){
        return zombieSpeed;
    }

    public void setZombieSpeed (double zombieSpeed){
        this.zombieSpeed = zombieSpeed;
    }

    // No additional attributes needed for basic Zombie

    // Getter and Setter methods are inherited from Organism class

    private static Timer zombieSpawningTimer;
    private float CoordX;
    private int CoordY;

    private int getCoordYbyLane(int lane) {
        return lane;
    }

    public Zombie genrateRandomZombie(int lane) {
        Zombie[] ZombieInventory = new Zombie[] {
            new BucketheadZombie(),    
            new DuckyTubeZombie(),
            new ConeheadZombie(),
            new NormalZombie(),
            new DolphinRiderZombie(),
            new PoleVaultingZombie()
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
}
