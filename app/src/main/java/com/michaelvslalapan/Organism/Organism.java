package com.michaelvslalapan.Organism;

import java.util.concurrent.*;

public abstract class Organism {
    private String name;
    private double health;
    private double attackDamage;
    private double attackSpeed;
    private Boolean is_aquatic;
    private long startTime;

    public Organism(String name, double health, double attackDamage, double attackSpeed, Boolean is_aquatic){
        this.name = name;
        this.health = health;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
        this.is_aquatic = is_aquatic;
        startTime = TimeUnit.SECONDS.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    //Getter Method
    public String get_name(){
        return name;
    }

    public double get_Health(){
        return health;
    }

    public double get_Attack_Damage(){
        return attackDamage;
    }

    public double get_Attack_Speed(){
        return attackSpeed;
    }

    public boolean get_is_aquatic(){
        return is_aquatic;
    }

    // Setter 
    public void setName(String name){
        this.name = name;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public void setAttackDamage(int attackDamage){
        this.attackDamage = attackDamage;
    }

    public void setAttackSpeed(int attackSpeed){
        this.attackSpeed = attackSpeed;
    }

    public void setAquatic(Boolean is_aquatic){
        this.is_aquatic = is_aquatic;
    }

    public void decreaseHealth(double health){
        this.health -= health;
    }

    public boolean isDead(){
        return health <= (double) 0;
    }

    public long getStartTime(){
        return startTime;
    }

    // decreaseHealth bakal dipindah ke abstract class zombie dan plant karena dua duanya bakal punya method decreaseHealth yang beda
}