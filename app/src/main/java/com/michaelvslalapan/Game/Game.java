package com.michaelvslalapan.Game;

public class Game {
    private static boolean isGameOver = false;
    private static long startTime = System.currentTimeMillis();
    private static int sun = 0;
    // plantdeck
    // gamemap


    public static void startGame(){

    }

    public static boolean getIsGameOver(){
        return isGameOver;
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
}
