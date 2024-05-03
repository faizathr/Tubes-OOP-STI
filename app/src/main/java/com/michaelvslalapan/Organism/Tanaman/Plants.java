package com.michaelvslalapan.Organism.Tanaman;

import com.michaelvslalapan.Organism.Organism;

public class Plants extends Organism {

    private int cost;
    private int range;
    private int cooldown;

    public Plants(String name, int health, int attackDamage, int attackSpeed, Boolean is_aquatic, int cost, int range, int cooldown) {
        super(name, health, attackDamage, attackSpeed, is_aquatic);
        this.cost = cost;
        this.range = range;
        this.cooldown = cooldown;
    }

    // Getter Methods
    public int getCost() {
        return cost;
    }

    public int getRange() {
        return range;
    }

    public int getCooldown() {
        return cooldown;
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

    // Method untuk menyerang
    public void attack() {
        // Implementasi serangan tanaman
    }
}