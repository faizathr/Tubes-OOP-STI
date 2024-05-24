package com.michaelvslalapan.Organism.Tanaman;

import java.util.ArrayList;

public class PlantInventory {
    private static final ArrayList<Plants<Integer>> plantCatalog = new ArrayList<Plants<Integer>>();

    static{
        initializeCatalog();
    }

    private static void initializeCatalog() {
        // Menambahkan tiap plants ke inventory
        plantCatalog.add(new Sunflower(-1,-1));
        plantCatalog.add(new Peashooter(-1,-1));
        plantCatalog.add(new Wallnut(-1,-1));
        plantCatalog.add(new SnowPea(-1,-1));
        plantCatalog.add(new Squash(-1,-1));
        plantCatalog.add(new Lilypad(-1,-1));
        plantCatalog.add(new Repeater(-1,-1));
        plantCatalog.add(new ExplodeONut(-1,-1));
        plantCatalog.add(new CherryBomb(-1,-1));
        plantCatalog.add(new Puffshroom(-1,-1));
        
    }

    public static ArrayList<Plants<Integer>> getPlantCatalog() {
        return plantCatalog;
    }
}
