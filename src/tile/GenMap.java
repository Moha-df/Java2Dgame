package tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import gamePackage.GamePanel;

public class GenMap {
	GamePanel gp;
    private static final int WIDTH = 29;
    private static final int HEIGHT = 25;
    private static final int GRASS = 1;
    private static final int TREE = 16;
    private static final int ROCK = 42;
    private static final int PATH = GRASS;

    public int[][] map;

    public GenMap(String fileName) throws IOException {
        map = new int[WIDTH][HEIGHT];
        loadMap(fileName);
        modifyGrassTiles();
        ensurePath();
    }

    // Load the map from a file
    private void loadMap(String fileName) throws IOException {
        try (InputStream is = getClass().getResourceAsStream(fileName);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null && row < HEIGHT) {
                String[] tiles = line.trim().split("\\s+");
                for (int col = 0; col < WIDTH && col < tiles.length; col++) {
                    map[col][row] = Integer.parseInt(tiles[col]);
                }
                row++;
            }
        }
    }


    private void modifyGrassTiles() {
        Random random = new Random();
        for (int col = 0; col < WIDTH; col++) {
            for (int row = 0; row < HEIGHT; row++) {
                if (map[col][row] == GRASS && (row != HEIGHT - 2 || col != WIDTH / 2 - 1)) {
                    if (random.nextInt(100) < 30) { 
                        map[col][row] = TREE;
                    }else if (random.nextInt(100) < 15) { 
               		 map[col][row] = ROCK;
                    }
                    
                }
            }
        }
    }

    private void ensurePath() {
        int middleCol = WIDTH /2 - 1;
        int middleRow = WIDTH /2 - 2;
        for (int row = HEIGHT - 2; row >= 0; row--) {
            map[middleCol][row] = PATH;
        }
        for (int col = 9; col <= 18; col++) { // deuxieme boucle pour la ligne ou la clée et les monstres apparaitrons
            map[col][middleRow] = PATH;		  // de cette maniere on genere aleatoirement la map
        }									   // mais les monstres spawneron sur une seul ligne, et le joueur aura une sortie au 
        										// moins sur une colonne
        for (int col = 9; col <= 18; col++) {
        	map[col][middleRow+1] = PATH;
        }
    }

    
    

    //For debugging print the map to console
    public void printMap() {
        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                System.out.print(String.format("%02d ", map[col][row]));
            }
            System.out.println();
        }
    }

}
