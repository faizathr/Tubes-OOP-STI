package com.michaelvslalapan.Organism.Zombie;

import com.michaelvslalapan.Organism.Organism;

public class Zombie extends Organism {

    public Zombie(String name, int health, int attackDamage, int attackSpeed, Boolean is_aquatic) {
        super(name, health, attackDamage, attackSpeed, is_aquatic);
    }

    // No additional attributes needed for basic Zombie

    // Getter and Setter methods are inherited from Organism class
}
