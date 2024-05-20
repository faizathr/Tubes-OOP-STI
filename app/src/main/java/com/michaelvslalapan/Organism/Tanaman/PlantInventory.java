package com.michaelvslalapan.Organism.Tanaman;

import java.util.ArrayList;

public class PlantInventory {
    private final ArrayList<Plants> plantCatalog;

    public PlantInventory() {
        plantCatalog = new ArrayList<Plants>();
        initializeCatalog();
    }

    private void initializeCatalog() {
        // Menambahkan tiap plants ke inventory
        plantCatalog.add(new Sunflower(-1,-1));
        plantCatalog.add(new Peashooter(-1,-1));
        plantCatalog.add(new Wallnut(-1,-1));
        plantCatalog.add(new Lilypad(-1,-1));
        plantCatalog.add(new Squash(-1,-1));
        plantCatalog.add(new SnowPea(-1,-1));
        plantCatalog.add(new CherryBomb(-1,-1));
        plantCatalog.add(new ExplodeONut(-1,-1));
        plantCatalog.add(new Puffshroom(-1,-1));
        plantCatalog.add(new Repeater(-1,-1));
    }

    public ArrayList<Plants> getPlantCatalog() {
        return plantCatalog;
    }
}
