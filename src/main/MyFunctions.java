package main;

import static main.Direction.ARROW;
import static main.Direction.ATTACK;
import static main.Direction.CHARGE;
import static main.Direction.LEFT;
import static main.Direction.POWER;
import static main.Direction.RIGHT;
import static main.Direction.SLEEP;
import static main.Direction.SWITCH;
import static main.GamePanel.SRC1;
import static main.GamePanel.SRCBar;
import static main.GamePanel.SRCHealthBar;
import static main.GamePanel.SRCStaminaBar;
import static main.GamePanel.SRCreka;
import static main.GamePanel.action;
import static main.GamePanel.arrows;
import static main.GamePanel.ce;
import static main.GamePanel.down;
import static main.GamePanel.enemy;
import static main.GamePanel.fast;
import static main.GamePanel.fps;
import static main.GamePanel.fwss;
import static main.GamePanel.fwssend;
import static main.GamePanel.g2d;
import static main.GamePanel.gotoArena;
import static main.GamePanel.helmet;
import static main.GamePanel.inGame;
import static main.GamePanel.lastclicked;
import static main.GamePanel.messege;
import static main.GamePanel.money;
import static main.GamePanel.player;
import static main.GamePanel.pop;
import static main.GamePanel.shelf;
import static main.GamePanel.shirt;
import static main.GamePanel.sword1;
import static main.GamePanel.weapon;
import static main.GamePanel.zzz;

import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

import main.gladiators.Enemy;
import main.gladiators.Player;
import main.weapon.bow.Bow;

public class MyFunctions {

	public static void actionhandler(Point2D clk) {
		if (detector(clk, (int) player.getX(), (int) player.getY(), (int) action.getWidth() + (int) player.getX(),
				(int) action.getHeight() + (int) player.getY())) {
			sop("IDINAHOY");

			if (money >= shirt[fwss].getPrice()) {
				player.setShirt((Shirt) shirt[fwss]);
				money -= shirt[fwss].getPrice();

			}

		} else if (detector(clk, 900, 135, 803, 296)) {
			sop("second shelf clicked");
			if (money >= shirt[fwss + 1].getPrice()) {
				player.setShirt((Shirt) shirt[fwss + 1]);
				money -= shirt[fwss + 1].getPrice();
			}
		} else if (detector(clk, 700, 310, 796, 472)) {
			sop("third shelf bought");
			if (money >= helmet[fwss].getPrice()) {
				player.setHelmet((Helmet) helmet[fwss]);
				money -= helmet[fwss].getPrice();

			}

		} else if (detector(clk, 804, 310, 899, 472)) {
			sop("fourth shelf bought");
			if (money >= helmet[fwss + 1].getPrice()) {
				player.setHelmet((Helmet) helmet[fwss + 1]);
				money -= helmet[fwss + 1].getPrice();
			}

		} else if (detector(clk, 590, 134, 687, 296)) {
			sop("swordup shelf bought");
			if (money >= weapon[2].getPrice()) {
				player.setWeapon((Sword) weapon[2]);
				money -= weapon[2].getPrice();
			}

		} else if (detector(clk, 590, 310, 687, 472)) {
			sop("sworddown shelf bought");
			if (money >= weapon[1].getPrice()) {
				player.setWeapon((Sword) weapon[1]);
				money -= weapon[1].getPrice();
			}

		} else if (detector(clk, 300, 58, 595, 92)) {

			ce++;
			inGame = true;
			sop("gh");

		}
	}

	/**
	 * 
	 * @param whichattack
	 *            - ATTACK
	 * @param armorPoints
	 *            - maximum is 140, remember!, put both shirt and helmet
	 * @return
	 */
	public static boolean mazal(Direction whichattack, int armorPoints) {
		boolean ret = false;
		int armorbonus = 0;
		sop("which attack: " + whichattack + " armorPoints " + armorPoints);

		if (whichattack == ATTACK) {

			if (armorPoints > 50)
				armorbonus = armorPoints + ((armorPoints - 50) / 2);
			else
				armorbonus = armorPoints;

			ret = ((int) (((Math.random() * 50) - (armorbonus / 10)) / 18) >= 1);
		}

		if (whichattack == POWER) {

			if (armorPoints > 50)
				armorbonus = armorPoints + ((armorPoints - 50) / 2);
			else
				armorbonus = armorPoints;

			ret = ((int) (((Math.random() * 50) - (armorbonus / 10)) / 35) >= 1);
		}
		if (whichattack == ARROW) {

			if (armorPoints > 50)
				armorbonus += ((armorPoints - 50) / 2);
			else
				armorbonus = armorPoints;

			ret = ((int) (((Math.random() * 50) - (armorbonus / 10)) / 11) >= 1);
		}

		return ret;

	}

	/**
	 * 
	 * @param str
	 *            - string for drawing
	 * @param x
	 * @param y
	 * @param i
	 *            - color
	 * @param fontSize
	 */
	public static void textDraw(String str, int x, int y, Color i, int fontSize) {

		g2d.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));

		g2d.setColor(i);

		g2d.drawString(str, x, y);
	}

	public static void reset() {
		sop("Game Resetted");
		player.setX(80);
		enemy[ce].setX(880);
		player.setHealth(player.getDefaultHealth());
		player.setStamina(player.getDefaultStamina());
		enemy[ce].setHealth(enemy[ce].getDefaultHealth());
		enemy[ce].setStamina(enemy[ce].getDefaultStamina());
	}

	public static void console(String str) {
		try {
			str = str.toLowerCase();
			String[] command = str.split(" ");

			switch (command[0]) {
			case "reset":
				reset();
				break;
			case "kill":

				if (command[1].equals("player"))
					sop("player killed");
				// GamePanel.player.setHealth(0);
				if (command[1].equals("enemy"))
					GamePanel.enemy[GamePanel.ce].setHealth(0);
				// else
				// GamePanel.enemy[GamePanel.currentEnemy].setHealth(0);

				break;

			case "addmoney":
				GamePanel.money += Integer.parseInt(command[1]);
				break;
			case "ce":
				GamePanel.ce = Integer.parseInt(command[1]);

				break;
			case "fwss":
				GamePanel.fwss = Integer.parseInt(command[1]);

				break;

			case "game":
				if (command[1] == "stop") {
					inGame = false;
				} else if (command[1] == "start") {
					inGame = true;
				}
				break;

			default:
				sop("Not a command!");
				break;
			}
		} catch (Exception e) {
			System.out.println("U nup");
		}
	}

	public static Direction AI() {
		double dist = enemy[ce].getX() - (player.getX() + player.getWidth() / fps);
		sop("the distance is: " + dist);
		if (enemy[ce].getWeapon().isIsbow())
			if (((Bow) (enemy[ce].getWeapon())).getArrows().size() == 0)
				return SWITCH;

		if (dist > 250 && !enemy[ce].getWeapon().isIsbow() && enemy[ce].getSecondery().isIsbow())
			if (((Bow) (enemy[ce].getSecondery())).getArrows().size() != 0)
				return SWITCH;
		if (dist > 250 && enemy[ce].getWeapon().isIsbow())
			return ATTACK;
		if (dist < 250 && enemy[ce].getWeapon().isIsbow() && !enemy[ce].getSecondery().isIsbow())
			return SWITCH;
		if (enemy[ce].getWeapon().isIsbow()) {

			if (enemy[ce].getHealth() >= enemy[ce].getDefaultHealth() / 2
					|| enemy[ce].getStamina() > enemy[ce].getDefaultStamina() / 2) {
				if (dist < 200 && dist > 100)
					return CHARGE;
				if (dist <= -15)
					return RIGHT;
				if (dist >= 200)
					return ATTACK;
			} else
				return SLEEP;

		}

		if (dist < 200 && dist > 100)
			return CHARGE;
		else if (dist < 100 && dist >= -15 && enemy[ce].getStamina() > enemy[ce].getDefaultStamina() / 2)
			return POWER;
		else if (dist < 100 && dist >= -15)
			return ATTACK;
		else {
			if (enemy[ce].getHealth() >= enemy[ce].getDefaultHealth() / 2
					|| enemy[ce].getStamina() > enemy[ce].getDefaultStamina() / 2) {
				if (dist <= -15)
					return RIGHT;
				if (dist >= 200)
					return LEFT;
			} else
				return SLEEP;
		}

		return null;
	}

	/**
	 * 
	 * @param weaponStartPoint
	 * @param gladWidth
	 * @param weaponHeight
	 * @param otherGladClosePoint
	 * @param otherGladOtherPoint
	 * @param toRight
	 * @return true or false
	 */
	public static boolean intersect(int weaponStartPoint, int gladWidth, int weaponHeight, int otherGladClosePoint,
			int otherGladOtherPoint, boolean toRight)

	{
		int reach;
		if (toRight) {
			reach = (int) weaponStartPoint + weaponHeight;
		} else {
			reach = (int) weaponStartPoint - weaponHeight;
		}

		if ((reach <= otherGladOtherPoint && reach >= otherGladClosePoint)
				|| (reach >= otherGladOtherPoint && reach <= otherGladClosePoint)

		)

		{
			return true;
		}

		return false;
	}

	public static <T> void sop(T v) {
		System.out.println(v);
	}

	public static BufferedImage rotateCw(BufferedImage img) {
		int width = img.getWidth();
		int height = img.getHeight();
		BufferedImage newImage = new BufferedImage(height, width, img.getType());

		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				newImage.setRGB(height - 1 - j, i, img.getRGB(i, j));

		return newImage;
	}

	/**
	 * 
	 * @param p
	 *            - time to sleep
	 */
	public static void sleep(int p) {
		try {
			Thread.sleep(p);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}

	public static void init() {

		pop[1] = "Power attack";
		pop[2] = "Attack";
		pop[3] = "Charge attack";
		pop[4] = "Switch";
		pop[5] = "Push";
		pop[6] = "Sleep";
		StoreConstructor.ConstructStore();
		player = new Player();
		try {
			enemy[0] = new Enemy(0, "C:/Users/uriah/OneDrive/JAVA/Gladiators/enemys.json");
			enemy[1] = new Enemy(1, "C:/Users/uriah/OneDrive/JAVA/Gladiators/enemys.json");
			enemy[2] = new Enemy(2, "C:/Users/uriah/OneDrive/JAVA/Gladiators/enemys.json");
			enemy[2].setSecondery(Gladiator.fweapon[3]);
			enemy[3] = new Enemy(3, "C:/Users/uriah/OneDrive/JAVA/Gladiators/enemys.json");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		try {

			down = javax.imageio.ImageIO.read(new java.io.File("down.png"));
			gotoArena = javax.imageio.ImageIO.read(new java.io.File("gotoarena.png"));
			SRCreka = javax.imageio.ImageIO.read(new java.io.File("reka.bmp"));
			shelf = javax.imageio.ImageIO.read(new java.io.File("shelf.png"));
			SRCHealthBar = javax.imageio.ImageIO.read(new java.io.File("Bars/healthProgressBar.png"));
			SRCStaminaBar = javax.imageio.ImageIO.read(new java.io.File("Bars/staminaProgressBar.png"));
			SRCBar = javax.imageio.ImageIO.read(new java.io.File("Bars/progressBar.png"));
			SRC1 = javax.imageio.ImageIO.read(new java.io.File("rules.png"));
			zzz = javax.imageio.ImageIO.read(new java.io.File("zzz.png"));
			sword1 = javax.imageio.ImageIO.read(new java.io.File("Swords/Sword1.png"));

			fast = javax.imageio.ImageIO.read(new java.io.File("fast.png"));
			action = javax.imageio.ImageIO.read(new java.io.File("action.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void armorshop(Point2D clk) {

		if (detector(clk, (int) player.getX() + 80, (int) (player.getY() + player.getHeight() / 4),
				(int) player.getWidth() / fps * 2 + (int) player.getX(), (int) (player.getY() - 20))) {
			sop("IDINAHOY4");

			messege = "";
			player.Switch();
			GamePanel.whosTurn = WhosTurn.COMPUTER;

		}

		if (detector(clk, 700, 135, 798, 296)) {
			sop("first shelf clicked");

			if (money >= shirt[fwss].getPrice()) {
				player.setShirt(shirt[fwss]);
				money -= shirt[fwss].getPrice();

			}

		} else if (detector(clk, 900, 135, 803, 296)) {
			sop("second shelf clicked");
			if (money >= helmet[fwss].getPrice()) {
				player.setHelmet(helmet[fwss]);
				money -= helmet[fwss].getPrice();
			}
		} else if (detector(clk, 700, 310, 796, 472)) {
			sop("third shelf bought");
			if (money >= shirt[fwss + 1].getPrice()) {
				player.setShirt(shirt[fwss + 1]);
				money -= shirt[fwss + 1].getPrice();

			}

		} else if (detector(clk, 804, 310, 899, 472)) {
			sop("fourth shelf bought");
			if (money >= helmet[fwss + 1].getPrice()) {
				player.setHelmet(helmet[fwss + 1]);
				money -= helmet[fwss + 1].getPrice();
			}

		} else if (detector(clk, 590, 134, 687, 296)) {
			sop("swordup shelf bought");
			if (money >= weapon[fwss].getPrice()) {
				if (weapon[fwss].isIsbow())
					player.setWeapon(weapon[fwss]);
				else
					player.setWeapon(weapon[fwss]);
				money -= weapon[fwss].getPrice();
			}

		} else if (detector(clk, 590, 310, 687, 472)) {
			sop("sworddown shelf bought");
			if (money >= weapon[fwss + 1].getPrice()) {
				if (weapon[fwss + 1].isIsbow())
					player.setWeapon(weapon[fwss + 1]);
				else
					player.setWeapon(weapon[fwss + 1]);
				money -= weapon[fwss + 1].getPrice();
			}

		} else if (detector(clk, 480, 135, 577, 297)) {
			sop("arrowup shelf bought");
			if (money >= arrows[fwss].getPrice()) {
				if (player.getWeapon().isIsbow())
					((Bow) (player.getWeapon())).getArrows().add(arrows[fwss]);
				else if (player.getSecondery().isIsbow())
					((Bow) (player.getSecondery())).getArrows().add(arrows[fwss]);
				money -= arrows[fwss].getPrice();
			}

		} else if (detector(clk, 480, 310, 577, 472)) {
			sop("arrowDown shelf bought");
			if (money >= arrows[fwss + 1].getPrice()) {
				if (player.getWeapon().isIsbow())
					((Bow) (player.getWeapon())).getArrows().add(arrows[fwss + 1]);
				else if (player.getSecondery().isIsbow())
					((Bow) (player.getSecondery())).getArrows().add(arrows[fwss + 1]);
				money -= arrows[fwss + 1].getPrice();
			}

		} else if (detector(clk, 300, 58, 595, 92)) {

			ce++;
			inGame = true;
			sop("current enemy++");

		} else if (detector(clk, 905, 310, 905 + down.getWidth(), 310 + down.getHeight())) {

			if (fwss < fwssend) {
				fwss++;

			}
			sop("fwss++");

		} else if (detector(clk, 905, 310, 905 + down.getWidth(), 310 - down.getHeight())) {

			if (fwss > 0) {
				fwss--;

			}
			sop("fwss--");

		}

	}

	/**
	 * 
	 * @param pnt
	 *            - pnt to check
	 * @param firstpoint
	 *            first pnt in rectangle
	 * @param secondpoint
	 *            second pnt in rectangle
	 * @return
	 */
	public static boolean detector(Point2D pnt, Point2D firstpoint, Point2D secondpoint) {
		double maxx = Math.max(firstpoint.getX(), secondpoint.getX());
		double minx = Math.min(firstpoint.getX(), secondpoint.getX());
		double maxy = Math.max(firstpoint.getY(), secondpoint.getY());
		double miny = Math.min(firstpoint.getY(), secondpoint.getY());
		if (pnt.getX() <= maxx && pnt.getX() >= minx && pnt.getY() <= maxy && pnt.getY() >= miny

		) {
			lastclicked.setLocation(0, 0);
			return true;
		}

		return false;

	}

	/**
	 * 
	 * @param pnt
	 * @param fx
	 * @param fy
	 * @param sx
	 * @param sy
	 * @return
	 */
	public static boolean detector(Point2D pnt, int fx, int fy, int sx, int sy) {
		double maxx = Math.max(fx, sx);
		double minx = Math.min(fx, sx);
		double maxy = Math.max(fy, sy);
		double miny = Math.min(fy, sy);
		if (pnt.getX() <= maxx && pnt.getX() >= minx && pnt.getY() <= maxy && pnt.getY() >= miny

		) {
			return true;
		}

		return false;

	}

}