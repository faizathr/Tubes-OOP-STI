package com.michaelvslalapan.Organism.Tanaman;

public class ExplodeONut extends Plants{
    private boolean isExploded;
    private boolean isAreaDamaged;
    Thread threadToExplode;
    public ExplodeONut(){
        super("Explode-O-Nut", 100, 1000, 1800, 0, 1, 20, false);
        isExploded = false;
        isAreaDamaged = false;
        threadToExplode = new Thread(new waitToExplode());
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

    public class waitToExplode implements Runnable { 
        public void run() { 
            try{
                Thread.sleep(800);
            } catch (InterruptedException e) {}
        }
    } 
    public void startTimer(){
        threadToExplode.start();
    }
    public boolean isthreadToExplodeAlive(){
        return threadToExplode.isAlive();
    }
}
