import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SierpinskiTriangle extends JPanel{
	
	private JFrame frame;
    
	public static void main(String args[]) 
	{
		SierpinskiTriangle sierp = new SierpinskiTriangle();
		sierp.frame = new JFrame("Sierpinski Triangle");
		
		sierp.frame.add(sierp);
		sierp.frame.setVisible(true);
		sierp.frame.setMinimumSize(new Dimension(150,150));
//		sierp.frame.setPreferredSize(sierp.frame.getSize());
		sierp.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sierp.frame.pack();
		sierp.frame.setSize(500, 500);
	}

	/**
	 * P1 (width / 2, 0)
	 * P2 (0, height)
	 * P3 (width, height)
	 */
	public void between(Point a, Point b, Graphics g) 
	{
		g.drawLine(a.x, a.y, b.x, b.y);
	}
	
	public void drawTriangle(Graphics g, Point a, Point b , Point c, int depth) 
	{
		Color[] c1 = {Color.red,Color.blue,Color.green,Color.magenta,Color.pink,Color.yellow,Color.cyan,Color.orange};
		
		Polygon p = new Polygon();
		p.addPoint(a.x,a.y);
		p.addPoint(b.x,b.y);
		p.addPoint(c.x,c.y);
		
		g.drawLine(a.x, a.y, b.x, b.y);
		g.drawLine(b.x, b.y, c.x, c.y);
		g.drawLine(c.x, c.y, a.x, a.y);
		g.setColor(c1[depth]);
		g.fillPolygon(p);//4. Aufgabe mit Farben
		
		Point midAB = midPoint(a, b);
		Point midBC = midPoint(b, c);
		Point midCA = midPoint(c, a);
		
		if(depth > 0) {
	    drawTriangle(g, a, midAB, midCA, depth-1);
		drawTriangle(g, midAB, b, midBC, depth-1);
		drawTriangle(g, c, midCA, midBC, depth-1);
		}
	}
	
	public Point midPoint(Point a, Point b) 
	{
		return new Point((a.x + b.x) / 2 , (a.y + b.y) / 2);
	}
	
	public void paint(Graphics g) 
	{
		Dimension d = getSize();
		Point a = midPoint(new Point(0, 10), new Point(d.width, 10));
		Point b = new Point(d.width - 10, d.height - 10);
		Point c = new Point(10, d.height - 10);
		
		drawTriangle(g, a, b, c, 5);
	}
}
