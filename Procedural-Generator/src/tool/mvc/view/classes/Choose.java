package tool.mvc.view.classes;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import tool.Main;
import tool.mvc.control.ChooseControl;
import tool.mvc.model.interfaces.MyModel;

/**
 * 
 * 
 * 
 * @author Tihomir Bicanic
 * 
 */
public class Choose extends JPanel
{
	private MyModel model;
	private JComboBox<String> box;

	public Choose(MyModel m)
	{
		this.model = m;
		this.initBox();
		this.setBackground(new Color(20, 20, 20));

	}

	private void initBox()
	{
		box = new JComboBox<String>(Main.ALGONAMES);
		box.setSelectedItem(null);
		box.addActionListener(new ChooseControl(model));
		this.add(box);
	}

	public void setNull()
	{
		// box.setSelectedIndex(-1);
	}

}
