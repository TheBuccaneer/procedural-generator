package tool.mvc.view.classes.adjustments;

import tool.Main;
import tool.mvc.model.interfaces.MyModel;

/**
 * 
 * 
 * 
 * @author Tihomir Bicanic
 * 
 */
public class DiamondSquareView extends Displacements
{

	public DiamondSquareView(MyModel model)
	{
		super(model, Main.ALGONAMES[1]);
		super.init(-1);

	}

	@Override
	public void adjustmentChanged()
	{
		super.adjustmentChanged();
	}
}
