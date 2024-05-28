package gamePackage;

import entity.NPC_OldMen;
import monster.Mob_GreenSlime;
import monster.Mob_RedSlime;
import object.OBJ_door;
import object.OBJ_key;

public class AssetSetter {
	GamePanel gp;
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
		
		gp.obj[1][1] = new OBJ_door(gp);
		gp.obj[1][1].worldX = gp.tileSize*13;
		gp.obj[1][1].worldY = gp.tileSize*6;
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
		
		gp.mob[1][1] = new Mob_RedSlime(gp);
		gp.mob[1][1].worldX = gp.tileSize*12;
		gp.mob[1][1].worldY = gp.tileSize*10;
	}
}
