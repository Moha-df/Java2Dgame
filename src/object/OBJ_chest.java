package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import gamePackage.GamePanel;

public class OBJ_chest extends SuperObject{
	
	GamePanel gp;
	
	public OBJ_chest(GamePanel gp) {
		
		this.gp = gp;
		
		name = "Chest";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/object/chest.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
