package tool.mvc.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import tool.mvc.model.interfaces.MyModel;

/**
 * 
 * 
 * 
 * @author Tihomir Bicanic
 * 
 */
public class ValueNoiseControl implements ActionListener, ChangeListener
{

	private MyModel model;

	public ValueNoiseControl(MyModel m)
	{

		this.model = m;
	}

	@Override
	public void stateChanged(ChangeEvent arg0)
	{
		JSlider slider = (JSlider) arg0.getSource();
		int value = slider.getValue();
		int sliderName = Integer.parseInt(slider.getName());
		switch (sliderName)
		{
		case 0:
			this.model.setSeed(value);
			break;
		case 1:
			this.model.setNoiseAmpl(value);
			break;
		case 2:
			this.model.setPersistence(value);
			break;
		case 3:
			this.model.setNoiseFrequence(value);
			break;
		case 4:
			this.model.setOctaves(value);
			break;
		}

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
		} else if (name.equals("1"))
		{
			try
			{
				value = Integer.parseInt(stringValue);
			} catch (NumberFormatException e)
			{
				value = this.model.getAmpl();
			}
			this.model.setNoiseAmpl(value);
		} else if (name.equals("2"))
		{
			try
			{
				value = Integer.parseInt(stringValue);
			} catch (NumberFormatException e)
			{
				value = (int) this.model.getPersistence() * 100;
			}
			this.model.setPersistence(value);
		} else if (name.equals("3"))
		{
			try
			{
				value = Integer.parseInt(stringValue);
			} catch (NumberFormatException e)
			{
				value = (int) this.model.getFrequence();
			}
			this.model.setNoiseFrequence(value);
		} else if (name.equals("4"))
		{
			try
			{
				value = Integer.parseInt(stringValue);
			} catch (NumberFormatException e)
			{
				value = (int) this.model.getOctaves();
			}
			this.model.setOctaves(value);
		}

	}
}
