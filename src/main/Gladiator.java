package main;

import static main.Direction.ATTACK;
import static main.Direction.BLOCK;
import static main.Direction.CHARGE;
import static main.Direction.CLOSERANGE;
import static main.Direction.LEFT;
import static main.Direction.MOVE;
import static main.Direction.POWER;
import static main.Direction.PUSH;
import static main.Direction.RIGHT;
import static main.Direction.SLEEP;
import static main.Direction.SWITCH;
import static main.GamePanel.ce;
import static main.GamePanel.enemy;
import static main.GamePanel.player;
import static main.MyFunctions.sleep;
import static main.MyFunctions.sop;

import java.awt.image.BufferedImage;
import java.io.IOException;

import main.external_libraries.heinemanns_json.JSONObj;
import main.weapon.bow.ArrowFactory;
import main.weapon.bow.BowBuild;
import main.weapon.sword.SwordBuild;

public abstract class Gladiator {
	protected static Weapon[] fsecondery = new Weapon[10];
	protected static Weapon[] fweapon = new Weapon[10];
	protected static Helmet[] fhelmet = new Helmet[10];
	protected static Shirt[] fshirt = new Shirt[10];
	static {
		try {

			fweapon[0] = new SwordBuild(5, javax.imageio.ImageIO.read(new java.io.File("Swords/EnemySword1.png")));
			fweapon[1] = new SwordBuild(8, javax.imageio.ImageIO.read(new java.io.File("Swords/EnemySword2.png")));
			fweapon[2] = new SwordBuild(10, javax.imageio.ImageIO.read(new java.io.File("Swords/ew3.png")));
			fweapon[3] = new BowBuild(5, javax.imageio.ImageIO.read(new java.io.File("Swords/greenbow.png")),
					new ArrowFactory(5, javax.imageio.ImageIO.read(new java.io.File("Swords/greenarrow.png"))), 5);

			fshirt[0] = new ShirtBuild(30, javax.imageio.ImageIO.read(new java.io.File("Armor/enemy0shirt.png")));
			fshirt[1] = new ShirtBuild(35, javax.imageio.ImageIO.read(new java.io.File("Armor/secenemyshirt.png")));
			fshirt[2] = new ShirtBuild(40, javax.imageio.ImageIO.read(new java.io.File("Armor/es3.png")));

			fhelmet[0] = new HelmetBuild(6, javax.imageio.ImageIO.read(new java.io.File("Armor/enemyhelmet.png")));
			fhelmet[1] = new HelmetBuild(8, javax.imageio.ImageIO.read(new java.io.File("Armor/enemy2helmet.png")));
			fhelmet[2] = new HelmetBuild(12, javax.imageio.ImageIO.read(new java.io.File("Armor/eh3.png")));
			fhelmet[3] = new HelmetBuild(20, javax.imageio.ImageIO.read(new java.io.File("Armor/kogos.png")));

			fsecondery[0] = new BowBuild(5, javax.imageio.ImageIO.read(new java.io.File("Swords/rugatca.png")),
					new ArrowFactory(5, javax.imageio.ImageIO.read(new java.io.File("Swords/rock.png"))), 4);
			fsecondery[1] = new BowBuild(5, javax.imageio.ImageIO.read(new java.io.File("Swords/rugatca.png")),
					new ArrowFactory(5, javax.imageio.ImageIO.read(new java.io.File("Swords/rock.png"))), 0);
			fsecondery[2] = new BowBuild(5, javax.imageio.ImageIO.read(new java.io.File("Swords/rugatca.png")),
					new ArrowFactory(5, javax.imageio.ImageIO.read(new java.io.File("Swords/rock.png"))), 4);
			fsecondery[3] = new BowBuild(5, javax.imageio.ImageIO.read(new java.io.File("Swords/rugatca.png")),
					new ArrowFactory(5, javax.imageio.ImageIO.read(new java.io.File("Swords/rock.png"))), 4);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	// square
	protected double x;
	protected double y;
	protected double width;
	protected double height;
	protected double xspeed;

	// values
	protected double health;
	protected double stamina;
	protected double attackDamage;
	protected double speed;

	// weapon and armor
	protected Weapon weapon;
	protected Weapon secondery;

	protected Helmet helmet;
	protected Shirt shirt;

	// default:
	protected double defaultHealth;
	protected double defaultStamina;

	// image
	protected BufferedImage src;

	// booleans
	protected boolean isZZZ;

	public Gladiator(double x, double y, double width, double height, double health, double stamina,
			double attackDamage, Weapon weapon, Weapon secondery, Helmet helmet, Shirt shirt, BufferedImage bf,
			boolean iszzz) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.health = health;
		this.stamina = stamina;
		this.defaultHealth = health;
		this.defaultStamina = stamina;
		this.attackDamage = attackDamage;
		this.weapon = weapon;
		this.secondery = secondery;
		this.helmet = helmet;
		this.shirt = shirt;
		this.isZZZ = iszzz;
		src = bf;
		sop("player summoned");

	}

	public Gladiator(JSONObj g) {
		this.x = 880;
		this.y = 280; // jsonObj.getInt(300);
		this.health = g.getInt("health");
		this.stamina = g.getInt("stamina");
		this.defaultHealth = g.getInt("health");
		this.defaultStamina = g.getInt("stamina");
		this.attackDamage = g.getInt("attack damage");
		this.weapon = fweapon[g.getInt("weapon")];
		this.secondery = fsecondery[g.getInt("weapon")];
		this.helmet = fhelmet[g.getInt("helmet")];
		this.shirt = fshirt[g.getInt("shirt")];
		this.isZZZ = false;
		try {
			this.src = javax.imageio.ImageIO.read(new java.io.File(g.getString("imgsrc")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.width = this.getSrc().getWidth();
		this.height = this.getSrc().getHeight();

	}

	public boolean isZZZ() {
		return isZZZ;
	}

	public void setZZZ(boolean isZZZ) {
		this.isZZZ = isZZZ;
	}

	public double getDefaultHealth() {
		return defaultHealth;
	}

	public void setDefaultHealth(double defaultHealth) {
		this.defaultHealth = defaultHealth;
	}

	public double getDefaultStamina() {
		return defaultStamina;
	}

	public void setDefaultStamina(double defaultStamina) {
		this.defaultStamina = defaultStamina;
	}

	public void setHealth(double health) {
		this.health = health;
	}

	public double getHealth() {
		return health;
	}

	public double getStamina() {
		return stamina;
	}

	public void setStamina(double stamina) {
		this.stamina = stamina;
	}

	public double getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage(double attackDamage) {
		this.attackDamage = attackDamage;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public BufferedImage getSrc() {
		return src;
	}

	public void setSrc(BufferedImage src) {
		this.src = src;
	}

	public abstract boolean attack();

	public abstract boolean power();

	public abstract boolean push();

	public boolean move(Direction d) {

		if (d == BLOCK) {
			// TODO block system
			return false;
		} else if (d == ATTACK) {
			attack();
			return true;

		} else if (d == SWITCH) {
			this.Switch();

		} else if (d == CLOSERANGE) {

			return true; // IF HITTED! TODO AN ATTACKING SYSTEM!

		} else if (d == POWER)// STRONG ATTACK, MOST LIKELY NOT TO SUCSEED
		{
			power();
			return true; // IF HITTED! TODO AN ATTACKING SYSTEM!

		} else if (d == SLEEP) {
			isZZZ = true;
			this.health += (this.defaultHealth - this.health) / 10; // SLEEP SYSTEM!!
			this.stamina += (this.defaultStamina - this.stamina) / 5;

			// this.health += this.defaultHealth/10; // SLEEP SYSTEM!!
			// SLEEP SYSTEM!!
			if (this.health > this.defaultHealth) {
				this.health = this.defaultHealth;
			}
			if (this.stamina > this.defaultStamina) {
				this.stamina = this.defaultStamina;
			}

		} else if (d == PUSH) {
			this.push();
			return true;

		}

		return false;

	}

	public boolean move(Direction d, Direction RoL, boolean isPlayer) {
		int speedy = 50;
		if (d == CHARGE)// STRONG ATTACK, MOST LIKELY NOT TO SUCSEED
		{
			if (isPlayer) { // player
				if (RoL == RIGHT) {
					this.xspeed += speedy;
					GamePanel.player.setStamina(GamePanel.player.getStamina() - 3);
				} else if (RoL == LEFT) {
					this.xspeed -= speedy;
					GamePanel.player.setStamina(GamePanel.player.getStamina() - 3);
				}
				sleep(300);/////// TODOdel
				attack();
				return true;
			}

			else// enemy
			{
				if (enemy[ce].getWeapon().isIsbow()) {

				}

				if (RoL == RIGHT) {
					this.xspeed += speedy;
					enemy[ce].setStamina(enemy[ce].getStamina() - 3);
				} else if (RoL == LEFT) {
					this.xspeed -= speedy;
					enemy[ce].setStamina(enemy[ce].getStamina() - 3);
				}
				sleep(300);/////// TODOdel
				attack();
				return true;
			}
		}

		if (d == MOVE) {
			if (isPlayer) {
				if (RoL == RIGHT) {
					this.xspeed += speedy;
					player.setStamina(GamePanel.player.getStamina() - 3);
				}

				if (RoL == LEFT) {
					this.xspeed -= speedy;
					player.setStamina(GamePanel.player.getStamina() - 3);
				}
			} else if (!isPlayer) {
				if (RoL == RIGHT) {
					this.xspeed += speedy;
					enemy[ce].setStamina(enemy[ce].getStamina() - 3);
				}

				if (RoL == LEFT) {
					this.xspeed -= speedy;
					enemy[ce].setStamina(enemy[ce].getStamina() - 3);
				}
			}
		}

		return false;
	}

	public double getXspeed() {
		return xspeed;
	}

	public void setXspeed(double xspeed) {
		this.xspeed = xspeed;
	}

	// Getters and Setters
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	/**
	 * sucky
	 * 
	 * 
	 */
	public double getWidth() {
		return width;

	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public Helmet getHelmet() {
		return helmet;
	}

	public void setHelmet(Helmet helmet) {
		this.helmet = helmet;
	}

	public Shirt getShirt() {
		return shirt;
	}

	public void setShirt(Shirt shirt) {
		this.shirt = shirt;

	}

	public Weapon getSecondery() {
		return secondery;
	}

	public void setSecondery(Weapon secondery) {
		this.secondery = secondery;
	}

	public abstract void Arrowhit();

	public void Switch() {
		Weapon tmp = this.weapon;
		this.weapon = secondery;
		this.secondery = tmp;
		sop("weirdush");

	}
}
