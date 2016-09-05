package tool.heightmap;

import java.awt.Color;
import java.security.SecureRandom;
import java.util.Random;

public abstract class Heightmap
{
	/**
	 * 
	 * 
	 * Basisklasse alle in tool.heightmap.classes befindlichen Klassen
	 * 
	 * @author Tihomir Bicanic
	 * 
	 */
	protected int ampl, frequence, octaves, increase, faultIteration, decrease,
			blurIteration, seed, vulcanos, drops, distance;

	protected boolean linear, cosin, cubic, blur;
	protected float persistence, rough;
	protected float[][] heights;
	protected Random rand;
	protected Vector[][] vec;

	public final static Color[] SHADESOFGRAY = new Color[256];
	static
	{
		for (int i = 0; i < SHADESOFGRAY.length; i++)
		{
			SHADESOFGRAY[i] = new Color(i, i, i);
		}
	}

	public Heightmap(int size, float rough, int ampl, float persistence)
	{
		this.heights = new float[size][size];
		this.rand = new SecureRandom();
		this.rough = rough;
		this.ampl = ampl;
		this.persistence = persistence;
		this.frequence = 2;
		this.linear = true;
	}

	public Heightmap(int inc, int dec, int faultIt)
	{
		this(512, 1.0f, 1, 0.5f);
		this.increase = inc;
		this.decrease = dec;
		this.faultIteration = faultIt;
	}

	public Heightmap(int freq, int octaves)
	{
		this(512, 1.0f, 128, 1.0f);
		this.vec = new Vector[129][129];
		this.frequence = freq;
		this.octaves = octaves;
	}

	public Heightmap(int size, int drop, int stones, int distance)
	{
		this(513, 1.0f, 128, 0.5f);
		this.drops = drop;
		this.vulcanos = stones;
		this.distance = distance;
	}

	public void blur()
	{
		float[][] heights2 = new float[heights.length][heights.length];
		float f = 1 / 25f;
		for (int i = 0; i < blurIteration; i++)
		{
			for (int y = 0; y < heights.length; y++)
				for (int x = 0; x < heights.length; x++)
				{
					if (x > 1 && y > 2 && x < heights.length - 3
							&& y < heights.length - 3)
					{
						heights2[x][y] = (int) (heights[x - 1][y - 1] * f
								+ heights[x][y - 1] * f
								+ heights[x + 1][y - 1] * f
								+ heights[x - 1][y] * f + heights[x][y] * f
								+ heights[x + 1][y] * f
								+ heights[x - 1][y + 1] * f
								+ heights[x][y + 1] * f + heights[x][y + 1] * f
								+ heights[x - 2][y - 2] * f
								+ heights[x - 1][y - 2] * f
								+ heights[x][y - 2] * f
								+ heights[x + 1][y - 2] * f
								+ heights[x + 2][y - 2] * f
								+ heights[x - 2][y - 1] * f
								+ heights[x + 2][y - 1] * f
								+ heights[x - 2][y] * f + heights[x + 2][y] * f
								+ heights[x - 2][y + 1] * f
								+ heights[x + 2][y + 1] * f
								+ heights[x - 2][y + 2] * f
								+ heights[x - 1][y + 2] * f + heights[x][y + 2]
								* f + heights[x + 2][y + 2] * f + heights[x + 1][y + 2]
								* f);
						heights2[x][y] = correctHeight(heights2[x][y]);
					}
				}
			heights = heights2;
		}
	}

	public float correctHeight(float heightValue)
	{
		if (heightValue > 255)
		{
			heightValue = 255;
		} else if (heightValue < 0)
		{
			heightValue = 0;
		}
		return heightValue;
	}

	protected void feed()
	{
		float x, y, temp;
		for (int i = 0; i < vec.length; i++)
		{
			for (int j = 0; j < vec[0].length; j++)
			{
				x = 2 * rand.nextFloat() - 1;
				y = 2 * rand.nextFloat() - 1;
				temp = (float) Math.sqrt(x * x + y * y);
				vec[i][j] = new Vector(x / temp, y / temp);
			}

		}
	}

	public void setRough(float roughness)
	{
		if (roughness > 1)
		{
			roughness = 1;
		} else if (roughness < 0.1f)
		{
			roughness = 0.1f;
		}
		this.rough = roughness;
	}

	public int nextAmpl(int a)
	{
		if (a <= 1)
		{
			return 1;
		}
		return a *= this.persistence;
	}

	protected int nextIntInAmplRange(int a)
	{
		if (a < 1)
		{
			a = 1;
		}
		return 2 * rand.nextInt(a) - a;
	}

	public void setSeed(int seed)
	{
		if (seed == 0)
		{
			this.rand = new SecureRandom();
			this.seed = 0;
			return;
		}
		this.seed = seed;

	}

	public int getSeed()
	{
		return this.seed;
	}

	public float getRough()
	{
		return this.rough;

	}

	public void setAmpl(int value)
	{
		if (value < 0)
			value = 0;
		else if (value > 128)
			value = 128;
		this.ampl = value;
	}

	public void setValueNoiseAmpl(int value)
	{
		this.ampl = value;
	}

	public int getAmpl()
	{
		return this.ampl;
	}

	public float getPersistence()
	{
		return this.persistence;
	}

	public void setPersistence(float p)
	{
		this.persistence = p;
	}

	public int getMapSize()
	{
		return heights.length;
	}

	public int[][] getMap()
	{
		int[][] temp = new int[heights.length][heights.length];

		for (int y = 0; y < heights.length; y++)
		{
			for (int x = 0; x < heights.length; x++)
			{
				temp[y][x] = (int) heights[y][x];
			}
		}
		return temp;
	}

	public void setNewMap(int len)
	{
		if (len == 512)
		{
			len = 513;
		} else if (len == 1024)
		{
			len = 1025;
		}
		heights = new float[len][len];

	}

	public void generate()
	{
		rand = new SecureRandom();
		rand.setSeed(seed);
		this.setNewMap(heights.length);
	}

	public void setBlur(boolean b)
	{
		this.blur = b;

	}

	public void setBlurIteration(int value)
	{
		this.blurIteration = value;

	}

	public int getDecrease()
	{
		return this.decrease;
	}

	public void setDecrease(int value)
	{
		this.decrease = value;
	}

	public int getIncrease()
	{
		return this.increase;
	}

	public void setIncrease(int value)
	{
		this.increase = value;
	}

	public int getFaultIteration()
	{
		return this.faultIteration;
	}

	public void setFaultIteration(int value)
	{
		this.faultIteration = value;
	}

	public int getFrequence()
	{
		return frequence;
	}

	public void setFrequence(int frequence)
	{
		this.frequence = frequence;
	}

	public int getOctaves()
	{
		return octaves;
	}

	public void setOctaves(int octaves)
	{
		this.octaves = octaves;
	}

	public void setCosin(boolean cosin)
	{
		this.cosin = cosin;
	}

	public void setCubic(boolean cubic)
	{
		this.cubic = cubic;
	}

	public void setLinear(boolean linear)
	{
		this.linear = linear;
	}

	public boolean isCosin()
	{
		return cosin;
	}

	public boolean isCubic()
	{
		return cubic;
	}

	public boolean isLinear()
	{
		return linear;
	}

	public static class Vector
	{
		public float x;
		public float y;

		public Vector(float x, float y)
		{
			this.x = x;
			this.y = y;
		}
	}

	public void setHeightMap(float[][] newMap)
	{
		this.heights = newMap;
	}

	public int getVulcanos()
	{
		return vulcanos;
	}

	public void setVulcanos(int stones)
	{
		this.vulcanos = stones;
	}

	public int getDrops()
	{
		return drops;
	}

	public void setDrops(int drops)
	{
		this.drops = drops;
	}

	public int getDistance()
	{
		return distance;
	}

	public void setDistance(int distance)
	{
		this.distance = distance;
	}

}
