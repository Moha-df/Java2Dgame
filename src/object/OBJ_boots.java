package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import gamePackage.GamePanel;

public class OBJ_boots extends SuperObject{
	
	GamePanel gp;
	
	public OBJ_boots(GamePanel gp) {
		
		this.gp = gp;
		
		name = "Boots";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/object/boots.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
