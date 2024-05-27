package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import gamePackage.GamePanel;
import gamePackage.ToolBox;

// on n'instancie jamais cette classe

public class Entity  {

	GamePanel gp;
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction = "down";

    public int spriteCounter = 0;
    public int spriteNum = 1;
    
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    
    public boolean invicible = false;
    public int invicibleCounter = 0;
    
    String dialogues[] = new String[20];
    int dialogueIndex = 0;
    
	public BufferedImage image, image2, image3;
	public String name;
	public boolean collision = false;
    
	public int type; // 0 = player; 1 = npc; 2 = mob; pr le moment seulement le 2 est utile.
	
    public int maxLife;
    public int life;
    
    public Entity(GamePanel gp) {
    	this.gp = gp;
    }
    
    public void setAction(){}
    public void speak() {
    	if(dialogues[dialogueIndex] == null) {
			dialogueIndex = 0;
		}
		gp.ui.currentDIalogue = dialogues[dialogueIndex];
		dialogueIndex++;
		
		switch(gp.player.direction) {
		case"up":
			direction = "down";
			break;
		case"down":
			direction = "up";
			break;
		case"right":
			direction ="left";
			break;
		case"left":
			direction = "right";
			break;
		}
    }
    public void update(){
    	setAction();
    	collisionOn = false;
    	gp.checker.checkTile(this);
    	gp.checker.checkObject(this, false);
    	gp.checker.checkEntity(this, gp.npc);
    	gp.checker.checkEntity(this, gp.mob);
    	boolean contactPlayer = gp.checker.checkPlayer(this);
    	
    	if(this.type == 2 && contactPlayer == true) {
    		if(gp.player.invicible == false) {
        		gp.player.life -= 1;
        		gp.player.invicible = true;
    		}

    	}
    	
    	if(collisionOn == false) {
    		switch(direction) {
    		case "up": worldY -= speed; break;
    		case "down": worldY += speed; break;
    		case "left": worldX -= speed; break;
    		case "right": worldX += speed; break;
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
    
    public void draw(Graphics2D g2) {
    	BufferedImage image = null;
    	int screenX = worldX - gp.player.worldX + gp.player.screenX; 
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		
		if(worldX > gp.player.worldX - gp.player.screenX - gp.tileSize && worldX < gp.player.worldX + gp.player.screenX + gp.tileSize 
		&& worldY > gp.player.worldY - gp.player.screenY - gp.tileSize && worldY < gp.player.worldY + gp.player.screenY + gp.tileSize) {
			
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
    
    public BufferedImage setup(String imagePath) {
    	ToolBox uTool = new ToolBox();
    	BufferedImage image = null;
    	try {
    		image = ImageIO.read(getClass().getResourceAsStream(imagePath +".png"));
    		image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	return image;
    }
}
