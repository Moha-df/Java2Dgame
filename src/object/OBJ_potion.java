package object;

import entity.Entity;
import gamePackage.GamePanel;

public class OBJ_potion extends Entity{
	
	public OBJ_potion(GamePanel gp) {
		
		super(gp);
		
		name = "Potion";
		down1 = setup("/object/potion", gp.tileSize, gp.tileSize);
	}
}