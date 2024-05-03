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
    //Getter Method
    public String get_Name(){
        return name;
    }

    public int get_Cost(){
        return cost;
    }

    public int get_Health(){
        return health;
    }

    public int  get_Attack_Damage(){
        return attackDamage;
    }

    public int get_Attack_Speed(){
        return attackSpeed;
    }

    public int get_Range(){
        return range;
    }

    public int get_Cooldown(){
        return cooldown;
    }

    // Setter methods
    public void setName(String name) {
        this.name = name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    // Method untuk menyerang zombie
    public void attackZombie(/** */) {
        // Implementasi klo nyerang zombie
    }

    // Method untuk mengurangi health
    public void decreaseHealth() {
        // Implementasi pengurangan health akibat attack dari zombie
    }

}
