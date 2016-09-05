package tool.mvc.view.classes.adjustments;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import tool.Main;
import tool.mvc.control.PerlinNoiseControl;
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
public class PerlinNoiseView extends JPanel implements MyAdjustmentChange
{
	private MyModel model;

	protected JTextField seedTextField = new JTextField("0");
	protected JTextField frequenceTextField = new JTextField("0");
	protected JTextField octavesTextField = new JTextField("0");

	protected JSlider seedSlider = new JSlider(0, 0, 1000, 1);
	protected JSlider frequenceSlider = new JSlider(0, 1, 7, 1);
	protected JSlider octavesSlider = new JSlider(0, 1, 16, 1);

	private JRadioButton linearButton = new JRadioButton();
	private JRadioButton cosinButton = new JRadioButton();

	public PerlinNoiseView(MyModel m)
	{
		this.model = m;
		GridLayout rootLayout = new GridLayout(0, 1);
		this.setLayout(rootLayout);
		this.setBackground((Main.BACK));
		TitledBorder bb = new TitledBorder("Perlin Noise");
		bb.setTitleColor(Adjustment.BORDERTITLECOLOR);
		this.setBorder(bb);
		this.init();

	}

	private void init()
	{
		this.add(new SizePanel(model));
		this.initAdjustmentPanel();
		this.interpolationPanel();
		this.add(new BlurPanel(model));

	}

	private void initAdjustmentPanel()
	{
		PerlinNoiseControl nac = new PerlinNoiseControl(model);
		JPanel temp, tempUp;
		JLabel[] labels = { new JLabel("Seed"), new JLabel("Frequence"),
				new JLabel("Octaves") };
		JTextField[] fields = { seedTextField, frequenceTextField,
				octavesTextField };
		JSlider[] sliders = { seedSlider, frequenceSlider, octavesSlider };

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
			fields[i].addActionListener(nac);
			sliders[i].addChangeListener(nac);

			if (i == 1)
			{
				fields[i].setEditable(false);
			}

			this.add(temp);
		}
	}

	private void interpolationPanel()
	{
		JPanel panel = new JPanel(new GridLayout(3, 2));
		panel.setBackground(Main.BACK);
		panel.setForeground(Main.FRONT);
		ButtonGroup bg = new ButtonGroup();
		JLabel[] labels = { new JLabel("Linear"), new JLabel("Cosin") };
		JRadioButton[] rButtons = { linearButton, cosinButton };

		for (int i = 0; i < labels.length; i++)
		{
			panel.add(labels[i]);
			panel.add(rButtons[i]);
			labels[i].setForeground(Main.FRONT);
			rButtons[i].setBackground(Main.BACK);
			bg.add(rButtons[i]);
			rButtons[i].setName(String.valueOf(i));

			if (i == 0)
			{
				rButtons[i].setSelected(true);
			}

			rButtons[i].addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					JRadioButton b = (JRadioButton) arg0.getSource();
					String name = b.getName();
					if (name.equals("0"))
					{
						model.setLinear(true);
					} else if (name.equals("1"))
					{
						model.setCosin(true);
					}
				}
			});
		}

		this.add(panel);

	}

	public void adjustmentChanged()
	{
		int seedValue = model.getSeed();
		int frequenceValue = model.getFrequence();
		int octavesValue = model.getOctaves();

		seedTextField.setText(String.valueOf(seedValue));
		frequenceTextField.setText(String.valueOf(frequenceValue));
		octavesTextField.setText(String.valueOf(octavesValue));

		seedSlider.setValue(seedValue);
		frequenceSlider
				.setValue((int) (Math.log(frequenceValue) / Math.log(2)));
		octavesSlider.setValue(octavesValue);

		linearButton.setSelected(model.isLinear());
		cosinButton.setSelected(model.isCosin());
	}

}
