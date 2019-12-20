package main.weapon.knife;

import java.awt.image.BufferedImage;

import main.GamePanel;
import main.Weapon;
import main.gladiators.Player;

public abstract class Knife extends Weapon {
	protected int x = -1000;
	protected int xspeed = 0;
	protected int rad;

	public boolean canGameProceedAfterKnife = true;

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

	public Knife(double attackBonus, BufferedImage src) {
		super(attackBonus, src);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attackAnimation() {
		// TODO Auto-generated method stub

	}

}
