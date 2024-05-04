package com.michaelvslalapan.Organism.Zombie;

import com.michaelvslalapan.Organism.Organism;
import com.michaelvslalapan.Organism.Tanaman.Plants;

public abstract class Zombie extends Organism {

    private long zombie_speed = 5;

    static {
        System.out.println("test");
    }

    public Zombie(String name, int health, int attackDamage, int attackSpeed, Boolean is_aquatic) {
        super(name, health, attackDamage, attackSpeed, is_aquatic);
    }

    public abstract void zombie_Attack_Plants(Plants p); // buat zombie melakukan attack ke tanaman

    // No additional attributes needed for basic Zombie

    // Getter and Setter methods are inherited from Organism class
}
