package com.pineconeindustries.shipparser.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageReader {

	public static BufferedImage readImage(String fileName) {

		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("res/" + fileName + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return img;

	}

}
