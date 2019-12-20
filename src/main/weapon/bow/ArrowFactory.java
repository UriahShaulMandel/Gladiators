package main.weapon.bow;

import java.awt.image.BufferedImage;

import main.GamePanel;

public class ArrowFactory extends Arrow {
	public static BufferedImage Dfile;
	static {
		try {
			Dfile = javax.imageio.ImageIO.read(new java.io.File("Swords/arrow.png"));
		} catch (java.io.IOException e) {
		}

	}

	public ArrowFactory(double attackBonus, BufferedImage src) {
		super(attackBonus, src);
		// TODO Auto-generated constructor stub
	}

	public ArrowFactory(double attackBonus, BufferedImage src, String name, int Price) {
		super(attackBonus, src);
		this.name = name;
		this.price = Price;
		// TODO Auto-generated constructor stub
	}

	public ArrowFactory() {
		super(10, Dfile);
		// TODO Auto-generated constructor stub
	}

}
