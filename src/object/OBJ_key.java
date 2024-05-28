package object;

import entity.Entity;
import gamePackage.GamePanel;

public class OBJ_key extends Entity{
	
	public OBJ_key(GamePanel gp) {
		super(gp);
		
		name = "Key";
		down1 = setup("/object/key", gp.tileSize, gp.tileSize);
	}
}
