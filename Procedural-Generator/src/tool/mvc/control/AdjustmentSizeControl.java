package tool.mvc.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JRadioButton;
import tool.mvc.model.interfaces.MyModel;

/**
 * Control Klasse zur Auswahl der Kartengroesse
 * 
 * 
 * @author Tihomir Bicanic
 * 
 */
public class AdjustmentSizeControl implements ActionListener
{

	private MyModel model;

	public AdjustmentSizeControl(MyModel m)
	{
		this.model = m;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		JRadioButton b = (JRadioButton) arg0.getSource();

		switch (b.getActionCommand())
		{
		case "Small":
			if (b.isSelected())
			{
				this.model.setMapSize(512);
			}
			break;
		case "Large":
			if (b.isSelected())
			{
				this.model.setMapSize(1024);
			}
			break;

		}

	}

}
