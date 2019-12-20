package main.weapon.bow;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.GamePanel;

public class BowBuild extends Bow {
	public static BufferedImage Dfile;
	static {

		try {
			Dfile = javax.imageio.ImageIO.read(new java.io.File("Swords/bow.png"));
		} catch (java.io.IOException e) {
		}

	}

	public BowBuild(double attackBonus, BufferedImage src, Arrow arrow, int ammo) {
		super(attackBonus, src, arrow, ammo);

	}

	public BowBuild(double attackBonus, BufferedImage src, ArrayList<Arrow> arrows) {
		super(attackBonus, src, arrows);

	}

	// TODO make arrow's as arraylist
	public BowBuild() {
		super(10, Dfile, new ArrowFactory(), 10);

	}

	public BowBuild(int armrpntsm, BufferedImage read, Arrow ar, int ammo, String name, int price) {
		super(armrpntsm, read, ar, ammo);
		setPrice(price);
		setName(name);
	}

	public BowBuild(int armrpntsm, BufferedImage read, ArrayList<Arrow> ar, String name, int price) {
		super(armrpntsm, read, ar);
		setPrice(price);
		setName(name);
	}

}
