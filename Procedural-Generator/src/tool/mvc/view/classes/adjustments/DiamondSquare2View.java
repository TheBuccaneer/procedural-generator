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
public class DiamondSquare2View extends Displacements
{

	public DiamondSquare2View(MyModel model)
	{
		super(model, Main.ALGONAMES[2]);
		super.init(2);

	}

	@Override
	public void adjustmentChanged()
	{
		super.adjustmentChanged();
	}
}
