package object;

import entity.Entity;
import gamePackage.GamePanel;

public class OBJ_chest extends Entity{
	
	
	public OBJ_chest(GamePanel gp) {
		
		super(gp);
		name = "Chest";
		
		down1 = setup("/objects/chest");

	}
}
