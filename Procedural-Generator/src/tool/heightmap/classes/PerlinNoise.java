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
public class PerlinNoise extends Heightmap
{

	public PerlinNoise()
	{
		super(4, 2);

	}

	public void generate()
	{
		super.generate();

		int len = (heights.length / frequence) + 1;
		if (heights.length == 513)
		{
			this.vec = new Vector[len][len];
		} else if (heights.length == 1025)
		{
			this.vec = new Vector[len][len];
		}

		this.feed();

		float height = 0.0f;
		for (int i = 0; i < heights.length - 1; i++)
			for (int j = 0; j < heights[0].length - 1; j++)
			{
				height = (float) (noise2D(j, i) * 127 + 127);
				heights[j][i] = correctHeight(height);
			}
		if (blur)
		{
			super.blur();
		}
	}

	private double noise2D(float x, float y)
	{
		int freq = super.frequence;
		double c = 0;
		for (int i = 0; i < super.octaves; i++)
		{

			x /= freq;
			y /= freq;

			int leftX = (int) Math.floor(x);
			int upY = (int) Math.floor(y);
			int rightX = leftX + 1;
			int downY = upY + 1;
			Vector g00 = vec[leftX][upY];
			Vector g10 = vec[rightX][upY];
			Vector g01 = vec[leftX][downY];
			Vector g11 = vec[rightX][downY];

			Vector delta00 = new Vector(x - leftX, y - upY);
			Vector delta10 = new Vector(x - rightX, y - upY);
			Vector delta01 = new Vector(x - leftX, y - downY);
			Vector delta11 = new Vector(x - rightX, y - downY);

			double n00 = innerProduct(g00, delta00);
			double n10 = innerProduct(g10, delta10);
			double n01 = innerProduct(g01, delta01);
			double n11 = innerProduct(g11, delta11);

			double a;
			double b;

			if (super.linear)
			{
				a = linearInt(n00, n01, delta00.y);
				b = linearInt(n10, n11, delta00.y);
				c += linearInt(a, b, delta00.x);
			} else if (super.cosin)
			{
				a = cosin(n00, n01, delta00.y);
				b = cosin(n10, n11, delta00.y);
				c += cosin(a, b, delta00.x);
			}
			freq *= 2;

		}
		return c;
	}

	private double linearInt(double a, double b, double x)
	{
		return a * (1 - x) + b * x;
	}

	double cosin(double a, double b, double x)
	{
		double ft = x * Math.PI;
		ft = (1 - Math.cos(ft)) * .5f;
		return a * (1 - ft) + b * ft;
	}

	private double innerProduct(Vector one, Vector two)
	{
		return one.x * two.x + one.y * two.y;
	}

}
