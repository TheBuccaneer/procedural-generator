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
public class ParticleDeposition extends Heightmap
{

	public ParticleDeposition()
	{
		super(513, 400, 1000, 30);
	}

	public void generate()
	{
		super.generate();
		int x1, y1, i, ii, dis, tx, ty, sx, sy;
		x1 = y1 = i = ii = dis = tx = ty = sx = sy = 0;
		final int[] dx = { -1, 0, 1, -1, 1, -1, 0, 1 };
		final int[] dy = { -1, -1, -1, 0, 0, 1, 1, 1 };
		int localDrop = super.drops;
		int localVulcanos = super.vulcanos;
		int localDistance = super.distance;
		x1 = 1 + rand.nextInt(heights.length - 3);
		y1 = 1 + rand.nextInt(heights.length - 3);
		for (i = 0; i < localVulcanos; i++)
		{
			localDrop = super.drops;
			while (--localDrop > 0)
			{

				tx = x1;
				ty = y1;

				dis = rand.nextInt(8);

				for (ii = 0; ii < 8; ii++)
				{

					sx = (dx[(ii + dis) % 8] + tx) % heights.length;
					sy = (dy[(ii + dis) % 8] + ty) % heights.length;

					if (sx < 0)
						sx = heights.length - 1;
					if (sy < 0)
						sy = heights.length - 1;
					if (heights[tx][ty] - heights[sx][sy] >= 1)
					{

						tx = sx;
						ty = sy;

						dis = rand.nextInt(8);
						ii = 0;
					}
				}

				heights[tx][ty] = heights[tx][ty] + heights[tx][ty] + 1;
				heights[tx][ty] = correctHeight(heights[tx][ty]);

			}

			x1 = (x1 + (2 * rand.nextInt(localDistance) - localDistance / 2))
					% heights.length;
			y1 = (y1 + (2 * rand.nextInt(localDistance) - localDistance / 2))
					% heights.length;

			if (x1 < 0)
				x1 = 1;
			if (y1 < 0)
				y1 = 1;

		}
		if (blur)
		{
			blur();
		}
	}

}
