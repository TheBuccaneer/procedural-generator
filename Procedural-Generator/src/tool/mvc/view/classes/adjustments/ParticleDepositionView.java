package tool.mvc.view.classes.adjustments;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import tool.Main;
import tool.mvc.control.ParticleDepositionControl;
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
public class ParticleDepositionView extends JPanel implements
		MyAdjustmentChange
{

	private MyModel model;

	private JTextField dropsTextField = new JTextField("0");
	private JTextField vulcanosTextField = new JTextField("0");
	private JTextField distanceTextField = new JTextField("0");

	private JSlider dropsSlider = new JSlider(0, 0, 5000, 1);
	private JSlider vulcanosSlider = new JSlider(0, 0, 10000, 1);
	private JSlider distanceSlider = new JSlider(0, 0, 512, 1);

	public ParticleDepositionView(MyModel model)
	{
		this.model = model;
		GridLayout rootLayout = new GridLayout(0, 1);
		this.setLayout(rootLayout);
		this.setBackground((Main.BACK));
		TitledBorder bb = new TitledBorder(Main.ALGONAMES[7]);
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
		ParticleDepositionControl ac = new ParticleDepositionControl(model);

		JPanel temp, tempUp;
		JLabel[] labels = { new JLabel("Drops"), new JLabel("Stones"),
				new JLabel("Distance") };
		JTextField[] fields = { dropsTextField, vulcanosTextField,
				distanceTextField };
		JSlider[] sliders = { dropsSlider, vulcanosSlider, distanceSlider };

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

			this.add(temp);
		}
	}

	public void adjustmentChanged()
	{

		int dropsValue = model.getDrops();
		int stonesValue = model.getVulcanos();
		int distanceValue = model.getDistance();

		this.dropsTextField.setText((String.valueOf(dropsValue)));
		this.vulcanosTextField.setText(String.valueOf(stonesValue));
		this.distanceTextField.setText(String.valueOf(distanceValue));

		this.dropsSlider.setValue(dropsValue);
		this.vulcanosSlider.setValue(stonesValue);
		this.distanceSlider.setValue(distanceValue);

	}
}
