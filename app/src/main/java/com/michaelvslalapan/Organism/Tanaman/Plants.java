package com.michaelvslalapan.Organism.Tanaman;

import com.michaelvslalapan.Organism.Organism;
import com.michaelvslalapan.Organism.Zombie.Zombie;

public abstract class Plants extends Organism {

    private int cost;
    private int range;
    private int LaneX, LaneY; // [9][6]
    private static boolean[][] isSlotFilled = new int[9][6];
    private static Point[][] MapSlot = new Point[9][6];
    private double cooldown;
    private Timer peaTimer;
    private Timer repeaterPeaTimer;
    private Timer sunDropTimer;

    public Plants(String name, int cost, double health, double attackDamage, double attackSpeed, int range, double cooldown, Boolean is_aquatic) {
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

    public double getCooldown() {
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

    public void zombie_Attack_Plants(Zombie z) {
        z.decreaseHealth(this.get_Attack_Damage());
    } // buat plant melakukan attack ke zombie 

    public int getLaneX() {
        return LaneX;
    }
    public int getLaneY() {
        return LaneY;
    }

    public static int getIsSlotFilled(int x, int y) {
        return isSlotFilled[x][y];
    }
    public static Point getMapSlot(int x, int y) {
        return MapSlot[x][y];
    }

    public static void emptySlot(int x, int y){
        isSlotFilled[x][y] = false;
    }
    public static void setMapSlot(int x, int y){
        MapSlot[x][y] = new Point();
    }

    this.get_Attack_Speed()
}