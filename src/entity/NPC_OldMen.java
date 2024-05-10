package entity;


import java.util.Random;

import gamePackage.GamePanel;


public class NPC_OldMen extends Entity{
	public NPC_OldMen(GamePanel gp) {
		super(gp);
		direction = "down";
		speed = 1;
		
		getImage();
		setDialogue();
	}
	
	public void getImage(){
        up1 = setup("/npc/oldman_up_1");
        up2 = setup("/npc/oldman_up_2");
        down1 = setup("/npc/oldman_down_1");
        down2 = setup("/npc/oldman_down_2");
        left1 = setup("/npc/oldman_left_1");
        left2 = setup("/npc/oldman_left_2");
        right1 = setup("/npc/oldman_right_1");
        right2 = setup("/npc/oldman_right_2");
    }
	
	public void setDialogue() {
		dialogues[0] = "Hello, son of the mage";
		dialogues[1] = "Your destiny is to collect all \nthe magic power";
		dialogues[2] = "For that you need to defeat \nall the demon";
		dialogues[3] = "But they will not give them \nto you alive !!";
	}
	
	public void setAction() {
		
		actionLockCounter ++;
		
		if(actionLockCounter == 120) {
			Random random = new Random();
			int i = random.nextInt(100)+1; // random de 1 a 100
			
			if(i<=25) {
				direction = "up";
			}
			if(i>25 && i<= 50) {
				direction = "down";
			}
			if(i>50 && i<=75) {
				direction = "left";
			}
			if(i>75 && i<=100) {
				direction = "right";
			}
			
			actionLockCounter = 0;
		}
	}
	
	public void speak() {
		super.speak();
	}
    
    
}
