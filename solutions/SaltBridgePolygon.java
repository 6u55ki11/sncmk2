package solutions;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Arc2D;


public class SaltBridgePolygon extends Polygon{
	
	
	
	
	private Rectangle outBoundingBox = new Rectangle(325,225,425,350);	
	public SaltBridgePolygon()
	{
		
		//algorithm for creating outside line
		//origin of circle
		int obx = 500,  oby = 400;
		//radius
		int r = 175, r2 = 175*175;
		//left most point represented
		int lx = 325, ly = 400;
		//draw the outer circle
		for(int i = 0; i < 351; i++)
		{
			int px = lx + i;
			int py = oby - (int) Math.sqrt(r2 - ((px - obx)*(px - obx))) ;
			this.addPoint(px,py);
		}
		this.addPoint(675,650);
		this.addPoint(600,650);
		
		//draw the inner circle
		int rx = 600, ry = 400;
		this.addPoint(rx, ry);
		//radius
		r = 100; r2 = r*r;
		for(int i = 0; i < 201; i++)
		{
			int px = rx - i;
			int py = oby - (int) Math.sqrt(r2 - ((px - obx)*(px - obx)));
			System.out.println(px + " " + py);
			this.addPoint(px,py);
		}
		this.addPoint(400, 650);
		this.addPoint(325,650);
		
		
	}
	
	public void draw(Graphics2D g2d)
	{
		//Shape s = g2d.getClip();
		//draw the curve of the 
		//g2d.setClip(outBoundingBox);
		g2d.setColor(Color.cyan);
		g2d.draw(this);
		g2d.fill(this);
		g2d.setColor(Color.black);
		//g2d.setClip(s);
	}
	
	
	
}
