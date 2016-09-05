package tool.mvc.model.interfaces;

import tool.heightmap.Heightmap;
import tool.mvc.view.interfaces.MyAdjustmentChange;
import tool.mvc.view.interfaces.MyAlgorithmChange;
import tool.mvc.view.interfaces.MyDrawView;

/**
 * Interface des genutzten Models
 * 
 * 
 * @author Tihomir Bicanic
 * 
 */
public interface MyModel
{

	public void addSetupView(MyAlgorithmChange v);

	public void addDrawView(MyDrawView v);

	public void newHeightmap(Heightmap hMap);

	public int getSeed();

	public void setSeed(int value);

	public void setRough(int value);

	public void setAdjustmentChange(MyAdjustmentChange a);

	public float getRough();

	public void setAmpl(int value);

	public int getAmpl();

	public void setPersistence(int value);

	public float getPersistence();

	public int getMapSize();

	public int[][] getMap();

	public void setMapSize(int len);

	public void generate();

	public void setBlur(boolean b);

	public void setBlurIteration(int value);

	public void setDecrease(int value);

	public void setIncrease(int value);

	public int getDecrease();

	public int getIncrease();

	public int getFaultIteration();

	public void setFaultIteration(int value);

	public int getFrequence();

	public void setFrequence(int frequence);

	public int getOctaves();

	public void setOctaves(int octaves);

	public boolean isCosin();

	public void setCosin(boolean cosin);

	public boolean isCubic();

	public void setCubic(boolean cubic);

	public boolean isLinear();

	public void setLinear(boolean linear);

	public void setNoiseFrequence(int frequence);

	public void setNoiseAmpl(int value);

	public void setHeightMap(float[][] newMap);

	public int getVulcanos();

	public void setVulcanos(int stones);

	public int getDrops();

	public void setDrops(int drops);

	public int getDistance();

	public void setDistance(int distance);

}
