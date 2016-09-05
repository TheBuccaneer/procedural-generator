package tool.heightmap.classes;

import java.util.Random;
import tool.heightmap.Heightmap;

/**
 * 
 * 
 * 
 * 
 * @author Tihomir Bicanic
 * 
 */
public class TriangleSubDevision extends Heightmap
{
	private Random rand = new Random(100);

	public TriangleSubDevision()
	{
		super(512, 1.0f, 128, 0.5f);
	}

	public void initCorners()
	{
		heights[0][0] = rand.nextInt(256);
		heights[0][heights.length - 1] = rand.nextInt(256);
		heights[heights.length - 1][0] = rand.nextInt(256);
		heights[heights.length - 1][heights.length - 1] = rand.nextInt(256);
		heights[heights.length / 2][heights.length / 2] = rand.nextInt(256);
	}

	public void generate()
	{
		super.generate();
		initCorners();
		int len = heights.length / 2;
		int amplitude = super.ampl;
		calculate(amplitude, len, 0, 0, 0, heights.length - 1, len, len);
		calculate(amplitude, len, heights.length - 1, 0, heights.length - 1,
				heights.length - 1, len, len);
		calculate(amplitude, len, 0, 0, heights.length - 1, 0, len, len);
		calculate(amplitude, len, 0, heights.length - 1, heights.length - 1,
				heights.length - 1, len, len);
		calculate(amplitude, len, 0, heights.length - 1, heights.length - 1, 0,
				0, 0);

		if (blur)
		{
			blur();
		}
	}

	private void calculate(int amplitude, int len, int ax, int ay, int bx,
			int by, int cx, int cy)
	{
		if (len < 1)
			return;
		int newLen = len / 2;
		float height = 0.0f;
		int abx = (ax + bx) / 2;
		int aby = (ay + by) / 2;
		int bcx = (bx + cx) / 2;
		int bcy = (by + cy) / 2;
		int acx = (ax + cx) / 2;
		int acy = (ay + cy) / 2;

		height = ((heights[ax][ay] + heights[bx][by]) / 2)
				+ nextIntInAmplRange(amplitude) * rough;
		heights[abx][aby] = correctHeight(height);

		height = ((heights[bx][by] + heights[cx][cy]) / 2)
				+ nextIntInAmplRange(amplitude) * rough;
		heights[bcx][bcy] = correctHeight(height);

		height = ((heights[ax][ay] + heights[cx][cy]) / 2)
				+ nextIntInAmplRange(amplitude) * rough;
		heights[acx][acy] = correctHeight(height);

		amplitude = super.nextAmpl(amplitude);
		calculate(amplitude, newLen, ax, ay, abx, aby, acx, acy);
		calculate(amplitude, newLen, abx, aby, bx, by, bcx, bcy);
		calculate(amplitude, newLen, acx, acy, bcx, bcy, abx, aby);
		calculate(amplitude, newLen, acx, acy, bcx, bcy, cx, cy);

	}

	@Override
	public int[][] getMap()
	{
		return super.getMap();
	}

	@Override
	public void setNewMap(int len)
	{
		super.setNewMap(len);

	}

}
