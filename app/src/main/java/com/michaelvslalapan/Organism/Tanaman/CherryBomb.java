package com.michaelvslalapan.Organism.Tanaman;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CherryBomb extends Plants<Integer> {
    @JsonIgnore
    Thread threadToKill = new Thread(new waitToKill());

    private boolean isExploded = false;

    public CherryBomb(int LaneX, int LaneY) {
        super(8, "CherryBomb", 150, 100, 1000, 0, 1, 15, false, LaneX, LaneY);
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
