package tool.mvc.view.classes;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import tool.Main;
import tool.heightmap.Heightmap;
import tool.mvc.model.interfaces.MyModel;
import tool.mvc.view.interfaces.MyDrawView;

/**
 * 
 * 
 * 
 * @author Tihomir Bicanic
 * 
 */
public class Show extends JPanel implements MyDrawView
{
	private boolean suc;
	private MyModel model;
	private Image image;
	private int[][] drawMap = new int[0][0];

	public Show(MyModel m, String path)
	{
		this.model = m;
		this.model.addDrawView(this);
		try
		{
			final ImageIcon iReport = new ImageIcon(getClass()
					.getResource(path));
			image = iReport.getImage();
			suc = true;
		} catch (Exception e)
		{
			this.setBackground(Main.BACK);
		}

	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if (suc)
		{
			g.drawImage(image, 0, 0, this.getParent().getWidth(), this
					.getParent().getHeight(), this);
		}

		for (int y = 0; y < drawMap.length; y++)
		{
			for (int x = 0; x < drawMap.length; x++)
			{
				g.setColor(Heightmap.SHADESOFGRAY[drawMap[x][y]]);
				g.drawRect(x + 10, y + 10, 1, 1);
			}
		}
		repaint();

	}

	@Override
	public void draw()
	{
		drawMap = this.model.getMap();
		this.repaint();
	}

}
