package com.michaelvslalapan.Organism.Tanaman;

import com.michaelvslalapan.Game.Game;
import java.util.concurrent.*;

public class Sunflower extends Plants implements Runnable {
    private int startTime = (int) TimeUnit.SECONDS.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    private static int[] listSunflower = new int[10]; //idenya adalah sunflower yang diciptain kelipatan 10 detik bakal masuk ke indeks 0, dst

    static{
        for (int i = 0; i < listSunflower.length; i++) {
            listSunflower[i] = 0;
        }
        
        Thread t = new Thread(new Sunflower());
        t.start();
    }


    public void run(){
        try{
            int i = 0;
            while(!Game.getIsGameOver()){
                Game.addSun(10*listSunflower[i % 10]);
                Thread.sleep(1000);
                i++;
            }
            throw new InterruptedException();
        }catch(InterruptedException e){
            return;
        }
        
    }

    public Sunflower(){
        super("Sunflower", 50, 100, 0, 0, 0, 10, false);
        listSunflower[startTime] += 1;
    }
}
