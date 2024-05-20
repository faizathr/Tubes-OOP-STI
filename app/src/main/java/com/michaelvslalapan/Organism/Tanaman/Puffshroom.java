package com.michaelvslalapan.Organism.Tanaman;

/* Kelas Puffshroom (Puffshroom) merupakan turunan dari kelas Plants dan merepresentasikan tanaman Puffshroom
Puffshroom (mirip dengan peashooter, tetapi hanya bisa dipanggil di malam hari)
*/
public class Puffshroom extends Plants<Integer> {
    public Puffshroom(int LaneX, int LaneY){
        super(9, "Puffshroom", 100, 100, 25, 4, -1, 10, false, LaneX, LaneY);
    }
}
