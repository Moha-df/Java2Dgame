package object;

import entity.Entity;
import gamePackage.GamePanel;

public class OBJ_door extends Entity {
	
	public OBJ_door(GamePanel gp) {
		
		super(gp);
		name = "Door";
		down1 = setup("/object/door");
		
		collision = true;
		solidArea.x = 0;
		solidArea.y = 16;
		solidArea.width = 48;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
}
