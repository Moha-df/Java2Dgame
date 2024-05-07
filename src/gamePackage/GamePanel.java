package gamePackage;

import javax.swing.JPanel;

import entity.Player;
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
    
    public final int maxWorldCol = 50;
    public final int maxWorldRow =50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // Set FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this, keyH);
    
    

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
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
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);
        
        player.draw(g2);

        g2.dispose();
    }
}
