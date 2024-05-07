package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import gamePackage.GamePanel;
import gamePackage.KeyHandler;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        
        screenX = gp.screenWidth/2 - (gp.tileSize/2); // on doit diviser enlever gp.tileSize/2 sinon ca commence a dessiner une case trop bas
        screenY = gp.screenHeight/2 - (gp.tileSize/2);// car la coordonner quon va donne cest pas le centre de limage mais en haut a gauche
        
        //pour colision
        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        //initialize
        setDefaultValues();
        getPlayerImage();
    }
    


    public void setDefaultValues(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
    	
    	// normalement on check les collisions apres etre passer dans le if dans haut puis ensuite on appel quune fois la fonctions
        // qui check les collisions mais ca cancel les diagonal et le jeu est plus agreable avec
        // alors je prefere regarder a chaque mouvement si on peut bouger mais au moins on peut en faire deux en meme temps
    	
    	if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
    		if(keyH.upPressed == true) {
                direction = "up";
                collisionOn = false;
                int objIndex = gp.checker.checkObject(this, true);
                pickUpObject(objIndex);
                gp.checker.checkTile(this);
                if(collisionOn == false) {
                	worldY -= speed;
                }
            }
            if(keyH.downPressed == true) {
                direction = "down";
                collisionOn = false;
                int objIndex = gp.checker.checkObject(this, true);
                pickUpObject(objIndex);
                gp.checker.checkTile(this);
                if(collisionOn == false) {
                	worldY += speed;
                }
            }
            if(keyH.leftPressed == true) {
                direction = "left";     
                collisionOn = false;
                int objIndex = gp.checker.checkObject(this, true);
                pickUpObject(objIndex);
                gp.checker.checkTile(this);
                if(collisionOn == false) {
                	worldX -= speed;
                }
            }
            if(keyH.rightPressed == true) {
                direction = "right";
                collisionOn = false;
                int objIndex = gp.checker.checkObject(this, true);
                pickUpObject(objIndex);
                gp.checker.checkTile(this);
                if(collisionOn == false) {
                	worldX += speed;
                }
            }
            
            
            

            spriteCounter++;
            if(spriteCounter > 10){
                if(spriteNum == 1){
                    spriteNum = 2;
                }else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
    	}
    }
    
    public void pickUpObject(int in) {
    	if(in != 999) {
    		String objectName = gp.obj[in].name;
    		switch(objectName) {
    		case "Key":
    			gp.playSE(1);
    			hasKey++;
    			gp.obj[in] = null;
    			gp.ui.showMessage("You got a key!");
    			break;
    		case "Door":
    			if(hasKey>0) {
    				gp.playSE(3);
    				gp.obj[in] = null;
    				hasKey--;
    				gp.ui.showMessage("You opened the dooor!");
    			}
    			else {
    				gp.ui.showMessage("You need a key!");
    			}
    			break;
    		case "Boots":
    			gp.playSE(2);
    			speed += 2;
    			gp.obj[in] = null;
    			gp.ui.showMessage("Speed up!");
    			break;
    		case "Chest":
    			gp.ui.gameFinished = true;
    			gp.stopMusic();
    			gp.playSE(4);
    			gp.obj[in] = null;
    			break;
    		}
    	}
    }
    
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        switch(direction){
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
