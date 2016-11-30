package com.starters.service;

import java.util.List;

import com.starters.bean.Coordinates;

public class CalculateMedianService {
	
	public static Coordinates getMedian(List<Coordinates> coordinateList) {

		double latitude;
		double longitude;

		if (coordinateList.size() == 1) {
			return coordinateList.get(0);
		}

		double x = 0;
		double y = 0;
		double z = 0;

		for (Coordinates coordinate : coordinateList) {
			latitude = coordinate.getLatitude() * Math.PI / 180;
			longitude = coordinate.getLongitude() * Math.PI / 180;

			x += Math.cos(latitude) * Math.cos(longitude);
			y += Math.cos(latitude) * Math.sin(longitude);
			z += Math.sin(latitude);
		}

		double total = coordinateList.size();

		x = x / total;
		y = y / total;
		z = z / total;

		double centralLongitude = Math.atan2(y, x);
		double centralSquareRoot = Math.sqrt(x * x + y * y);
		double centralLatitude = Math.atan2(z, centralSquareRoot);

		return new Coordinates(centralLatitude * 180 / Math.PI, centralLongitude * 180 / Math.PI);
	}
}
