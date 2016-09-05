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
import tool.mvc.control.ValueNoiseControl;
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
public class ValueNoiseView extends JPanel implements MyAdjustmentChange
{
	private MyModel model;

	protected JTextField seedTextField = new JTextField("0");
	protected JTextField frequenceTextField = new JTextField("0");
	protected JTextField octavesTextField = new JTextField("0");
	protected JTextField amplTextField = new JTextField("0");
	protected JTextField persTextField = new JTextField("0");

	protected JSlider seedSlider = new JSlider(0, 0, 1000, 1);
	protected JSlider frequenceSlider = new JSlider(0, 1, 512, 1);
	protected JSlider octavesSlider = new JSlider(0, 1, 16, 1);
	protected JSlider amplSlider = new JSlider(0, 1, 512, 1);
	protected JSlider persSlider = new JSlider(0, 0, 100, 1);

	private JRadioButton linearButton = new JRadioButton();
	private JRadioButton cosinButton = new JRadioButton();
	private JRadioButton cubicButton = new JRadioButton();

	public ValueNoiseView(MyModel m)
	{
		this.model = m;
		GridLayout rootLayout = new GridLayout(0, 1);
		this.setLayout(rootLayout);
		this.setBackground((Main.BACK));
		TitledBorder bb = new TitledBorder("Value Noise");
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
		ValueNoiseControl vnc = new ValueNoiseControl(model);
		JPanel temp, tempUp;

		JLabel[] labels = { new JLabel("Seed"), new JLabel("Amplitude"),
				new JLabel("Persistence"), new JLabel("Frequence"),
				new JLabel("Octaves") };

		JTextField[] fields = { seedTextField, amplTextField, persTextField,
				frequenceTextField, octavesTextField };
		JSlider[] sliders = { seedSlider, amplSlider, persSlider,
				frequenceSlider, octavesSlider };

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
			fields[i].addActionListener(vnc);
			sliders[i].addChangeListener(vnc);

			this.add(temp);
		}
	}

	private void interpolationPanel()
	{
		JPanel panel = new JPanel(new GridLayout(3, 2));
		panel.setBackground(Main.BACK);
		panel.setForeground(Main.FRONT);
		ButtonGroup bg = new ButtonGroup();
		JLabel[] labels = { new JLabel("Linear"), new JLabel("Cosin"),
				new JLabel("Cubic") };
		JRadioButton[] rButtons = { linearButton, cosinButton, cubicButton };

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
					} else if (name.equals("2"))
					{
						model.setCubic(true);
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
		int amplitudeValue = model.getAmpl();
		float persistenceValue = model.getPersistence();

		seedTextField.setText(String.valueOf(seedValue));
		amplTextField.setText(String.valueOf(amplitudeValue));
		persTextField.setText(String.valueOf(persistenceValue));
		frequenceTextField.setText(String.valueOf(frequenceValue));
		octavesTextField.setText(String.valueOf(octavesValue));

		seedSlider.setValue(seedValue);
		frequenceSlider.setValue(frequenceValue);
		octavesSlider.setValue(octavesValue);
		amplSlider.setValue(amplitudeValue);
		persSlider.setValue((int) (persistenceValue * 100));

		linearButton.setSelected(model.isLinear());
		cosinButton.setSelected(model.isCosin());
		cubicButton.setSelected(model.isCubic());
	}

}
