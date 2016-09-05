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
public class DiamondSquare2 extends Heightmap
{

	public DiamondSquare2()
	{
		super(513, 1.0f, 1, 0.5f);
	}

	private void initCorners()
	{
		heights[0][0] = rand.nextInt(256);
		heights[0][heights.length - 1] = rand.nextInt(256);
		heights[heights.length - 1][0] = rand.nextInt(256);
		heights[heights.length - 1][heights.length - 1] = rand.nextInt(256);
	}

	@Override
	public void generate()
	{
		super.generate();
		initCorners();

		float height = 0.0f;
		for (int sideLength = heights.length - 1; sideLength >= 2; sideLength /= 2)
		{
			int halfSide = sideLength / 2;

			for (int x = 0; x < heights.length - 1; x += sideLength)
			{
				for (int y = 0; y < heights.length - 1; y += sideLength)
				{
					height = (heights[x][y] + heights[x + sideLength][y]
							+ heights[x][y + sideLength] + heights[x
							+ sideLength][y + sideLength]) / 4;
					height = height + nextIntInAmplRange(sideLength) * rough;
					heights[x + halfSide][y + halfSide] = height = correctHeight(height);

				}
			}
			for (int x = 0; x < heights.length - 1; x += halfSide)
			{
				for (int y = (x + halfSide) % sideLength; y < heights.length - 1; y += sideLength)
				{
					height = heights[(x - halfSide + heights.length)
							% heights.length][y]
							+ heights[(x + halfSide) % heights.length][y]
							+

							heights[x][(y + halfSide) % heights.length]
							+ heights[x][(y - halfSide + heights.length)
									% heights.length];

					height /= 4.0;
					height = height + nextIntInAmplRange(sideLength) * rough;
					heights[x][y] = height = correctHeight(height);

					if (x == 0)
						heights[heights.length - 1][y] = height;
					if (y == 0)
						heights[x][heights.length - 1] = height;
				}
			}
		}

	}

	public int[][] getMap()
	{
		return super.getMap();
	}

	public void setNewMap(int len)
	{
		super.setNewMap(len);

	}

}
