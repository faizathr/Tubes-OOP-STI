package com.michaelvslalapan.Organism.Tanaman;

public class PotatoMine extends Plants {
    private boolean isArmed;
    private Thread armingThread;

    public PotatoMine() {
        super("Potato Mine", 25, 100, 1000, 0, 0, 15, false);
        this.isArmed = false;
        startArmingThread();
    }

    public boolean isArmed() {
        return isArmed;
    }

    private void arm() {
        this.isArmed = true;
    }

    private void startArmingThread() {
        armingThread = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(5000); // Arming delay
                    arm(); // Arm the mine after the delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        armingThread.start();
    }

    // Method untuk cek apakah mine explodes
    public boolean explodes() {
        return isArmed;
    }
}
