
public class TransitSystem {
	
	private Station[] allStations;
	private TransitLine[] allLines;
	private GUI gui;

	public TransitSystem (Station[] sArr, TransitLine[] tArr, boolean showMap) {
		allStations = sArr;
		allLines = tArr;
		if (showMap)
            gui = new GUI(this, showMap);
	}

	public Station[] getStations () {
		return allStations;
	}
	
	public TransitLine[] getLines () {
		return allLines;
	}
	
	
	public Station getStationByIndex (int index) {
		return allStations[index];
	}
	
	public Station getStationByID (int id) {
		for (int i = 0; i < allStations.length; i++) {
			if (allStations[i].getStnNo() == id) {
				return allStations[i];
			}
		}
		return null;
	}
	
	public TransitLine getTransitLineByIndex (int index) {
		return allLines[index];
	}
	
	public void showShortestPath (Station[] path) {
		if (gui != null) {
			gui.showShortestPath(path);
			gui.repaint();
		}
	}

}
