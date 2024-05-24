package com.michaelvslalapan.Organism.Tanaman;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class Sunflower extends Plants<Integer> {
    public Sunflower(@JsonProperty("laneX")int LaneX, @JsonProperty("laneY")int LaneY){
        super(0, "Sunflower", 50, 100, 0, 0, 0, 10, false, LaneX, LaneY);
    }
}
