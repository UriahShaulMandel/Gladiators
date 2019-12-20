package main;

import java.awt.image.BufferedImage;

public abstract class Sword extends Weapon {
	public Sword(double attackBonus, BufferedImage src) {
		super(attackBonus, src);
	}

}
