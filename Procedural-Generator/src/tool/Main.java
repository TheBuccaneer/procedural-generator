package tool;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import tool.mvc.model.classes.ModelImplem;
import tool.mvc.model.interfaces.MyModel;
import tool.mvc.view.classes.Adjustment;
import tool.mvc.view.classes.Choose;
import tool.mvc.view.classes.Information;
import tool.mvc.view.classes.Show;
import tool.util.MyMenuBar;

/**
 * 
 * 
 * Startpunkt für Anwendung.
 * 
 * @author Tihomir Bicanic
 * 
 */
public class Main extends JFrame
{

	public final static String[] ALGONAMES = { "Midpoint Displacement 2D",
			"DiamondSquare", "DiamonsSquareAlt.", "Triangle-Sub-Dev",
			"Fault Algorithm", "Perlin Noise", "Value Noise",
			"Particle Deposison" };
	public final static Color BACK = new Color(23, 23, 23);
	public final static Color FRONT = new Color(250, 250, 250);

	public Main()
	{
		super("Tool & Plug-In");
		MyModel model = new ModelImplem();
		final String path = "wall.jpg";
		this.initSettings();
		this.initViews(model, path);
		this.setJMenuBar(new MyMenuBar(this, model));
		this.setVisible(true);
	}

	private void initViews(MyModel m, String path)
	{
		TitledBorder titled = null;

		JPanel root = new JPanel();
		JPanel setup = new JPanel();
		JPanel upper = new JPanel();

		setup.setBackground(BACK);

		Show show = new Show(m, path);
		Choose choose = new Choose(m);
		Adjustment adjustment = new Adjustment(m);
		Information information = new Information(m);

		titled = new TitledBorder("Chooser");
		titled.setTitleColor(FRONT);
		choose.setBorder(titled);

		titled = new TitledBorder("Adjustment");
		titled.setTitleColor(FRONT);
		adjustment.setBorder(titled);

		titled = new TitledBorder("Information");
		titled.setTitleColor(FRONT);
		information.setBorder(titled);

		root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
		setup.setLayout(new BorderLayout());
		upper.setLayout(new BorderLayout());

		information.setPreferredSize(new Dimension(100, 100));
		upper.setPreferredSize(new Dimension(200, 100));
		setup.add(choose, BorderLayout.NORTH);
		setup.add(adjustment, BorderLayout.CENTER);
		upper.add(setup, BorderLayout.WEST);
		upper.add(show, BorderLayout.CENTER);
		root.add(upper);
		root.add(information);

		this.add(root);
	}

	private void initSettings()
	{
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setMinimumSize(new Dimension(800, 600));
		this.setSize(dim.height, dim.height - 100);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}

	public static void main(String[] args)
	{
		new Main();

	}

}
