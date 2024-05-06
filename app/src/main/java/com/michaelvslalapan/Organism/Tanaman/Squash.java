package com.michaelvslalapan.Organism.Tanaman;

public class Squash extends Plants{
    private boolean isExploded;
    public Squash(){
        super("Squash", 50, 100, 5000, 0, 1, 20, false);
        isExploded = false;
    }

    public boolean getIsExploded(){
        return isExploded;
    }

    public void useSquash(){ // tanaman meledak, setelah meledak, tanaman mati
        this.isExploded = true;
    }

}
