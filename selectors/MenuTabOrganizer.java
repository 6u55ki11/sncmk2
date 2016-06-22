package selectors;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MenuTabOrganizer{
	// coordinate the actions between the components on the polygon and the
	// polygon
	private Boolean isSelected = false;
	private int dist = 0, direction = 0;
	private MenuTabPolygon polygon;
	private MenuTabSelector list;
	private MassSelector mField;
	private Boolean METORLIQ;
	private JPanel compPanel;
	private Boolean scrolling = false, scrolDirection = false;
	public MenuTabOrganizer(Boolean type, JPanel panel) 
	{
		compPanel = panel;
		METORLIQ = type;
	}
	
	public void mouseDragged(MouseEvent e, int mouseY)
	{
		scrolling = false;
		int y = e.getY();
		//if the mouse is in the part of the tab that controls scrolling 
		if(list.getScroll().contains(e.getPoint()))
		{
			if( y > mouseY)
			{
				//doesnt work at all
				scrolling = true;
				scrolDirection = true;
			}else if(y < mouseY)
			{
				//this works somewhat okay but like not really
				scrolling = true;
				scrolDirection = false;
			}
			mouseY = y;
		}
	}
	 
	
	public void scrollList()
	{
		if(scrolling)
		{
			list.traverseList(scrolDirection);
		}
	}
	public void stopScrolling()
	{
		scrolling = false;
	}
	public Boolean isScrolling()
	{
		return scrolling;
	}
	
	public Boolean scrolDirection()
	{
		return scrolDirection;
	}
	
	public void moveMenuTab(int XTRANS)
	{
		setDist(XTRANS);
		polygon.translate(XTRANS * direction, 0);
		list.move(XTRANS*direction);
		if(METORLIQ)
		{
			mField.move(XTRANS*direction);
		}
	}
	
	public void setDist(int x) 
	{
		dist += x;
	}

	public Boolean isMax() 
	{
		return dist >= 200;
	}

	public Boolean atBase() {
		return dist == 0;
	}

	public Boolean getSelected() {
		return isSelected;
	}

	public void draw(Graphics2D g)
	{
		//draw the metals string
		String s = "Solutions";
		if(METORLIQ)
		{
			 s = "Metals";
		}
		g.draw(polygon);
		list.draw(g);
		if(METORLIQ)
		{
			compPanel.revalidate();
			compPanel.repaint();
		}	
			
		
	}
	public void updateSelect(Boolean b) {
		isSelected = b;
	}

	public void generatePolygon(int[] Xcoord, int[] Ycoord, int translation, Boolean side) {
		if (side) {
			direction = 1;
		} else {
			direction = -1;
		}
		polygon = new MenuTabPolygon(Xcoord, Ycoord, translation, side);

	}

	public void generateSelector(String[] options, int Xcoord, int Ycoord, int translation, Boolean side) 
	{// create a new selector
		list = new MenuTabSelector(options, Xcoord, Ycoord, translation, side);
	}

	public MenuTabPolygon getPolygon() 
	{
		return polygon;
	}
	private JLabel label;
	private JTextField field;
	private Double mass = 10.00;
	public void generateMassSelector(Rectangle lBounds, Rectangle fBounds)
	{
		if(METORLIQ)
		{
			mField = new MassSelector();
			label = mField.createLabel(lBounds);
			field = mField.createField(fBounds);
			compPanel.add(label);
			compPanel.add(field);
			compPanel.revalidate();
			compPanel.repaint();
			field.addKeyListener(new KeyListener()
					{
						public void keyPressed(KeyEvent arg0) {
						
						}

						@Override
						public void keyReleased(KeyEvent arg0) {
						
						
						}
						public void keyTyped(KeyEvent arg0) {
							if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
							{
								try{
									mass = Double.valueOf(field.getText()); 
								}catch(NumberFormatException e)
								{
								
								
								}
							
							}
						
						}
					
					});

			}
	}

	public double getMass()
	{
		return mass;
	}

	public Boolean getType()
	{
		return METORLIQ;
	}
	
	public int getSelectedIndex()
	{
		return list.getSelectedIndex();
	}
	
}
