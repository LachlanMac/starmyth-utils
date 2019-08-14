package com.pineconeindustries.shipparser.main;

import java.awt.Color;

import com.pineconeindustries.shipparser.main.Room.type;

public class Tile {

	int x, y, rgb;

	boolean roomAssigned = false;

	Room r;

	type tileType;

	public Tile(int x, int y, int rgb) {
		this.x = x;
		this.y = y;
		this.rgb = rgb;
	}

	@Override
	public String toString() {

		return "Tile@(" + x + "," + y + ")";

	}

	public int getRGB() {
		return rgb;
	}

	public void parseTile() {

		Color c = new Color(rgb);

		if (c.getGreen() == 255 && c.getBlue() == 0 && c.getRed() == 0) {
			tileType = type.SPACE;
		} else if (c.getGreen() == 0 && c.getBlue() == 0 && c.getRed() == 0) {
			tileType = type.WALL;
		} else if (c.getGreen() == 0 && c.getBlue() == 255 && c.getRed() == 255) {
			tileType = type.ROOM;
		} else if (c.getGreen() == 0 && c.getBlue() == 255 && c.getRed() == 0) {
			tileType = type.HALLWAY;
		} else if (c.getGreen() == 0 && c.getBlue() == 0 && c.getRed() == 255) {
			tileType = type.DOOR;
		} else if (c.getGreen() == 255 && c.getBlue() == 0 && c.getRed() == 255) {
			tileType = type.ZONE;
		} else {
			System.out.println("UMMM NO IDEA?");
		}

	}

	public void assignRoom(Room r) {
		this.r = r;
		roomAssigned = true;
	}

	public boolean isRoomAssigned() {
		return roomAssigned;
	}

	public type getType() {
		return tileType;
	}

	public String getTileCode() {

		char x = '0';

		switch (tileType) {
		case BRIDGE:
			x = 'z';
			break;
		case DOOR:
			System.out.println("DEFAULT DOOR loaded");
			break;
		case DOOR_EW_OPEN:
			x = 'y';
			break;
		case DOOR_EW_CLOSED:
			x = 'm';
			break;
		case DOOR_NS_OPEN:
			x = 'n';
			break;
		case DOOR_NS_CLOSED:
			x = 'o';
			break;
		case ZONE:
			System.out.println("Default zone loaded");
			break;
		case ZONE_E_CLOSED:
			x = 'd';
			break;
		case ZONE_W_CLOSED:
			x = 'e';
			break;
		case ZONE_N_CLOSED:
			x = 'f';
			break;
		case ZONE_S_CLOSED:
			x = 'g';
			break;
		case ZONE_E_OPEN:
			x = 'h';
			break;
		case ZONE_W_OPEN:
			x = 'i';
			break;
		case ZONE_N_OPEN:
			x = 'j';
			break;
		case ZONE_S_OPEN:
			x = 'k';
			break;
		case ENGINEERING:
			x = 'c';
			break;
		case FOYER:
			x = 'b';
			break;
		case HALLWAY:
			x = 'a';
			break;
		case MEDICAL:
			x = 'x';
			break;
		case ROOM:
			x = 'w';
			break;
		case SHOP:
			x = 'v';
			break;
		case SPACE:
			x = 'p';
			break;
		case WALL:
			x = 'q';
			break;
		case DIAGWALL_NE:
			x = 'r';
			break;
		case DIAGWALL_NW:
			x = 's';
			break;
		case DIAGWALL_SE:
			x = 't';
			break;
		case DIAGWALL_SW:
			x = 'u';
			break;
		default:
			System.out.println("UNRECONGIZED PARAM = " + x);
			break;

		}

		char[] temp = new char[] { x };

		return new String(temp);

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setRoomType(type t) {
		this.tileType = t;
	}

}
