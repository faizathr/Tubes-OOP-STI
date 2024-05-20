package com.michaelvslalapan.Organism.Zombie;

public class SnorkelZombie extends Zombie{
    private boolean isUnderwater;

    public SnorkelZombie(int LaneY){
        super("Snorkel Zombie", 150, 100, 1, true, LaneY);
        this.isUnderwater = false;
    }

    public boolean getIsUnderwater(){
        return this.isUnderwater;
    }

    public void dive(){
        this.isUnderwater = true;
    }
}