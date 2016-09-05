package tool.mvc.view.classes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import tool.Main;
import tool.heightmap.Heightmap;
import tool.heightmap.classes.DiamondSquare;
import tool.heightmap.classes.DiamondSquare2;
import tool.heightmap.classes.EmptyClass;
import tool.heightmap.classes.FaultAlgorithm;
import tool.heightmap.classes.MidpointDisplacement;
import tool.heightmap.classes.ParticleDeposition;
import tool.heightmap.classes.PerlinNoise;
import tool.heightmap.classes.TriangleSubDevision;
import tool.heightmap.classes.ValueNoise;
import tool.mvc.control.AdjustButtonControl;
import tool.mvc.model.interfaces.MyModel;
import tool.mvc.view.classes.adjustments.DiamondSquare2View;
import tool.mvc.view.classes.adjustments.DiamondSquareView;
import tool.mvc.view.classes.adjustments.FaultAlgorithmView;
import tool.mvc.view.classes.adjustments.MidpointDisplacementView;
import tool.mvc.view.classes.adjustments.ParticleDepositionView;
import tool.mvc.view.classes.adjustments.PerlinNoiseView;
import tool.mvc.view.classes.adjustments.TriangleSubView;
import tool.mvc.view.classes.adjustments.ValueNoiseView;
import tool.mvc.view.interfaces.MyAlgorithmChange;

/**
 * 
 * 
 * 
 * @author Tihomir Bicanic
 * 
 */
public class Adjustment extends JPanel implements MyAlgorithmChange
{

	private MyModel model;
	private boolean enabledHeightButton;
	private boolean enabledLandButton;
	public static Color BORDERTITLECOLOR = new Color(200, 200, 200);

	public Adjustment(MyModel m)
	{
		super(new BorderLayout());
		this.model = m;
		this.model.addSetupView(this);
		this.setBackground(Main.BACK);
	}

	@Override
	public void algorithmChanged(Heightmap map)
	{
		this.removeAll();
		this.enabledHeightButton = true;
		this.enabledLandButton = false;
		if (map instanceof MidpointDisplacement)
		{
			MidpointDisplacementView v = new MidpointDisplacementView(model);
			this.add(v, BorderLayout.NORTH);
			model.setAdjustmentChange(v);
			v.adjustmentChanged();

		} else if (map instanceof DiamondSquare)
		{
			DiamondSquareView v = new DiamondSquareView(model);
			this.add(v, BorderLayout.NORTH);
			model.setAdjustmentChange(v);
			v.adjustmentChanged();
		} else if (map instanceof DiamondSquare2)
		{
			DiamondSquare2View v = new DiamondSquare2View(model);
			this.add(v, BorderLayout.NORTH);
			model.setAdjustmentChange(v);
			v.adjustmentChanged();
		} else if (map instanceof FaultAlgorithm)
		{
			FaultAlgorithmView v = new FaultAlgorithmView(model);
			this.add(v, BorderLayout.NORTH);
			model.setAdjustmentChange(v);
			v.adjustmentChanged();
		} else if (map instanceof TriangleSubDevision)
		{
			TriangleSubView v = new TriangleSubView(model);
			this.add(v, BorderLayout.NORTH);
			model.setAdjustmentChange(v);
			v.adjustmentChanged();
		} else if (map instanceof PerlinNoise)
		{
			PerlinNoiseView v = new PerlinNoiseView(model);
			this.add(v, BorderLayout.CENTER);
			model.setAdjustmentChange(v);
			v.adjustmentChanged();
		} else if (map instanceof ValueNoise)
		{
			ValueNoiseView v = new ValueNoiseView(model);
			this.add(v, BorderLayout.CENTER);
			model.setAdjustmentChange(v);
			v.adjustmentChanged();
		} else if (map instanceof EmptyClass)
		{
			this.enabledHeightButton = false;
			this.enabledLandButton = true;
		} else if (map instanceof ParticleDeposition)
		{
			ParticleDepositionView v = new ParticleDepositionView(model);
			this.add(v, BorderLayout.CENTER);
			model.setAdjustmentChange(v);
			v.adjustmentChanged();
		}
		this.initButtons();
		this.revalidate();
	}

	private void initButtons()
	{

		JPanel buttons = new JPanel(new GridLayout(2, 1));
		JButton buttonHeight = new JButton("create Heightmap");
		JButton buttonLand = new JButton("create Landscape");
		AdjustButtonControl c = new AdjustButtonControl(model, buttonLand);
		buttonHeight.setEnabled(enabledHeightButton);
		buttonLand.setEnabled(enabledLandButton);
		buttonLand.addActionListener(c);
		buttonHeight.addActionListener(c);
		buttons.add(buttonHeight);
		buttons.add(buttonLand);
		buttons.setBackground(Main.BACK);

		this.add(buttons, BorderLayout.SOUTH);

	}
}
