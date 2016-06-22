package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import metals.MetalHandler;
import selectors.MenuTabOrganizer;
import solutions.ContainerHandler;

public class SimMain implements Runnable, MouseListener, MouseMotionListener{
	public final int NEGATIVE = -1;
	public final int POSITIVE = 1;
	private int mouseY;
	public final int XTRANS = 6;
	private JLayeredPane layerdPanel = new JLayeredPane(); 
	private SimPanel simPanel = new SimPanel(this);
	private JPanel compPanel = new JPanel();
	//the x coords for all of the menu tabs
	private int[] MTXCOORDS = {100,0,-200,-200,0,0,100};
	//the Ycoords for the points
	private int[] MTYCOORDS = {150,150,150,350,350,300,300};
	private int[] YTRANS = {0,0,-150};
	private Boolean[] SIDE = {true,false,false};
	//replace with the list of names for all the colours
	//point list , translation, inversion
	private MenuTabOrganizer[] menuTabs = 
		{ //true or false for wether it is a liquid tab or not
				new MenuTabOrganizer(true,compPanel),
				new MenuTabOrganizer(true,compPanel),
				//new MenuTabOrganizer(false,compPanel),
	};
	private boolean[] hasMSelector = {true, true , false};
	public  final int WIDTH = 1000;
	public  final int HEIGHT = 1000;
	private MetalHandler metalHandler = new MetalHandler();
	private ContainerHandler containerHandler;
	public SimMain() {
		Thread th = new Thread(this);
		th.run();
	}

	public static void main(String[] args) {
		new SimMain();
	}
	
	public void init()
	{
		JFrame frame = new JFrame("science bitch");
		
		//initialize the handler for the solutions
		containerHandler = new ContainerHandler(compPanel);
		//generate the list of metals
		metalHandler.buildMetalList();
		metalHandler.generate(WIDTH);
		
		for (int i = 0; i < menuTabs.length;i++)
		{
			menuTabs[i].generatePolygon(MTXCOORDS, MTYCOORDS, YTRANS[i] ,SIDE[i]);
			menuTabs[i].generateSelector(metalHandler.buildNamesList(),MTXCOORDS[2], MTYCOORDS[2], YTRANS[i] ,SIDE[i]);
			if(hasMSelector[i])
			{
				int x = 0, y , dx = 0;
				if (SIDE[i])
				{
					dx = MTXCOORDS[2];
					y = MTYCOORDS[0]  + 151 - YTRANS[i];//150
				}
				else 
				{
					 dx = WIDTH + 1;
					 System.out.println(frame.getWidth());
					 y = MTYCOORDS[0]  + 151 - YTRANS[i];
				}
				
				int height = 48;//50
				int width  = 98;//100
				Rectangle lr = new Rectangle(x, y, width,height);
				lr.translate(dx, 0);
				Rectangle fr = new Rectangle(x + width,y,width, height);
				fr.translate(dx, 0);
				menuTabs[i].generateMassSelector(lr,fr);
				compPanel.revalidate();
			}
		}
		
		
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayeredPane(layerdPanel);
		frame.setLayout(null);
		layerdPanel.add(simPanel, JLayeredPane.DEFAULT_LAYER);
		layerdPanel.add(compPanel, JLayeredPane.PALETTE_LAYER);
		compPanel.setLayout(null);
		compPanel.setSize(WIDTH,HEIGHT);
		compPanel.setOpaque(false);
		compPanel.setVisible(true);
		simPanel.addMouseListener(this);
		simPanel.addMouseMotionListener(this);
		frame.setVisible(true);
	}
	public void run() {
		
		init();
		
		double nextTime = (double) System.nanoTime() / 1000000000.0;
		do {
			double currTime = (double) System.nanoTime() / 1000000000.0;
			if (currTime >= nextTime) {
				// add the Delta to the time
				nextTime += 1 / 60.0;
				// currently at 60 fps time is frames/seconds
				update();
				if (currTime < nextTime)
				{//draw
					simPanel.repaint();
					compPanel.repaint();
					//simPanel.revalidate();
				}	
			} else {
				int sleepTime = (int) (1000.0 * (nextTime - currTime));
				// sanity check
				if (sleepTime > 0) {
					try {
						Thread.sleep(sleepTime);
					} catch (InterruptedException e) {
					}
				}
			}
		} while (true);
	}

	public void update()
	{	//check the status of the different tabs
		int[] indexArray = new int[2];
		for(MenuTabOrganizer tab : menuTabs)
		{
			//if it is selected and it hasn't reached its max range
			
			if(tab.getSelected() && !tab.isMax())
			{
				tab.moveMenuTab(XTRANS);
			}
			else if(!tab.getSelected() && tab.atBase())
			{
				//do nothing
			}
			else if (!tab.getSelected() && !tab.atBase())
			{
				tab.moveMenuTab(-XTRANS);
			}
				tab.scrollList();
		}
		//update which metal is selected
		for(int i = 0; i < menuTabs.length;i++)
		{
			if(menuTabs[i].getType())
			{
				indexArray[i] = menuTabs[i].getSelectedIndex();
			}
		}
		metalHandler.updateSelectedMetals(indexArray[0], indexArray[1]);
		
		//metalHandler.update();
		
		//check the value of the text fields on each tab
		//make it so that it grabs the mouse position at update for more intuitive scrolling
		
		//check values on the top menu
		
		//check the value of the menu sliders
		
		//update the calculation based on the menu sliders
		
		//perform mass loss calculation
		//update mass labels metalHandler.genericMassUpdate(massHere);
		//update particle positions
		
	}
	public void mouseClicked(MouseEvent e) {
		
	}
	
	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}
	
	public void mousePressed(MouseEvent e) {
		
		for(MenuTabOrganizer mTemp : menuTabs)
		{
			mTemp.updateSelect(mTemp.getPolygon().contains(e.getPoint()));
			if(mTemp.getPolygon().contains(e.getPoint()))
			{
				mouseY = e.getY();
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) 
	{
		for(MenuTabOrganizer mTemp : menuTabs)
		{
			mTemp.updateSelect(mTemp.getPolygon().contains(e.getPoint()));
			mTemp.stopScrolling();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		//get the new y value of the mouse
		//compare it to the last y value of the mouse to determine which way to move the scroll wheel
		for(MenuTabOrganizer mTemp : menuTabs)
		{
			//mTemp.updateSelect(mTemp.getPolygon().contains(e.getPoint()));
			if(mTemp.getPolygon().contains(e.getPoint()))
			{
				mTemp.mouseDragged(e, mouseY);
			}
		}
	}

	
	public void mouseMoved(MouseEvent e) {
	}
	private class SimPanel extends JPanel {
		private SimMain m;
		public SimPanel(SimMain main)
		{
			m = main;
			this.setSize(m.WIDTH, m.HEIGHT);
			this.setLayout(null);
		}
		
		Graphics2D g2d;
		//new MTab(MTXCOORDS, MTYCOORDS, 0, false)
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
	        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g2d.setColor(Color.BLACK);
			//draw all the polygons and then fill them
			for(MenuTabOrganizer mTemp : menuTabs)
			{
				mTemp.draw(g2d);
			}
			containerHandler.draw(g2d);
			metalHandler.draw(g2d);
		}
	}
}