package tool.mvc.control;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import tool.mvc.model.interfaces.MyModel;
import tool.util.LandscapeShowDialog;
/**
 * 
 * Control Klasse der beiden Heightmap und Landscape Buttons
 * 
 * @author Tihomir Bicanic
 * 
 */
public class AdjustButtonControl implements ActionListener
{

	private MyModel model;
	private JButton landButton;

	public AdjustButtonControl(MyModel m, JButton landButton)
	{
		this.model = m;
		this.landButton = landButton;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		final String s = arg0.getActionCommand();

		new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				if (s.equals("create Heightmap"))
				{
					model.generate();
					EventQueue.invokeLater(new Runnable()
					{

						@Override
						public void run()
						{
							landButton.setEnabled(true);

						}
					});
				} else if (s.equals("create Landscape"))
				{
					new LandscapeShowDialog(model);
				}

			}
		}).start();

	}
}
