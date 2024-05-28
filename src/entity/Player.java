package entity;

import java.awt.AlphaComposite;
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
        
        attackArea.width = 36;
        attackArea.height = 36;
        
        //initialize
        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
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
        up1 = setup("/player/boy_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/player/boy_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/player/boy_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/player/boy_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/player/boy_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/player/boy_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/player/boy_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/player/boy_right_2", gp.tileSize, gp.tileSize);
    }
    public void getPlayerAttackImage() {
    	attackUp1 = setup("/player/attack_up_1", gp.tileSize, gp.tileSize*2);
    	attackUp2 = setup("/player/attack_up_2", gp.tileSize, gp.tileSize*2);
    	attackDown1 = setup("/player/attack_down_1", gp.tileSize, gp.tileSize*2);
    	attackDown2 = setup("/player/attack_down_2", gp.tileSize, gp.tileSize*2);
    	attackLeft1 = setup("/player/attack_left_1", gp.tileSize*2, gp.tileSize);
    	attackLeft2 = setup("/player/attack_left_2", gp.tileSize*2, gp.tileSize);
    	attackRight1 = setup("/player/attack_right_1", gp.tileSize*2, gp.tileSize);
    	attackRight2 = setup("/player/attack_right_2", gp.tileSize*2, gp.tileSize);
    	
    }
    
    
    public void update(){
    	// normalement on check les collisions apres etre passer dans le if dans haut puis ensuite on appel quune fois la fonctions
        // qui check les collisions mais ca cancel les diagonal et le jeu est plus agreable avec
        // alors je prefere regarder a chaque mouvement si on peut bouger mais au moins on peut en faire deux en meme temps
    	
    	if(attacking == true) {
    		attacking();
    	}
    	else if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {
    		if(keyH.upPressed == true) {
                direction = "up";
                checkAllCollision();
                if(collisionOn == false && keyH.enterPressed == false) {
                	worldY -= speed;
                }
            }
    		if(keyH.downPressed == true) {
                direction = "down";
                checkAllCollision();
                if(collisionOn == false && keyH.enterPressed == false) {
                	worldY += speed;
                }
            }
            if(keyH.leftPressed == true) {
                direction = "left";     
                checkAllCollision();
                if(collisionOn == false && keyH.enterPressed == false) {
                	worldX -= speed;
                }
            }
            if(keyH.rightPressed == true) {
                direction = "right";
                checkAllCollision();
                if(collisionOn == false && keyH.enterPressed == false) {
                	worldX += speed;
                }
            }
         
            if(keyH.enterPressed == true) {
                checkAllCollision();
            }
            
            gp.keyH.enterPressed = false;
            
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
    	
    	if(invicible == true) {
    		invicibleCounter++;
    		if(invicibleCounter > 60) {
    			invicible = false;
    			invicibleCounter = 0;
    		}
    	}
    }
    public void attacking() {
    	spriteCounter++;
    	
    	if(spriteCounter <=5) {
    		spriteNum = 1;
    	}
    	if(spriteCounter >5 && spriteCounter <25) {
    		spriteNum = 2;
    		//temp current data
    		int currentWorldX = worldX;
    		int currentWorldY = worldY;
    		int solidAreaWidth = solidArea.width;
    		int solidAreaHeight = solidArea.height;
    		//Temp modify current data
    		switch(direction) {
	    		case"up": worldY -= attackArea.height; break;
	    		case "down": worldY += attackArea.height; break;
	    		case "left": worldX -= attackArea.width; break;
	    		case "right": worldX += attackArea.width; break;
    		}
    		//attackArea deviens solideArea
    		solidArea.width = attackArea.width;
    		solidArea.height = attackArea.height;
    		// Check if monster collide with the attackArea
    		int mobIndex = gp.checker.checkEntity(this, gp.mob);
    		damageMob(mobIndex);
    		
    		worldX = currentWorldX;
    		worldY = currentWorldY;
    		solidArea.width = solidAreaWidth;
    		solidArea.height = solidAreaHeight;
    		
    	}
    	if(spriteCounter > 25) {
    		spriteNum = 1;
    		spriteCounter = 0;
    		attacking = false;
    	}
    }
    
    public void checkAllCollision() {
        collisionOn = false;
        int objIndex = gp.checker.checkObject(this, true);
        pickUpObject(objIndex);
        int npcIndex = gp.checker.checkEntity(this, gp.npc);
        interactNPC(npcIndex);
        gp.checker.checkTile(this);
        int monsterIndex = gp.checker.checkEntity(this, gp.mob);
        contactMonster(monsterIndex);
        gp.eHandler.chechEvent();
        gp.keyH.enterPressed = false;
    }
    
    public void pickUpObject(int in) {
    	if(in != 999) {
    		
    	}
    }
    
    public void interactNPC(int index) {
    	
    	if(gp.keyH.enterPressed == true) {
    		if(index != 999) {
    			gp.gameState = gp.dialogueState;
        		gp.npc[index].speak();
        	}
        	else {
        		gp.playSE(7);
    			attacking = true;
        	}
    	}
    	
    	
    }
    
    public void contactMonster(int index) {
    	if(index != 999) {
    		if(invicible == false) {
    			gp.playSE(6);
    			life -= 1;
    			invicible = true;
    		}
    	}
    }
    
    public void damageMob(int index) {
    	if(index != 999) {
    		if(gp.mob[index].invicible == false) {
    			gp.playSE(5);
    			gp.mob[index].life -= 1;
    			gp.mob[index].invicible = true;
    			gp.mob[index].damageReaction();
    			
    			if(gp.mob[index].life <= 0) {
    				gp.mob[index].dying = true;
    			}
    		}
    		
    	}
    }
    
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;
        
        switch(direction){
            case "up":
            	if(attacking == false) {
            		 if(spriteNum == 1){ image = up1; }
                     if(spriteNum == 2){ image = up2; }
            	}
            	if(attacking == true) {
            		tempScreenY = screenY-gp.tileSize;
            		 if(spriteNum == 1){ image = attackUp1; }
                     if(spriteNum == 2){ image = attackUp2; }
            	}
                break;
            case "down":
            	if(attacking == false) {
           		 	if(spriteNum == 1){ image = down1; }
                    if(spriteNum == 2){ image = down2; }
            	}
            	if(attacking == true) {
           		 	if(spriteNum == 1){ image = attackDown1; }
                    if(spriteNum == 2){ image = attackDown2; }
            	}
                break;
            case "left":
            	if(attacking == false) {
           		 	if(spriteNum == 1){ image = left1; }
                    if(spriteNum == 2){ image = left2; }
            	}
            	if(attacking == true) {
            		tempScreenX = screenX-gp.tileSize;
           		 	if(spriteNum == 1){ image = attackLeft1; }
                    if(spriteNum == 2){ image = attackLeft2; }
            	}
                break;
            case "right":
            	if(attacking == false) {
           		 	if(spriteNum == 1){ image = right1; }
                    if(spriteNum == 2){ image = right2; }
            	}
            	if(attacking == true) {
           		 	if(spriteNum == 1){ image = attackRight1; }
                    if(spriteNum == 2){ image = attackRight2; }
            	}
                break;
        }
        
        if(invicible == true && halfOpacity == false) {
        	halfOpacityCounter++;
        	if(halfOpacityCounter == 10) {
        		halfOpacity = true;
        		halfOpacityCounter = 0;
        	}
        }
        if(invicible == true && halfOpacity == true) {
        	g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        	halfOpacityCounter++;
        	if(halfOpacityCounter == 10) {
        		halfOpacity = false;
        		halfOpacityCounter = 0;
        	}
        }
        
        g2.drawImage(image, tempScreenX, tempScreenY, null);
        
        //reset opacity
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
