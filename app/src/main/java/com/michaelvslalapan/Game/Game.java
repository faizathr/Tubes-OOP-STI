package com.michaelvslalapan.Game;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private static boolean isGameStarted = false;
    private static boolean isPlaying = true;
    private static boolean isWinning = false;
    private static long startTime = System.currentTimeMillis();
    private static int sun = 0;
    // plantdeck
    // gamemap

    private static int zombieCount = 0;
    //public final static int maxZombie = 50;

    public static List<Plants> plants = new ArrayList<Plant>();
    public static List<Zombie> zombies = new ArrayList<Zombie>();

    public static void startGame(){

    }

    public static boolean getIsGameStarted(){
        return isGameStarted;
    }

    public static long getStartTime(){
        return startTime;
    }

    public static int getSun(){
        return sun;
    }

    public synchronized static void addSun (int sun){
        Game.sun += sun;
    }
    
    public synchronized static void decreaseSun(int sun){
        Game.sun -= sun;
    }

    public int getZombieCount() {
        return zombieCount;
    }

    public void increaseZombieCount() {
        zombieCount++;
    }

    public void decreaseZombieCount() {
        zombieCount--;
    }
}
