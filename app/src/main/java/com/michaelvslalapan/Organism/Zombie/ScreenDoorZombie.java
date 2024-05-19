package com.michaelvslalapan.Organism.Zombie;

public class ScreenDoorZombie extends Zombie {
    private int screenDoor;

    public ScreenDoorZombie(int LaneY){
        super("Screen Door Zombie", 200, 100, 1, false, LaneY);
        this.screenDoor = 100; // Screen Door can absorb 100 damage
    }

    public int getScreenDoor(){
        return this.screenDoor;
    }

    public void damageScreenDoor(int damage){
        this.screenDoor -= damage;
        if(this.screenDoor < 0){
            this.screenDoor = 0;
        }
    }
}