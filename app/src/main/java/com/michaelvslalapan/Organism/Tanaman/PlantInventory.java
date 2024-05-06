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
        plantCatalog.add(new Sunflower());
        plantCatalog.add(new Peashooter());
        plantCatalog.add(new Wallnut());
        plantCatalog.add(new Lilypad());
        plantCatalog.add(new Squash());
        plantCatalog.add(new SnowPea());
    }

    public ArrayList<Plants> getPlantCatalog() {
        return plantCatalog;
    }
}
