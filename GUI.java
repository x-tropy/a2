import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JPanel {

	private Station[] stns;
	private TransitLine[] lines;
	private Station[] shortestPath;
	private boolean showPath;
	private Color[] lineColours = new Color[] {Color.red, Color.blue, Color.green.darker(), new Color(175, 0, 200), Color.yellow.darker()};

	public GUI (TransitSystem ltc, boolean showMap) {
		stns = ltc.getStations();
		lines = ltc.getLines();
		
		JFrame f = new JFrame("City Transit Map");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(this);
		f.setSize(1000, 700);
		f.setLocation(550, 25);
		if (showMap) {
			f.setVisible(true);
		}
		
		showPath = false;
	}
	
	public void showShortestPath (Station[] path) {
		shortestPath = path;
		showPath = true;
	}

	public void paint(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
		
		// Draw transit lines.
		g2.setStroke(new BasicStroke(4));
		for (int i = 0; i < lines.length; i++) {
			g2.setColor(lineColours[i]);
			Station prev = null;
			Station curr = lines[i].getFirstStn();
			char ln = lines[i].getLineLetter();
			while (lines[i].hasNext(curr)) {
				prev = curr;
				curr = curr.getNext(ln);
				g2.drawLine(prev.getX(), prev.getY(), curr.getX(), curr.getY());
			}
			
		}
		
		// Legend
		for (int i = 0; i < lines.length; i++) {
			g2.setColor(lineColours[i]);
			char ln = lines[i].getLineLetter();
			g2.drawLine(700, 500+(i*30), 750, 500+(i*30));
			g2.drawString("Line " + ln, 760, 505+(i*30));
		}
		
		
		
		if (showPath) {
			g2.setColor(new Color(50, 50, 50, 170));
			g2.setStroke(new BasicStroke(12));
			Station prev = shortestPath[0];
			for (int i = 1; i < shortestPath.length; i++) {
				if (shortestPath[i] != null) {
					int x1 = prev.getX(), y1 = prev.getY();
					int x2 = shortestPath[i].getX(), y2 = shortestPath[i].getY();
					g2.drawLine(x1, y1, x2, y2);
					prev = shortestPath[i];
				}
			}

		} // if show shortest path

		// Draw stations as circles.
		for (int i = 0; i < stns.length; i++) {
			int x = stns[i].getX();
			int y = stns[i].getY();
			if (stns[i].isVisited()) {
				g2.setColor(Color.yellow);
			} else {
				g2.setColor(Color.black);
			}
			g2.fillOval(x-15, y-15, 30, 30);
			if (stns[i].isVisited()) {
				g2.setColor(Color.black);
			} else {
				g2.setColor(Color.white);
			}
			g2.drawString(String.valueOf(stns[i].getStnNo()), x - 7, y + 5);
		}

	}

}
