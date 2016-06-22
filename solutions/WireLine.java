package solutions;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;


public class WireLine {

	
	//class for drawing the length of the wire
	private Line2D lPart = new Line2D.Double(), mPart = new Line2D.Double(), rPart = new Line2D.Double();
	
	public WireLine()
	{
		lPart.setLine(225, 350, 225, 125);
		mPart.setLine(225, 125, 775, 125);
		rPart.setLine(775, 125, 775, 350);
	}
	
	public void draw(Graphics2D g2d)
	{
		g2d.draw(lPart);
		g2d.draw(mPart);
		g2d.draw(rPart);
	}

}
