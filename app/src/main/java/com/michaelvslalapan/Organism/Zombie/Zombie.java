package com.michaelvslalapan.Organism.Zombie;

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
}
