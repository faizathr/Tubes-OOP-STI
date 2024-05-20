package com.michaelvslalapan.Game;

import java.awt.geom.Ellipse2D;
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;
import java.lang.Math;
import javax.swing.Timer;

import com.michaelvslalapan.Organism.Tanaman.Plants;

public class Sun {
    private int CoordX, CoordY, CoordYLimit;
    private boolean isFromSunflower, isWaiting = false;
    private Ellipse2D sunObj;
    private static Timer timerNonSunflowerDrop;
    private Thread sunDropThread = new Thread(new waitSunDropping());

    public Sun(){
        CoordX = (int) (Math.random() * (900 - 270 + 1) + 270);
        CoordY = -85;
        CoordYLimit = (int) (Math.random() * (470 - 200 + 1) + 200);
        isFromSunflower = false;
    }

    public Sun(int CoordX, int CoordY){
        this.CoordX = Plants.getMapSlot(CoordX, CoordY).getX() - 15;
        this.CoordY = Plants.getMapSlot(CoordX, CoordY).getY() - 30;
        isFromSunflower = true;
    }

    public static void startTimer() {
        timerNonSunflowerDrop = new Timer(5000, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                Game.suns.add(new Sun());
                timerNonSunflowerDrop.setDelay(((int)(Math.random() * 6) + 5) * 1000);
            }
        });
        timerNonSunflowerDrop.setRepeats(true);
        timerNonSunflowerDrop.start();
    }
    public static void stop(){
        timerNonSunflowerDrop.stop();
    }

    private class waitSunDropping implements Runnable { 
        public void run() { 
            try{
                Thread.sleep(3000);
            } catch (InterruptedException e) {}
        }
    } 
    public void startThread(){
        sunDropThread.start();
    }

    public int getCoordX() {
        return CoordX;
    }
    public int getCoordY() {
        return CoordY;
    }
    public int getCoordYLimit() {
        return CoordYLimit;
    }
    public boolean isFromSunflower() {
        return isFromSunflower;
    }
    public Ellipse2D getObj() {
        return sunObj;
    }
    public boolean isTsunAlive() {
        return sunDropThread.isAlive();
    }
    public boolean isWaiting() {
        return isWaiting;
    }

    public void setSunObj(Ellipse2D sunObj){
        this.sunObj = sunObj;
    }
    public void setWaiting() {
        isWaiting = true;
    }
    public void lowerCoordY(){
        CoordY += 2;
    }
}
