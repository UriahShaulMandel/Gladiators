package main.weapon.bow;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.Weapon;
import main.gladiators.Player;

public abstract class Bow extends Weapon {

	protected ArrayList<Arrow> arrows = new ArrayList<Arrow>();

	public ArrayList<Arrow> getArrows() {
		return arrows;
	}

	public void setArrows(ArrayList<Arrow> arrows) {
		this.arrows = arrows;
	}

	public Bow(double attackBonus, BufferedImage src, Arrow arrow, int Ammo) {
		super(attackBonus, src);
		isbow = true;
		for (int i = 0; i < Ammo; i++)
			arrows.add(arrow);

		// TODO make arrow's as arraylist

		// TODO Auto-generated constructor stub
	}

	public Bow(double attackBonus, BufferedImage src, ArrayList<Arrow> arrow) {
		super(attackBonus, src);
		this.arrows = arrow;
		isbow = true;

		// TODO make arrow's as arraylist

		// TODO Auto-generated constructor stub
	}

	@Override
	public void attackAnimation() {
		// TODO Auto-generated method stub

	}

}
