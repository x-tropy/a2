
public class TransitLine {
	
	private char lineLetter;
	private Station firstStn;
	
	public TransitLine (char ln, Station first) {
		lineLetter = ln;
		firstStn = first;
	}
	
	public char getLineLetter () {
		return lineLetter;
	}
	
	public Station getFirstStn () {
		return firstStn;
	}
	
	
	public boolean hasNext (Station stn) {
		Station next = stn.getNext(lineLetter);
		return next != null;
	}
	
	public void addStation (Station stn) {
		Station lastAdded = firstStn;
		
		// Traverse to current last added station.
		while (hasNext(lastAdded)) {
			lastAdded = lastAdded.getNext(lineLetter);
		}
		
		// Set prev connections.
		stn.setPrev(lastAdded, lineLetter);
		
		// Set next connections.
		lastAdded.setNext(stn, lineLetter);

	}

	
	public String toString () {
		String s = "Line " + lineLetter + ":  " + firstStn.getStnNo() + "  ";
		
		Station curr = firstStn;
		while (hasNext(curr)) {
			curr = curr.getNext(lineLetter);
			s += curr.getStnNo() + "  ";
		}
		
		return s;
	}

}
