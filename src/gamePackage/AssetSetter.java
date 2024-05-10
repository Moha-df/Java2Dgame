package gamePackage;

import entity.NPC_OldMen;
import object.OBJ_boots;
import object.OBJ_chest;
import object.OBJ_door;
import object.OBJ_key;

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
}
