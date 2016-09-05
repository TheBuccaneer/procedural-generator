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
public class FaultAlgorithmControl implements ActionListener, ChangeListener
{

	private MyModel model;

	public FaultAlgorithmControl(MyModel m)
	{
		this.model = m;
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
			model.setIncrease(value);
		} else if (name.equals("2"))
		{
			model.setDecrease(value);
		} else if (name.equals("3"))
		{
			model.setFaultIteration(value);
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		JTextField tf = (JTextField) arg0.getSource();
		String name = tf.getName();
		int value = 0;
		if (name.equals("0"))
		{
			try
			{
				value = Integer.parseInt(tf.getText());
			} catch (NumberFormatException e)
			{
				value = model.getSeed();
			}
			model.setSeed(value);
		} else if (name.equals("1"))
		{
			try
			{
				value = Integer.parseInt(tf.getText());
			} catch (NumberFormatException e)
			{
				value = model.getIncrease();
			}
			model.setIncrease(value);
		} else if (name.equals("2"))
		{
			try
			{
				value = Integer.parseInt(tf.getText());
			} catch (NumberFormatException e)
			{
				value = model.getDecrease();
			}
			model.setDecrease(value);
		} else if (name.equals("3"))
		{
			try
			{
				value = Integer.parseInt(tf.getText());
			} catch (NumberFormatException e)
			{
				value = model.getFaultIteration();
			}
			model.setFaultIteration(value);
		}

	}
}
