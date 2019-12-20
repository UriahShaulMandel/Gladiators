package main;

import java.awt.image.BufferedImage;

public class HelmetBuild extends Helmet {

	public HelmetBuild(int amnt, BufferedImage read) {
		super(amnt, read);

		// TODO Auto-generated constructor stub
	}

	public HelmetBuild(int amnt, BufferedImage read, String name, int price) {
		super(amnt, read);
		setPrice(price);
		setName(name);

		// TODO Auto-generated constructor stub
	}

}
