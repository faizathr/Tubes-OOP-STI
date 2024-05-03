package com.michaelvslalapan.Organism.Tanaman;

public class Plants {
    private String name;
    private int cost;
    private int health;
    private int attackDamage;
    private int attackSpeed;
    private int range;
    private int cooldown; 

    // Konstruktor
    public Plants(String name, int cost, int health, int attackDamage, int attackSpeed, int range, int cooldown){
        this.name = name;
        this.cost = cost;
        this.health = health;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
        this.range = range;
        this.cooldown = cooldown;
    }
    

}
