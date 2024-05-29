package object;

import entity.Entity;
import gamePackage.GamePanel;

public class OBJ_burger extends Entity{
	
	public OBJ_burger(GamePanel gp) {
		
		super(gp);
		
		name = "Burger";
		down1 = setup("/object/burger", gp.tileSize, gp.tileSize);
	}
}
