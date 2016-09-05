package tool.mvc.view.classes;

import javax.swing.JLabel;
import javax.swing.JPanel;

import tool.Main;
import tool.heightmap.Heightmap;
import tool.mvc.model.interfaces.MyModel;
import tool.mvc.view.interfaces.MyAlgorithmChange;

/**
 * 
 * 
 * 
 * @author Tihomir Bicanic
 * 
 */
public class Information extends JPanel implements MyAlgorithmChange
{

	private MyModel model;

	public Information(MyModel m)
	{
		this.model = m;
		this.model.addSetupView(this);
		this.setBackground(Main.BACK);

	}

	@Override
	public void algorithmChanged(Heightmap map)
	{

	}

}
