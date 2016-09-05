package tool.mvc.view.classes.adjustments.utilities;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

import tool.Main;
import tool.mvc.control.AdjustBlurControl;
import tool.mvc.model.interfaces.MyModel;

/**
 * 
 * 
 * 
 * @author Tihomir Bicanic
 * 
 */
public class BlurPanel extends JPanel
{

	private MyModel model;

	public BlurPanel(MyModel m)
	{
		this.model = m;
		this.init();
	}

	private void init()
	{
		this.setLayout(new GridLayout(2, 2));
		TitledBorder titled = new TitledBorder("Blur Filter");
		titled.setTitleColor(Main.FRONT);
		this.setBorder(titled);
		JCheckBox jcb = new JCheckBox();
		JLabel label = new JLabel("Numb. of. it");
		JSpinner spinner = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1));
		this.add(new JLabel("activate?"));
		this.add(jcb);
		this.add(label);
		this.add(spinner);

		AdjustBlurControl blur = new AdjustBlurControl(model, spinner);
		jcb.addActionListener(blur);
		spinner.addChangeListener(blur);

		this.setBackground(Main.BACK);
		spinner.setBackground(Main.BACK);
		jcb.setBackground(Main.BACK);
	}

}
