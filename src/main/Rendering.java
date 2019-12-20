package main;

import static main.GamePanel.*;
import static main.MyFunctions.sleep;
import static main.MyFunctions.textDraw;
import static main.WhosTurn.PLAYER;
import static main.weapon.bow.BowBuild.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import main.gladiators.Player;
import main.weapon.bow.Bow;
import main.weapon.bow.BowBuild;

public class Rendering {
	public static void arena() {
		// enemy[ce].setWeapon(new FirstBow());
		if (player.getWeapon().isIsbow())
			if (!((Bow) player.getWeapon()).getArrows().isEmpty())
				((Bow) player.getWeapon()).getArrows().get(0)
						.setX(((Bow) (player.getWeapon())).getArrows().get(0).getX()
								+ ((Bow) player.getWeapon()).getArrows().get(0).getXspeed());

		if (enemy[ce].getWeapon().isIsbow())
			if (!((Bow) enemy[ce].getWeapon()).getArrows().isEmpty())
				((Bow) enemy[ce].getWeapon()).getArrows().get(0)
						.setX(((Bow) (enemy[ce].getWeapon())).getArrows().get(0).getX()
								+ ((Bow) enemy[ce].getWeapon()).getArrows().get(0).getXspeed());

		if (player.getX() <= 0) {
			player.setXspeed(player.getXspeed() + 30);
		}
		if (player.getX() + player.getWidth() / fps >= GameMain.WIDTH) {
			player.setXspeed(player.getXspeed() - 30);
		}
		if (enemy[ce].getX() <= 0) {
			enemy[ce].setXspeed(enemy[ce].getXspeed() + 30);
		}
		if (enemy[ce].getX() + enemy[ce].getWidth() / fps >= GameMain.WIDTH) {
			enemy[ce].setXspeed(enemy[ce].getXspeed() - 30);
		}

		g2d.clearRect(0, 0, img.getWidth(), img.getHeight());
		// setBackground(Color.white);
		g2d.setColor(Color.WHITE);

		// draw reka
		g2d.drawImage(SRCreka, 0, 50, null);

		// draw the rulz
		// if (GamePanel.whosTurn == WhosTurn.PLAYER) g2d.drawImage(SRC1, 350, 50,
		// null);
		// textDraw("Rulz: \r\n\r Press Enter to go forward and attack\n\rPress Space to
		// normally attack\n\rPress the arrows to
		// move\n\r",GameMain.WIDTH/3,200,Color.BLACK,17);

		// sleep(100); //100 is default
		frame = frame + 100;
		if (frame == 500) {
			frame = 0;
		}
		// real = g2d.drawImage(Player.SRC,(int)player.getX(),(int)player.getY(),null);

		player.setX(player.getX() + player.getXspeed());
		player.setXspeed((int) (0.3 * player.getXspeed()));

		enemy[ce].setX(enemy[ce].getX() + enemy[ce].getXspeed());
		enemy[ce].setXspeed((int) (0.3 * enemy[ce].getXspeed()));

		textDraw("Rules", 4, 28, Color.WHITE, 30);

		// drawing player////////////////////////////////////////////
		g2d.drawImage(Player.SRC, (int) player.getX() + 100, (int) player.getY() + 200, (int) player.getX(),
				(int) player.getY(), frame + 100, 200, frame, 0, null);

		// g2d.drawImage(enemy[currentEnemy].getSrc(),(int)enemy[currentEnemy].getX(),(int)enemy[currentEnemy].getY(),null);

		// drawing enemy////////////////////////////////////////
		g2d.drawImage(enemy[ce].getSrc(), (int) enemy[ce].getX(), (int) enemy[ce].getY(), (int) enemy[ce].getX() + 100,
				(int) enemy[ce].getY() + 200, frame + 100, 0, frame, 200, null);

		// zzz
		if (player.isZZZ()) {
			g2d.drawImage(zzz, (int) (player.getX() + 0.3 * player.getWidth() / fps),
					(int) (player.getY() - zzz.getHeight()), null);
		}
		if (enemy[ce].isZZZ()) {
			g2d.drawImage(zzz, (int) (enemy[ce].getX() + 0.3 * enemy[ce].getWidth() / fps),
					(int) (enemy[ce].getY() - zzz.getHeight()), null);
		}
		// zzz
		//////////////////////////////////
		// armor!

		g2d.drawImage(player.getShirt().getSrc(), (int) player.getX() + 22, (int) player.getY() + 45, null);
		g2d.drawImage(enemy[ce].getShirt().getSrc(), (int) enemy[ce].getX() + 22, (int) enemy[ce].getY() + 45, null);

		g2d.drawImage(player.getHelmet().getSrc(), (int) player.getX() + 32, (int) player.getY()

				, null);
		g2d.drawImage // enemy helmet
		(enemy[ce].getHelmet().getSrc(), (int) enemy[ce].getX() + 32, (int) enemy[ce].getY(), null);

		player.getWeapon().setRad(player.getWeapon().getRad() + player.getWeapon().getRadSpeed());
		g2d.translate(+(int) (player.getX() + player.getWidth() / fps),
				+(int) (player.getY() + player.getHeight() / 2));
		g2d.rotate(Math.toRadians(player.getWeapon().getRad()));
		GamePanel.g2d.drawImage(player.getWeapon().getSrc(), -0, // (int)(player.getWeapon().getSrc().getWidth()*0.55),
				-player.getWeapon().getSrc().getHeight(), null);
		g2d.rotate(Math.toRadians(-player.getWeapon().getRad()));
		g2d.translate(-(int) (player.getX() + player.getWidth() / fps),
				-(int) (player.getY() + player.getHeight() / 2));
		if (player.getWeapon().getRad() >= 100) {
			player.getWeapon().setRad(80);
			player.getWeapon().setRadSpeed(-player.getWeapon().getRadSpeed());
		}
		if (player.getWeapon().getRadSpeed() >= 90) {
			player.getWeapon().setRadSpeed(-player.getWeapon().getRadSpeed());
		} else if (player.getWeapon().getRad() < 1) {
			player.getWeapon().setRadSpeed(0);
			player.getWeapon().setRad(0);
		}

		/// enemy!!!
		enemy[ce].getWeapon().setRad(enemy[ce].getWeapon().getRad() + enemy[ce].getWeapon().getRadSpeed());
		g2d.translate((int) ((enemy[ce].getX() + enemy[ce].getWidth() / fps - enemy[ce].getWidth() / fps)),
				(int) (enemy[ce].getY() + enemy[ce].getHeight() / 2));
		g2d.rotate(Math.toRadians(-enemy[ce].getWeapon().getRad()));
		GamePanel.g2d.drawImage(enemy[ce].getWeapon().getSrc(), -player.getWeapon().getSrc().getWidth(),
				-player.getWeapon().getSrc().getHeight(), 0, 0,

				// IsSet!
				player.getWeapon().getSrc().getWidth(), 0, 0, player.getWeapon().getSrc().getHeight(),
				// IsSet!

				// -enemy[currentEnemy].getWeapon().getSrc().getWidth()/fps,
				// - enemy[currentEnemy].getWeapon().getSrc().getHeight(),

				null);
		g2d.rotate(Math.toRadians(enemy[ce].getWeapon().getRad()));
		g2d.translate(-(int) ((enemy[ce].getX() + enemy[ce].getWidth() / fps - enemy[ce].getWidth() / fps)),
				-(int) (enemy[ce].getY() + enemy[ce].getHeight() / 2));
		if (enemy[ce].getWeapon().getRad() >= 100) {
			enemy[ce].getWeapon().setRad(80);
			enemy[ce].getWeapon().setRadSpeed(-enemy[ce].getWeapon().getRadSpeed());
		}
		if (enemy[ce].getWeapon().getRad() >= 90) {
			enemy[ce].getWeapon().setRadSpeed(-enemy[ce].getWeapon().getRadSpeed());
		} else if (enemy[ce].getWeapon().getRad() <= 1) {
			enemy[ce].getWeapon().setRadSpeed(0);
			enemy[ce].getWeapon().setRad(0);
		}

		// Bars: ENEMY!!!
		// arrow bar:
		if (enemy[ce].getWeapon().isIsbow())
			textDraw(
					"Arrow's left in Main Weapon: "
							+ String.valueOf(((Bow) (enemy[ce].getWeapon())).getArrows().size()),
					700, 160, Color.BLACK, 18);
		if (enemy[ce].getSecondery().isIsbow())
			textDraw(
					"Arrow's left in Secondery Weapon: "
							+ String.valueOf(((Bow) (enemy[ce].getSecondery())).getArrows().size()),
					700, 140, Color.BLACK, 18);

		// stamina bar

		Staminaamount = (int) (enemy[ce].getStamina() * (180.0 / enemy[ce].getDefaultStamina())); // TODO make stamina
																									// lol
		g2d.drawImage(SRCBar, 800, 70, null);
		g2d.drawImage(SRCStaminaBar, 800, 70, 800 + Staminaamount, 90, 0, 0, Staminaamount, 20, null);

		textDraw(String.valueOf((int) enemy[ce].getStamina()), 800 - 35, (int) (70 + 17), Color.GREEN, 20);

		// health bar
		Healthamount = (int) (enemy[ce].getHealth() * (180.0 / enemy[ce].getDefaultHealth()));
		g2d.drawImage(SRCBar, 800, 100, null);
		g2d.drawImage(SRCHealthBar, 800, 100, 800 + Healthamount, 120, 0, 0, Healthamount, 20, null);
		// Bars
		textDraw(String.valueOf((int) enemy[ce].getHealth()), 800 - 35, (int) (100 + 17), Color.RED, 20);

		// Bars: PLAYER!!!
		if (player.getWeapon().isIsbow())
			textDraw("Arrow's left in Main Weapon: " + String.valueOf(((Bow) (player.getWeapon())).getArrows().size()),
					20, 160, Color.BLACK, 18);
		if (player.getSecondery().isIsbow())
			textDraw(
					"Arrow's left in Secondery Weapon: "
							+ String.valueOf(((Bow) (player.getSecondery())).getArrows().size()),
					20, 140, Color.BLACK, 18);

		// stamina bar

		Staminaamount = (int) (player.getStamina() * (180.0 / player.getDefaultStamina())); // TODO make stamina lol
		g2d.drawImage(SRCBar, 20, 70, null);
		g2d.drawImage(SRCStaminaBar, 20, 70, 20 + Staminaamount, 90, 0, 0, Staminaamount, 20, null);
		textDraw(String.valueOf((int) player.getStamina()), 20 + 180, (int) (70 + 17), Color.GREEN, 20);

		// health bar
		Healthamount = (int) (player.getHealth() * (180.0 / player.getDefaultHealth()));
		g2d.drawImage(SRCBar, 20, 100, null);
		g2d.drawImage(SRCHealthBar, 20, 100, 20 + Healthamount, 120, 0, 0, Healthamount, 20, null);
		textDraw(String.valueOf((int) player.getHealth()), 20 + 180, (int) (100 + 17), Color.RED, 20);
		// Bars

		if (whosTurn == PLAYER) {
			textDraw(pop[1], (int) player.getX() - 80, (int) player.getY() + 20, Color.BLACK, 18);
			textDraw(pop[2], (int) player.getX() - 60, (int) ((player.getY() + 20) + 0.4 * player.getHeight()),
					Color.BLACK, 18);
			textDraw(pop[3], (int) player.getX() - 80, (int) ((player.getY() + 20) + 0.8 * player.getHeight()),
					Color.BLACK, 18);

			// player.getx+80 - player.getx+200
			// player.gety+30 - player.gety
			textDraw(pop[4], (int) player.getX() + 100, (int) player.getY() + 20, Color.BLACK, 18);
			textDraw(pop[5], (int) player.getX() + 140, (int) ((player.getY() + 20) + 0.4 * player.getHeight()),
					Color.BLACK, 18);
			textDraw(pop[6], (int) player.getX() + 120, (int) ((player.getY() + 20) + 0.8 * player.getHeight()),
					Color.BLACK, 18);

		}
		// arrow

		if (player.getWeapon().isIsbow())
			if (!((Bow) player.getWeapon()).getArrows().isEmpty()) {
				g2d.drawImage(((Bow) player.getWeapon()).getArrows().get(0).getSrc(),
						((Bow) (player.getWeapon())).getArrows().get(0).getX(),
						(int) (player.getY() + player.getHeight() / 4), null);

				if (((Bow) player.getWeapon()).getArrows().get(0).getX() >= enemy[ce].getX()
						&& !((Bow) player.getWeapon()).getArrows().get(0).canGameProceedAfterArrow) {
					((Bow) player.getWeapon()).getArrows().get(0).canGameProceedAfterArrow = true;
					player.Arrowhit();
					((Bow) (player.getWeapon())).getArrows().remove(0);
				}
			}

		if (enemy[ce].getWeapon().isIsbow())
			if (!((Bow) enemy[ce].getWeapon()).getArrows().isEmpty()) {
				g2d.drawImage(((Bow) enemy[ce].getWeapon()).getArrows().get(0).getSrc(),
						((Bow) (enemy[ce].getWeapon())).getArrows().get(0).getX(),
						(int) (enemy[ce].getY() + enemy[ce].getHeight() / 4),
						((Bow) (enemy[ce].getWeapon())).getArrows().get(0).getX()
								+ ((Bow) (enemy[ce].getWeapon())).getArrows().get(0).getSrc().getWidth(),
						(int) (enemy[ce].getY() + enemy[ce].getHeight() / 4)
								+ ((Bow) (enemy[ce].getWeapon())).getArrows().get(0).getSrc().getHeight(),
						((Bow) (enemy[ce].getWeapon())).getArrows().get(0).getSrc().getWidth(), 0, 0,

						((Bow) (enemy[ce].getWeapon())).getArrows().get(0).getSrc().getHeight(),

						null);

				if (((Bow) enemy[ce].getWeapon()).getArrows().get(0).getX() <= player.getX()
						&& !((Bow) enemy[ce].getWeapon()).getArrows().get(0).canGameProceedAfterArrow) {
					((Bow) enemy[ce].getWeapon()).getArrows().get(0).canGameProceedAfterArrow = true;
					enemy[ce].Arrowhit();
					((Bow) (enemy[ce].getWeapon())).getArrows().remove(0);
				}

			}

		// arrow
		int l;
		if (whosTurn == PLAYER) {
			l = -160;
		} else {
			l = -165;
		}
		textDraw(String.valueOf(whosTurn) + " TURN", 500 + l, 40, Color.WHITE, 40);

		textDraw(messege, (int) (((player.getX() - player.getWidth() / fps) + enemy[ce].getX()) / 2),
				(int) player.getY(), Color.BLACK, 30);

		// fast
		// setBackground(Color.black); //TODO
		// g2d.drawImage(fast, 250, 200,null);
	}

	public static void Store() {

		// movement handler
		frame = frame + 100;
		if (frame == 500) {
			frame = 0;
		}
		// movement handler
		g2d.clearRect(0, 0, img.getWidth(), img.getHeight());
		g2d.setColor(Color.WHITE);

		// draw reka
		g2d.drawImage(SRCreka, 0, 50, null);

		// hover
		textDraw(hover, 656, 100, Color.BLACK, 20);

		// draw money
		textDraw(title, 50, 35, Color.WHITE, 26);
		textDraw("Money: " + String.valueOf(money) + "$", 10, 70, Color.BLACK, 22);
		if (player.getWeapon().isIsbow())
			textDraw("Arrow's left in Main Weapon: " + String.valueOf(((Bow) (player.getWeapon())).getArrows().size()),
					10, 90, Color.BLACK, 19);
		if (player.getSecondery().isIsbow())
			textDraw(
					"Arrow's left in Secondery Weapon: "
							+ String.valueOf(((Bow) (player.getSecondery())).getArrows().size()),
					10, 110, Color.BLACK, 19);

		// Shelf
		g2d.drawImage(shelf, 700, 50, null);
		g2d.drawImage(shelf, 590, 50, null);
		g2d.drawImage(shelf, 480, 50, null);

		// First Shelf
		g2d.drawImage(shirt[fwss].getSrc(), 735, 170, null);
		textDraw(shirt[fwss].getName() + ": " + shirt[fwss].getPrice(), 715 - 20, 150, Color.black, 16);
		// First Shelf

		// Second Shelf
		g2d.drawImage(helmet[fwss].getSrc(), 810, 170, null);
		textDraw(helmet[fwss].getName() + ": " + helmet[fwss].getPrice(), 805, 150, Color.black, 16);
		// Second Shelf

		// third Shelf
		g2d.drawImage(shirt[fwss + 1].getSrc(), 735, 348, null);
		textDraw(shirt[fwss + 1].getName() + ": " + shirt[fwss + 1].getPrice(), 715 - 20, 330, Color.black, 16);

		// fourth Shelf
		g2d.drawImage(helmet[fwss + 1].getSrc(), 810, 348, null);
		textDraw(helmet[fwss + 1].getName() + ": " + helmet[fwss + 1].getPrice(), 805, 330, Color.black, 16);

		// thihthdt Shelf

		g2d.drawImage(weapon[fwss].getSrc(), 616, 170, null);
		textDraw(weapon[fwss].getName() + ": " + weapon[fwss].getPrice(), 616 - 20, 150, Color.black, 16);
		// Second Shelf

		// sixst Shelf
		g2d.drawImage(weapon[fwss + 1].getSrc(), 616, 370, null);
		textDraw(weapon[fwss + 1].getName() + ": " + weapon[fwss + 1].getPrice(), 616 - 20, 330, Color.black, 16);

		// seventh Shelf
		g2d.drawImage(arrows[fwss].getSrc(), 499, 212, null);
		textDraw(arrows[fwss].getName() + ": " + arrows[fwss].getPrice(), 499 - 10, 150, Color.black, 16);

		// eight'th Shelf
		// seventh Shelf
		g2d.drawImage(arrows[fwss + 1].getSrc(), 499, 400, null);
		textDraw(arrows[fwss + 1].getName() + ": " + arrows[fwss + 1].getPrice(), 499 - 10, 330, Color.black, 16);

		g2d.drawImage(player.SRC, (int) player.getX() + 100, (int) player.getY() + 200, (int) player.getX(),
				(int) player.getY(), frame + 100, 200, frame, 0, null);

		// armor
		g2d.drawImage(player.getShirt().getSrc(), (int) player.getX() + 22, (int) player.getY() + 45, null);
		// armor

		player.getWeapon().setRad(player.getWeapon().getRad() + player.getWeapon().getRadSpeed());
		g2d.translate(+(int) (player.getX() + player.getWidth() / fps),
				+(int) (player.getY() + player.getHeight() / 2));
		g2d.rotate(Math.toRadians(player.getWeapon().getRad()));
		GamePanel.g2d.drawImage(player.getWeapon().getSrc(), -0, // (int)(player.getWeapon().getSrc().getWidth()*0.55),
				-player.getWeapon().getSrc().getHeight(), null);
		g2d.rotate(Math.toRadians(-player.getWeapon().getRad()));
		g2d.translate(-(int) (player.getX() + player.getWidth() / fps),
				-(int) (player.getY() + player.getHeight() / 2));
		if (player.getWeapon().getRad() >= 100) {
			player.getWeapon().setRad(80);
			player.getWeapon().setRadSpeed(-player.getWeapon().getRadSpeed());
		}
		if (player.getWeapon().getRadSpeed() >= 90) {
			player.getWeapon().setRadSpeed(-player.getWeapon().getRadSpeed());
		} else if (player.getWeapon().getRad() < 1) {
			player.getWeapon().setRadSpeed(0);
			player.getWeapon().setRad(0);
		}

		g2d.drawImage // player helmet
		(player.getHelmet().getSrc(), (int) player.getX() + 32, (int) player.getY(), null);

		textDraw(pop[4], (int) player.getX() + 100, (int) player.getY() + 20, Color.BLACK, 18);
		g2d.drawImage(down, 900, 310, null);
		g2d.drawImage(down, 900, 310, 900 + down.getWidth(), 310 - down.getHeight(), 0, 0, down.getWidth(),
				down.getHeight(), null);

		// gotoarena -
		g2d.drawImage(gotoArena, 300, 50, null);

		sleep(150);

	}

}