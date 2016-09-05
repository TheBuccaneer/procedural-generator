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
public class TriangleSubView extends Displacements
{
	public TriangleSubView(MyModel model)
	{
		super(model, Main.ALGONAMES[3]);
		super.init(-1);

	}

	@Override
	public void adjustmentChanged()
	{
		super.adjustmentChanged();
	}
}
