package entity;


import java.util.Random;

import gamePackage.GamePanel;


public class NPC_OldMen extends Entity{
	public NPC_OldMen(GamePanel gp) {
		super(gp);
		direction = "down";
		speed = 1;
		solidArea.x = 4; // pas sur de la taille de la box collision mais bon
		solidArea.y = 10;
		solidArea.width = 40;
		solidArea.height = 38;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
		setDialogue();
	}
	
	public void getImage(){
        up1 = setup("/npc/oldman_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/oldman_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/oldman_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/oldman_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/oldman_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/oldman_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/oldman_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/oldman_right_2", gp.tileSize, gp.tileSize);
    }
	
	public void setDialogue() {
		dialogues[0] = "Bonjour! Dialoguez avec moi\nsi vous voulez en savoir + \nsur le jeu";
		dialogues[1] = "Les niveaux 1 et 2 \nne sont pas generer aleatoirement";
		dialogues[2] = "Des bonus apparaissent aleatoirement \nparfois ils peuvent ne pas etre accesible";
		dialogues[3] = "Faites attentions au slime rouge\nils sont particulierement coriace";
		dialogues[4] = "Les potions rouges améliore votre attaque\nmais il va falloir etre chanceux\npour en trouver";
		dialogues[5] = "C'est bizarre que je parle francais\nalors que tout le jeux est en anglais\nnon ?";
		dialogues[6] = "C'est juste par ce que j'avais\nla flemme de traduire tout ça";
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
