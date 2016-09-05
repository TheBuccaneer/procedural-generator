package tool.mvc.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import tool.mvc.model.interfaces.MyModel;

public class PerlinNoiseControl implements ActionListener, ChangeListener
{
	private MyModel model;

	public PerlinNoiseControl(MyModel m)
	{
		this.model = m;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		JTextField textField = (JTextField) arg0.getSource();
		String stringValue = textField.getText();
		String name = textField.getName();

		int value = 0;

		if (name.equals("0"))
		{
			try
			{
				value = Integer.parseInt(stringValue);
			} catch (NumberFormatException e)
			{
				value = this.model.getSeed();
			}
			this.model.setSeed(value);
		} else if (name.equals("2"))
		{
			try
			{
				value = Integer.parseInt(stringValue);
			} catch (NumberFormatException e)
			{
				value = this.model.getOctaves();
			}
			this.model.setOctaves(value);
		}

	}

	@Override
	public void stateChanged(ChangeEvent arg0)
	{
		JSlider slider = (JSlider) arg0.getSource();
		String name = slider.getName();
		int value = slider.getValue();
		if (name.equals("0"))
		{
			model.setSeed(value);
		} else if (name.equals("1"))
		{
			model.setFrequence(value);
		} else if (name.equals("2"))
		{
			model.setOctaves(value);
		}

	}

}
