package gamePackage;

import entity.NPC_OldMen;
import monster.Mob_GreenSlime;
import monster.Mob_RedSlime;
import object.OBJ_skull;

public class AssetSetter {
	GamePanel gp;
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	public void setObject() {
		gp.obj[0] = new OBJ_skull(gp);
	}
	
	public void setNPC() {
		gp.npc[0] = new NPC_OldMen(gp);
		gp.npc[0].worldX = gp.tileSize*21;
		gp.npc[0].worldY = gp.tileSize*21;
	}
	
	public void setMob() {

		gp.mob[0] = new Mob_GreenSlime(gp);
		gp.mob[0].worldX = gp.tileSize*23;
		gp.mob[0].worldY = gp.tileSize*36;
		
		gp.mob[1] = new Mob_GreenSlime(gp);
		gp.mob[1].worldX = gp.tileSize*25;
		gp.mob[1].worldY = gp.tileSize*37;
		
		gp.mob[2] = new Mob_RedSlime(gp);
		gp.mob[2].worldX = gp.tileSize*35;
		gp.mob[2].worldY = gp.tileSize*36;
		
		gp.mob[3] = new Mob_RedSlime(gp);
		gp.mob[3].worldX = gp.tileSize*38;
		gp.mob[3].worldY = gp.tileSize*37;
		
		gp.mob[4] = new Mob_RedSlime(gp);
		gp.mob[4].worldX = gp.tileSize*40;
		gp.mob[4].worldY = gp.tileSize*10;
		
		
	}
}
