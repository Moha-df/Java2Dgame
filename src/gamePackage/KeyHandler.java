package gamePackage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	
	GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    public boolean checkDrawTime = false;
    
    public KeyHandler(GamePanel gp){
    	this.gp = gp;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();
        
        if(gp.gameState == gp.titleState || gp.gameState == gp.gameOverState) {
        	if(code == KeyEvent.VK_Z) {
        		gp.ui.commandNum--;
        		if(gp.ui.commandNum < 0) {
        			gp.ui.commandNum = 2;
        		}
            }
            if(code == KeyEvent.VK_S) {
            	gp.ui.commandNum++;
            	if(gp.ui.commandNum > 2) {
        			gp.ui.commandNum = 0;
        		}
            }
            if(code == KeyEvent.VK_ENTER) {
           	 	if(gp.ui.commandNum == 0) {
           	 		gp.gameState = gp.playState;
           	 		gp.playMusic(0);
           	 	}
           	 	if(gp.ui.commandNum == 1) {
        	 		gp.gameState = gp.playState;
        	 		gp.playMusic(0);
        	 	}
	           	 if(gp.ui.commandNum == 2) {
	     	 		System.exit(0);
	     	 	}
            }
        }
        
        else if(gp.gameState == gp.playState) {
        	 if(code == KeyEvent.VK_Z) {
                 upPressed = true;
             }
             if(code == KeyEvent.VK_S) {
                 downPressed = true;
             }
             if(code == KeyEvent.VK_Q) {
                 leftPressed = true;
             }
             if(code == KeyEvent.VK_D) {
                 rightPressed = true;
             }
             if(code == KeyEvent.VK_P) {
            	 gp.gameState = gp.pauseState;
             }
             if(code == KeyEvent.VK_ENTER) {
            	 enterPressed = true;
             }
             
             if(code == KeyEvent.VK_T) {
             	if(checkDrawTime == false) {
             		checkDrawTime = true;
             	}else if(checkDrawTime == true) {
             		checkDrawTime = false;
             	}
             	
             }       
        }
        else if(gp.gameState == gp.pauseState) {
        	if(code == KeyEvent.VK_P) {
           	 gp.gameState = gp.playState;
            }
        }
        
        else if(gp.gameState == gp.dialogueState) {
        	if(code == KeyEvent.VK_ENTER) {
        		gp.gameState = gp.playState;
        	}
        }
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        int code = e.getKeyCode();
        
        if(code == KeyEvent.VK_Z) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if(code == KeyEvent.VK_Q) {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    
}
