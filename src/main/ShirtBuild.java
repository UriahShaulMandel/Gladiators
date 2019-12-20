package main;

import java.awt.image.BufferedImage;

public class ShirtBuild extends Shirt {

	public ShirtBuild(int armrpntsm, BufferedImage read) {
		super(armrpntsm, read);
	}

	public ShirtBuild(int armrpntsm, BufferedImage read, String name, int price) {
		super(armrpntsm, read);
		setPrice(price);
		setName(name);
	}

}
