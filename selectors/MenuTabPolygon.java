package selectors;
import java.awt.Polygon;

public class MenuTabPolygon extends Polygon {
	private static final long serialVersionUID = 1L;
	//these are menu tabs that are on the side of the frame
	//when clicked on they will expand to show the menu
	private int[] xVals;
	private int[] yVals;
	
	public MenuTabPolygon(int[] Xcoord, int[] Ycoord, int translation, Boolean side)
	{	
		xVals = new int[Xcoord.length];
		yVals = new int[Ycoord.length];
		if(side)
		{// if the polygon is on the left side 
			for(int i = 0; i < xVals.length; i++)
			{//adjust the point by the translation value
				yVals[i]= Ycoord[i] - translation;
				xVals[i] = Xcoord[i];
				this.addPoint(xVals[i], yVals[i]);
			}
		}else if (!side) 
		{	//if the menu is on the right side of the frame
			for(int i = 0; i < xVals.length; i++)
			{
				//invert the xValues to make it fit on the opposite side of the shape
				xVals[i] = 1000 - Xcoord[i];
				yVals[i]= Ycoord[i] - translation;
				this.addPoint(xVals[i], yVals[i]);
			}
		}
		
	}
	
}
