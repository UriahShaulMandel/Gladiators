package main;

import static main.GamePanel.arrows;
import static main.GamePanel.helmet;
import static main.GamePanel.shirt;
import static main.GamePanel.weapon;

import java.io.IOException;

import main.weapon.bow.ArrowFactory;
import main.weapon.bow.BowBuild;
import main.weapon.sword.SwordBuild;

public class StoreConstructor {

	public static void ConstructStore() {

		try {

			shirt[0] = new ShirtBuild(40, javax.imageio.ImageIO.read(new java.io.File("Armor/shirt1.png")), "Pinkness",
					1000);
			shirt[1] = new ShirtBuild(60, javax.imageio.ImageIO.read(new java.io.File("Armor/shirt2.png")), "Yellow",
					1500);
			shirt[2] = new ShirtBuild(75, javax.imageio.ImageIO.read(new java.io.File("Armor/shirt3.png")), "pur",
					2000);
			shirt[3] = new ShirtBuild(80, javax.imageio.ImageIO.read(new java.io.File("Armor/shirt4.png")), "blackar",
					2500);
			shirt[4] = new ShirtBuild(90, javax.imageio.ImageIO.read(new java.io.File("Armor/shirt5.png")), "Blu",
					3000);

			helmet[0] = new HelmetBuild(20, javax.imageio.ImageIO.read(new java.io.File("Armor/helmet1.png")), "ShyshY",
					300);
			helmet[1] = new HelmetBuild(30, javax.imageio.ImageIO.read(new java.io.File("Armor/helmet2.png")), "Fruit",
					800);
			helmet[2] = new HelmetBuild(40, javax.imageio.ImageIO.read(new java.io.File("Armor/helmet3.png")), "ple",
					1800);
			helmet[3] = new HelmetBuild(50, javax.imageio.ImageIO.read(new java.io.File("Armor/helmet4.png")), "elazy",
					3000);
			helmet[4] = new HelmetBuild(60, javax.imageio.ImageIO.read(new java.io.File("Armor/helmet5.png")), "Red",
					3800);

			weapon[0] = new SwordBuild(13, javax.imageio.ImageIO.read(new java.io.File("Swords/sword1.png")), "AFQ",
					700);
			weapon[1] = new SwordBuild(15, javax.imageio.ImageIO.read(new java.io.File("Swords/sword2.png")), "Name A",
					1100);
			weapon[2] = new SwordBuild(19, javax.imageio.ImageIO.read(new java.io.File("Swords/sword3.png")), "YOODA",
					1700);
			weapon[3] = new BowBuild(10, javax.imageio.ImageIO.read(new java.io.File("Swords/bow.png")),
					new ArrowFactory(), 10, "abandon", 1000);
			weapon[4] = new SwordBuild(25, javax.imageio.ImageIO.read(new java.io.File("Swords/sword4.png")), "R_B",
					2500);

			arrows[0] = new ArrowFactory(5, javax.imageio.ImageIO.read(new java.io.File("Swords/rock.png")), "Rock", 2);
			arrows[1] = new ArrowFactory(12, javax.imageio.ImageIO.read(new java.io.File("Swords/orange.png")),
					"Orange", 5);
			arrows[2] = new ArrowFactory(25, javax.imageio.ImageIO.read(new java.io.File("Swords/PURPLE.png")),
					"PURPLE!", 15);
			arrows[3] = new ArrowFactory(45, javax.imageio.ImageIO.read(new java.io.File("Swords/arrow.png")), "blAck",
					45);
			arrows[4] = new ArrowFactory(80, javax.imageio.ImageIO.read(new java.io.File("Swords/AVIDAN.png")),
					"AVIDAN", 80);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
