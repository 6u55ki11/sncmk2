package selectors;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JComboBox;

public class MenuTabSelector {
	//Jlist of all of the components on the tab
	
	//number of different options to display
	//get the values from here with a simple get method
	private final int NUMDISPLAY = 5;
	private ArrayList<Rectangle> scrollingRects = new ArrayList<Rectangle>();
	private Rectangle outline;
	private ArrayList<String> workingLabels = new ArrayList<String>();
	private String[] fullList;
	private int scrollPosition = 3;
	private final int HEIGHT = 150;
	private final int WIDTH = 200;
	public MenuTabSelector(String[] options, int Xcoord, int Ycoord, int translation, Boolean side)
	{
		
		MAXOPTIONS = options.length;
		fullList = options;
		//if its on the right then the 
		if (!side) 
		{
			Xcoord = 1000;
		}
		
		outline = new Rectangle(Xcoord, Ycoord - translation, WIDTH, HEIGHT);
		//generate five rectangles within the other rectangle which will form the rotating selection list;
		//as well as two other rectangles that are above and below which will cycle in as the list is scrolled
		int topDist = 0;
		scrollingRects.add(new Rectangle(Xcoord,Ycoord - translation - HEIGHT/NUMDISPLAY, WIDTH,HEIGHT/NUMDISPLAY ));
		workingLabels.add(fullList[0]);
		for(int i = 1; i < NUMDISPLAY + 2; i++)
		{	
			workingLabels.add(fullList[i]);
			scrollingRects.add(new Rectangle(Xcoord,Ycoord - translation + topDist, WIDTH,HEIGHT/NUMDISPLAY ));
			topDist += HEIGHT/NUMDISPLAY;
		}
		
	}
	private int rectNum = 0;
	private final int MAXOPTIONS, VELOCITY = 4;
	public Rectangle getScroll()
	{
		//returns the rectangle that checks for scrolling
		return outline;
	}
	
	public void traverseList(Boolean direction)
	{
		//scrollPosition = scrollPosition % fullList.length -1; 
		//true = up , false = down
		if(direction)
		{
				// if its scrolling up and isn't at start
				for(int i = 0; i < scrollingRects.size();i++)
				{	
					Rectangle r = scrollingRects.get(i);
					r.setLocation((int)r.getX(), (int)r.getY() - VELOCITY);
				}
				//if the bottom rectangle is completely off of the thingy delete it and create a new one at the opposite side
				if(scrollingRects.get(1).getY() <= outline.getY())
				{
					scrollPosition++;
					scrollingRects.remove(0);
					scrollingRects.add(6, new Rectangle((int)outline.getX(), (int)outline.getY() + HEIGHT/ NUMDISPLAY + (int)outline.getHeight(), WIDTH, HEIGHT/ NUMDISPLAY)); 
					
					workingLabels.remove(0);
					workingLabels.add(fullList[(scrollPosition + 3 ) % (fullList.length - 1)]);
				}	
				
		}
		else if(!direction)
		{
			// if its scrolling down
			for(int i = 0; i < scrollingRects.size();i++)
			{	
				Rectangle r = scrollingRects.get(i);
				r.setLocation((int)r.getX(), (int)r.getY() + VELOCITY);
				//set the y value for each of the strings
			}
			//if the second bottom rectangle is outside the outline
			if(scrollingRects.get(5).getY() >= outline.getY()  + outline.getHeight())
			{
				scrollPosition--;
				scrollingRects.remove(6);
				scrollingRects.add(0,new Rectangle((int)outline.getX(), (int)outline.getY() - HEIGHT/ NUMDISPLAY, WIDTH, HEIGHT/ NUMDISPLAY)); 	
				workingLabels.remove(6);
				workingLabels.add(0, fullList[(scrollPosition - 3 + fullList.length) % (fullList.length - 1)]);
			}
		}
	}

	public void move(int dist)
	{
		outline.translate(dist, 0);
		for(Rectangle r : scrollingRects)
		{
			r.translate(dist , 0);
		}
	}

	public void draw(Graphics2D g)
	{
		g.draw(outline);
		Shape temp = g.getClip();
		g.setClip(outline);
		for(int i = 0; i < scrollingRects.size();i++)
		{	
			Rectangle r = scrollingRects.get(i);
			g.draw(r);
			g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,14));
			g.drawString(workingLabels.get(i), (int) outline.getX(), (int) r.getY());
		}
		g.setClip(temp);
	}

	public int getSelectedIndex()
	{
		return scrollPosition;
	}

}
