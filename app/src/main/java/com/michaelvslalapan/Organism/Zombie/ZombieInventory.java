package com.michaelvslalapan.Organism.Zombie;

import java.util.ArrayList;

public class ZombieInventory {
    private final ArrayList<Zombie> zombieCatalog;
    public ZombieInventory() {
        zombieCatalog = new ArrayList<Zombie>();
        initializeCatalog();
    }

    private void initializeCatalog() {
        // Menambahkan tiap zombie ke inventory
        zombieCatalog.add(new NormalZombie());
        zombieCatalog.add(new ConeheadZombie());
        zombieCatalog.add(new BucketheadZombie());
        zombieCatalog.add(new DolphinRiderZombie());
        zombieCatalog.add(new DuckyTubeZombie());
        zombieCatalog.add(new PoleVaultingZombie());
    }

    public ArrayList<Zombie> getZombieCatalog() {
        return zombieCatalog;
    }
}
