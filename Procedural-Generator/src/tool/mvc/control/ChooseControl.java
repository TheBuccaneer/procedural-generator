package tool.mvc.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import tool.heightmap.classes.DiamondSquare;
import tool.heightmap.classes.DiamondSquare2;
import tool.heightmap.classes.FaultAlgorithm;
import tool.heightmap.classes.MidpointDisplacement;
import tool.heightmap.classes.ParticleDeposition;
import tool.heightmap.classes.PerlinNoise;
import tool.heightmap.classes.TriangleSubDevision;
import tool.heightmap.classes.ValueNoise;
import tool.mvc.model.interfaces.MyModel;

/**
 * Control Klasse zur Auswahl der Algorithmen
 * 
 * 
 * @author Tihomir Bicanic
 * 
 */
public class ChooseControl implements ActionListener
{
	private MyModel model;

	public ChooseControl(MyModel m)
	{
		this.model = m;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		@SuppressWarnings("unchecked")
		JComboBox<String> temp = (JComboBox<String>) arg0.getSource();
		String name = (String) temp.getSelectedItem();
		switch (name)
		{
		case "DiamondSquare":
			model.newHeightmap(new DiamondSquare());
			break;
		case "DiamonsSquareAlt.":
			model.newHeightmap(new DiamondSquare2());
			break;
		case "Midpoint Displacement 2D":
			model.newHeightmap(new MidpointDisplacement());
			break;
		case "Triangle-Sub-Dev":
			model.newHeightmap(new TriangleSubDevision());
			break;
		case "Fault Algorithm":
			model.newHeightmap(new FaultAlgorithm());
			break;
		case "Perlin Noise":
			model.newHeightmap(new PerlinNoise());
			break;
		case "Value Noise":
			model.newHeightmap(new ValueNoise());
			break;
		case "Particle Deposison":
			model.newHeightmap(new ParticleDeposition());
		}

	}

}
