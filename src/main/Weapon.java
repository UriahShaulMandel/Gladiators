package main;

import java.awt.image.BufferedImage;

public abstract class Weapon extends Object {
	protected double attackBonus;
	protected BufferedImage src;
	protected int price;
	protected int rad = 0;
	protected int radSpeed = 0;
	protected String name;
	protected boolean isbow = false;

	public int getPrice() {
		return price;
	}

	public boolean isIsbow() {
		return isbow;
	}

	public void setIsbow(boolean isbow) {
		this.isbow = isbow;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Weapon(double attackBonus, BufferedImage src) {
		this.attackBonus = attackBonus;
		this.src = src;
	}

	public double getAttackBonus() {
		return attackBonus;
	}

	public void setAttackBonus(double attackBonus) {
		this.attackBonus = attackBonus;
	}

	public BufferedImage getSrc() {
		return src;
	}

	public void setSrc(BufferedImage src) {
		this.src = src;
	}

	public int getRad() {
		return rad;
	}

	public void setRad(int rad) {
		this.rad = rad;
	}

	public int getRadSpeed() {
		return radSpeed;
	}

	public void setRadSpeed(int radSpeed) {
		this.radSpeed = radSpeed;
	}

	public abstract void attackAnimation();
}
