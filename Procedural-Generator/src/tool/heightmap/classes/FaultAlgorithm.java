package tool.heightmap.classes;

import tool.heightmap.Heightmap;

/**
 * 
 * 
 * 
 * 
 * @author Tihomir Bicanic
 * 
 */
public class FaultAlgorithm extends Heightmap
{
	public FaultAlgorithm()
	{
		super(4, 3, 128);
	}

	public void generate()
	{
		super.generate();
		int[][] x1y1 = new int[1][2], x2y2 = new int[1][2];
		int start, end;
		float height = 0.0f;
		for (int i = 0; i < faultIteration; i++)
		{
			start = end = -1;
			while (start == end)
			{
				start = rand.nextInt(4);
				end = rand.nextInt(4);
			}
			x1y1 = getStart(start);
			x2y2 = getEnd(end);
			for (int y = 0; y < heights.length; y++)
				for (int x = 0; x < heights.length; x++)
				{
					if ((x2y2[0][0] - x1y1[0][0]) * (y - x1y1[0][1])
							- (x2y2[0][1] - x1y1[0][1]) * (x - x1y1[0][0]) > 0)
					{
						height = heights[x][y] + increase;
						heights[x][y] = correctHeight(height);
					} else
					{
						height = heights[x][y] - decrease;
						heights[x][y] = correctHeight(height);
					}
				}
		}

		if (blur)
		{
			blur();
		}

	}

	private int[][] getStart(int start)
	{
		return get(start);
	}

	private int[][] getEnd(int end)
	{
		return get(end);
	}

	private int[][] get(int side)
	{
		int[][] temp = new int[1][2];
		int value = rand.nextInt(heights.length);
		if (side == 0)
		{
			temp[0][0] = value;
			temp[0][1] = 0;
		} else if (side == 1)
		{
			temp[0][0] = value;
			temp[0][1] = heights.length - 1;
		} else if (side == 2)
		{
			temp[0][0] = 0;
			temp[0][1] = value;
		} else
		{
			temp[0][0] = heights.length - 1;
			temp[0][1] = value;
		}
		return temp;

	}

}
