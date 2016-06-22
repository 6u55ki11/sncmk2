package solutions;

import java.awt.Graphics2D;

import javax.swing.JPanel;

public class ContainerHandler {
	// draw the bridge, solutions, wires, and the display for the voltage
	private ContainerPolygon[] cPolygons = new ContainerPolygon[2];
	private SaltBridgePolygon sbPolygon;
	private WireLine wLine;
	private Display display;
	public ContainerHandler(JPanel jp)
	{
		generateDisplay(jp);
		generateWire();
		generateSolutionContainers();
		generateSaltBridgePolygon();
	}
	
	public void generateSolutionContainers()
	{
		cPolygons[0] = new ContainerPolygon(true);
		cPolygons[1] = new ContainerPolygon(false);
	}
	public void generateSaltBridgePolygon()
	{
		sbPolygon= new SaltBridgePolygon();
	}
	public void generateWire()
	{
		wLine = new WireLine();
	}
	
	public void generateDisplay(JPanel jp)
	{
		display = new Display(jp);
		
		
	}
	
	public void draw(Graphics2D g2d)
	{
		for(ContainerPolygon cp : cPolygons)
		{
			cp.draw(g2d);
		}
		sbPolygon.draw(g2d);
		wLine.draw(g2d);
	}
	
	public void updateParticles()
	{
		//do after everything else works
		
	}
	
}
