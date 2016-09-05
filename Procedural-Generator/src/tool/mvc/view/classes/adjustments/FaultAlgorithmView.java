package tool.mvc.view.classes.adjustments;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import tool.Main;
import tool.mvc.control.FaultAlgorithmControl;
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
public class FaultAlgorithmView extends JPanel implements MyAdjustmentChange
{

	private MyModel model;

	private JTextField increaseText = new JTextField();
	private JTextField decreaseText = new JTextField();
	private JTextField iterationText = new JTextField();
	private JTextField seedText = new JTextField();

	private JSlider seedSlider = new JSlider(0, 0, 1000, 1);
	private JSlider increaseSlider = new JSlider(0, 1, 10, 1);
	private JSlider decreaseSlider = new JSlider(0, 0, 10, 1);
	private JSlider iterationSlider = new JSlider(0, 1, 512, 1);

	public FaultAlgorithmView(MyModel model)
	{
		this.model = model;

		GridLayout rootLayout = new GridLayout(0, 1);
		rootLayout.setVgap(5);
		this.setLayout(rootLayout);
		this.setBackground((Main.BACK));
		TitledBorder bb = new TitledBorder(Main.ALGONAMES[4]);
		bb.setTitleColor(Adjustment.BORDERTITLECOLOR);
		this.setBorder(bb);

		this.init();
	}

	private void init()
	{
		this.add(new SizePanel(model));
		this.initAdjustment();
		this.add(new BlurPanel(model));
	}

	private void initAdjustment()
	{
		JPanel temp, tempUp;

		JLabel[] labels = { new JLabel("Seed"), new JLabel("Increase"),
				new JLabel("Decrease"), new JLabel("Iterations") };
		JTextField[] fields = { seedText, increaseText, decreaseText,
				iterationText };
		JSlider[] sliders = { seedSlider, increaseSlider, decreaseSlider,
				iterationSlider };

		FaultAlgorithmControl fac = new FaultAlgorithmControl(model);

		for (int i = 0; i < labels.length; i++)
		{
			temp = new JPanel(new BorderLayout());
			tempUp = new JPanel(new GridLayout(1, 2));

			tempUp.add(labels[i]);
			tempUp.add(fields[i]);
			tempUp.setBackground(Main.BACK);

			temp.add(tempUp, BorderLayout.NORTH);
			temp.add(sliders[i], BorderLayout.CENTER);

			labels[i].setBackground(Main.BACK);
			sliders[i].setBackground(Main.BACK);
			fields[i].setBackground(Main.BACK);

			labels[i].setForeground(Main.FRONT);
			sliders[i].setForeground(Main.FRONT);
			fields[i].setForeground(Main.FRONT);

			fields[i].setName(String.valueOf(i));
			sliders[i].setName(String.valueOf(i));

			fields[i].addActionListener(fac);
			sliders[i].addChangeListener(fac);

			this.add(temp);
		}

	}

	@Override
	public void adjustmentChanged()
	{
		int decreaseValue = model.getDecrease();
		int increaseValue = model.getIncrease();
		int faultIterationValue = model.getFaultIteration();
		int seedValue = model.getSeed();

		decreaseText.setText(String.valueOf(decreaseValue));
		increaseText.setText(String.valueOf(increaseValue));

		decreaseSlider.setValue(decreaseValue);
		increaseSlider.setValue(increaseValue);

		iterationText.setText(String.valueOf(faultIterationValue));
		iterationSlider.setValue(faultIterationValue);

		seedText.setText(String.valueOf(seedValue));
		seedSlider.setValue(seedValue);
	}
}
