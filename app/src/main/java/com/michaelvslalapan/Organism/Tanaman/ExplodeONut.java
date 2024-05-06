package com.michaelvslalapan.Organism.Tanaman;

public class ExplodeONut extends Plants{
    private boolean isExploded;
    private boolean isAreaDamaged;
    public ExplodeONut(){
        super("Explode-O-Nut", 100, 1000, 1800, 0, 1, 20, false);
        isExploded = false;
        isAreaDamaged = false;
    }

    public boolean getIsExploded(){
        return isExploded;
    }

    public boolean getIsAreaDamaged(){
        return isAreaDamaged;
    }

    public void useExplodeONut(){ // tanaman meledak, setelah meledak, tanaman mati
        this.isExploded = true;
    }

    public void SetAreaDamaged(){
        this.isAreaDamaged = true;
    }
}
