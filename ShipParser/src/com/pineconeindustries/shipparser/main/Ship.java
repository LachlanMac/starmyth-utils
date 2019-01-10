package com.pineconeindustries.shipparser.main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.pineconeindustries.shipparser.main.Room.type;

public class Ship {

	public static int roomCount = 0;
	private int width, height;
	Tile[][] layout;
	BufferedImage shipImage;

	ArrayList<Room> rooms;

	public Ship(BufferedImage shipImage) {
		this.shipImage = shipImage;
		this.height = shipImage.getHeight();
		this.width = shipImage.getWidth();

		layout = new Tile[width][height];

		rooms = new ArrayList<Room>();
	}

	public String pushtoData() {
		StringBuilder sb = new StringBuilder();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {

				sb.append(layout[x][y].getTileCode());

			}
		}

		return sb.toString();
	}

	public void handleDoors() {

		for (int y = 1; y < height - 1; y++) {
			for (int x = 1; x < width - 1; x++) {

				if (layout[x][y].tileType == Room.type.DOOR) {
					// going to smooth out with diag tiles
					Tile n = layout[x][y - 1];
					Tile s = layout[x][y + 1];
					Tile e = layout[x + 1][y];
					Tile w = layout[x - 1][y];

					boolean wallNorth = false;
					boolean wallSouth = false;
					boolean wallEast = false;
					boolean wallWest = false;

					if (n.tileType == type.WALL) {
						wallNorth = true;
					}
					if (s.tileType == type.WALL) {
						wallSouth = true;
					}
					if (e.tileType == type.WALL) {
						wallEast = true;
					}
					if (w.tileType == type.WALL) {
						wallWest = true;
					}

					if (wallNorth == true || wallSouth == true) {
						layout[x][y].setRoomType(type.DOOR_EW_CLOSED);
					}
					if (wallEast == true || wallWest == true) {
						layout[x][y].setRoomType(type.DOOR_NS_CLOSED);
					}

				}

			}
		}

	}

	public void handleZones() {

		for (int y = 1; y < height - 1; y++) {
			for (int x = 1; x < width - 1; x++) {

				if (layout[x][y].tileType == Room.type.ZONE) {
					// going to smooth out with diag tiles
					Tile n = layout[x][y - 1];
					Tile s = layout[x][y + 1];
					Tile e = layout[x + 1][y];
					Tile w = layout[x - 1][y];

					boolean wallNorth = false;
					boolean wallSouth = false;
					boolean wallEast = false;
					boolean wallWest = false;

					if (n.tileType == type.WALL) {
						wallNorth = true;
					}
					if (s.tileType == type.WALL) {
						wallSouth = true;
					}
					if (e.tileType == type.WALL) {
						wallEast = true;
					}
					if (w.tileType == type.WALL) {
						wallWest = true;
					}

					if (wallNorth == true || wallSouth == true) {
						if (w.tileType == type.SPACE) {
							layout[x][y].setRoomType(type.ZONE_W_CLOSED);
						} else {
							layout[x][y].setRoomType(type.ZONE_E_CLOSED);
						}
					}
					if (wallEast == true || wallWest == true) {
						if (n.tileType == type.SPACE) {
							layout[x][y].setRoomType(type.ZONE_N_CLOSED);
						} else {
							layout[x][y].setRoomType(type.ZONE_S_CLOSED);
						}
					}

				}

			}
		}

	}

	public void handleDiagWalls() {

		for (int y = 1; y < height - 1; y++) {
			for (int x = 1; x < width - 1; x++) {

				if (layout[x][y].tileType == Room.type.SPACE) {
					// going to smooth out with diag tiles
					Tile n = layout[x][y - 1];
					Tile s = layout[x][y + 1];
					Tile e = layout[x + 1][y];
					Tile w = layout[x - 1][y];

					boolean wallNorth = false;
					boolean wallSouth = false;
					boolean wallEast = false;
					boolean wallWest = false;

					if (n.tileType == type.WALL) {
						wallNorth = true;
					}
					if (s.tileType == type.WALL) {
						wallSouth = true;
					}
					if (e.tileType == type.WALL) {
						wallEast = true;
					}
					if (w.tileType == type.WALL) {
						wallWest = true;
					}

					if (wallSouth && wallEast) {
						if (!wallWest && !wallNorth) {

							layout[x][y].setRoomType(type.DIAGWALL_SE);

						}

					}

					if (wallSouth && wallWest) {
						if (!wallEast && !wallNorth) {

							layout[x][y].setRoomType(type.DIAGWALL_SW);

						}

					}

					if (wallNorth && wallEast) {
						if (!wallWest && !wallSouth) {

							layout[x][y].setRoomType(type.DIAGWALL_NE);

						}

					}

					if (wallNorth && wallWest) {
						if (!wallEast && !wallSouth) {

							layout[x][y].setRoomType(type.DIAGWALL_NW);

						}

					}

				}

			}
		}

	}

	public void read() {

		for (int y = 0; y < height; y++) {

			for (int x = 0; x < width; x++) {

				layout[x][y] = new Tile(x, y, shipImage.getRGB(x, y));
				// System.out.print(layout[x][y].toString() + "-");
				layout[x][y].parseTile();

			}
		}

	}

	public void explode(String path, int multiplier) {

		int bigWidth = multiplier * width;
		int bigHeight = multiplier * height;

		BufferedImage big = new BufferedImage(bigWidth, bigHeight, BufferedImage.TYPE_INT_ARGB);

		for (int y = 0; y < bigHeight; y++) {
			for (int x = 0; x < bigWidth; x++) {

				int tileX = x / multiplier;
				int tileY = y / multiplier;

				if (layout[tileX][tileY].isRoomAssigned()) {

					big.setRGB(x, y, (int) Color.ORANGE.getRGB());
				} else {

					big.setRGB(x, y, layout[tileX][tileY].getRGB());

				}

				if (layout[tileX][tileY].getType() == type.SPACE) {
					big.setRGB(x, y, (int) new Color(0, 0, 0, 0).getRGB());
				}

			}
		}

		File outputfile = new File("res/" + path + ".png");
		try {
			ImageIO.write(big, "png", outputfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getRoomData() {

		StringBuilder sb = new StringBuilder();

		for (Room r : rooms) {

			sb.append(r.getRoomData() + "\n");

		}

		return sb.toString();

	}

	public Room findRoom() {

		Room r = null;

		for (int y = 0; y < height; y++) {

			for (int x = 0; x < width; x++) {

				Tile t = layout[x][y];
				if (t.getType() == type.ROOM && !t.isRoomAssigned()) {

					r = new Room(roomCount, x, y);
					r.addTile(t);
					t.assignRoom(r);

					int startX = x;
					int startY = y;

					int roomWidth = 1;

					for (int theWidth = startX + 1; theWidth < width; theWidth++) {
						if (layout[theWidth][y].getType() != type.ROOM
								|| layout[theWidth][y].isRoomAssigned() == true) {

							break;
						} else {

							r.addTile(layout[theWidth][y]);
							layout[theWidth][y].assignRoom(r);
							roomWidth++;
						}
					}

					r.setRoomWidth(roomWidth);

					int roomHeight = 1;

					boolean foundEnd = false;

					for (int theHeight = startY + 1; theHeight < height; theHeight++) {
						if (foundEnd) {
							rooms.add(r);
							return r;
						}
						for (int theWidth = startX; theWidth < (roomWidth + startX); theWidth++) {
							if (foundEnd) {
								rooms.add(r);
								return r;
							}
							if (layout[theWidth][theHeight].getType() != type.ROOM
									|| layout[theWidth][theHeight].isRoomAssigned() == true) {

								if (layout[theWidth][theHeight].getType() != type.ROOM) {

								} else {

								}

								foundEnd = true;
							} else {

								r.addTile(layout[theWidth][theHeight]);
								layout[theWidth][theHeight].assignRoom(r);
							}
						}
					}
					rooms.add(r);
					return r;

				}

			}
		}

		return r;

	}

	public void readDataTest() {

		String start = pushtoData();

		ShipData ship = new ShipData(width, height, start);

		String okay = ship.parseTileData();

		if (okay.equals(start)) {
			System.out.println("IT WORKED?");
		} else {
			System.out.println("DIDNT WORK");
		}

	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}

	public Tile[][] getLayout() {
		return layout;
	}

	public void assignRooms() {

	}

	public void assignSpace() {

	}

}
