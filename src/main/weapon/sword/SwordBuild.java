package main.weapon.sword;

import java.awt.image.BufferedImage;

import main.Sword;

public class SwordBuild extends Sword {
	public SwordBuild(int amnt, BufferedImage read) {
		super(amnt, read);

	}

	public SwordBuild(int amnt, BufferedImage read, String name, int price) {
		super(amnt, read);
		setPrice(price);
		setName(name);

	}

	@Override
	public void attackAnimation() {
	}
}
