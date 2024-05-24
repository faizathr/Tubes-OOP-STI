package com.michaelvslalapan.Organism.Tanaman;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ExplodeONut extends Plants<Integer> {
    @JsonIgnore
    Thread threadToKill = new Thread(new waitToKill());

    private boolean isExploded = false;

    public ExplodeONut(int LaneX, int LaneY){
        super(7, "Explode-O-Nut", 100, 1000, 1800, 0, 1, 20, false, LaneX, LaneY);
    }

    public boolean getIsExploded(){
        return isExploded;
    }

    public void explodePlant(){ // tanaman meledak, setelah meledak, tanaman mati
        this.isExploded = true;
    }

    public void enlargeExplodingPlant(){
        plantWidth+=1; plantHeight+=1;
    }

    public void startTimer(){
        threadToKill.start();
    }

    @JsonIgnore
    public boolean isthreadToKillAlive(){
        return threadToKill.isAlive();
    }

    public class waitToKill implements Runnable { 
        public void run() { 
            try{
                Thread.sleep(800);
            } catch (InterruptedException e) {}
        }
    }
}
