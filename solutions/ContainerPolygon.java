package solutions;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

public class ContainerPolygon extends Polygon{
	int[] xPoints = {150,150,450,450};
	int[] yPoints = {400,700,700,400};
	 private final int FWIDTH = 1000;
	 private final int FHEIGHT = 1000;
	 public ContainerPolygon(Boolean side)
	{
		for(int i = 0; i < xPoints.length; i++)
		{
			if(side)
			{
				this.addPoint(xPoints[i], yPoints[i]);
			}
			else
			{
				this.addPoint(FWIDTH - xPoints[i], yPoints[i]);
			}
		}
	}
	
	
	public void draw(Graphics2D g2d)
	{
		Rectangle r =  (Rectangle) g2d.getClip();
		Rectangle shape = this.getBounds();
		shape.translate(0,2);
		g2d.setClip(shape);
		g2d.drawPolygon(this);
		g2d.fillPolygon(this);
		g2d.setClip(r);
	}
	
}
