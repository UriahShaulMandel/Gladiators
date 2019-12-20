package main.gladiators;

import static main.Direction.ARROW;
import static main.Direction.ATTACK;
import static main.Direction.POWER;
import static main.GamePanel.ce;
import static main.GamePanel.enemy;
import static main.GamePanel.fps;
import static main.GamePanel.player;
import static main.MyFunctions.intersect;
import static main.MyFunctions.mazal;
import static main.MyFunctions.sop;

import java.awt.image.BufferedImage;
import java.io.IOException;

import main.DefaultHelmet;
import main.DefaultShirt;
import main.GamePanel;
import main.Gladiator;
import main.Weapon;
import main.weapon.bow.ArrowFactory;
import main.weapon.bow.Bow;
import main.weapon.bow.BowBuild;
import main.weapon.sword.DefaultSword;

public class Player extends Gladiator {
	public static Weapon sec;
	public static BufferedImage SRC;
	static {
		try {
			sec = new BowBuild(5, javax.imageio.ImageIO.read(new java.io.File("Swords/rugatca.png")),
					new ArrowFactory(5, javax.imageio.ImageIO.read(new java.io.File("Swords/rock.png"))), 4);// TODO fix
			SRC = javax.imageio.ImageIO.read(new java.io.File("Gladiators/movingPlayer.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Player() {
		super(80, 300, SRC.getWidth(), SRC.getHeight(), 100, 100, 10, new DefaultSword(), sec, new DefaultHelmet(),
				new DefaultShirt(), SRC, false);

	}

	// @Override
	public void Arrowhit() {
		if (mazal(ARROW, (int) (enemy[ce].getShirt().getArmorPoints() + enemy[ce].getHelmet().getArmorPoints()))) {

			sop("playerOverRidelyBow&ArrowHittedTheEnemy !");
			enemy[ce].setHealth(
					enemy[ce].getHealth() - (0.5 * (player.getAttackDamage() + player.getWeapon().getAttackBonus()
							+ ((Bow) (player.getWeapon())).getArrows().get(0).getAttackBonus())));
			GamePanel.messege = "Player Hit!";

			// TODO make each one's spped diffrent
			// DefaultSword.setDfile(javax.imageio.ImageIO.read(new
			// java.io.File("DSwordAttack.png")));

		} else {
			GamePanel.messege = "Enemy Blocked!";
		}
	}

	public boolean attack() {

		if (this.getWeapon().isIsbow()) {
			sop(((Bow) (this.getWeapon())).getArrows());
			if (((Bow) (this.getWeapon())).getArrows().size() <= 0) {
				GamePanel.messege = "No more Arrows!";
				return false;
			}
			((Bow) (this.getWeapon())).getArrows().get(0);
			sop(((Bow) (this.getWeapon())).getArrows().size() + " Arrows left");
			player.setStamina(player.getStamina() - 10);// TODO stamina cool
			if (this.getWeapon().isIsbow())
				((Bow) this.getWeapon()).getArrows().get(0).canGameProceedAfterArrow = false;
			sop("player bowedly attacked");

			((Bow) (this.getWeapon())).getArrows().get(0).setXspeed(50);
			((Bow) (this.getWeapon())).getArrows().get(0).setX((int) ((int) this.getX() + this.getWidth() / fps));

			return true;
		}

		player.setStamina(player.getStamina() - 10);// TODO stamina cool

		System.out.println("PlayerOverrideAttacked");
		if (intersect((int) player.getX() + (int) player.getWidth() / fps, (int) player.getWidth(),
				(int) player.getWeapon().getSrc().getHeight(), (int) enemy[ce].getX(),
				(int) enemy[GamePanel.ce].getX() + (int) enemy[ce].getWidth() / fps, true)) {
			if (mazal(ATTACK, (int) (enemy[ce].getShirt().getArmorPoints() + enemy[ce].getHelmet().getArmorPoints()))) {

				sop("playerOverRidelyHittedTheEnemy!");
				enemy[ce].setHealth(
						enemy[ce].getHealth() - (player.getAttackDamage() + player.getWeapon().getAttackBonus()));
				GamePanel.messege = "Player Hit!";

				// TODO make each one's spped diffrent
				// DefaultSword.setDfile(javax.imageio.ImageIO.read(new
				// java.io.File("DSwordAttack.png")));

			} else {
				GamePanel.messege = "Enemy Blocked!";
			}

		} else
			GamePanel.messege = "Player Missed!";
		player.getWeapon().setRadSpeed(30);
		return true;

	}

	public boolean power() {
		player.setStamina(player.getStamina() - 20);// TODO stamina cool

		System.out.println("PlayerOverridePowerlyAttacked");
		//////
		if (intersect((int) player.getX() + (int) player.getWidth() / fps, (int) player.getWidth(),
				(int) player.getWeapon().getSrc().getHeight(), (int) enemy[ce].getX(),
				(int) enemy[GamePanel.ce].getX() + (int) enemy[ce].getWidth() / fps, true)) {
			if (mazal(POWER, (int) (enemy[ce].getShirt().getArmorPoints() + enemy[ce].getHelmet().getArmorPoints()))) {

				sop("playerOverRidelyPowerlyHittedTheEnemy!");
				enemy[ce].setHealth(
						enemy[ce].getHealth() - 3 * (player.getAttackDamage() + player.getWeapon().getAttackBonus()));
				GamePanel.messege = "Player Powerly Hit!";
				enemy[ce].setXspeed(enemy[ce].getXspeed() + 170);

				// TODO make each one's spped diffrent
				// DefaultSword.setDfile(javax.imageio.ImageIO.read(new
				// java.io.File("DSwordAttack.png")));

			} else {
				GamePanel.messege = "Enemy Blocked!";
			}

		} else
			GamePanel.messege = "Player Missed!";
		player.getWeapon().setRadSpeed(15);
		return true;

	}

	@Override
	public boolean push() {
		player.setStamina(player.getStamina() - 17);// TODO stamina cool

		System.out.println("PlayerOverridePushed");
		//////
		if (intersect((int) player.getX() + (int) player.getWidth() / fps, (int) player.getWidth(),
				(int) player.getWeapon().getSrc().getHeight(), (int) enemy[ce].getX(),
				(int) enemy[GamePanel.ce].getX() + (int) enemy[ce].getWidth() / fps, true)) {

			sop("playerOverRidelyPushedTheEnemy!");
			GamePanel.messege = "Player Pushed!";
			enemy[ce].setXspeed(200);
		} else
			GamePanel.messege = "Player Missed!";
		player.getWeapon().setRadSpeed(45);
		return true;
	}

}
