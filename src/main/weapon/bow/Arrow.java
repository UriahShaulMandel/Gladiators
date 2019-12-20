package main.weapon.bow;

import java.awt.image.BufferedImage;

import main.Weapon;

public abstract class Arrow extends Weapon {
	protected int x = -1000;
	protected int xspeed = 0;
	public boolean canGameProceedAfterArrow = true;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getXspeed() {
		return xspeed;
	}

	public void setXspeed(int xspeed) {
		this.xspeed = xspeed;
	}

	public int getAmmo() {
		return ammo;
	}

	protected final int ammo = 10;

	public Arrow(double attackBonus, BufferedImage src) {
		super(attackBonus, src);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attackAnimation() {
		// TODO Auto-generated method stub

	}

}
