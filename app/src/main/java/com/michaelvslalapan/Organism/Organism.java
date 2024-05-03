package com.michaelvslalapan.Organism;

public class Organism {
    private String name;
    private int health;
    private int attackDamage;
    private int attackSpeed;
    private Boolean is_aquatic;

    public Organism(String name, int health, int attackDamage, int attackSpeed, Boolean is_aquatic){
        this.name = name;
        this.health = health;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
        this.is_aquatic = is_aquatic;
    }

    //Getter Method
    public String get_name(){
        return name;
    }

    public int get_Health(){
        return health;
    }

    public int get_Attack_Damage(){
        return attackDamage;
    }

    public int get_Attack_Speed(){
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

    // Method untuk mengurangi health
    public void decreaseHealth() {
        // Implementasi pengurangan health akibat attack dari zombie (ke tanaman) dan attack dari tanaman (ke zombie) 
    }
}
