package metals;

import java.awt.Graphics2D;

public class MetalHandler {

	private Metal[] metalList;
	private MetalFactory metFac = new MetalFactory();
	private int m1Index = 3, m2Index = 3;
	private MetalPolygon[] polygonArray = new MetalPolygon[2];
	private MetalNumDisplay[] numDisplays = new MetalNumDisplay[2];
	//handles both different metals
	
	
	private final static double FARADYSCONST = 96485.3329;
	
	public MetalHandler()
	{
		
	}
	
	public void buildMetalList()
	{
		metalList = metFac.fullBuild();
	}
	
	public Metal getMetalAt(int index)
	{
		return metalList[index];
	}
	
	public void updatePolygons()
	{
		Metal m1 = getMetalAt(m1Index);
		Metal m2 = getMetalAt(m2Index);

		polygonArray[0].setPolyColor(m1.getColor());
		polygonArray[1].setPolyColor(m2.getColor());
		
	}
	
	public void generate(int XMAX)
	{
		polygonArray[0] = new MetalPolygon(true, XMAX);
		polygonArray[1] = new MetalPolygon(false, XMAX);
		
		polygonArray[0].setPolyColor(metalList[m1Index].getColor());
		polygonArray[1].setPolyColor(metalList[m1Index].getColor());
		
		numDisplays[0] = new MetalNumDisplay(true,metalList[m1Index].getName(),10.00);
		numDisplays[1] = new MetalNumDisplay(false,metalList[m1Index].getName(),10.00);
		
	}
	
	public void draw(Graphics2D g)
	{
		Metal m1 = getMetalAt(m1Index);
		Metal m2 = getMetalAt(m2Index);
		g.setColor(m1.getColor());
		g.draw(polygonArray[0]);
		g.fill(polygonArray[0]);
		g.setColor(m2.getColor());
		g.draw(polygonArray[1]);
		g.fill(polygonArray[1]);
	}
	
	
	public void updateSelectedMetals(int m1index, int m2index)
	{
		m1Index = m1index;
		m2Index = m2index;
		
		
		
	}
	
	public void massUpdate(int[] masses)
	{
		numDisplays[0].setMass(masses[0]);
		numDisplays[1].setMass(masses[1]);
	}
	
	//update for doing math
	public float getPotential()
	{
		
		Metal m1 = getMetalAt(m1Index);
		Metal m2 = getMetalAt(m2Index);
		return 0 - Math.abs(m1.getPotential() - m2.getPotential());	
	}
	
	
	
	
	//only call after building the list obviously
	public String[] buildNamesList()
	{
		if(metFac.getArray() == null)
		{
			metFac.fullBuild();
		}
		return metFac.getNames();
			
	}
	
}
