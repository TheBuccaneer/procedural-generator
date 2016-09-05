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
public class DiamondSquare extends Heightmap
{

	public DiamondSquare()
	{
		super(513, 0.6f, 128, 0.5f);

	}

	private void initCorners()
	{
		super.heights[0][0] = rand.nextInt(256);
		super.heights[0][heights.length - 1] = rand.nextInt(256);
		super.heights[heights.length - 1][0] = rand.nextInt(256);
		super.heights[heights.length - 1][heights.length - 1] = rand
				.nextInt(256);
	}

	public void generate()
	{
		super.generate();

		this.initCorners();
		float height, leftUp, leftDown, rightUp, rightDown, left, up, down, right;
		int squareLen = super.heights.length - 1;
		int diamondLen = squareLen / 2;
		int amplitude = super.ampl;

		while (squareLen > 1)
		{
			for (int posX = 0; posX < super.heights.length - 1; posX += squareLen)
			{
				for (int posY = 0; posY < super.heights.length - 1; posY += squareLen)
				{

					leftUp = super.heights[posX][posY];
					rightUp = super.heights[posX + squareLen][posY];
					leftDown = super.heights[posX][posY + squareLen];
					rightDown = super.heights[posX + squareLen][posY
							+ squareLen];

					height = ((leftUp + rightUp + leftDown + rightDown) / 4)
							+ (int) (super.rough * nextIntInAmplRange(amplitude));
					super.heights[posX + diamondLen][posY + diamondLen] = correctHeight(height);
				}
			}
			for (int x = 0; x < super.heights.length; x += diamondLen)
			{
				for (int y = (x + diamondLen) % squareLen; y < super.heights.length; y += squareLen)
				{
					left = super.heights[(x - diamondLen + super.heights.length)
							% super.heights.length][y];
					right = super.heights[(x + diamondLen)
							% super.heights.length][y];
					up = super.heights[x][(y - diamondLen + super.heights.length)
							% super.heights.length];
					down = super.heights[x][(y + diamondLen)
							% super.heights.length];

					height = (left + right + up + down)
							/ 4
							+ (int) (super.rough * nextIntInAmplRange(amplitude));

					super.heights[x][y] = correctHeight(height);

				}
			}
			amplitude = super.nextAmpl(amplitude);
			squareLen = squareLen / 2;
			diamondLen = squareLen / 2;
		}

		if (super.blur)
		{
			super.blur();
		}

	}

	public int[][] getMap()
	{
		return super.getMap();
	}

	public void setNewMap(final int len)
	{
		super.setNewMap(len);

	}

}
