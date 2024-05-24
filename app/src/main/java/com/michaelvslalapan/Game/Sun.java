package com.michaelvslalapan.Game;

import java.awt.geom.Ellipse2D;
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;
import java.lang.Math;
import javax.swing.Timer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.michaelvslalapan.Organism.Tanaman.Plants;


@JsonIgnoreProperties({"sunObj"})
public class Sun {
    private int CoordX, CoordY, CoordYLimit;
    private boolean isFromSunflower, isWaiting = false;

    @JsonIgnore
    private Ellipse2D sunObj;

    private static Timer timerNonSunflowerDrop;
    private Thread sunDropThread = new Thread(new waitSunDropping());

    public Sun(){
        CoordX = (int) (Math.random() * (900 - 270 + 1) + 270);
        CoordY = -85;
        CoordYLimit = (int) (Math.random() * (470 - 200 + 1) + 200);
        isFromSunflower = false;
        sunObj = new Ellipse2D.Float(CoordX, CoordY, 80, 80);
    }

    public Sun(int LaneX, int LaneY){
        CoordX = Plants.getMapSlot(LaneX, LaneY).getX() - 15;
        CoordY = Plants.getMapSlot(LaneX, LaneY).getY() - 30;
        isFromSunflower = true;
        sunObj = new Ellipse2D.Float(CoordX, CoordY, 80, 80);
    }

    public Sun(@JsonProperty("coordX")int CoordX, @JsonProperty("coordY")int CoordY, @JsonProperty("fromSunflower")boolean isFromSun, @JsonProperty("waiting") boolean IsWaiting){
        this.CoordX = CoordX;
        this.CoordY = CoordY;
        isFromSunflower = isFromSun;
        isWaiting = IsWaiting;
        sunObj = new Ellipse2D.Float(CoordX, CoordY, 80, 80);
    }

    public static void startTimer() {
        timerNonSunflowerDrop = new Timer(5000, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                Game.suns.add(new Sun());
                timerNonSunflowerDrop.setDelay(((int)(Math.random() * 6) + 5) * 1000);
                if(Game.stopThread){
                    ((Timer)e.getSource()).stop();
                }
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
    @JsonIgnore
    public int getCoordYLimit() {
        return CoordYLimit;
    }
    public boolean isFromSunflower() {
        return isFromSunflower;
    }

    @JsonIgnore
    public Ellipse2D getObj() {
        return sunObj;
    }
    @JsonIgnore
    public boolean isTsunAlive() {
        return sunDropThread.isAlive();
    }
    public boolean isWaiting() {
        return isWaiting;
    }

    @JsonIgnore
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
