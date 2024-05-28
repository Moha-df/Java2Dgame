package object;

import entity.Entity;
import gamePackage.GamePanel;

public class OBJ_boots extends Entity{
	
	public OBJ_boots(GamePanel gp) {
		
		super(gp);
		
		name = "Boots";
		down1 = setup("/objects/boots", gp.tileSize, gp.tileSize);
	}
}
