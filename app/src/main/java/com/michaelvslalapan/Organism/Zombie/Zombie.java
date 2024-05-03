package com.michaelvslalapan.Organism.Zombie;

import com.michaelvslalapan.Organism.Organism;

public class Zombie extends Organism {
    
    private int zombie_speed;

    public Zombie(String name, int health, int attackDamage, int attackSpeed, Boolean is_aquatic) {
        super(name, health, attackDamage, attackSpeed, is_aquatic);
        this.zombie_speed = zombie_speed;
    }

    public int get_Zombie_Speed(){
        return zombie_speed;
    }

    public void set_Zombie_Speed(){
        this.zombie_speed = zombie_speed;
    }
}
