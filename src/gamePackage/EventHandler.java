package gamePackage;

public class EventHandler {
	GamePanel gp;
	EventRect eventRect[][][];
	
	int previousEventX, previousEventY;
	boolean canTouchEvent = true;
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		
		int map = 0;
		int col = 0;
		int row = 0;
		while(map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
			eventRect[map][col][row] = new EventRect();
			eventRect[map][col][row].x = 23; 
			eventRect[map][col][row].y = 23;
			eventRect[map][col][row].width =2;
			eventRect[map][col][row].height = 2;
			eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
			eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
			
			col++;
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
				
				if(row == gp.maxWorldRow) {
					row = 0;
					map++;
				}
			}
		}
		
		
	}
	
	public void chechEvent() {
		int xDistance = Math.abs(gp.player.worldX - previousEventX); // math.abs pour avoir la distance absolue jamais de negatif
		int yDistance = Math.abs(gp.player.worldY - previousEventY);
		int distance = Math.max(xDistance, yDistance);
		if(distance > gp.tileSize) {
			canTouchEvent = true;
		}
		if(canTouchEvent == true) {
			/*
			if(hit(0, 23, 19, "any") == true) { damagePit(gp.dialogueState);}
			if(hit(0, 23,12,"up") == true) { healingPool(gp.dialogueState);}
			*/
			for(int i =0; i<9; i++) {
				if(hit(i,13,6, "any") == true) { teleport(i+1, 13, 17); }
				if(hit(i+1, 13, 18, "any") == true) { teleport(i, 13,7); }
			}
			if(hit(0, 13, 12, "any") == true) { damagePit(gp.dialogueState);}
		}
	}
	
	public void teleport(int map, int col, int row) {
		gp.numMap = map;
		
		gp.player.worldX = gp.tileSize*col;
		gp.player.worldY = gp.tileSize*row;
		previousEventX = gp.player.worldX;
		previousEventY = gp.player.worldY;
		canTouchEvent = false;
		gp.playSE(8);
	}
	
	public boolean hit(int map, int col, int row, String reqDirection) {
		boolean hit = false;
		
		if(map == gp.numMap) {
			gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
			gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
			eventRect[map][col][row].x = col*gp.tileSize + eventRect[map][col][row].x;
			eventRect[map][col][row].y = row*gp.tileSize + eventRect[map][col][row].y;
			
			if(gp.player.solidArea.intersects(eventRect[map][col][row])) {
				if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
					hit = true;
					
					previousEventX = gp.player.worldX;
					previousEventY = gp.player.worldY;
					
				}
			}
			gp.player.solidArea.x = gp.player.solidAreaDefaultX;
			gp.player.solidArea.y = gp.player.solidAreaDefaultY;
			eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
			eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
		}
		
		
		
		return hit;
	}
	public void damagePit(int gameState) {
		gp.gameState = gameState;
		gp.ui.currentDIalogue = "you fall into a litle pit";
		gp.player.life -= 1;
		canTouchEvent = false;
	}
	
	public void healingPool(int col, int row, int gameState) {
		if(gp.keyH.enterPressed == true) {
			gp.gameState = gameState;
			gp.ui.currentDIalogue = "you dring the water. \nYour life hqs been recovered.";
			gp.player.life = gp.player.maxLife;
		}
	}
	
}
