package com.michaelvslalapan.Organism.Tanaman;

/* Kelas FumeShroom (Fume-shroom) merupakan turunan dari kelas Plants dan merepresentasikan tanaman Fume-shroom
Fume-shroom (mirip dengan peashooter, tetapi hanya bisa dipanggil di malam hari)
*/
public class FumeShroom extends Plants{
    private boolean isNight;
    public FumeShroom(){
        super("Fume-shroom", 100, 100, 25, 4, -1, 10, false);
        isNight = true;
    }
    
    public boolean getIsNight(){
        return isNight;
    }

    public void setIsNight(boolean isNight){
        this.isNight = isNight;
    }
}
