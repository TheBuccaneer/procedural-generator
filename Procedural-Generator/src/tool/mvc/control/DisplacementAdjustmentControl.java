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
 * Control Klasse aller von tool.mvc.view.classes.adjustments.Displacements
 * abgeleiteten Klassen
 * 
 * @author Tihomir Bicanic
 * 
 */
public class DisplacementAdjustmentControl implements ActionListener,
		ChangeListener
{

	private MyModel model;
	private JSlider slider;
	private JTextField textField;

	public DisplacementAdjustmentControl(MyModel m)
	{
		this.model = m;
	}

	@Override
	public void stateChanged(ChangeEvent arg0)
	{

		slider = (JSlider) arg0.getSource();
		switch (slider.getName())
		{
		case "0":
			model.setSeed(slider.getValue());
			break;
		case "1":
			model.setRough(slider.getValue());
			break;
		case "2":
			model.setAmpl(slider.getValue());
			break;
		case "3":
			model.setPersistence(slider.getValue());
			break;
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		textField = (JTextField) arg0.getSource();
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
				value = this.model.getAmpl();
			}
			this.model.setAmpl(value);
		} else if (name.equals("1"))
		{
			try
			{
				value = (int) (100 * Float.parseFloat(stringValue));
			} catch (NumberFormatException e)
			{
				value = (int) (100 * this.model.getRough());
			}
			this.model.setRough(value);
		} else if (name.equals("3"))
		{
			try
			{
				value = (int) (100 * Float.parseFloat(stringValue));
			} catch (NumberFormatException e)
			{
				value = (int) (100 * this.model.getPersistence());
			}
			this.model.setPersistence(value);
		}
	}
}
