package com.michaelvslalapan.Organism.Zombie;

public class PoleVaultingZombie extends Zombie {
    private boolean isPoleVaultingUsed;

    public PoleVaultingZombie(int LaneY){
        super("Pole Vaulting Zombie", 175, 100, 1, false, LaneY);
        isPoleVaultingUsed = false;
    }

    public boolean getIsPoleVaultingUsed(){
        return isPoleVaultingUsed;
    }

    public void usePoleVault(){ // pole vaulting dipakai, memastikan hanya bisa dipakai sekali
        this.isPoleVaultingUsed = true;
    }
}
