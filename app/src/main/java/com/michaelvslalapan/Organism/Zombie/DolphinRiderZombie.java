package com.michaelvslalapan.Organism.Zombie;

public class DolphinRiderZombie extends Zombie {
    private boolean isAlreadyJumped;

    public DolphinRiderZombie(){
        super("Dolphin Rider Zombie", 175, 100, 1, true);
        isAlreadyJumped = false;
    }

    public boolean getIsAlreadyJumped(){
        return isAlreadyJumped;
    }

    public void usePoleVault(){ // zombie lompat, memastikan hanya bisa dipakai sekali
        this.isAlreadyJumped = true;
    }
}
