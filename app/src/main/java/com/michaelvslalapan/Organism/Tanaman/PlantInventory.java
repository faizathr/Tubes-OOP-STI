package com.michaelvslalapan.Organism.Tanaman;
import java.util.ArrayList;

public class PlantInventory {
    private final ArrayList<Plant> plantCatalog;

    public PlantInventory() {
        plantCatalog = new ArrayList<>();
        initializeCatalog();
    }

    private void initializeCatalog() {
        // Menambahkan tiap plants ke inventory
        plantCatalog.add(new Sunflower());
        plantCatalog.add(new Peashooter());
        plantCatalog.add(new Wallnut());
        plantCatalog.add(new CherryBomb());
        plantCatalog.add(new Lilypad());
        plantCatalog.add(new Squash());
    }

    public ArrayList<Plant> getPlantCatalog() {
        return plantCatalog;
    }
}
