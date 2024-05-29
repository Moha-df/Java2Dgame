package gamePackage;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class ToolBox {
	public BufferedImage scaleImage(BufferedImage original, int width, int height) {
		BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
		Graphics2D g2 = scaledImage.createGraphics();
		g2.drawImage(original, 0, 0, width, height, null);
		g2.dispose();
		
		return scaledImage;
	}
	public static <T> int findFirstEmptyIndex(T[] array) {
	        for (int i = 0; i < array.length; i++) {
	            if (array[i] == null) {
	                return i;
	            }
	        }
	        return -1; 
	}
	public static <T> int countNonEmptyCells(T[] array) {
        int count = 0;
        for (T item : array) {
            if (item != null) {
                count++;
            }
        }
        return count;
    }
}
