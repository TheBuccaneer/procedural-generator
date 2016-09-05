package tool.mvc.model.classes;

import java.util.ArrayList;

import tool.heightmap.Heightmap;
import tool.mvc.model.interfaces.MyModel;
import tool.mvc.view.interfaces.MyAdjustmentChange;
import tool.mvc.view.interfaces.MyAlgorithmChange;
import tool.mvc.view.interfaces.MyDrawView;

/**
 * 
 * 
 * Modelklasse. Kommuniziert mit Heightmaps und leitet Daten an Views weiter
 * 
 * @author Tihomir Bicanic
 * 
 */
public class ModelImplem implements MyModel
{
	private Heightmap map;
	private MyAdjustmentChange adjustChange;
	private ArrayList<MyAlgorithmChange> setupViews = new ArrayList<MyAlgorithmChange>();
	private ArrayList<MyDrawView> drawViews = new ArrayList<MyDrawView>();

	@Override
	public void addSetupView(MyAlgorithmChange v)
	{
		setupViews.add(v);
	}

	@Override
	public void addDrawView(MyDrawView v)
	{
		drawViews.add(v);
	}

	private void algoChange()
	{
		for (MyAlgorithmChange v : setupViews)
		{
			v.algorithmChanged(map);
		}
	}

	private void adjustChange()
	{
		adjustChange.adjustmentChanged();

	}

	public void setAdjustmentChange(MyAdjustmentChange a)
	{
		this.adjustChange = a;
	}

	private void drawChange()
	{
		for (MyDrawView v : drawViews)
		{
			v.draw();
		}
	}

	@Override
	public void newHeightmap(Heightmap hMap)
	{
		this.map = hMap;
		this.algoChange();
	}

	@Override
	public int getSeed()
	{
		return this.map.getSeed();
	}

	@Override
	public void setSeed(int seed)
	{
		if (seed < 0)
		{
			seed = 0;
		} else if (seed > 1000)
		{
			seed = 1000;
		}
		this.map.setSeed(seed);
		this.adjustChange();
	}

	@Override
	public void setRough(int value)
	{
		float f = value / 100f;
		this.map.setRough(f);
		this.adjustChange();
	}

	@Override
	public float getRough()
	{
		return this.map.getRough();
	}

	@Override
	public void setAmpl(int value)
	{
		this.map.setAmpl(value);
		this.adjustChange();
	}

	public int getAmpl()
	{
		return this.map.getAmpl();
	}

	@Override
	public void setPersistence(int value)
	{
		float f = value / 100.0f;

		if (f > 1.0)
			f = 1.0f;
		else if (f < 0.1)
			f = 0.1f;
		this.map.setPersistence(f);
		this.adjustChange();

	}

	@Override
	public float getPersistence()
	{
		return this.map.getPersistence();
	}

	@Override
	public int getMapSize()
	{
		return this.map.getMapSize();
	}

	public void setMapSize(int len)
	{
		this.map.setNewMap(len);
		this.drawChange();
	}

	@Override
	public int[][] getMap()
	{
		return this.map.getMap();
	}

	@Override
	public void generate()
	{
		this.map.generate();
		this.drawChange();
		this.adjustChange();

	}

	@Override
	public void setBlur(boolean b)
	{
		map.setBlur(b);

	}

	@Override
	public void setBlurIteration(int value)
	{
		map.setBlurIteration(value);
	}

	@Override
	public void setDecrease(int value)
	{
		if (value < 0)
		{
			value = 0;
		} else if (value > 10)
		{
			value = 10;
		}
		this.map.setDecrease(value);
		this.adjustChange();
	}

	@Override
	public void setIncrease(int value)
	{
		if (value < 0)
		{
			value = 0;
		} else if (value > 10)
		{
			value = 10;
		}
		this.map.setIncrease(value);
		this.adjustChange();
	}

	@Override
	public int getDecrease()
	{
		return this.map.getDecrease();
	}

	@Override
	public int getIncrease()
	{
		return this.map.getIncrease();
	}

	@Override
	public int getFaultIteration()
	{
		return this.map.getFaultIteration();
	}

	@Override
	public void setFaultIteration(int value)
	{
		if (value < 1)
		{
			value = 1;
		} else if (value > 512)
		{
			value = 512;
		}
		this.map.setFaultIteration(value);
		this.adjustChange();
	}

	@Override
	public int getFrequence()
	{
		return this.map.getFrequence();
	}

	@Override
	public void setFrequence(int frequence)
	{
		frequence = (int) Math.pow(2, frequence);
		if (frequence < 4)
		{
			frequence = 4;
		}
		this.map.setFrequence(frequence);
		this.adjustChange();
	}

	public void setNoiseFrequence(int frequence)
	{
		if (frequence < 2)
		{
			frequence = 2;
		} else if (frequence > 512)
		{
			frequence = 512;
		}
		this.map.setFrequence(frequence);
		this.adjustChange();
	}

	@Override
	public int getOctaves()
	{
		return this.map.getOctaves();
	}

	@Override
	public void setOctaves(int octaves)
	{
		this.map.setOctaves(octaves);
		this.adjustChange();
	}

	@Override
	public boolean isCosin()
	{
		return this.map.isCosin();
	}

	@Override
	public void setCosin(boolean cosin)
	{
		this.map.setLinear(false);
		this.map.setCosin(true);
		this.map.setCubic(false);
		this.adjustChange();
	}

	@Override
	public boolean isCubic()
	{
		return this.map.isCubic();
	}

	@Override
	public void setCubic(boolean cubic)
	{
		this.map.setLinear(false);
		this.map.setCosin(false);
		this.map.setCubic(true);
		this.adjustChange();

	}

	@Override
	public boolean isLinear()
	{
		return this.map.isLinear();
	}

	@Override
	public void setLinear(boolean linear)
	{
		this.map.setLinear(true);
		this.map.setCosin(false);
		this.map.setCubic(false);
		this.adjustChange();

	}

	public void setNoiseAmpl(int value)
	{
		this.map.setValueNoiseAmpl(value);
		this.adjustChange();
	}

	@Override
	public void setHeightMap(float[][] newMap)
	{
		this.map.setHeightMap(newMap);
		this.drawChange();
	}

	public int getVulcanos()
	{
		return this.map.getVulcanos();
	}

	public void setVulcanos(int stones)
	{
		if (stones > 10000)
		{
			stones = 10000;
		} else if (stones < 100)
		{
			stones = 100;
		}
		this.map.setVulcanos(stones);
		adjustChange();
	}

	public int getDrops()
	{
		return this.map.getDrops();
	}

	public void setDrops(int drops)
	{
		if (drops > 5000)
		{
			drops = 5000;
		} else if (drops < 100)
		{
			drops = 100;
		}
		this.map.setDrops(drops);
		adjustChange();
	}

	public int getDistance()
	{
		return this.map.getDistance();
	}

	public void setDistance(int distance)
	{
		if (distance > 500)
		{
			distance = 500;
		} else if (distance < 10)
		{
			distance = 10;
		}

		this.map.setDistance(distance);
		adjustChange();
	}

}
