package com.michaelvslalapan.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip; 

public class AudioManager{
    private static Clip[] AudioClip = new Clip[27]; 
    private static Timer AudioTimer;

    static{
        String errfile = "";
        try{
            String wavfilenames[] = new String[] {
                "Menu", 
                "Background", 
                "Win", 
                "Lose", 
                "Zombies_coming", 
                "Seedlift", 
                "Plant", 
                "Eat", 
                "Buzzer", 
                "Evillaugh", 
                "Shovel", 
                "Remove", 
                "Wave", 
                "Siren", 
                "Groan_brains1", 
                "Groan_brains2", 
                "Groan_brains3",
                "Groan1",
                "Groan2",
                "Groan3",
                "Yuck",
                "Yuck2",
                "Splat",
                "Shieldhit",
                "Cherry_enlarge",
                "Cherrybomb",
                "Points"
            };

            for (int i = 0; i < wavfilenames.length; i++){
                errfile = wavfilenames[i] + ".wav";
                AudioClip[i] = AudioSystem.getClip();
                AudioClip[i].open(AudioSystem.getAudioInputStream(AudioManager.class.getResource(("/wav/" + wavfilenames[i] + ".wav"))));
            }
        } catch(Exception ex) { 
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot open audio! : " + errfile);
        } 

        //play zombies coming after 20 seconds
        AudioTimer = new Timer(20000, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                AudioClip[4].setMicrosecondPosition(0);
                AudioClip[4].start(); 
                AudioTimer.stop();
            }
        });
    }

    public static void menu(){
        AudioClip[0].start(); 
        AudioClip[0].loop(Clip.LOOP_CONTINUOUSLY);
    }
    public static void evillaugh(){
        AudioClip[0].stop();
        AudioClip[0]=null;
        AudioClip[9].start(); 
    }

    public static void begin(){
        AudioClip[9]=null;
        AudioClip[2].stop();
        AudioClip[3].stop();
        
        AudioClip[1].start(); 
        AudioClip[1].loop(Clip.LOOP_CONTINUOUSLY);
        AudioTimer.start();
    }

    public static void win(){
        AudioClip[1].stop(); 
        AudioClip[1].setMicrosecondPosition(0);
        
        AudioClip[2].setMicrosecondPosition(0);
        AudioClip[2].start();
    }

    public static void lose(){
        AudioClip[1].stop(); 
        AudioClip[1].setMicrosecondPosition(0);
        
        AudioClip[3].setMicrosecondPosition(0);
        AudioClip[3].start();
    }
    
    public static void seedlift(){
        AudioClip[5].setMicrosecondPosition(0);
        AudioClip[5].start();
    }
    public static void plant(){
        AudioClip[6].setMicrosecondPosition(0);
        AudioClip[6].start();
    }
    public static void shovel(){
        AudioClip[10].setMicrosecondPosition(0);
        AudioClip[10].start();
    }
    public static void remove(){
        AudioClip[11].setMicrosecondPosition(0);
        AudioClip[11].start();
    }
    
    public static void eat(){
        AudioClip[7].setMicrosecondPosition(0);
        AudioClip[7].start();
    }
    public static boolean isEating(){
        return AudioClip[7].isActive();
    }

    public static void buzzer(){
        AudioClip[8].setMicrosecondPosition(0);
        AudioClip[8].start();
    }

    public static void wave(){
        AudioClip[12].setMicrosecondPosition(0);
        AudioClip[12].start();
    }
    public static void siren(){
        AudioClip[13].setMicrosecondPosition(0);
        AudioClip[13].start();
    }

    public static void brain1(){
        AudioClip[14].setMicrosecondPosition(0);
        AudioClip[14].start();
    }
    public static void brain2(){
        AudioClip[15].setMicrosecondPosition(0);
        AudioClip[15].start();
    }
    public static void brain3(){
        AudioClip[16].setMicrosecondPosition(0);
        AudioClip[16].start();
    }
    public static void groan1(){
        AudioClip[17].setMicrosecondPosition(0);
        AudioClip[17].start();
    }
    public static void groan2(){
        AudioClip[18].setMicrosecondPosition(0);
        AudioClip[18].start();
    }
    public static void groan3(){
        AudioClip[19].setMicrosecondPosition(0);
        AudioClip[19].start();
    }
    public static void yuck(){
        AudioClip[20].setMicrosecondPosition(0);
        AudioClip[20].start();
    }
    public static void yuck2(){
        AudioClip[21].setMicrosecondPosition(0);
        AudioClip[21].start();
    }
    public static void splat(){
        AudioClip[22].setMicrosecondPosition(0);
        AudioClip[22].start();
    }
    public static void shieldhit(){
        AudioClip[23].setMicrosecondPosition(0);
        AudioClip[23].start();
    }
    public static void cherryEnlarge(){
        AudioClip[24].setMicrosecondPosition(0);
        AudioClip[24].start();
    }
    public static void cherryExplode(){
        AudioClip[25].setMicrosecondPosition(0);
        AudioClip[25].start();
    }
    public static void sunPoints(){
        AudioClip[26].setMicrosecondPosition(0);
        AudioClip[26].start();
    }
}