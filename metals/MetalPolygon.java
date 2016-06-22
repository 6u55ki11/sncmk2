package metals;


import java.awt.Color;
import java.awt.Polygon;

public class MetalPolygon extends Polygon{
	//this is the polygon that describes the area that is the metal
	private Color polyColor;
	public MetalPolygon(Boolean side, int xMax)
	{
		int[]  mxc= {175,175,275,275};
		int[] myc = {350,650,650,350};
		//left or right
		if(side)
		{
			for(int i =0; i < mxc.length; i++)
			{
				this.addPoint(mxc[i], myc[i]);
			}
		}else
		{
			for(int i =0; i < mxc.length; i++)
			{
				this.addPoint(xMax - mxc[i], myc[i]);
			}
			
		}
	}
	
	public void setPolyColor(Color c)
	{
		polyColor = c;
		
	}
	
	public Color getPolyColor()
	{
		return polyColor;
	}
	
	
	
}
