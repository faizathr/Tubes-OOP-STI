package com.michaelvslalapan.Game;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pea {
    private int PlantID, SpawnLaneX, SpawnLaneY;
    private int CoordX, CoordY;
    private double damage;

    public Pea(int SpawnLaneX, int SpawnLaneY, double damage, int PlantID) {
        this.SpawnLaneX = SpawnLaneX;
        this.SpawnLaneY = SpawnLaneY;
        this.damage = damage;
        this.PlantID = PlantID;
        CoordX = 306 + 82 * SpawnLaneX;
        CoordY = 62 + 88 * SpawnLaneY;
    }
    public Pea(@JsonProperty("spawnLaneX")int SpawnLaneX, @JsonProperty("spawnLaneY")int SpawnLaneY, @JsonProperty("damage")double damage, @JsonProperty("plantID")int PlantID, @JsonProperty("coordX")int coordX, @JsonProperty("coordY") int coordY) {
        this.SpawnLaneX = SpawnLaneX;
        this.SpawnLaneY = SpawnLaneY;
        this.damage = damage;
        this.PlantID = PlantID;
        CoordX = coordX;
        CoordY = coordY;
    }

    public int getSpawnLaneX() {
        return SpawnLaneX;
    }

    public int getSpawnLaneY() {
        return SpawnLaneY;
    }

    public int getCoordX() {
        return CoordX;
    }

    public int getCoordY() {
        return CoordY;
    }

    public double getDamage() {
        return damage;
    }

    public int getPlantID() {
        return PlantID;
    }

    public void move(int speed) {
        CoordX += speed;
    }


}
