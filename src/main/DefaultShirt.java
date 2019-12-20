package main;

import java.awt.image.BufferedImage;

public class DefaultShirt extends Shirt {
	public static BufferedImage Dfile;
	static {
		try {
			Dfile = javax.imageio.ImageIO.read(new java.io.File("pxl.bmp"));
		} catch (java.io.IOException e) {
		}

	}

	public DefaultShirt() {
		super(15, Dfile);
	}

}
