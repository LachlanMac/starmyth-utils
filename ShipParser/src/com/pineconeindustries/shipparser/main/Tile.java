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

		int x = 0;

		switch (tileType) {
		case BRIDGE:
			x = 25;
			break;
		case DOOR:
			System.out.println("DEFAULT DOOR loaded");
			break;

		case DOOR_EW_OPEN:
			x = 31;
			break;
		case DOOR_EW_CLOSED:
			x = 32;
			break;
		case DOOR_NS_OPEN:
			x = 33;
			break;
		case DOOR_NS_CLOSED:
			x = 34;
			break;
		case ZONE:
			System.out.println("Default zone loaded");
			break;
		case ZONE_E_CLOSED:
			x = 40;
			break;
		case ZONE_W_CLOSED:
			x = 41;
			break;
		case ZONE_N_CLOSED:
			x = 42;
			break;
		case ZONE_S_CLOSED:
			x = 43;
			break;
		case ZONE_E_OPEN:
			x = 44;
			break;
		case ZONE_W_OPEN:
			x = 45;
			break;
		case ZONE_N_OPEN:
			x = 46;
			break;
		case ZONE_S_OPEN:
			x = 47;
			break;
		case ENGINEERING:
			x = 50;
			break;
		case FOYER:
			x = 20;
			break;
		case HALLWAY:
			x = 21;
			break;
		case MEDICAL:
			x = 60;
			break;
		case ROOM:
			x = 200;
			break;
		case SHOP:
			x = 80;
			break;
		case SPACE:
			x = 0;
			break;
		case WALL:
			x = 1;
			break;
		case DIAGWALL_NE:
			x = 2;
			break;
		case DIAGWALL_NW:
			x = 3;
			break;
		case DIAGWALL_SE:
			x = 4;
			break;
		case DIAGWALL_SW:
			x = 5;
			break;
		default:
			System.out.println("UNRECONGIZED PARAM = " + x);
			break;

		}

		String hex = Integer.toHexString(x);
		if (hex.length() == 1) {
			hex = "0" + hex;
		}

		return hex;

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
