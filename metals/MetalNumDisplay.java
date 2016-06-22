package metals;

import java.awt.Rectangle;

public class MetalNumDisplay {

	
	private Rectangle outline;
	private String name = "";
	private double mass = 0;
	public MetalNumDisplay(Boolean side,String name, double mass)
	{
		this.name = name;
		this.mass = mass;
		if(side)
		{
			outline = new Rectangle(500,200,200,200);
		}
		else
		{
			outline = new Rectangle(500,800,200,200);
		}
	}
	
	public void setMass(int m)
	{
		mass = m;
		
	}
	
	public void setName(String s)
	{
		name = s;
		
	}
	
	
	
}
