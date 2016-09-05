package tool.mvc.view.classes.adjustments;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import tool.Main;
import tool.mvc.control.DisplacementAdjustmentControl;
import tool.mvc.model.interfaces.MyModel;
import tool.mvc.view.classes.Adjustment;
import tool.mvc.view.classes.adjustments.utilities.BlurPanel;
import tool.mvc.view.classes.adjustments.utilities.SizePanel;
import tool.mvc.view.interfaces.MyAdjustmentChange;

/**
 * 
 * 
 * 
 * @author Tihomir Bicanic
 * 
 */
public abstract class Displacements extends JPanel implements
		MyAdjustmentChange
{
	private MyModel model;

	protected JTextField seedTextField = new JTextField("0");
	protected JTextField roughTextField = new JTextField("0");
	protected JTextField amplTextField = new JTextField("0");
	protected JTextField persTextField = new JTextField("0");

	protected JSlider seedSlider = new JSlider(0, 0, 1000, 1);
	protected JSlider roughSlider = new JSlider(0, 0, 100, 1);
	protected JSlider amplSlider = new JSlider(0, 1, 128, 1);
	protected JSlider persSlider = new JSlider(0, 0, 100, 1);

	public Displacements(MyModel m, String name)
	{
		this.model = m;
		GridLayout rootLayout = new GridLayout(0, 1);
		this.setLayout(rootLayout);
		this.setBackground((Main.BACK));
		TitledBorder bb = new TitledBorder(name);
		bb.setTitleColor(Adjustment.BORDERTITLECOLOR);
		this.setBorder(bb);

	}

	protected void init(int deactivateIndex)
	{
		this.add(new SizePanel(model));
		this.initAdjustmentPanel(deactivateIndex);
		this.add(new BlurPanel(model));
	}

	private void initAdjustmentPanel(int deactivateIndex)
	{
		DisplacementAdjustmentControl ac = new DisplacementAdjustmentControl(
				model);

		JPanel temp, tempUp;
		JLabel[] labels = { new JLabel("Seed"), new JLabel("Roughness"),
				new JLabel("Amplitude"), new JLabel("Persistence") };
		JTextField[] fields = { seedTextField, roughTextField, amplTextField,
				persTextField };
		JSlider[] sliders = { seedSlider, roughSlider, amplSlider, persSlider };

		for (int i = 0; i < labels.length; i++)
		{
			temp = new JPanel(new BorderLayout());
			tempUp = new JPanel(new GridLayout(1, 2));
			tempUp.add(labels[i]);
			tempUp.add(fields[i]);
			temp.add(tempUp, BorderLayout.NORTH);
			temp.add(sliders[i], BorderLayout.CENTER);
			tempUp.setBackground(Main.BACK);
			labels[i].setForeground(Main.FRONT);
			fields[i].setBackground(Main.BACK);
			fields[i].setForeground(Main.FRONT);
			sliders[i].setBackground(Main.BACK);
			sliders[i].setForeground(Main.FRONT);
			sliders[i].setName(String.valueOf(i));
			fields[i].setName(String.valueOf(i));
			fields[i].addActionListener(ac);
			sliders[i].addChangeListener(ac);

			if (deactivateIndex == i)
			{
				labels[i].setEnabled(false);
				fields[i].setEnabled(false);
				sliders[i].setEnabled(false);
			}
			this.add(temp);
		}
	}

	public void adjustmentChanged()
	{

		int seedValue = model.getSeed();
		float roughValue = model.getRough();
		int amplitudeValue = model.getAmpl();
		float persValue = model.getPersistence();

		this.seedTextField.setText((String.valueOf(seedValue)));
		this.roughTextField.setText(String.valueOf(roughValue));
		this.amplTextField.setText(String.valueOf(amplitudeValue));
		this.persTextField.setText(String.valueOf(persValue));

		this.seedSlider.setValue(seedValue);
		this.roughSlider.setValue((int) (100 * roughValue));
		this.persSlider.setValue((int) (100 * persValue));
		this.amplSlider.setValue(amplitudeValue);

	}
}
