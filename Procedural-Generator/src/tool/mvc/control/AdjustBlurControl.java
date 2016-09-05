package tool.mvc.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JSpinner;
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
public class AdjustBlurControl implements ActionListener, ChangeListener
{

	private MyModel model;
	private JSpinner spinner;

	public AdjustBlurControl(MyModel model, JSpinner spinner)
	{
		this.model = model;
		this.spinner = spinner;
		this.spinner.setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		JCheckBox b = (JCheckBox) arg0.getSource();

		if (b.isSelected())
		{
			spinner.setEnabled(true);
			model.setBlur(true);
		} else
		{
			spinner.setEnabled(false);
			model.setBlur(false);
		}
	}

	@Override
	public void stateChanged(ChangeEvent arg0)
	{

		model.setBlurIteration((int) spinner.getValue());
	}

}
