package gamePackage;

import entity.Entity;

public class CollisionCheck {
	
	GamePanel gp;
	
	public CollisionCheck(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) {
		// La gauche droite haut et bas du rectangle de collision
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		int tileNum1, tileNum2;// tileNum3, tileNum4;
		
		switch(entity.direction) {
			case "up": // ici on verifie la case au dessus a gauche et au dessus a droite etant donner quon avance en avant
				entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize; // ca fais peur tout ce qui est ecris mais cest simple
				tileNum1 = gp.tileM.mapTileNum[gp.numMap][entityLeftCol][entityTopRow]; 
				tileNum2 = gp.tileM.mapTileNum[gp.numMap][entityRightCol][entityTopRow];
				if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
					entity.collisionOn = true;
				}
				break;
			case "down":
				entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
			    tileNum1 = gp.tileM.mapTileNum[gp.numMap][entityLeftCol][entityBottomRow];
			    tileNum2 = gp.tileM.mapTileNum[gp.numMap][entityRightCol][entityBottomRow];
			    if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
			        entity.collisionOn = true;
			    }

				break;
			case "left":
				entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
			    tileNum1 = gp.tileM.mapTileNum[gp.numMap][entityLeftCol][entityTopRow];
			    tileNum2 = gp.tileM.mapTileNum[gp.numMap][entityLeftCol][entityBottomRow];
			    if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
			        entity.collisionOn = true;
			    }
				break;
			case "right":
				entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
			    tileNum1 = gp.tileM.mapTileNum[gp.numMap][entityRightCol][entityTopRow];
			    tileNum2 = gp.tileM.mapTileNum[gp.numMap][entityRightCol][entityBottomRow];

			    if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
			        entity.collisionOn = true;
			    }
				break;
		
		}
	}
	
	public int checkObject(Entity entity, boolean player){
		
		int index = 999;
		
		for(int i = 0; i< gp.obj[1].length; i++) {
			if(gp.obj[gp.numMap][i] != null) {
				
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
				gp.obj[gp.numMap][i].solidArea.x = gp.obj[gp.numMap][i].worldX + gp.obj[gp.numMap][i].solidArea.x;
				gp.obj[gp.numMap][i].solidArea.y = gp.obj[gp.numMap][i].worldY + gp.obj[gp.numMap][i].solidArea.y;
				
				switch(entity.direction) {
					case "up": entity.solidArea.y -= entity.speed; break;
					case "down": entity.solidArea.y += entity.speed; break;
					case "left": entity.solidArea.x -= entity.speed; break;
					case "right": entity.solidArea.x += entity.speed; break;
					}
				
				// utilisation ici de intersect car nombre dobjet limiter donc peut dimapct sur les performances
				if(entity.solidArea.intersects(gp.obj[gp.numMap][i].solidArea)) {
					if(gp.obj[gp.numMap][i].collision == true) {
						entity.collisionOn = true;
					}
					if(player == true) {
						index = i;
					}
				}
				
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gp.obj[gp.numMap][i].solidArea.x = gp.obj[gp.numMap][i].solidAreaDefaultX;
				gp.obj[gp.numMap][i].solidArea.y = gp.obj[gp.numMap][i].solidAreaDefaultY;
			}
		}
		
		return index;
	}
	//Collision pnj ou monstre
	public int checkEntity(Entity entity, Entity[][] target) {
		int index = 999;
		
		for(int i = 0; i< target[1].length; i++) {
			if(target[gp.numMap][i] != null) {
				
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
				target[gp.numMap][i].solidArea.x = target[gp.numMap][i].worldX + target[gp.numMap][i].solidArea.x;
				target[gp.numMap][i].solidArea.y = target[gp.numMap][i].worldY + target[gp.numMap][i].solidArea.y;
				
				switch(entity.direction) {
					case "up": entity.solidArea.y -= entity.speed; break;
					case "down": entity.solidArea.y += entity.speed; break;
					case "left": entity.solidArea.x -= entity.speed; break;
					case "right": entity.solidArea.x += entity.speed; break;
					}
				// utilisation ici de intersect car nombre dobjet limiter donc peut dimapct sur les performances
				if(entity.solidArea.intersects(target[gp.numMap][i].solidArea)) {
					if(target[gp.numMap][i] != entity) {
						entity.collisionOn = true;
						index = i;
					}
				}
				
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				target[gp.numMap][i].solidArea.x = target[gp.numMap][i].solidAreaDefaultX;
				target[gp.numMap][i].solidArea.y = target[gp.numMap][i].solidAreaDefaultY;
			}
		}
		
		return index;
	}
	
	public boolean checkPlayer(Entity entity) {
		
		boolean contactPlayer = false;
		
		entity.solidArea.x = entity.worldX + entity.solidArea.x;
		entity.solidArea.y = entity.worldY + entity.solidArea.y;
		
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		
		switch(entity.direction) {
			case "up": entity.solidArea.y -= entity.speed; break;
			case "down": entity.solidArea.y += entity.speed; break;
			case "left": entity.solidArea.x -= entity.speed; break;
			case "right": entity.solidArea.x += entity.speed; break;
		}
		// utilisation ici de intersect car nombre dobjet limiter donc peut dimapct sur les performances
		if(entity.solidArea.intersects(gp.player.solidArea)) {
			entity.collisionOn = true;
			contactPlayer = true;
		}
		
		
		entity.solidArea.x = entity.solidAreaDefaultX;
		entity.solidArea.y = entity.solidAreaDefaultY;
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		
		return contactPlayer;
	}
}














