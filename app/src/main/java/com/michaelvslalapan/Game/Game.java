package com.michaelvslalapan.Game;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.michaelvslalapan.Main;
import com.michaelvslalapan.ADT.Point;
import com.michaelvslalapan.Organism.Tanaman.PlantInventory;
import com.michaelvslalapan.Organism.Tanaman.Plants;
import com.michaelvslalapan.Organism.Tanaman.Sunflower;
import com.michaelvslalapan.Organism.Zombie.Zombie;
import com.michaelvslalapan.SaveAndLoad.Load;
import com.michaelvslalapan.SaveAndLoad.Save;

public class Game extends JPanel implements ActionListener{
    private String[] imgfilenames = new String[] {
        "Background.png",
        "Background_night.png",
        "Backyard.png",
        "PlantDeck.png",
        "PlantCatalog1.png",
        "PlantCatalog2.png",
        "Sun.png",
        "Shovel1.png",
        "Shovel2.png",
        "Shovel3.png",
        "Sunflower.png",
        "Sunflower_g.png",
        "Peashooter.png",
        "Peashooter_g.png",
        "Wallnut.png",
        "Wallnut_g.png",
        "Snowpea.png",
        "Snowpea_g.png",
        "Squash.png",
        "Squash_g.png",
        "Lilypad.png",
        "Lilypad_g.png",
        "Repeater.png",
        "Repeater_g.png",
        "ExplodeONut.png",
        "ExplodeONut_g.png",
        "Cherrybomb.png",
        "Cherrybomb_g.png",
        "Puffshroom.png",
        "Puffshroom_g.png",
        "FinalWave.png",
        "HugeWave.png",
        "Brain.png",
        "Wasted.png",
        "Pea_p.png",
        "Pea_s.png",
        "Pea_r.png",
        "Pea_ps.png",
        "Sunflower.gif",
        "Peashooter.gif",
        "Wallnut_full.gif",
        "Snowpea.gif",
        "Squash.gif",
        "Lilypad.gif",
        "Repeater.gif",
        "ExplodeONut.png",
        "Cherrybomb.png",
        "Puffshroom.png",
        "Wallnut_half.gif",
        "StartPlaying.png",
        "StartPlaying.png",
        "Powie.png",
        "NormalZombie.gif",
        "ConeheadZombie.gif",
        "PoleVaultingZombie.gif",
        "BucketheadZombie.gif",
        "DuckyTubeZombie.gif",
        "DolphinRiderZombie.gif",
        "FootballZombie.gif",
        "SnorkelZombie.gif",
        "WallnutZombie.gif",
        "ScreendoorZombie.gif",
        "DolphinRiderZombieJump.gif",
        "PoleVaultingZombieJump.gif",
        "NormalZombieSlow.gif",
        "ConeheadZombieSlow.gif",
        "PoleVaultingZombieSlow.gif",
        "BucketheadZombieSlow.gif",
        "DuckyTubeZombieSlow.gif",
        "DolphinRiderZombieSlow.gif",
        "FootballZombieSlow.gif",
        "SnorkelZombieSlow.gif",
        "WallnutZombieSlow.gif",
        "ScreendoorZombieSlow.gif",
        "DolphinRiderZombieJumpSlow.gif",
        "PoleVaultingZombieJumpSlow.gif",
        "Tryagain.png",
        "Trophy.png",
        "Playagain.png",
        "SaveExitButton.png"
    };
    private Shape[][] fieldClickArea = new Shape[9][6];
    private Image[] img = new Image[imgfilenames.length];
    private Image MainMenu;
    private Ellipse2D shovel2D;
    private Point mouse = new Point();
    private Rectangle endScreen, startDeckSelectionButton, startGameButton, playAgainButton, saveAndExitButton;
    private Rectangle[] plantCatalogClickArea = new Rectangle[6];

    //Zombie
    private float zombieCoordX;
    private int zombieLaneY;

    //Plant
    private int plantCoordX;
    private int plantCoordY;
    private int plantLaneX;
    private int plantLaneY;
    private static List<Double> cooldownPlantList = new ArrayList<Double>(); 
    private static List<Double> realCooldownPlantList = new ArrayList<Double>(); 

    // Player
    private int sunCredits = 50, sunChanges, selectedPlant = -1;
    private boolean isUsingShovel = false;
    private Font font;
    private String[] PlantID = new String[]{
        "Sunflower",
        "Peashooter",
        "Wallnut",
        "SnowPea",
        "Squash",
        "Lilypad",
        "Repeater",
        "ExplodeONut",
        "Cherrybomb",
        "Puffshroom"
    };
    private int[] PlantCost = new int[]{
        50, 100, 50, 175, 50, 25, 200, 100, 150, 0
    };
    
    public int getPlantID(String PlantName) {
        for (int i = 0; i < PlantID.length; i++) {
            if (PlantName.equals(PlantID[i])) return i;
        }
        return -1;
    }
    public int getCostByPlantID(int PlantID) {
        return PlantCost[PlantID];
    }

    public double getCooldownByPlantID(int PlantID) {
        return (PlantInventory.getPlantCatalog().get(PlantID).getCooldown());
    }

    // Plant Catalog
    private Rectangle[] PlantDeckClickArea = new Rectangle[6];
    private Rectangle[] PlantCatalogClickArea = new Rectangle[10];
    private int selectedCatalogPlantID = -1, selectedDeckPlantID = -1;
    private int[] PlantDeck = new int[]{-1, -1, -1, -1, -1, -1};
    private int prevPlantDeck = -1;
    private boolean isPlantSelectedOnDeck(int PlantID) {
        for (int PlantDeckID = 0; PlantDeckID < 6; PlantDeckID++) {
            if (PlantDeck[PlantDeckID] == PlantID) {
                return true;
            }
        }
        return false;
    }
    private boolean isPlantDeckFilled() {
        for (int i = 0; i < 6; i++) {
            if (PlantDeck[i] == -1) {
                return false;
            }
        }
        return true;
    }
    private static int zombieCategory = 5;
    private static int zombieDisplayMultiplier = 3;
    private static Point[][] zombieDisplayCoord = new Point[zombieCategory][zombieDisplayMultiplier];
    static{
        for (int i = 0; i < zombieDisplayMultiplier; i++) {
            for (int zombieDisplay = 0; zombieDisplay < zombieCategory; zombieDisplay++) {
                zombieDisplayCoord[zombieDisplay][i] = new Point((int)(Math.random() * ((1024 - (73 + 10)) - 770 + 1 ) + 770), (int)(Math.random() * ((626 - (119 + 10)) - 0 + 1) + 0));
            }
        }
    }
    private Timer plantCatalogSlideTimer;
    private float plantCatalogSlideVal = -376f;
    private boolean isSliding = false;

    //Game
    private Timer gameTimer, secondsTimer;
    private Toolkit toolkit = Toolkit.getDefaultToolkit();
    private boolean plantDeckSelection = false;
    private boolean isGameStarted = false;
    private boolean isNight = false;
    private boolean isPlaying = true;
    private boolean isWinning = false;
    private boolean endSoundPlaying = false;
    private boolean isClickingSun = false;
    private boolean zombieFlagDead = false;
    private boolean isFinalWave = false;
    private static int zombieInMap = 0;
    private static int zombieWave = 0;
    public static boolean isUsingPreviousGame = false;
    public static int isContinueGameSaved;
    
    private Plants<Integer> plant = new Sunflower(0, 0);
    private Pea pea;
    private Sun sun;
    public static List<Plants<Integer>> plants = new ArrayList<Plants<Integer>>();
    public static List<Zombie> zombies = new ArrayList<Zombie>();
    public static List<Sun> suns = new ArrayList<Sun>();
    public static List<Pea> peas = new ArrayList<Pea>();
    private static int secondsTime = 0;

    private void loadMainMenu() {
        try {
            MainMenu = toolkit.getImage(getClass().getResource("/img/Menu.jpg"));
        } catch(Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot open image!");
        }
    }

    public void loadImage() {
        String errfile = "";
        try {
            for (int i = 0; i < imgfilenames.length; i++) {
                errfile = imgfilenames[i];
                img[i] = toolkit.getImage(getClass().getResource("/img/" + imgfilenames[i]));
            }
        } catch(Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot open image! : " + errfile);
        }
    }

    private void loadFont() {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("/font/Chalkboard.ttc").openStream()).deriveFont(Font.BOLD, 20f);
            GraphicsEnvironment GUIEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GUIEnv.registerFont(font);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Unable to open font!");
        }
    }

    private void drawSun(Graphics2D GUI_2D) {
        GUI_2D.setFont(font); 
        GUI_2D.setColor(Color.BLACK);
        FontMetrics metrics = GUI_2D.getFontMetrics(font);
        if (sunCredits == sunChanges) {
            GUI_2D.drawString(Integer.toString(sunChanges), 91-(metrics.stringWidth(Integer.toString(sunChanges))/2), 136);
        } else if (sunCredits < sunChanges) {
            sunChanges -= 5;
            GUI_2D.drawString(Integer.toString(sunChanges), 91-(metrics.stringWidth(Integer.toString(sunChanges))/2), 136);
        } else {
            sunChanges += 5;
            GUI_2D.drawString(Integer.toString(sunChanges), 91-(metrics.stringWidth(Integer.toString(sunChanges))/2), 136);
        }
    }

    public Game() {
        gameTimer = new Timer(25, this);
        loadMainMenu();

        addMouseListener(new MouseListener());
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                mouse.setX(e.getX());
                mouse.setY(e.getY());
            }
        });

        startDeckSelectionButton = new Rectangle(445, 525, 135, 42);

        AudioManager.menu();
        gameTimer.start();
    }

    public void startDeckSelection() {
        plantDeckSelection = true;
        loadFont();

        startGameButton = new Rectangle(343, 50, 338, 100);

        loadImage();
        plantCatalogSlideTimer = new Timer(48, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                plantCatalogSlideVal += 9.024f;
                for (int i = 0; i < zombieDisplayMultiplier; i++) {
                    for (int zombieDisplay = 0; zombieDisplay < zombieCategory; zombieDisplay++) {
                        Point zombiePoint = zombieDisplayCoord[zombieDisplay][i];
                        zombiePoint.setX(zombiePoint.getX() + 9);
                    }
                }
            }
        });

        if(isUsingPreviousGame){
            startGameButton = null;
            isSliding = true;
            plantCatalogSlideTimer.start();
        }
        AudioManager.selectPlant();
    }

    public void startGame() {
        for(int i = 0; i < 6; i++){
            cooldownPlantList.add(getCooldownByPlantID(i));
            realCooldownPlantList.add(0.0);
        }

        System.out.println(cooldownPlantList.toString());

        sunChanges = sunCredits;

        Sun.startTimer();
        Zombie.startSpawning();
        init();
        
        AudioManager.begin();
        gameTimer.start();
        secondsTimer = new Timer(1000, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                secondsTime += 1;
                if (secondsTime >= 100) {
                    isNight = true;
                }
            }
        });
        secondsTimer.start();

        saveAndExitButton = new Rectangle(805, 560, 200, 58);
    }

    private void drawPlantCost(Graphics2D GUI_2D) {
        GUI_2D.setFont(font); 
        GUI_2D.setColor(Color.WHITE);
        for (int p = 0; p < 6; p++) {
            GUI_2D.drawString(PlantDeck[p] == -1 ? "" : Integer.toString(PlantCost[PlantDeck[p]]), 120, 197 + 74 * p);

            if (!isSliding) {
                GUI_2D.drawString(Integer.toString(PlantCost[p]), 265 + 105, 197 + 74 * p);
                if (p < 4) {
                    GUI_2D.drawString(Integer.toString(PlantCost[p+6]), 415 + 105, 197 + 74 * p);
                }
            }
        }
    }

    private void drawTime(Graphics2D GUI_2D, int time) {
        GUI_2D.setFont(font); 
        if (time >= 100) GUI_2D.setColor(Color.WHITE);
        else GUI_2D.setColor(Color.BLACK);
        GUI_2D.drawString(Integer.toString(time) + " s", 385, 605);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameTimer.start();
        repaint();
    }

    @Override
    public void paintComponent(Graphics GUI) {
        super.paintComponent(GUI);
        Graphics2D GUI_2D = (Graphics2D) GUI;

        Image[] background = new Image[]{img[0], img[1], img[2]};
        Image[] plantDeckImg = new Image[]{img[3], img[4], img[5]};
        
        Image[] plantCatalogImg = new Image[]{img[10], img[12], img[14], img[16], img[18], img[20], img[22], img[24], img[26], img[28]};
        Image[] plantCatalogImgDark = new Image[]{img[11], img[13], img[15], img[17], img[19], img[21], img[23], img[25], img[27], img[29]};
        
        Image[] zombieImg = new Image[]{img[52], img[53], img[54], img[55], img[56], img[57], img[58], img[59], img[60], img[61], img[62], img[63]};
        Image[] zombieImgSlowed = new Image[]{img[64], img[65], img[66], img[67], img[68], img[69], img[70], img[71], img[72], img[73], img[74], img[75]};
        
        Image[] peasImg = new Image[]{img[34], img[35], img[36], img[37]};
        Image[] plantGif = new Image[]{img[38], img[39], img[40], img[41], img[42], img[43], img[44], img[45], img[46], img[47]};
        Image[] shovelImg = new Image[]{img[7], img[8], img[9]};
        Image[] losingGameImg = new Image[]{img[32], img[33], img[76]};
        Image sunImg = img[6];
        Image WallnutHalfLife = img[48];
        Image explosionImage = img[51];
        Image startPlayingImg = img[49];
        Image FinalWave = img[30];
        Image HugeWave = img[31];

        if (!plantDeckSelection) {
            GUI.drawImage(MainMenu, 0, 0, 1024, 625, this);
        } else if (!isGameStarted) {
            GUI.drawImage(background[2], (int) plantCatalogSlideVal, 0, 1400, 625, this);

            GUI.drawImage(plantDeckImg[1], 15, 22, 150, 580, this);

            if (!isSliding) {
                GUI.drawImage(plantDeckImg[1], 265, 22, 150, 580, this);
                GUI.drawImage(plantDeckImg[2], 415, 22, 150, 580, this);
            }
            
            drawPlantCost(GUI_2D);

            if (!isSliding) {
                for (int i = 0; i < 6; i++) {
                    PlantDeckClickArea[i] = new Rectangle(25, 156 + 74 * i, 132, 68);
                    if (PlantDeck[i] > -1) {
                        GUI.drawImage(plantCatalogImg[PlantDeck[i]], 40, 156 + 74 * i, 62, 66, this);
                    }
        
                    PlantCatalogClickArea[i] = new Rectangle(275, 156 + 74 * i, 132, 68);
                    GUI.drawImage(plantCatalogImg[i], 290, 156 + 74 * i, 62, 66, this);
                    if (i < 4) {
                        PlantCatalogClickArea[i+6] = new Rectangle(425, 156 + 74 * i, 132, 68);
                        GUI.drawImage(plantCatalogImg[i+6], 440, 156 + 74 * i, 62, 66, this);
                    }
                }
                if (selectedCatalogPlantID > -1) {
                    GUI_2D.setComposite(AlphaComposite.SrcOver.derive(0.7f));
                    GUI_2D.drawImage(plantCatalogImg[selectedCatalogPlantID], mouse.getX() - 31, mouse.getY() - 33, 62, 66, this);
                    GUI_2D.setComposite(AlphaComposite.SrcOver.derive(1f));
                }
                if (selectedDeckPlantID > -1) {
                    GUI_2D.setComposite(AlphaComposite.SrcOver.derive(0.7f));
                    GUI_2D.drawImage(plantCatalogImg[selectedDeckPlantID], mouse.getX() - 31, mouse.getY() - 33, 62, 66, this);
                    GUI_2D.setComposite(AlphaComposite.SrcOver.derive(1f));
                }
        
                GUI.drawImage(startPlayingImg, 343, 50, 338, 100, this);
            }

            if (isSliding) {
                for (int i = 0; i < 6; i++) {
                    GUI.drawImage(plantCatalogImgDark[PlantDeck[i]], 40, 156 + 74 * i, 62, 66, this);
                }
            }

            for (int i = 0; i < zombieDisplayMultiplier; i++) {
                for (int zombieDisplay = 0; zombieDisplay < zombieCategory; zombieDisplay++) {
                    GUI.drawImage(zombieImg[zombieDisplay], zombieDisplayCoord[zombieDisplay][i].getX(), zombieDisplayCoord[zombieDisplay][i].getY(), 73, 119, this);
                }
            }
            

            if (plantCatalogSlideVal >= 0.0f) {
                plantCatalogSlideVal = 0;
                plantCatalogSlideTimer.stop();
                isGameStarted = true;
                startGame();
            }
        } else {
            if (!isNight) {
                GUI.drawImage(background[0], 0, 0, 1024, 625, this);
            } else {
                GUI.drawImage(background[1], 0, 0, 1024, 625, this);
            }

            //draw plant
            Iterator<Plants<Integer>> iteratedPlant = plants.iterator(); 
            while (iteratedPlant.hasNext()){
                plant = iteratedPlant.next();

                plantCoordX = Plants.getMapSlot(plant.getLaneX(), plant.getLaneY()).getX();
                plantCoordY = Plants.getMapSlot(plant.getLaneX(), plant.getLaneY()).getY();

                if (plant.getPlantID().equals(0)) { // Sunflower
                    GUI.drawImage(plantGif[0], plantCoordX, plantCoordY, plant.getPlantWidth(), plant.getPlantHeight(), this);
                    plant.startProducingSun();
                } else if (plant.getPlantID().equals(2)) { // Wallnut
                    if(plant.get_Health() >= 150){
                        GUI.drawImage(plantGif[2], plantCoordX, plantCoordY, plant.getPlantWidth(), plant.getPlantHeight(), this);
                    } else { // Wallnut half life
                        GUI.drawImage(WallnutHalfLife, plantCoordX, plantCoordY, plant.getPlantWidth(), plant.getPlantHeight(), this);
                    }
                } else if (plant.getPlantID().equals(4)) { // Squash
                    GUI.drawImage(plantGif[4], plantCoordX, plantCoordY, plant.getPlantWidth(), plant.getPlantHeight(), this);
                } else if (plant.getPlantID().equals(5)) { // Lilypad
                    //System.out.println(new Point(plant.getLaneX(), plant.getLaneY()).print());
                    GUI.drawImage(plantGif[5], 265 + 82 * plant.getLaneX(), 75 + 88 * plant.getLaneY(), plant.getPlantWidth(), plant.getPlantHeight(), this);
                } else if(plant.getPlantID().equals(7)) { // ExplodeONut
                    if (!plant.isDead()) {
                        GUI.drawImage(plantGif[7], 265 + 82 * plant.getLaneX(), 75 + 88 * plant.getLaneY(), plant.getPlantWidth(), plant.getPlantHeight(), this);
                    } else {
                        if (plant.getPlantWidth() < 110) { //enlarge
                            GUI.drawImage(plantGif[7], plantCoordX - (plant.getPlantWidth() - 62) / 2, plantCoordY - (plant.getPlantHeight() - 66) / 2, plant.getPlantWidth(), plant.getPlantWidth(), this);
                            plant.enlargeExplodingPlant();
                            AudioManager.cherryEnlarge();
                        } else {
                            plantLaneX = plant.getLaneX();
                            plantLaneY = plant.getLaneY();
                            if(!plant.getIsExploded()){
                                AudioManager.cherryExplode();
                                plant.explodePlant();
                                plant.startTimer();
                                Plants.emptySlot(plantLaneX, plantLaneY);
                                
                                //kill zombie
                                Iterator<Zombie> iteratedZombie = zombies.iterator(); 
                                while (iteratedZombie.hasNext()){
                                    Zombie zombie = iteratedZombie.next();
                                    if (zombie.getLaneY() <= (plantLaneY+1) && zombie.getLaneY() >= (plantLaneY-1) 
                                    && zombie.getLaneX() <= (plantLaneX+1) && zombie.getLaneX() >= (plantLaneX-1)) {
                                        zombie.stopAttackingPlant();
                                        iteratedZombie.remove();
                                        reduceZombieInMapCount();
                                    }
                                }
                            }
                            if (plant.isthreadToExplodeAlive()) {
                                GUI.drawImage(explosionImage, plantCoordX-150, plantCoordY-125, 300, 250, this);
                            } else {
                                iteratedPlant.remove();
                            }
                        }
                    }
                } else if(plant.getPlantID().equals(8)) { // Cherrybomb
                    if (plant.getPlantWidth() < 110) { //enlarge
                        GUI.drawImage(plantGif[8], plantCoordX - (plant.getPlantWidth() - 76) / 2, plantCoordY - (plant.getPlantHeight() - 74) / 2, plant.getPlantWidth(), plant.getPlantWidth(), this);
                        plant.enlargeExplodingPlant();
                        AudioManager.cherryEnlarge();
                    } else {
                        plantLaneX = plant.getLaneX();
                        plantLaneY = plant.getLaneY();
                        if(!plant.getIsExploded()){
                            AudioManager.cherryExplode();
                            plant.explodePlant();
                            plant.startTimer();
                            Plants.emptySlot(plantLaneX, plantLaneY);
                            
                            //kill zombie
                            Iterator<Zombie> iteratedZombie = zombies.iterator(); 
                            while (iteratedZombie.hasNext()){
                                Zombie zombie = iteratedZombie.next();
                                if (zombie.getLaneY() <= (plantLaneY+1) && zombie.getLaneY() >= (plantLaneY-1) 
                                && zombie.getLaneX() <= (plantLaneX+1) && zombie.getLaneX() >= (plantLaneX-1)) {
                                    zombie.stopAttackingPlant();
                                    iteratedZombie.remove();
                                    reduceZombieInMapCount();
                                }
                            }
                        }
                        if (plant.isthreadToExplodeAlive()) {
                            GUI.drawImage(explosionImage, plantCoordX-150, plantCoordY-125, 300, 250, this);
                        } else {
                            iteratedPlant.remove();
                        }
                    }      
                } else {
                    if(plant.getPlantID().equals(1)){ //peashooter gif
                        GUI.drawImage(plantGif[1], plantCoordX, plantCoordY, plant.getPlantWidth(), plant.getPlantHeight(), this);
                    } else if(plant.getPlantID().equals(3)){ //snowpea gif
                        GUI.drawImage(plantGif[3], plantCoordX, plantCoordY, plant.getPlantWidth(), plant.getPlantHeight(), this);
                    } else if(plant.getPlantID().equals(6)) { //repeater gif
                        GUI.drawImage(plantGif[6], plantCoordX, plantCoordY, plant.getPlantWidth(), plant.getPlantHeight(), this);
                    } else if(plant.getPlantID().equals(9)) { //puffshroom gif
                        GUI.drawImage(plantGif[9], plantCoordX, plantCoordY, plant.getPlantWidth(), plant.getPlantHeight(), this);
                    }

                    //shoot zombie
                    plantLaneX = plant.getLaneX();
                    plantLaneY = plant.getLaneY();

                    IterateZombieTarget: for (Zombie zombie: zombies) {
                        if (plantLaneY == zombie.getLaneY() && plantLaneX <= zombie.getLaneX()){
                            if (plant.getIsIdle()) {
                                if (plant.getPlantID().equals(9) && isNight) {
                                    plant.attack();
                                } else if (!plant.getPlantID().equals(9)) {
                                    plant.attack();
                                }
                            }
                            plant.setIsThreaten(true);
                            break IterateZombieTarget;
                        } else {
                            plant.setIsThreaten(false);
                        }
                    }
                    if (zombies.isEmpty()) {
                        plant.setIsThreaten(false);
                    }
                    if (!plant.getIsThreaten()) {
                        plant.stop();
                    }
                }
            }

            //zombie
            Iterator<Zombie> iteratedZombie = zombies.iterator(); 
            while (iteratedZombie.hasNext()){
                Zombie zombie = iteratedZombie.next();

                zombie.attackOrMove();
                
                zombieCoordX = zombie.getCoordX();
                zombieLaneY = zombie.getLaneY();

                Image[] zombieSlowedOrNotImg = new Image[]{zombieImg[zombie.getZombieID()], zombieImgSlowed[zombie.getZombieID()]};
                int isZombieImgSlowed = zombie.getIsSlowed() ? 1 : 0;

                //System.out.println("zombie.getZombieID(): " + zombie.getZombieID());
                //System.out.println("isZombieImgSlowed: " + isZombieImgSlowed);
                //System.out.println("zombieCoordX: " + zombieCoordX);
                
                //draw zombie
                GUI.drawImage(zombieSlowedOrNotImg[isZombieImgSlowed], Math.round(zombieCoordX), zombie.getCoordY(), zombie.getWidth(), zombie.getHeight(), this);
                
                //check if zombie intersects pea
                Iterator<Pea> iteratedPea = peas.iterator(); 
                while (iteratedPea.hasNext()){
                    pea = iteratedPea.next();
                    if(pea.getSpawnLaneY() == zombieLaneY){
                        if((pea.getCoordX() >= zombieCoordX-6) && (pea.getCoordX() <= zombieCoordX+92)){
                            AudioManager.splat();
                            zombie.decreaseHealth(pea.getDamage());
                            if (pea.getPlantID() == 3) zombie.slowed();
                            iteratedPea.remove();
                        }
                    }
                }

                if(zombie.isDead()){
                    zombie.stopAttackingPlant();
                    AudioManager.yuck();
                    iteratedZombie.remove();
                    reduceZombieInMapCount();
                }
                
                if (zombie.isGameOver()){
                    isPlaying = false;
                    isWinning = false;
                    if(zombieCoordX <= 23){
                        iteratedZombie.remove();
                    }
                }
            }

            //check if all zombies before wave are dead
            if (zombieWave == 0 && zombies.isEmpty() && zombieFlagDead){
                Zombie.startWave();
            }

            //check if all zombies are dead
            if(secondsTime > 160 && zombies.isEmpty()){
                isPlaying = false;
                isWinning = true;
            }

            //draw plant menu
            GUI.drawImage(plantDeckImg[0], 15, 22, 150, 580, this);

            //draw sunflower points
            drawSun(GUI_2D);

            //draw black&white plant menu
            for (int i = 0; i < 6; i++) {
                if (getSunCredits() >= PlantCost[PlantDeck[i]] && realCooldownPlantList.get(i) == 0.0) {
                    GUI.drawImage(plantCatalogImg[PlantDeck[i]], 40, 156 + 74 * i, 62, 66, this);
                } else {
                    GUI.drawImage(plantCatalogImgDark[PlantDeck[i]], 40, 156 + 74 * i, 62, 66, this);
                }
            }

            drawPlantCost(GUI_2D);

            //draw shovel
            if(!isUsingShovel()){ //if shovel is idle
                GUI.drawImage(shovelImg[1], 171, 548, 70, 70, this);
            }else{ //if shovel is taken
                GUI.drawImage(shovelImg[2], 171, 548, 70, 70, this);
                //draw shovel following mouse position
                GUI.drawImage(shovelImg[0], mouse.getX(), mouse.getY()-70, 68, 70, this);
            }

            drawTime(GUI_2D, secondsTime);

            if (getSelectedPlant() > -1) {
                GUI_2D.setComposite(AlphaComposite.SrcOver.derive(0.7f));
                GUI_2D.drawImage(plantCatalogImg[selectedPlant], mouse.getX() - 31, mouse.getY() - 33, 62, 66, this);
                GUI_2D.setComposite(AlphaComposite.SrcOver.derive(1f));
            }

            // save and exit
            GUI.drawImage(img[79], 805, 560, 200, 58, this);


            if (isPlaying) {
                Iterator<Pea> iteratedPea = peas.iterator();
                while (iteratedPea.hasNext()){
                    pea=iteratedPea.next();
                    if(pea.getPlantID() == 1){
                        GUI.drawImage(peasImg[0], pea.getCoordX(), pea.getCoordY(), this);
                    } else if (pea.getPlantID() == 3) {
                        GUI.drawImage(peasImg[1], pea.getCoordX(), pea.getCoordY(), this);
                    } else if (pea.getPlantID() == 6) {
                        GUI.drawImage(peasImg[2], pea.getCoordX(), pea.getCoordY(), this);
                    } else if (pea.getPlantID() == 9) {
                        GUI.drawImage(peasImg[3], pea.getCoordX(), pea.getCoordY(), this);
                    }
                    pea.move(6);
                        
                    if(pea.getCoordX() > 1030){
                        iteratedPea.remove();
                    }
                }
            
                //draw falling sun
                Iterator<Sun> iteratedSun = suns.iterator(); 
                while (iteratedSun.hasNext()){
                    sun = iteratedSun.next();
                    if (sun.isFromSunflower()){ 
                        if(!sun.isWaiting()){
                            sun.startThread();
                            sun.setWaiting();
                        }
                        if(sun.isTsunAlive()){
                            GUI.drawImage(sunImg,sun.getCoordX(),sun.getCoordY(),80,80,this);
                            sun.setSunObj(new Ellipse2D.Float(sun.getCoordX(), sun.getCoordY(), 80, 80));
                        }else{
                            // Auto collect sun
                            AudioManager.sunPoints();
                            addSunCredits();
                            iteratedSun.remove();
                        }
                    } else {
                        if (sun.getCoordY() < sun.getCoordYLimit()) {
                            GUI.drawImage(sunImg,sun.getCoordX(),sun.getCoordY(),80,80,this);
                            sun.setSunObj(new Ellipse2D.Float(sun.getCoordX(), sun.getCoordY(), 80, 80));
                            sun.lowerCoordY();
                        } else if(sun.getCoordY()<(sun.getCoordYLimit()+300)) {
                            if (!sun.isWaiting()) {
                                sun.startThread();
                                sun.setWaiting();
                            }
                            if (sun.isTsunAlive()) {
                                GUI.drawImage(sunImg,sun.getCoordX(),sun.getCoordY(),80,80,this);
                                sun.setSunObj(new Ellipse2D.Float(sun.getCoordX(), sun.getCoordY(), 80, 80));
                            } else {
                                AudioManager.sunPoints();
                                addSunCredits();
                                iteratedSun.remove();
                            }
                        }
                    }
                }

                //wave
                if (zombieWave == 1) { //a huge wave of zombies is approaching
                    GUI.drawImage(HugeWave, 160, 290, 743, 42, this);
                } else if (zombieWave == 2) { //final wave
                    GUI.drawImage(FinalWave, 380, 280, 300, 61, this);
                }

            } else {
                selectPlant(-1);
                for(Plants<Integer> plant: plants){
                    plant.stop();
                }
                secondsTimer.stop();
                Zombie.stopSpawning();
                Sun.stop();
                suns.clear();
                peas.clear();

                if(isWinning){
                    if (!endSoundPlaying) {
                        AudioManager.win();
                        endSoundPlaying = true;
                    }                    
                    GUI_2D.setColor(Color.WHITE);
                    GUI_2D.setComposite(AlphaComposite.SrcOver.derive(0.6f));
                    GUI_2D.fill(endScreen);
                    GUI_2D.setComposite(AlphaComposite.SrcOver.derive(1f));
                    
                    //playAgainButton = new Rectangle(442, 410, 140, 65);

                    GUI.drawImage(img[77],430,187,166,136,this);
                    //GUI.drawImage(img[78],442,410,140,65,this);
                    
                } else {
                    if(!endSoundPlaying){
                        AudioManager.lose();
                        endSoundPlaying = true;
                    }
                    GUI_2D.setColor(Color.WHITE);
                    GUI_2D.setComposite(AlphaComposite.SrcOver.derive(0.6f));
                    GUI_2D.fill(endScreen);
                    GUI_2D.setComposite(AlphaComposite.SrcOver.derive(1f));
                    //playAgainButton = new Rectangle(400, 395, 220, 45);
                    
                    GUI.drawImage(losingGameImg[0],425,85,180,210,this); //brain image
                    GUI.drawImage(losingGameImg[1],365,190,this); //lose image
                    //GUI.drawImage(losingGameImg[2],410,405,200,25,this); //try again image
                }
            }
        }

        GUI.dispose();
    }

    private class MouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if (!plantDeckSelection) {
                if(startDeckSelectionButton.contains(e.getPoint())) {
                    AudioManager.evillaugh();
                    startDeckSelectionButton = null;
                    checkContinueGame();
                    startDeckSelection();
                }
            } else if (!isGameStarted) {
                for (int i = 0; i < 6; i++) {
                    if (PlantDeckClickArea[i].contains(e.getPoint())) {
                        if (selectedCatalogPlantID > -1) {
                            if (!isPlantSelectedOnDeck(selectedCatalogPlantID)) {
                                PlantDeck[i] = selectedCatalogPlantID;
                                selectedCatalogPlantID = -1;
                                AudioManager.shovel();
                            } else {
                                selectedCatalogPlantID = -1;
                                selectedDeckPlantID = -1;
                                AudioManager.buzzer();
                            }
                        } else if (selectedDeckPlantID > -1) {
                            if (PlantDeck[i] > -1 && prevPlantDeck > -1) {
                                PlantDeck[prevPlantDeck] = PlantDeck[i];
                                PlantDeck[i] = selectedDeckPlantID;
                                selectedDeckPlantID = -1;
                                prevPlantDeck = -1;
                            } else {
                                PlantDeck[i] = selectedDeckPlantID;
                                selectedDeckPlantID = -1;
                                prevPlantDeck = -1;
                            }
                            AudioManager.shovel();
                        } else if (PlantDeck[i] > -1) {
                            selectedDeckPlantID = PlantDeck[i];
                            PlantDeck[i] = -1;
                            prevPlantDeck = i;
                            AudioManager.shovel();
                        } else {
                            selectedCatalogPlantID = -1;
                            selectedDeckPlantID = -1;
                            AudioManager.buzzer();
                        }
                    }
                }
                for (int i = 0; i < 10; i++) {
                    if (PlantCatalogClickArea[i].contains(e.getPoint())) {
                        if (selectedDeckPlantID > -1) {
                            selectedCatalogPlantID = -1;
                            selectedDeckPlantID = -1;
                            AudioManager.remove();
                        } else {
                            if (selectedCatalogPlantID == -1) {
                                selectedCatalogPlantID = i;
                                AudioManager.shovel();
                            } else {
                                selectedCatalogPlantID = -1;
                                AudioManager.remove();
                            }
                        }
                    }
                }
                if(startGameButton.contains(e.getPoint())) {
                    if (isPlantDeckFilled()) {
                        isSliding = true;
                        startGameButton = null;
                        plantCatalogSlideTimer.start();
                    } else {
                        AudioManager.buzzer();
                    }
                }
            } else {
                if (isPlaying) {
                    Iterator<Sun> iteratedSun = suns.iterator(); 
                    IterateSun: while (iteratedSun.hasNext()){
                        sun = iteratedSun.next();
                        try {
                            if(sun.getObj().contains(e.getPoint())){
                                AudioManager.sunPoints();
                                addSunCredits();
                                isClickingSun = true;
                                iteratedSun.remove();
                                break IterateSun;
                            }
                        } catch(Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    if (!isClickingSun) {
                        if (plantCatalogClickArea[0].contains(e.getPoint())) {
                            if (getSunCredits() >= getCostByPlantID(PlantDeck[0]) && realCooldownPlantList.get(0) == 0.0) {
                                AudioManager.seedlift();
                                selectPlant((getSelectedPlant() == PlantDeck[0]) ? -1 : PlantDeck[0]);
                            } else {
                                AudioManager.buzzer();
                                selectPlant(-1);
                            }
                        } else if (plantCatalogClickArea[1].contains(e.getPoint())) {
                            if (getSunCredits() >= getCostByPlantID(PlantDeck[1])&& realCooldownPlantList.get(1) == 0.0) {
                                AudioManager.seedlift();
                                selectPlant((getSelectedPlant() == PlantDeck[1]) ? -1 : PlantDeck[1]);
                            } else {
                                AudioManager.buzzer();
                                selectPlant(-1);
                            }
                        } else if (plantCatalogClickArea[2].contains(e.getPoint())) {
                            if (getSunCredits() >= getCostByPlantID(PlantDeck[2]) && realCooldownPlantList.get(2) == 0.0) {
                                AudioManager.seedlift();
                                selectPlant((getSelectedPlant() == PlantDeck[2]) ? -1 : PlantDeck[2]);
                            } else {
                                AudioManager.buzzer();
                                selectPlant(-1);
                            }
                        } else if (plantCatalogClickArea[3].contains(e.getPoint())) {
                            if (getSunCredits() >= getCostByPlantID(PlantDeck[3]) && realCooldownPlantList.get(3) == 0.0) {
                                AudioManager.seedlift();
                                selectPlant((getSelectedPlant() == PlantDeck[3]) ? -1 : PlantDeck[3]);
                            } else {
                                AudioManager.buzzer();
                                selectPlant(-1);
                            }
                        } else if (plantCatalogClickArea[4].contains(e.getPoint())) {
                            if (getSunCredits() >= getCostByPlantID(PlantDeck[4]) && realCooldownPlantList.get(4) == 0.0) {
                                AudioManager.seedlift();
                                selectPlant((getSelectedPlant() == PlantDeck[4]) ? -1 : PlantDeck[4]);
                            } else {
                                AudioManager.buzzer();
                                selectPlant(-1);
                            }
                        } else if (plantCatalogClickArea[5].contains(e.getPoint())) {
                            if (getSunCredits() >= getCostByPlantID(PlantDeck[5]) && realCooldownPlantList.get(5) == 0.0) {
                                AudioManager.seedlift();
                                selectPlant((getSelectedPlant() == PlantDeck[5]) ? -1 : PlantDeck[5]);
                            } else {
                                AudioManager.buzzer();
                                selectPlant(-1);
                            }
                        } else if (getSelectedPlant() != -1) {
                            //System.out.println("selectedPlant: " + selectedPlant);
                            if (getSelectedPlant() == 5) {
                                int LaneX;
                                IterateLilypadMapSlot: for(LaneX = 0; LaneX < 9; LaneX++){
                                    for(int LaneY = 0; LaneY < 6; LaneY++){
                                        if (fieldClickArea[LaneX][LaneY].contains(e.getPoint())){
                                            //System.out.println("<" + LaneX + "," + LaneY + ">");
                                            if (LaneY == 2 || LaneY == 3) {
                                                if(plant.plantLilypad(LaneX, LaneY)){
                                                    AudioManager.plant();
                                                    plantSelected();
                                                } else {
                                                    AudioManager.buzzer();
                                                }
                                            } else {
                                                AudioManager.buzzer();
                                            }
                                            selectPlant(-1);
                                            break IterateLilypadMapSlot;
                                        }
                                    }
                                }
                                if (LaneX == 9) {
                                    selectPlant(-1);
                                }
                            } else {
                                int LaneX;
                                IterateMapSlot: for(LaneX = 0; LaneX < 9; LaneX++){
                                    for(int LaneY = 0; LaneY < 6; LaneY++){
                                        if (fieldClickArea[LaneX][LaneY].contains(e.getPoint())){
                                            if (LaneY == 2 || LaneY == 3) {
                                                if (Plants.getIsLilypadSlotFilled(LaneX, LaneY) != false) {
                                                    if(plant.plant(LaneX, LaneY, getSelectedPlant())){
                                                        AudioManager.plant();
                                                        plantSelected();
                                                        timerForCooldown(cooldownPlantList.get(getSelectedPlant()), getSelectedPlant());
                                                        realCooldownPlantList.set(getSelectedPlant(), cooldownPlantList.get(getSelectedPlant()));
                                                    } else {
                                                        AudioManager.buzzer();
                                                    }
                                                } else {
                                                    AudioManager.buzzer();
                                                }
                                            } else {
                                                if(plant.plant(LaneX, LaneY, getSelectedPlant())){
                                                    AudioManager.plant();
                                                    plantSelected();
                                                    timerForCooldown(cooldownPlantList.get(getSelectedPlant()), getSelectedPlant());
                                                    realCooldownPlantList.set(getSelectedPlant(), cooldownPlantList.get(getSelectedPlant()));
                                                } else {
                                                    AudioManager.buzzer();
                                                }
                                            }
                                            selectPlant(-1);
                                            break IterateMapSlot;
                                        }
                                    }
                                }
                                if (LaneX == 9) {
                                    selectPlant(-1);
                                }
                            }
                            
                        }
                    } else isClickingSun = false;

                    if (isUsingShovel()) {
                        IterateMapSlot: for (int LaneX = 0; LaneX < 9; LaneX++) {
                            for (int LaneY = 0; LaneY < 6; LaneY++) {
                                if (fieldClickArea[LaneX][LaneY].contains(e.getPoint())) {
                                    if (Plants.getIsSlotFilled(LaneX, LaneY) != false) {
                                        Plants.emptySlot(LaneX, LaneY);
                                        IterateGamePlants: for(Plants<Integer> plant: plants){
                                            if(plant.getLaneX() == LaneX && plant.getLaneY() == LaneY && !plant.getPlantID().equals(5)){
                                                plant.stop();
                                                AudioManager.remove();
                                                plants.remove(plant);
                                                break IterateGamePlants;
                                            }
                                        }
                                        if (LaneY != 2 && LaneY != 3) {
                                            for (Zombie zombie: zombies){
                                                if(zombie.getLaneX() == LaneX && zombie.getLaneY() == LaneY){
                                                    zombie.stopAttackingPlant();
                                                }
                                            }
                                        }
                                    } else if (LaneY == 2 || LaneY == 3) {
                                        if (Plants.getIsLilypadSlotFilled(LaneX, LaneY) != false) {
                                            IterateGamePlants: for(Plants<Integer> plant: plants){
                                                if(plant.getLaneX() == LaneX && plant.getLaneY() == LaneY && plant.getPlantID().equals(5)){
                                                    plant.stop();
                                                    AudioManager.remove();
                                                    plants.remove(plant);
                                                    break IterateGamePlants;
                                                }
                                            }
                                            for (Zombie zombie: zombies){
                                                if(zombie.getLaneX() == LaneX && zombie.getLaneY() == LaneY){
                                                    zombie.stopAttackingPlant();
                                                }
                                            }
                                        }
                                    }
                                    break IterateMapSlot;
                                }
                            }
                        }
                        setShovel(false);

                    } else if(shovel2D.contains(e.getPoint())) {
                        setShovel(true);
                        AudioManager.shovel();
                    }

                    if (saveAndExitButton.contains(e.getPoint())) {
                        try {
                            int SaveGame = JOptionPane.showConfirmDialog(Main.frame, "Do You Want to Save The Current Game and Exit?", "Save Confirmation", JOptionPane.YES_NO_OPTION);
                            if(JOptionPane.YES_OPTION == SaveGame){
                                Save.saveGame();
                                System.exit(0);
                                System.out.println("Save Berhasil");
                            }
                        } catch (Exception e1) {
                            System.out.println("Save Error!");
                        }
                        
                    }
                } 
                /*
                else {
                    if (playAgainButton.contains(e.getPoint())) {
                        isPlaying = true;
                        isWinning = false;
                        endSoundPlaying = true;
			            for(Zombie zombie: zombies){
                            zombie.stopAttackingPlant();
                        }
                        plants.clear();
                        zombies.clear();
                        Zombie.resetGameOver();
			            resetSunCredits();
			            for (int LaneX = 0; LaneX < 9; LaneX++) {
                            for (int LaneY = 0; LaneY < 6; LaneY++) {
                                Plants.emptySlot(LaneX, LaneY);
                            }
                        }
                        
                        AudioManager.begin();
                        Sun.startTimer();
                        Zombie.startSpawning();   
                    }
                }
                 */
            }
        }
    }

    private void init() {
        endScreen = new Rectangle(0, 0, 1024, 626);
        for (int plantCatalogID = 0; plantCatalogID < 6; plantCatalogID++) {
            plantCatalogClickArea[plantCatalogID] = new Rectangle(25, 156 + 74 * plantCatalogID, 132, 68);
        }

        shovel2D = new Ellipse2D.Float(171, 548, 70, 70);

        int[] fieldLaneWidth = {0,83,162,250,328,412,493,576,656,737};
        int[] fieldLaneHeight = {0,82,180,270,360,445,525};
        for(int LaneX = 0; LaneX < 9; LaneX++){
            for(int LaneY = 0; LaneY < 6; LaneY++) {
                fieldClickArea[LaneX][LaneY] = new Rectangle(245 + fieldLaneWidth[LaneX], 50 + fieldLaneHeight[LaneY], fieldLaneWidth[LaneX+1] - fieldLaneWidth[LaneX], fieldLaneHeight[LaneY+1] - fieldLaneHeight[LaneY]);
                Plants.emptySlot(LaneX, LaneY);
                Plants.setMapSlot(LaneX, LaneY);
            }
        }
    }

    public static void setWave(int w){
        zombieWave = w;
    }

    public static void setZombieInMapCount(int w){
        zombieInMap = w;
    }

    public static int getSecondsTime() {
        return secondsTime;
    }

    public int getSunCredits() {
        return sunCredits;
    }

    public void addSunCredits() {
        sunCredits += 25;
    }

    public void resetSunCredits() {
        sunCredits = 25;
    }

    public void spendSunCredits(int spendValue) {
        sunCredits -= spendValue;
    }

    public int getSelectedPlant() {
        return selectedPlant;
    }

    public void selectPlant(int PlantID) {
        selectedPlant = PlantID;
    }

    public boolean isUsingShovel() {
        return isUsingShovel;
    }

    public void setShovel(boolean isUsingShovel) {
        this.isUsingShovel = isUsingShovel;
    }

    public void plantSelected() {
        spendSunCredits(PlantCost[selectedPlant]);
    }

    public void emptyPlantSelection() {
        selectedPlant = -1;
    }

    public static int getZombieInMapCount() {
        return zombieInMap;
    }

    public static int getWave() {
        return zombieWave;
    }

    public boolean getIsNight() {
        return isNight;
    }

    public static void addZombieInMapCount() {
        zombieInMap++;
    }

    public static void reduceZombieInMapCount() {
        zombieInMap--;
    }


    public Timer getSecondsTimer(){
        return secondsTimer;
    }

    public Timer getGameTimer(){
        return gameTimer;
    }

    public void setSunCredits(int sun){
        sunCredits = sun;
    }

    public void setSecondsTimer(Timer second){
        secondsTimer = second;
    }

    public void setGameTimer(Timer second){
        gameTimer = second;
    }

    public void setIsNight(boolean condition){
        isNight = condition;
    }

    public void setPlantDeckSelection(boolean condition){
        plantDeckSelection = true;
    }

    public int[] getPlantDeck(){
        return PlantDeck;
    }
    
    public void setPlantDeck(int[] deck){
        PlantDeck = deck;
    }

    private void checkContinueGame(){
        if(Main.previousGameFile.exists()){
            do{
                isContinueGameSaved = JOptionPane.showConfirmDialog(Main.frame, "Do You Want To Continue The Last Saved Game?", "Confirm Continue Last Game Dialog", JOptionPane.YES_NO_OPTION);
                try {
                    if(JOptionPane.YES_OPTION == isContinueGameSaved){
                        Game.isUsingPreviousGame = true;
                        Load.loadGame();
                        System.out.println("Starting a Previous Game !");
                    }else if(JOptionPane.NO_OPTION == isContinueGameSaved){
                        System.out.println("Starting a New Game");
                    }  
                } catch (Exception e){
                    e.printStackTrace();
                }
            }while((isContinueGameSaved != JOptionPane.YES_OPTION) && (isContinueGameSaved != JOptionPane.NO_OPTION));
        }
    }

    private static void timerForCooldown(double seconds, int PlantID){
        Timer coolDown = new Timer((int) (seconds*1000), new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                realCooldownPlantList.set(PlantID, 0.0);
            }
        });
        coolDown.setRepeats(false);
        coolDown.start();
    }
}
