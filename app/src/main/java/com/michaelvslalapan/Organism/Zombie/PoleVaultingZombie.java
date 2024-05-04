package com.michaelvslalapan.Organism.Zombie;

public class PoleVaultingZombie extends Zombie {
    private boolean isPoleVaultingUsed;

    public PoleVaultingZombie(){
        super("Pole Vaulting Zombie", 175, 100, 1, false);
        isPoleVaultingUsed = false;
    }

    public boolean getIsPoleVaultingUsed(){
        return isPoleVaultingUsed;
    }

    public void usePoleVault(){ // pole vaulting dipakai, memastikan hanya bisa dipakai sekali
        this.isPoleVaultingUsed = true;
    }
}
