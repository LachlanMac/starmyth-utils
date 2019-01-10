package com.pineconeindustries.shipparser.main;

import java.util.ArrayList;

public class Room {

	int roomID, roomWidth, roomHeight, startX, startY;

	ArrayList<Tile> tiles;
	ArrayList<Tile> doors;

	enum type {

		SPACE, BRIDGE, SHOP, ENGINEERING, MEDICAL, FOYER, WALL, ROOM, DOOR, HALLWAY, DIAGWALL_NE, DIAGWALL_NW, DOOR_EW_OPEN, DOOR_EW_CLOSED, DOOR_NS_OPEN, DOOR_NS_CLOSED, DIAGWALL_SE, DIAGWALL_SW, ZONE, ZONE_N_OPEN, ZONE_S_OPEN, ZONE_E_OPEN, ZONE_W_OPEN, ZONE_N_CLOSED, ZONE_S_CLOSED, ZONE_E_CLOSED, ZONE_W_CLOSED;
	}

	type roomType;

	public Room(int roomID, int startX, int startY) {
		this.roomID = roomID;
		this.startX = startX;
		this.startY = startY;
		tiles = new ArrayList<Tile>();
		doors = new ArrayList<Tile>();
		Ship.roomCount++;
	}

	public void setType(type t) {
		roomType = t;

	}

	public type getType() {
		return roomType;
	}

	public int getRoomID() {
		return roomID;
	}

	public void addTile(Tile t) {
		tiles.add(t);
	}

	public void setRoomWidth(int width) {
		this.roomWidth = width;
	}

	public String toString() {

		StringBuilder doorString = new StringBuilder();
		String doorInfo = "";

		for (Tile d : doors) {

			doorString.append(d.toString() + " ");

		}

		if (doors.size() == 0) {

		} else {
			doorInfo = " Door(s) " + doorString.toString();
		}

		return "Room " + roomID + " created size (" + roomWidth + "x" + roomHeight + ")" + doorInfo;
	}
	
	public String getRoomData() {

		StringBuilder sb = new StringBuilder();
		String doorData = "";
		for (Tile d : doors) {
			sb.append(d.getX() + "x" + d.getY() + "-");
		}
		doorData = sb.toString();

		if (doorData.equals("")) {
			doorData = "N";
		} else {
			doorData = doorData.substring(0, doorData.length() - 1);
		}

		return roomID + "-" + startX + "-" + startY + "-" + roomWidth + "-" + roomHeight + "-" + doorData;

	}

	public void setRoomHeight() {

		this.roomHeight = (tiles.size() / roomWidth);

	}

	public void findDoors(Tile[][] tiles) {

		for (int y = -1 + startY; y < roomHeight + startY + 1; y++) {
			for (int x = -1 + startX; x < roomWidth + startX + 1; x++) {

				if (tiles[x][y].getType() == type.DOOR) {

					doors.add(tiles[x][y]);

				}

			}
		}

	}

}
