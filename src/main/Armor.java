package main;

import java.awt.image.BufferedImage;

public abstract class Armor {
	protected double armorPoints;
	protected BufferedImage src;
	protected int price;
	protected String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Armor(double armp, BufferedImage src) {
		armorPoints = armp;
		this.src = src;
	}

	public BufferedImage getSrc() {
		return src;
	}

	public void setSrc(BufferedImage src) {
		this.src = src;
	}

	public double getArmorPoints() {
		return armorPoints;
	}

	public void setArmorPoints(double armorPoints) {
		this.armorPoints = armorPoints;
	}

}
