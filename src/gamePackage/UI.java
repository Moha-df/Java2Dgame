package gamePackage;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import object.OBJ_heart;
import object.SuperObject;

import object.OBJ_key;

public class UI {
	GamePanel gp;
	Graphics2D g2;
	Font maruMonica;
	BufferedImage heart_full, heart_half, heart_blank;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished;
	public String currentDIalogue = "";
	public int commandNum = 0;
	
	double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	public UI(GamePanel gp) {
		this.gp = gp;
		try {
			InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
			maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		SuperObject heart = new OBJ_heart(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;
	}
	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) { //10 9.45
		
		this.g2 = g2;
		g2.setFont(maruMonica);
		//g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setColor(Color.white);
		
		//Main menu
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		
		if(gp.gameState == gp.playState) {
			drawPlayerLife();
		}
		if(gp.gameState == gp.pauseState) {
			drawPlayerLife();
			drawPauseScreen();
		}
		if(gp.gameState == gp.dialogueState) {
			drawPlayerLife();
			drawDialogueScreen();
		}
	}
	
	public void drawPlayerLife() {
		int x = gp.tileSize/2;
		int y = gp.tileSize/2;
		int i = 0;
		/* Si on veut afficher aussi les coeur vide
		while(i < gp.player.maxLife/2) {
			g2.drawImage(heart_blank, x, y, null);
			i++;
			x += gp.tileSize;
		}
		*/
		x = gp.tileSize/2;
		y = gp.tileSize/2;
		i = 0;
		while(i < gp.player.life) {
			g2.drawImage(heart_half, x, y, null);
			i++;
			if(i < gp.player.life) {
				g2.drawImage(heart_full, x, y, null);
			}
			i++;
			x += gp.tileSize;
		}
		
	}
	
	public void drawTitleScreen() {
		g2.setColor(new Color(221, 195, 208));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
		String text = "Son of the mage";
		int x = getCenteredText(text);
		int y = gp.tileSize*3;
		g2.setColor(Color.black);
		g2.drawString(text, x+3, y+3);
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		x = gp.screenWidth/2 - gp.tileSize;
		y += gp.tileSize;
		g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);
		
		text = "NEW GAME";
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
		x = getCenteredText(text);
		y += gp.tileSize*4;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x-gp.tileSize, y);
		}
		
		text = "LOAD GAME";
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
		x = getCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString(">", x-gp.tileSize, y);
		}
		
		text = "QUIT";
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
		x = getCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum == 2) {
			g2.drawString(">", x-gp.tileSize, y);
		}
	}
	
	public void drawPauseScreen() {
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
		String text = "PAUSED";
		int x = getCenteredText(text);
		int y = gp.screenHeight/2;

		g2.drawString(text, x, y);
	}
	
	public void drawDialogueScreen() {
		int x = gp.tileSize*2;
		int y = gp.screenHeight - gp.tileSize*5;
		int width = gp.screenWidth - (gp.tileSize*4);
		int height = gp.tileSize*4;
		drawSubWindow(x, y , width, height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 35F));
		x += gp.tileSize;
		y += gp.tileSize;
		for(String line : currentDIalogue.split("\n")) {
			g2.drawString(line, x, y);
			y+=gp.tileSize;
		}
		
	}
	
	public void drawSubWindow(int x, int y, int width, int height) {
		Color c = new Color(0,0,0, 200); 
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255, 255, 255, 200);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
		
		
	}
	
	public int getCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		return gp.screenWidth/2 - length/2;
	}
}
