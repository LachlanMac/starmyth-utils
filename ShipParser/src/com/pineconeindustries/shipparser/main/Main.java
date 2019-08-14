package com.pineconeindustries.shipparser.main;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {

		BufferedImage img = ImageReader.readImage("ShipTemplate2");

		Ship s = new Ship(img);
		s.read();

		boolean moreRooms = true;
		while (moreRooms) {

			Room r = s.findRoom();

			if (r == null) {
				moreRooms = false;
				break;
			}

			r.setRoomHeight();

		}

		for (Room r : s.getRooms()) {

			r.findDoors(s.getLayout());
			System.out.println(r.toString());

		}

		s.handleDiagWalls();
		s.handleDoors();
		s.handleZones();

		String stringo = s.pushtoData();

		BufferedWriter br = null;

		String condensed = s.condense(stringo);

		try {

			br = new BufferedWriter(new FileWriter(new File("res/100-Betaship.txt")));
			br.write(condensed);
			br.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		try {

			br = new BufferedWriter(new FileWriter(new File("res/100-Betaship-rooms.txt")));

			br.write(s.getRoomData());
			br.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		s.explode("bigstation", 64);
	}

}
