package com.michaelvslalapan.Organism.Tanaman;

import com.michaelvslalapan.Game.Game;
import java.util.concurrent.*;

public final class Sunflower extends Plants implements Runnable {
    private long startTime = TimeUnit.SECONDS.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    private static long[] listSunflower = new long[10]; //idenya adalah sunflower yang diciptain kelipatan 10 detik bakal masuk ke indeks 0, dst

    static{
        for (int i = 0; i < listSunflower.length; i++) {
            listSunflower[i] = 0;
        }

        Thread test = new Thread (new Sunflower());
        test.start();
    }


    public void run(){
        try{
            int i = 0;
            Thread.sleep(10000);
            while(!Game.getIsGameOver()){
                Game.addSun(10*(int)listSunflower[i % 10]);
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
        listSunflower[(int)startTime%10] += 1;
    }

    public long getStartTime(){
        return startTime;
    }

    public static void main(String[] args) { //testing
        Sunflower test1 = new Sunflower();

        System.out.println("Waktu Mulai Test 1 : " + test1.startTime%10);

        Sunflower test2 = new Sunflower();

        System.out.println("Waktu Mulai Test 2 : " + test2.startTime%10);
        
        

        while(true){
            System.out.println(Game.getSun());
            // getting the group of the threads/
                ThreadGroup threadGroup
                = Thread.currentThread().getThreadGroup();

            // getting the total active count of the threads
            int threadCount = threadGroup.activeCount();

            Thread threadList[] = new Thread[threadCount];
            // enumerating over the thread list
            threadGroup.enumerate(threadList);

            System.out.println("Active threads are:");
        
            // iterating over the for loop to get the names of
            // all the active threads.
            for (int i = 0; i < threadCount; i++)
                System.out.println(threadList[i].getName());

            
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }

    }


}
