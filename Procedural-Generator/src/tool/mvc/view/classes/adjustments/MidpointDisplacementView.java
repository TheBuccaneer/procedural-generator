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
public class MidpointDisplacementView extends Displacements
{

	public MidpointDisplacementView(MyModel model)
	{
		super(model, Main.ALGONAMES[0]);
		super.init(-1);

	}

	@Override
	public void adjustmentChanged()
	{
		super.adjustmentChanged();
	}
}
