package selectors;

import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class MassSelector {
	
	private JLabel label;
	private JTextField field;
	public MassSelector( )
	{
		
	}
	
	public JLabel createLabel(Rectangle Bounds)
	{
		label = new JLabel("Mass: ");
		label.setBounds(Bounds);
		label.setOpaque(true);
		label.setVisible(true);
		return label;
	} 
	
	public JTextField createField(Rectangle Bounds)
	{
		field = new JTextField();
		field.setBounds(Bounds);
		field.setText("10.00");
		field.setOpaque(true);
		field.setVisible(true);
		return field;
	}
	
	public void move(int distance)
	{
		field.setLocation(field.getX() + distance, field.getY());
		label.setLocation(label.getX() + distance, field.getY());
	}
	
}