package com.michaelvslalapan.Organism.Tanaman;

public class Squash extends Plants<Integer> {
    private int jumpHeight = 0;
    private int jumpDisplacement = 0;
    private boolean isSquashJumped = false;

    public Squash(int LaneX, int LaneY){
        super(4, "Squash", 50, 100, 5000, 0, 1, 20, false, LaneX, LaneY);
    }

    public int getJumpHeight() {
        return jumpHeight;
    }

    public void addJumpHeight() {
        jumpHeight += 2;
    }
    
    public void reduceJumpHeight() {
        jumpHeight -= 2;
    }

    public int getJumpDisplacement() {
        return jumpDisplacement;
    }

    public void addJumpDisplacement() {
        jumpDisplacement += 2;
    }

    public boolean getIsSquashJumped() {
        return isSquashJumped;
    }

    public void setIsSquashJumped() {
        isSquashJumped = true;
    }
}
