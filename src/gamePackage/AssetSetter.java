package gamePackage;

import java.util.Random;

import entity.NPC_OldMen;
import monster.Mob_GreenSlime;
import monster.Mob_RedSlime;
import object.OBJ_boots;
import object.OBJ_burger;
import object.OBJ_door;
import object.OBJ_key;
import object.OBJ_potion;

public class AssetSetter {
	GamePanel gp;
	ToolBox uTool = new ToolBox();
	Random random = new Random();
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	public void setObject() {
		gp.obj[0][0] = new OBJ_key(gp);
		gp.obj[0][0].worldX = gp.tileSize*18;
		gp.obj[0][0].worldY = gp.tileSize*17;
		gp.obj[0][1] = new OBJ_door(gp);
		gp.obj[0][1].worldX = gp.tileSize*13;
		gp.obj[0][1].worldY = gp.tileSize*6;
		
		gp.obj[1][0] = new OBJ_key(gp);
		gp.obj[1][0].worldX = gp.tileSize*13;
		gp.obj[1][0].worldY = gp.tileSize*8;
		gp.obj[1][1] = new OBJ_door(gp);
		gp.obj[1][1].worldX = gp.tileSize*13;
		gp.obj[1][1].worldY = gp.tileSize*6;
		
		gp.obj[1][2] = new OBJ_potion(gp);
		gp.obj[1][2].worldX = gp.tileSize*18;
		gp.obj[1][2].worldY = gp.tileSize*15;
		
		for(int i =2; i < 10; i++) {
			gp.obj[i][0] = new OBJ_key(gp);
			gp.obj[i][0].worldX = gp.tileSize*(random.nextInt(10)+9);
			gp.obj[i][0].worldY = gp.tileSize*(random.nextInt(2)+12);
			gp.obj[i][1] = new OBJ_door(gp);
			gp.obj[i][1].worldX = gp.tileSize*13;
			gp.obj[i][1].worldY = gp.tileSize*6;
		}
		
		
		
	}
	
	public void setNPC() {
		gp.npc[0][0] = new NPC_OldMen(gp);
		gp.npc[0][0].worldX = gp.tileSize*9;
		gp.npc[0][0].worldY = gp.tileSize*8;	
	}
	
	public void setMob() {
		gp.mob[0][0] = new Mob_GreenSlime(gp);
		gp.mob[0][0].worldX = gp.tileSize*16;
		gp.mob[0][0].worldY = gp.tileSize*16;
		
		gp.mob[1][0] = new Mob_RedSlime(gp);
		gp.mob[1][0].worldX = gp.tileSize*11;
		gp.mob[1][0].worldY = gp.tileSize*10;
		
		for(int i =2; i < 5; i++) {
			gp.mob[i][0] = new Mob_GreenSlime(gp);
			gp.mob[i][0].worldX = gp.tileSize*(random.nextInt(5)+9);
			gp.mob[i][0].worldY = gp.tileSize*12;
			
			gp.mob[i][1] = new Mob_RedSlime(gp);
			gp.mob[i][1].worldX = gp.tileSize*(random.nextInt(4)+14);
			gp.mob[i][1].worldY = gp.tileSize*12;
		}
		for(int i =5; i < 10; i++) {
			gp.mob[i][0] = new Mob_RedSlime(gp);
			gp.mob[i][0].worldX = gp.tileSize*(random.nextInt(5)+9);
			gp.mob[i][0].worldY = gp.tileSize*12;
			
			gp.mob[i][1] = new Mob_RedSlime(gp);
			gp.mob[i][1].worldX = gp.tileSize*(random.nextInt(5)+14);
			gp.mob[i][1].worldY = gp.tileSize*12;
		}
	}
	
	
	// Si pour la gen aleatoire jai besoin de cree des cles ou des slimes :
	//
	public void setKey(int numMap, int col, int row) {
		int index = ToolBox.findFirstEmptyIndex(gp.obj[numMap]);
		if(index != -1) {
			gp.obj[numMap][index] = new OBJ_key(gp);
			gp.obj[numMap][index].worldX = gp.tileSize*col;
			gp.obj[numMap][index].worldY = gp.tileSize*row;
		}
	}
	public void setRedSlime(int numMap, int col, int row) {
		int index = ToolBox.findFirstEmptyIndex(gp.mob[numMap]);
		if(index != -1) {
			gp.mob[numMap][index] = new Mob_RedSlime(gp);
			gp.mob[numMap][index].worldX = gp.tileSize*col;
			gp.mob[numMap][index].worldY = gp.tileSize*row;
		}
	}
	public void setSpeedBoots(int numMap, int col, int row) {
		int index = ToolBox.findFirstEmptyIndex(gp.obj[numMap]);
		if(index != -1) {
			gp.obj[numMap][index] = new OBJ_boots(gp);
			gp.obj[numMap][index].worldX = gp.tileSize*col;
			gp.obj[numMap][index].worldY = gp.tileSize*row;
		}
	}
	public void setBurgerBoots(int numMap, int col, int row) {
		int index = ToolBox.findFirstEmptyIndex(gp.obj[numMap]);
		if(index != -1) {
			gp.obj[numMap][index] = new OBJ_burger(gp);
			gp.obj[numMap][index].worldX = gp.tileSize*col;
			gp.obj[numMap][index].worldY = gp.tileSize*row;
		}
	}
	public void setPotionBoots(int numMap, int col, int row) {
		int index = ToolBox.findFirstEmptyIndex(gp.obj[numMap]);
		if(index != -1) {
			gp.obj[numMap][index] = new OBJ_potion(gp);
			gp.obj[numMap][index].worldX = gp.tileSize*col;
			gp.obj[numMap][index].worldY = gp.tileSize*row;
		}
	}
}
