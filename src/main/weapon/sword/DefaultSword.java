package main.weapon.sword;

import java.awt.image.BufferedImage;

import main.Sword;

public class DefaultSword extends Sword {
	public static BufferedImage Dfile;
	static {
		try {
			Dfile = javax.imageio.ImageIO.read(new java.io.File("Swords/DSword.png"));
		} catch (java.io.IOException e) {
		}

	}

	public DefaultSword() {
		super(10, Dfile);

	}

	@Override
	public void attackAnimation() {

	}
}
