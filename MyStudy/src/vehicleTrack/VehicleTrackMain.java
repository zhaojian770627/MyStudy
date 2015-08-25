package vehicleTrack;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class VehicleTrackMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, Point> vehicleInfo = new HashMap<String, Point>();
		vehicleInfo.put("101", new Point(1, 1));
		vehicleInfo.put("102", new Point(1, 2));
		vehicleInfo.put("103", new Point(1, 3));
		vehicleInfo.put("201", new Point(2, 1));
		DelegatingVehicleTracker vk = new DelegatingVehicleTracker(vehicleInfo);
		vk.addLocation("202", 2, 2);
		Map<String, Point> m = vk.getLocations();
		for (Entry<String, Point> entry : m.entrySet()) {
			System.out.println(entry.getKey() + "-[x:" + entry.getValue().x
					+ ",y:" + entry.getValue().y + "]");
		}
	}

}
