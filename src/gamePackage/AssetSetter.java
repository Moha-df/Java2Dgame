package gamePackage;

import entity.NPC_OldMen;
import monster.Mob_GreenSlime;

public class AssetSetter {
	GamePanel gp;
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	public void setObject() {
		
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
	}
}
