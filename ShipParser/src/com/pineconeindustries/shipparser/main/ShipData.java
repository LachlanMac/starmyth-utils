package com.pineconeindustries.shipparser.main;

public class ShipData {

	private int shipID, width, height;

	ShipTileData[][] layout;
	String tileData;

	public ShipData(int width, int height, String tileData) {
		this.width = width;
		this.height = height;
		this.tileData = tileData;
	}

	public String parseTileData() {

		StringBuilder sb = new StringBuilder();

		layout = new ShipTileData[width][height];

		int startIndex = 0;
		int endIndex = 2;

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {

				String data = tileData.substring(startIndex, endIndex);
				startIndex += 2;
				endIndex += 2;
				sb.append(data);

			}
		}

		return sb.toString();

	}
	
	

	public void splitData() {

	}

}