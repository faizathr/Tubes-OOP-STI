package com.michaelvslalapan.Game;

public class Game {
    private static boolean isGameOver = true;
    private static long startTime = System.currentTimeMillis();
    private static int sun = 0;
    // plantdeck
    // gamemap


    public static void startGame(){

    }

    public boolean getIsGameOver(){
        return isGameOver;
    }

    public long getStartTime(){
        return startTime;
    }

    public int getSun(){
        return sun;
    }

    public void addSun (int sun){
        Game.sun += sun;
    }
    
    public void decreaseSun(int sun){
        Game.sun -= sun;
    }
}
