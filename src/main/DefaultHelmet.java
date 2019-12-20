package main;

import java.awt.image.BufferedImage;

public class DefaultHelmet extends Helmet {
	public static BufferedImage Dfile;
	static {
		try {
			Dfile = javax.imageio.ImageIO.read(new java.io.File("pxl.bmp"));
		} catch (java.io.IOException e) {
		}

	}

	public DefaultHelmet() {
		super(10, Dfile);
	}

	public DefaultHelmet(String name, int price) {
		super(5, Dfile);
		setPrice(price);
		setName(name);
		// TODO Auto-generated constructor stub
	}
}
