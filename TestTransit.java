
public class TestTransit {
	
	public static void main(String[] args) {

		TransitSystem sys = t1();
		
		int startStn = 32;
		int destStn = 77;
		
		Station start = sys.getStationByID(startStn);
		Station dest = sys.getStationByID(destStn);

		Station[] sp = RouteFinder.findShortestPath(sys, start, dest);
		String s = RouteFinder.getPathString(sp);
		System.out.println(s);

		sys.showShortestPath(sp);
		
		
		
	}
	
	public static TransitSystem t1 () {
		Station.resetIDCounter();
		Station s1 = new Station(55, 50, 220);
		Station s2 = new Station(72, 215, 220);
		Station s3 = new Station(19, 400, 220);
		Station s4 = new Station(45, 550, 220);
		Station s5 = new Station(32, 170, 350);
		Station s6 = new Station(98, 350, 440);
		Station s7 = new Station(15, 650, 220);
		Station s8 = new Station(77, 790, 220);
		
		TransitLine lineA = new TransitLine('A', s1);
		lineA.addStation(s2);
		lineA.addStation(s3);
		lineA.addStation(s4);
		lineA.addStation(s7);
		lineA.addStation(s8);
				
		Station s9 = new Station(27, 550, 100);
		Station s10 = new Station(48, 550, 400);
		Station s11 = new Station(32, 550, 500);
		Station s12 = new Station(81, 550, 600);
		Station s13 = new Station(40, 50, 100);
		
		TransitLine lineB = new TransitLine('B', s9);
		lineB.addStation(s4);
		lineB.addStation(s10);
		lineB.addStation(s11);
		lineB.addStation(s12);
		
		
		TransitLine lineC = new TransitLine('C', s13);
		lineC.addStation(s1);
		lineC.addStation(s5);
		lineC.addStation(s6);
		lineC.addStation(s11);
		

		Station[] sArr = new Station[] {s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13};
		TransitLine[] tArr = new TransitLine[] {lineA, lineB, lineC};
		TransitSystem ltc = new TransitSystem(sArr, tArr, true);
		
		return ltc;
	}

}
