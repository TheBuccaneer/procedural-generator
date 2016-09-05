package tool.mvc.view.classes.adjustments.utilities;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import tool.Main;
import tool.mvc.control.AdjustmentSizeControl;
import tool.mvc.model.interfaces.MyModel;

/**
 * 
 * 
 * 
 * @author Tihomir Bicanic
 * 
 */
public class SizePanel extends JPanel
{
	private MyModel model;

	public SizePanel(MyModel m)
	{
		this.model = m;
		this.init();
	}

	private void init()
	{
		this.setLayout(new GridLayout(1, 2));
		JRadioButton rb = null;
		ButtonGroup bg = new ButtonGroup();
		String[] sizes = { "Small", "Large" };

		for (int i = 0; i < sizes.length; i++)
		{
			rb = new JRadioButton(sizes[i]);
			rb.addActionListener(new AdjustmentSizeControl(model));
			rb.setBackground(Main.BACK);
			rb.setForeground(Main.FRONT);
			bg.add(rb);
			this.add(rb);
			if (i == 0)
			{
				rb.setSelected(true);

			}
		}
	}

}
