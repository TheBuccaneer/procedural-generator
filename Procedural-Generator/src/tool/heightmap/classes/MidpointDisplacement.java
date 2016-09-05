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
public class MidpointDisplacement extends Heightmap
{

	public MidpointDisplacement()
	{
		super(513, 0.6f, 128, 0.5f);

	}

	private void initCorners()
	{
		heights[0][0] = rand.nextInt(256);
		heights[0][heights.length - 1] = rand.nextInt(256);
		heights[heights.length - 1][0] = rand.nextInt(256);
		heights[heights.length - 1][heights.length - 1] = rand.nextInt(256);
	}

	public void generate()
	{
		super.generate();
		this.initCorners();

		float height, leftUp, leftDown, rightUp, rightDown;
		int squareLen = heights.length - 1;
		int diamondLen = squareLen / 2;
		int amplitude = ampl;

		while (squareLen > 1)
		{
			for (int posX = 0; posX < heights.length - 1; posX += squareLen)
			{
				for (int posY = 0; posY < heights.length - 1; posY += squareLen)
				{
					// MIDDLE POINT----------------------------
					leftUp = heights[posX][posY];
					rightUp = heights[posX + squareLen][posY];
					leftDown = heights[posX][posY + squareLen];
					rightDown = heights[posX + squareLen][posY + squareLen];

					height = (leftUp + rightUp + leftDown + rightDown) / 4
							+ (int) (rough * nextIntInAmplRange(amplitude));
					heights[posX + diamondLen][posY + diamondLen] = correctHeight(height);

					// MIDPOINT
					// up
					height = (heights[posX][posY] + heights[posX + squareLen][posY]) / 2;
					heights[posX + diamondLen][posY] = correctHeight(height);

					// left
					height = (heights[posX][posY] + heights[posX][posY
							+ squareLen]) / 2;
					heights[posX][posY + diamondLen] = correctHeight(height);

					// right
					height = (heights[posX + squareLen][posY] + heights[posX
							+ squareLen][posY + squareLen]) / 2;
					heights[posX + squareLen][posY + diamondLen] = correctHeight(height);

					// down
					height = (heights[posX][posY] + heights[posX][posY
							+ squareLen]) / 2;
					heights[posX + diamondLen][posY + squareLen] = correctHeight(height);
				}
			}
			amplitude = nextAmpl(amplitude);
			squareLen = squareLen / 2;
			diamondLen = squareLen / 2;
		}
		if (blur)
		{
			blur();
		}
	}

}
