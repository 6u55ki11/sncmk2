package metals;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class MetalFactory 
{	//create an array of metals
	private ArrayList<Metal> m = new ArrayList<Metal>(); 
	
	public Metal[] fullBuild()
	{
		int numLines = 0;
		try
		{
			URL url = this.getClass().getClassLoader().getResource("E0values.txt");
			File f = new File(url.getPath());
			BufferedReader r = Files.newBufferedReader(f.toPath());
			String name = "";
			float epotential = 0;
			String symbol = "";
			Scanner scanner = new Scanner(r);
			int ebalance;
			float molarmass;
			while(scanner.hasNext() != false)
			{
				numLines++;
				name = scanner.next();
				symbol = scanner.next(); 
				ebalance = scanner.nextInt();
				epotential = scanner.nextFloat();
				System.out.println(numLines);
				molarmass = scanner.nextFloat();
				float f1 = scanner.nextFloat();
				float f2 = scanner.nextFloat();
				float f3 = scanner.nextFloat();
				System.out.println(f1 + " " + f2 + " " + f3);
				Color c = new Color(f1,f2,f3);
				addLayer(name,symbol,ebalance, epotential,molarmass,c);
				
			}
			r.close();
			scanner.close();
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
		//read in from file containing the metal properties
		Metal[] met = new Metal[numLines];
		m.toArray(met);
		return met;
	}
	
	public String[] getNames()
	{
		if(m.size() == 0)
		{
			fullBuild();	
		}
		String[] s = new String[m.size()];
		for(int i = 0; i < s.length; i++)
		{
			s[i] = m.get(i).getName();
		}
		return s;
	}
	
	public void addLayer(String name, String symbol, float ePot, float ebalance, float molarmass, Color color)
	{
		m.add(new Metal(name, symbol, ePot,ebalance,molarmass, color));
	}
	
	public Metal[] getArray()
	{
		Metal[] temp = new Metal[m.size()];
		 m.toArray(temp);
		return  temp;
	}
}
