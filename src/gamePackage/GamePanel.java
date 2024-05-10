package gamePackage;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable {
    
	private static final long serialVersionUID = 1L;
	
	// Parametres de lecran, taille des tuiles de lecran etc
    final int originalTileSize = 16; // ca veux dire il y a 16x16 tiles pour les assets
    final int scale = 3; // pour que le jeu soit plus visible
    public final int tileSize = originalTileSize * scale; // donc il y a 48x48 tiles apres le scale
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12; // ratio de 4 par 3
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow; // soit il y a 768x576 pixels pour le jeu
    
    //Taille du monde
    public final int maxWorldCol = 50;
    public final int maxWorldRow =50;

    // Set FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    public CollisionCheck checker = new CollisionCheck(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    
    //Sound
    Sound music = new Sound();
    Sound se = new Sound();
    
    //Player et object
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10]; 
    public Entity npc[] = new Entity[10];
    
    // game state
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    
    

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    
    public void setupGame(){
    	aSetter.setObject();
    	aSetter.setNPC();
    	//playMusic(0);
    	gameState = titleState;
    }
    
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS; // l'interval au cours du quel on rafraichit le jeu
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        

        while(gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
                timer++;
                drawCount++;
            }
            if(timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                timer = 0;
                drawCount = 0;
            }
        }
            
    }
    public void update() {
    	if(gameState == playState) {
    		player.update();
    		for(int i = 0; i < npc.length; i++) {
    			if(npc[i] != null) {
    				npc[i].update();
    			}
    		}
    	}
    	if(gameState == pauseState) {
    		//
    	}
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        //debug
        long drawStart = 0;
        if(keyH.checkDrawTime == true) {
        	drawStart = System.nanoTime();
        }
        
        //menu screen
        if(gameState == titleState) {
        	ui.draw(g2);
        }
        else {
        	//Tile
            tileM.draw(g2, tileM.mapTileNum);
            //tileM.draw(g2, tileM.mapTileNum2);

            
            //Object
            for(int i = 0; i < obj.length; i++) {
            	if(obj[i] != null) {
            		obj[i].draw(g2, this);
            	}
            }
            
            //NPC
            for(int i = 0; i < npc.length; i++) {
            	if(npc[i] != null) {
            		npc[i].draw(g2);
            	}
            }
            
            //Player
            player.draw(g2);
            

            //UI
            ui.draw(g2);
        }
        
        
        
        if(keyH.checkDrawTime == true) {
        	long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw time: " + passed, 10, 400);
            System.out.println("Draw time: " + passed);
        }
        

        g2.dispose();
    }
    
    public void playMusic(int n) {
    	music.setFile(n);
    	music.play();
    	music.loop();
    	music.setVolume(-30.0f);
    }
    public void stopMusic() {
    	music.stop();
    }
    public void playSE(int n) {
    	se.setFile(n);
    	se.play();
    	se.setVolume(-20.0f);
    }
}
