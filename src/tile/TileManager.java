package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import gamePackage.GamePanel;
import gamePackage.ToolBox;

public class TileManager {
	
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][][];
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[50];
		mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
		
		// Je garde 2 map non aleatoire pour quelles soit cool au debut
		// par ce que mon aleatoire nest pas trop trop travailler par manque de temps
		loadMap("/maps/map01.txt", 0);
		loadMap("/maps/map02.txt", 1);
		
		try { // map generer aleatoirement 
			GenMap RandomMap1 = new GenMap("/maps/mapSample.txt");
			GenMap RandomMap2 = new GenMap("/maps/mapSample.txt");
			GenMap RandomMap3 = new GenMap("/maps/mapSample.txt");
			GenMap RandomMap4 = new GenMap("/maps/mapSample.txt");
			GenMap RandomMap5 = new GenMap("/maps/mapSample.txt");
			GenMap RandomMap6 = new GenMap("/maps/mapSample.txt");
			GenMap RandomMap7 = new GenMap("/maps/mapSample.txt");
			GenMap RandomMap8 = new GenMap("/maps/mapSample.txt");
			
			mapTileNum[2] = RandomMap1.map;
			mapTileNum[3] = RandomMap2.map;
			mapTileNum[4] = RandomMap3.map;
			mapTileNum[5] = RandomMap4.map;
			mapTileNum[6] = RandomMap5.map;
			mapTileNum[7] = RandomMap6.map;
			mapTileNum[8] = RandomMap7.map;
			mapTileNum[9] = RandomMap8.map;
			
			// pour le moment par ce que bah c le dernier niveau alors je bloque
			mapTileNum[9][13][7] = 32;
			mapTileNum[9][14][7] = 32;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void getTileImage() {
		for(int i =0; i<10; i++) {
			setup(i, "00"+i , false);
		}
		for(int i=10; i<43; i++) {
			setup(i, "0"+i , false);
		}
		tile[16].collision = true;
		for(int i=18; i<33; i++) {
			tile[i].collision = true;
		}
		tile[35].collision = true;
		tile[42].collision = true;
	}
	
	public void setup(int index, String imageName, boolean collision) {
		ToolBox uTool = new ToolBox();
		try { 
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+ imageName +".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String filePath, int map) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine();
				while(col < gp.maxWorldCol) {
					String number[] = line.split(" "); // split comme vu au S3 pour separer chaque nombre de la map des quil y a espace
					int num = Integer.parseInt(number[col]); // et la on transfer les "0" en int 0
					mapTileNum[map][col][row] = num;
					col++;
				}
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
			
		}catch(Exception e){

		}
	}
	
	public void draw(Graphics2D g2) {	
		int worldCol = 0;
		int worldRow = 0;
		
		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[gp.numMap][worldCol][worldRow]; // En gros pour explique comment je dessine les tuiles dans le background
														  // faut se dire quon disossie la position de la camera a la position du monde
														  // et la camera se deplace au dessus du monde avec le joueur tout le temps afficher au millieu
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX; // pas trop compris pourquoi je dois rajouter la positions du joueur sur lecran
			int screenY = worldY - gp.player.worldY + gp.player.screenY; // mais si je le fais pas cest decaller donc il faut
			
			// on entoure la fonction qui dessins les tuiles de if pour quen realiter on ne dessins pas toute les tuiles de lecran meme si la boucle les parcours toutes
			if(worldX > gp.player.worldX - gp.player.screenX - gp.tileSize && worldX < gp.player.worldX + gp.player.screenX + gp.tileSize 
			&& worldY > gp.player.worldY - gp.player.screenY - gp.tileSize && worldY < gp.player.worldY + gp.player.screenY + gp.tileSize) {
				g2.drawImage(tile[tileNum].image, screenX, screenY, null); // dis de maniere plus simple cest jsute la map qui se deplace enfaite
			}
			 
			worldCol++;

			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
	}
}
