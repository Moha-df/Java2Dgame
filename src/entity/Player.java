package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;



import gamePackage.GamePanel;
import gamePackage.KeyHandler;


public class Player extends Entity {


    KeyHandler keyH;
    public final int screenX;
    public final int screenY;

    

    public Player(GamePanel gp, KeyHandler keyH){
    	
    	super(gp);

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
        speed = 2;
        direction = "down";
        maxLife = 6;
        life = 6;
    }

    public void getPlayerImage(){
        up1 = setup("/player/boy_up_1");
        up2 = setup("/player/boy_up_2");
        down1 = setup("/player/boy_down_1");
        down2 = setup("/player/boy_down_2");
        left1 = setup("/player/boy_left_1");
        left2 = setup("/player/boy_left_2");
        right1 = setup("/player/boy_right_1");
        right2 = setup("/player/boy_right_2");
    }
    
    
    public void update(){
    	// normalement on check les collisions apres etre passer dans le if dans haut puis ensuite on appel quune fois la fonctions
        // qui check les collisions mais ca cancel les diagonal et le jeu est plus agreable avec
        // alors je prefere regarder a chaque mouvement si on peut bouger mais au moins on peut en faire deux en meme temps
    	
    	if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {
    		if(keyH.upPressed == true) {
                direction = "up";
                checkAllCollision();
                if(collisionOn == false) {
                	worldY -= speed;
                }
            }
    		if(keyH.downPressed == true) {
                direction = "down";
                checkAllCollision();
                if(collisionOn == false) {
                	worldY += speed;
                }
            }
            if(keyH.leftPressed == true) {
                direction = "left";     
                checkAllCollision();
                if(collisionOn == false) {
                	worldX -= speed;
                }
            }
            if(keyH.rightPressed == true) {
                direction = "right";
                checkAllCollision();
                if(collisionOn == false) {
                	worldX += speed;
                }
            }
         
            
            if(keyH.enterPressed == true) {
                checkAllCollision();
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
    public void checkAllCollision() {
        collisionOn = false;
        int objIndex = gp.checker.checkObject(this, true);
        pickUpObject(objIndex);
        int npcIndex = gp.checker.checkEntity(this, gp.npc);
        interactNPC(npcIndex);
        gp.checker.checkTile(this);
    }
    
    public void pickUpObject(int in) {
    	if(in != 999) {
    		
    	}
    }
    
    public void interactNPC(int index) {
    	if(index != 999) {
    		if(gp.keyH.enterPressed) {
    			gp.gameState = gp.dialogueState;
        		gp.npc[index].speak();
    		}
    	}
    	gp.keyH.enterPressed = false;
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
        g2.drawImage(image, screenX, screenY, null);
    }
}
