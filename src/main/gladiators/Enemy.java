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

import main.external_libraries.heinemanns_json.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import main.GamePanel;
import main.Gladiator;
import main.Helmet;
import main.Shirt;
import main.Weapon;
import main.weapon.bow.Bow;

public class Enemy extends Gladiator {
	public String fimgsrc = "";
	public int findex = 0;
	public int fhealth = 0;
	public int fstamina = 0;
	public int fattackdamage = 0;
	public Weapon fweapon;
	public Helmet fhelmet;
	public Shirt fshirt;
	public String fimagesrc;

	// JSONParser parser = new JSONParser();
	String file;

	public static BufferedImage SRC;

	/**
	 * 
	 * @param index
	 * @param source
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("resource")
	public Enemy(int index, String source) throws FileNotFoundException {
		super(new JSONArr(new Scanner(new File(source)).useDelimiter("\\A").next()).getObj(index));
	}

	@Override
	public boolean attack() {
		// Attack player : GamePanel.player.setHealth(GamePanel.player.getHealth() -
		// amount);
		// GamePanel.enemy[GamePanel.currentEnemy].getWeapon().setSrc(javax.imageio.ImageIO.read(new
		// java.io.File("DSwordAttack.png")));
		if (this.getWeapon().isIsbow()) {
			sop(((Bow) (this.getWeapon())).getArrows());
			if (((Bow) (this.getWeapon())).getArrows().size() <= 0) {
				GamePanel.messege = "No more Arrows!";
				return false;
			}
			this.setStamina(this.getStamina() - 10);// TODO stamina cool
			if (this.getWeapon().isIsbow())
				((Bow) this.getWeapon()).getArrows().get(0).canGameProceedAfterArrow = false;
			sop("enemy bowedly attacked");

			((Bow) (this.getWeapon())).getArrows().get(0).setXspeed(-50);
			((Bow) (this.getWeapon())).getArrows().get(0).setX((int) ((int) this.getX()));

			// ((Bow)(this.getWeapon())).getArrows().remove(0);
			return true;
		}

		sop("FirstEnemyAttacked");
		// sleep(500);

		enemy[ce].setStamina(enemy[ce].getStamina() - 10);// TODO stamina cool

		if (intersect((int) enemy[ce].getX(), (int) enemy[ce].getWidth(),
				(int) enemy[ce].getWeapon().getSrc().getHeight(), (int) player.getX(),
				(int) player.getX() + (int) player.getWidth() / fps, false)) {
			if (mazal(ATTACK, (int) (player.getShirt().getArmorPoints() + player.getHelmet().getArmorPoints()))) {

				sop("enemyOverRidelyHittedThePlayer!");
				player.setHealth(
						player.getHealth() - (enemy[ce].getAttackDamage() + enemy[ce].getWeapon().getAttackBonus()));
				GamePanel.messege = "Enemy Hit!";
			} else {
				GamePanel.messege = "Player Blocked!";
			}
		}

		else {
			GamePanel.messege = "Enemy Missed!";

		}

		// DefaultSword.setDfile(javax.imageio.ImageIO.read(new
		// java.io.File("DSwordAttack.png")));

		this.weapon.setRadSpeed(30);

		return true;
	}

	public boolean power() {
		sop("FirstEnemyPowerlyAttacked");
		// sleep(500);

		enemy[ce].setStamina(enemy[ce].getStamina() - 20);// TODO stamina cool

		if (intersect((int) enemy[ce].getX(), (int) enemy[ce].getWidth(),
				(int) enemy[ce].getWeapon().getSrc().getHeight(), (int) player.getX(),
				(int) player.getX() + (int) player.getWidth() / fps, false)) {
			if (mazal(POWER, (int) (player.getShirt().getArmorPoints() + player.getHelmet().getArmorPoints()))) {

				sop("enemyOverRidelyPowerlyHittedThePlayer!");
				player.setHealth(player.getHealth()
						- 3 * (enemy[ce].getAttackDamage() + enemy[ce].getWeapon().getAttackBonus()));
				GamePanel.messege = "Enemy Powerly Hit!";
				player.setXspeed(player.getXspeed() - 170);
			} else {
				GamePanel.messege = "Player Blocked!";
			}
		}

		else {
			GamePanel.messege = "Enemy Missed!";

		}

		// DefaultSword.setDfile(javax.imageio.ImageIO.read(new
		// java.io.File("DSwordAttack.png")));

		this.weapon.setRadSpeed(30);
		return true;
	}

	@Override
	public void Arrowhit() {
		sop("arrow hit the player");
		if (mazal(ARROW, (int) (player.getShirt().getArmorPoints() + player.getHelmet().getArmorPoints()))) {

			sop("EnemyOverRidelyBow&ArrowHittedThePlayer !");
			player.setHealth(
					player.getHealth() - (0.5 * (enemy[ce].getAttackDamage() + enemy[ce].getWeapon().getAttackBonus()
							+ ((Bow) (enemy[ce].getWeapon())).getArrows().get(0).getAttackBonus())));
			GamePanel.messege = "Enemy Hit!";

			// TODO make each one's spped diffrent
			// DefaultSword.setDfile(javax.imageio.ImageIO.read(new
			// java.io.File("DSwordAttack.png")));

		} else {
			GamePanel.messege = "Player Blocked!";
		}

	}

	@Override
	public boolean push() {
		sop("EnemyPushed");
		// sleep(500);

		enemy[ce].setStamina(enemy[ce].getStamina() - 17);// TODO stamina cool

		if (intersect((int) enemy[ce].getX(), (int) enemy[ce].getWidth(),
				(int) enemy[ce].getWeapon().getSrc().getHeight(), (int) player.getX(),
				(int) player.getX() + (int) player.getWidth() / fps, false)) {

			sop("enemyOverRidelyPushedThePlayer!");
			player.setXspeed(-200);
			GamePanel.messege = "Enemy Pushed!";

		}

		else {
			GamePanel.messege = "Enemy Missed!";

		}

		// DefaultSword.setDfile(javax.imageio.ImageIO.read(new
		// java.io.File("DSwordAttack.png")));

		this.weapon.setRadSpeed(40);
		return true;
	}

}
