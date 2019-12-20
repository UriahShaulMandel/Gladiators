package main;

import static main.Direction.ATTACK;
import static main.Direction.CHARGE;
import static main.Direction.LEFT;
import static main.Direction.MOVE;
import static main.Direction.POWER;
import static main.Direction.PUSH;
import static main.Direction.RIGHT;
import static main.Direction.SLEEP;
import static main.Direction.SWITCH;
import static main.MyFunctions.AI;
import static main.MyFunctions.armorshop;
import static main.MyFunctions.console;
import static main.MyFunctions.detector;
import static main.MyFunctions.init;
import static main.MyFunctions.reset;
import static main.MyFunctions.sleep;
import static main.MyFunctions.sop;
import static main.Rendering.Store;
import static main.Rendering.arena;
import static main.WhosTurn.PLAYER;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import main.gladiators.Player;
import main.weapon.bow.Arrow;
import main.weapon.bow.Bow;

public class GamePanel extends JPanel implements MouseMotionListener, MouseListener, java.awt.event.KeyListener {

	public static Point2D lastclicked;

	public static int fwss = 0; // from which place in the array the store starts
	public static int fwssend = 3; // which place in the array the store is the end
	public static int money = 0; // player's money
	public static int frame = 0; // current frame - for vectorimage handeling
	public static int fps = 6; // how much frames are in the vectorimage
	public static int Healthamount = 180; // for bar drawing
	public static int Staminaamount = 180; // for bar drawing
	public static int ce = 0; // current enemy in the array

	/////////////////////////////////////
	public static boolean inGame;
	public static boolean isRendering = true;

	public static String[] pop = new String[7]; // messege for mouse action

	public static String hover = "hover on an item to see it statistic"; // hover messege in store
	public static String messege = "";
	public static String title = "And so, in a frenzy of blood and with the stench of fear, your days as a Gladiator begin";
	String rulz = "Use the cursor to Attack\n\rPress the Arrows to move\n\rPress Down arrow to sleep";

	public static double enemySpeed = 0;
	public static double playerSpeed = 0;

	public static Scanner reader = new Scanner(System.in);

	// public static FirstShirt firstShirt = new
	public static Helmet[] helmet = new Helmet[5];
	public static Shirt[] shirt = new Shirt[5];
	public static Weapon[] weapon = new Weapon[5];
	public static Arrow[] arrows = new Arrow[5];

	public static BufferedImage arrow;
	public static BufferedImage down;
	public static BufferedImage img;
	public static BufferedImage SRC1;
	public static BufferedImage zzz;
	public static BufferedImage SRCreka;
	public static BufferedImage SRCHealthBar;
	public static BufferedImage SRCStaminaBar;
	public static BufferedImage SRCBar;
	public static BufferedImage sword1;
	public static BufferedImage shelf;
	public static BufferedImage gotoArena;
	public static BufferedImage fast;
	public static BufferedImage slow;
	public static BufferedImage action;

	public static Graphics2D g2d;

	public static Thread rendering;
	public static Thread engine;
	public static Thread console;

	public static Player player;
	public static Gladiator[] enemy = new Gladiator[5];

	// public static Shop[] shop = new Shop[6];

	public static WhosTurn whosTurn = PLAYER;

	public GamePanel() {

		setSize(GameMain.WIDTH, GameMain.HEIGHT);
		setFocusable(true);
		setVisible(true);
		setLayout(null);
		requestFocus();
		addMouseListener(this);
		addKeyListener(this);
		addMouseMotionListener(this);
		init();
		img = new BufferedImage(GameMain.WIDTH, GameMain.HEIGHT, BufferedImage.TYPE_INT_ARGB);
		g2d = img.createGraphics();

		inGame = !false;

		console = new Thread(() -> {
			while (true)
				console(reader.nextLine());
		});

		engine = new Thread(() -> {
			while (true) {
				sleep(10);
				while (inGame) {
					sleep(5);
					if (!(player.y >= 350)) {
						player.y += 2;
					}
					if (!(enemy[ce].y >= 350)) {
						enemy[ce].y += 2;
					}
					// ||
					if (player.getHealth() <= 0) {
						inGame = false;
						money -= money / 2;
						JOptionPane.showMessageDialog(null, "you lost half of your money :(", "Gladiators",
								JOptionPane.INFORMATION_MESSAGE);
						reset();
					}
					if (enemy[ce].getHealth() <= 0) {
						inGame = false;
						money += 1800;
						JOptionPane.showMessageDialog(null, "You won!, you earned 1800$!", "Gladiators",
								JOptionPane.INFORMATION_MESSAGE);
					}
					try {
						if (player.getWeapon().isIsbow())
							if (!((Bow) (player.getWeapon())).getArrows().isEmpty())
								while (!((Bow) (player.getWeapon())).getArrows().get(0).canGameProceedAfterArrow)
									sleep(20);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						if (enemy[ce].getWeapon().isIsbow())
							if (!((Bow) (enemy[ce].getWeapon())).getArrows().isEmpty())
								while (!((Bow) (enemy[ce].getWeapon())).getArrows().get(0).canGameProceedAfterArrow)
									sleep(20);
					} catch (Exception e) {
						e.printStackTrace();
					}

					if (whosTurn == PLAYER)
						urTurn();
					else
						computerTurn();
				}
			}
		});

		rendering = new Thread(() -> {
			while (isRendering) {
				reset();
				while (inGame) {
					arena();
					Graphics gr = getGraphics();
					if (gr != null) {
						g2d.drawRect(0, 0, img.getWidth(), img.getHeight());
						gr.drawImage(img, 0, 0, null);
						gr.dispose();
					}
					// RENDERINGTIMER
					sleep(120); // 40
				}

				reset();
				while (!inGame) {
					Store();
					// drawing the image to the screen
					Graphics gr = getGraphics();
					if (gr != null) {
						g2d.drawRect(0, 0, img.getWidth(), img.getHeight());
						gr.drawImage(img, 0, 0, null);
						gr.dispose();
					}
				}
				title = "Another Day, Another Death, Another Battle";

			}
		});

		engine.start();
		rendering.start();
		console.start();

	}

	private void urTurn() {

		if (player.getWeapon().isIsbow()) {
			pop[1] = "Close Attack";
			pop[2] = "Shoot";
			pop[3] = "Fall and Shoot";
		} else {
			pop[1] = "Power Attack";
			pop[2] = "Attack";
			pop[3] = "Charge Attack";
			pop[4] = "Switch";
			pop[5] = "Push";
			pop[6] = "Sleep";

		}
		if (player.isZZZ())
			player.setZZZ(false);
		if (player.stamina <= 0) {
			player.move(SLEEP);
			GamePanel.whosTurn = WhosTurn.COMPUTER;

		}

	}

	private void computerTurn() {
		sleep(1000);
		messege = "";

		if (enemy[ce].isZZZ())
			enemy[ce].setZZZ(false);
		if (enemy[ce].getStamina() <= 5) {
			enemy[ce].move(SLEEP);
		} else {
			Direction dic;
			dic = AI();
			System.out.println("AI decided to: " + dic);
			if (dic == CHARGE && enemy[ce].getWeapon().isIsbow()) {
				enemy[ce].move(CHARGE, RIGHT, false);
			}
			if (dic == SWITCH) {
				sop("sexus");
			}
			if (dic == CHARGE) {
				enemy[ce].move(CHARGE, LEFT, false);
			} else if (dic == ATTACK) {
				enemy[ce].move(ATTACK);
			} else if (dic == POWER) {
				enemy[ce].move(POWER);
			} else if (dic == LEFT) {
				enemy[ce].move(MOVE, LEFT, false);
			} else if (dic == RIGHT) {
				enemy[ce].move(MOVE, RIGHT, false);
			} else if (dic == SLEEP) {
				enemy[ce].move(SLEEP);
			} else {
				enemy[ce].move(dic);
			}

		}
		whosTurn = PLAYER;
	}

	@Override
	public void mouseClicked(MouseEvent pnt) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent pnt) {

		if (!inGame) {
			armorshop(pnt.getPoint());
		}
		sop(pnt.getPoint());
		if (inGame) {
			if (detector(pnt.getPoint(), (int) player.getX() + 20, (int) player.getY(),
					-(int) 100 + (int) player.getX(), +(int) 200 / 6 + (int) player.getY())) {
				if (whosTurn == PLAYER) {
					messege = "";
					player.move(POWER);
					GamePanel.whosTurn = WhosTurn.COMPUTER;
				}

				sop("IDINAHOY");

			}

			if (detector(pnt.getPoint(), (int) player.getX(), (int) ((int) player.getY() + player.getHeight() / 3),
					-(int) 200 + (int) player.getX(),
					+(int) 200 / 6 + (int) ((int) player.getY() + player.getHeight() / 2))) {
				sop("IDINAHOY2");

				if (whosTurn == PLAYER) {
					messege = "";
					player.move(ATTACK);
					GamePanel.whosTurn = WhosTurn.COMPUTER;
				}

			}

			if (detector(pnt.getPoint(), (int) player.getX(), (int) (player.getY() + player.getHeight()),
					-(int) 100 + (int) player.getX(), -(int) 200 / 6 + (int) (player.getY() + player.getHeight()))) {
				sop("IDINAHOY3");
				if (whosTurn == PLAYER) {
					messege = "";
					if (player.getWeapon().isIsbow())
						player.move(CHARGE, LEFT, true);
					else
						player.move(CHARGE, RIGHT, true);
					GamePanel.whosTurn = WhosTurn.COMPUTER;
				}

			}
			if (detector(pnt.getPoint(), (int) player.getX() + 80, (int) (player.getY() + player.getHeight() / 4),
					(int) player.getWidth() / fps * 2 + (int) player.getX(), (int) (player.getY() - 20))) {
				sop("IDINAHOY4");
				if (whosTurn == PLAYER) {
					messege = "";
					player.move(SWITCH);
					GamePanel.whosTurn = WhosTurn.COMPUTER;
				}

			}

			if (detector(pnt.getPoint(), (int) player.getX() + (int) player.getWidth() / fps,
					(int) (player.getY() + (int) player.getHeight() / 3),
					(int) (3 * player.getWidth() / fps) + (int) player.getX(),
					(int) (player.getY() + (int) (player.getHeight() / 1.8)))) {
				sop("IDINAHOY5");
				if (whosTurn == PLAYER) {
					messege = "";
					player.move(PUSH);
					GamePanel.whosTurn = WhosTurn.COMPUTER;
				}

			}

			if (detector(pnt.getPoint(), (int) player.getX() + (int) (player.getWidth() / fps),
					(int) (player.getY() + player.getHeight()),
					((int) player.getWidth() / fps * 2) + (int) player.getX(),
					-(int) action.getHeight() / 6 + (int) (player.getY() + player.getHeight()))) {
				sop("IDINAHOY6");
				if (whosTurn == PLAYER) {
					messege = "";
					player.move(SLEEP);
					GamePanel.whosTurn = WhosTurn.COMPUTER;
				}

			}
			// player.getx+80 - player.getx+200
			// player.gety+30 - player.gety

			if (detector(pnt.getPoint(), 0, 0, 71, 33)) {
				Color my = new Color(203, 156, 131); // Color white
				UIManager UI = new UIManager();
				UI.put("OptionPane.background", my);
				UI.put("Panel.background", my);

				JOptionPane.showMessageDialog(null, rulz, "Rules", JOptionPane.INFORMATION_MESSAGE);

			}
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			if (whosTurn == PLAYER) {
				messege = ""; ///
				player.move(MOVE, LEFT, true);

				GamePanel.whosTurn = WhosTurn.COMPUTER;
			}
		}

		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			if (whosTurn == PLAYER) {
				messege = ""; ///
				player.move(MOVE, RIGHT, true);

				GamePanel.whosTurn = WhosTurn.COMPUTER;
			}
		}

		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
			if (!inGame) {
				player.move(SWITCH);

			}

		}

		if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
			if (whosTurn == PLAYER) {
				messege = ""; ///
				player.move(SLEEP);

				GamePanel.whosTurn = WhosTurn.COMPUTER;
			}
		}

		if (key == KeyEvent.VK_ESCAPE) {
			inGame = !inGame;
			sop(inGame);
		}

		if (key == KeyEvent.VK_T) {
			reset();
		}
		if (key == KeyEvent.VK_R) {
			player.setX(20);
			enemy[ce].setX(880);
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {

		if (!inGame) {
			// hover tells
			Point2D clk = arg0.getPoint();
			if (detector(clk, 700, 135, 798, 296)) {
				hover = ("Armor Points: " + String.valueOf(shirt[fwss].getArmorPoints()));

			} else if (detector(clk, 900, 135, 803, 296)) {
				hover = ("Armor Points: " + String.valueOf(helmet[fwss].getArmorPoints()));

			} else if (detector(clk, 700, 310, 796, 472)) {
				hover = ("Armor Points: " + String.valueOf(shirt[fwss + 1].getArmorPoints()));

			} else if (detector(clk, 804, 310, 899, 472)) {
				hover = ("Armor Points: " + String.valueOf(helmet[fwss + 1].getArmorPoints()));

			} else if (detector(clk, 590, 134, 687, 296)) {
				hover = ("Atttack Points: " + String.valueOf(weapon[fwss].getAttackBonus()));

			} else if (detector(clk, 590, 310, 687, 472)) {

				hover = ("Atttack Points: " + String.valueOf(weapon[fwss + 1].getAttackBonus()));

			} else if (detector(clk, 480, 135, 577, 297)) {

				hover = ("Atttack Points: " + String.valueOf(arrows[fwss].getAttackBonus()));

			} else if (detector(clk, 480, 310, 577, 472)) {

				hover = ("Atttack Points: " + String.valueOf(arrows[fwss + 1].getAttackBonus()));

			}
		}
	}

}