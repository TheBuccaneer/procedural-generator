package tool.util;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;

import com.jme3.system.AppSettings;
import com.jme3.system.JmeCanvasContext;

import tool.heightmap.TerrainGeneration;
import tool.mvc.model.interfaces.MyModel;

/**
 * 
 * 
 * 
 * @author Tihomir Bicanic
 * 
 */
public class LandscapeShowDialog extends JDialog
{

	private MyModel model;

	public LandscapeShowDialog(MyModel model)
	{
		this.model = model;
		this.init();
		this.initContent();
		this.setVisible(true);
	}

	private void initContent()
	{
		TerrainGeneration terrain = new TerrainGeneration(model);
		AppSettings settings = new AppSettings(true);
		settings.setWidth(this.getWidth());
		settings.setHeight(this.getHeight());

		terrain.setSettings(settings);
		terrain.createCanvas();
		JmeCanvasContext ctx = (JmeCanvasContext) terrain.getContext();
		ctx.setSystemListener(terrain);
		Dimension dim = new Dimension(1024, 768);
		ctx.getCanvas().setPreferredSize(dim);

		this.add(ctx.getCanvas());
	}

	private void init()
	{
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setModal(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(dim.width - 100, dim.height - 100);
		this.setLocationRelativeTo(null);

	}

}
