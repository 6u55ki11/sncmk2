package metals;

import java.awt.Color;

public class Metal {

	private float ePtnt;
	private float eBlnc;
	private String metalName;
	private Color metalColor;
	private String eSymbol;
	private float molarMass;
	
	public Metal(String name, String symbol, float eptnt,float ebalance, float molarmass, Color color)
	{
		eSymbol = symbol;
		ePtnt = eptnt;
		metalName = name;
		metalColor = color;
		eBlnc = ebalance;
		molarMass = molarmass;
	}
	
	public Color getColor()
	{
		return metalColor;
	}
	public float getPotential()
	{
		return ePtnt;
	}
	public String getName()
	{
		return metalName;
	}
	public String getSymbol()
	{
		return eSymbol;
	}
	
}
