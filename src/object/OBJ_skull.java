package object;

import entity.Entity;
import gamePackage.GamePanel;

public class OBJ_skull extends Entity{
	
	public OBJ_skull(GamePanel gp) {
		super(gp);
		
		name = "Key";
		down1 = setup("/object/skull", gp.tileSize, gp.tileSize);
	}
}
