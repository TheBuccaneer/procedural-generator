package tool.heightmap.classes;

import tool.heightmap.Heightmap;

/**
 * 
 * 
 * 
 * 
 * @author Tihomir Bicanic, Allain Persu.
 * 
 *         Mit Allain Persu kommentierte Methoden basieren auf einer
 *         Zweitimplementierung und wurden angepasst
 */
public class ValueNoise extends Heightmap
{

	private final static float PI = (float) Math.PI;

	public ValueNoise()
	{
		super(24, 4);

	}

	public void generate()
	{

		super.generate();
		float height = 0.0f;
		for (int i = 0; i < heights.length; i++)
		{
			for (int j = 0; j < heights.length; j++)
			{
				if (linear)
				{
					height = linearInterpolation(i, j, frequence, ampl,
							persistence, octaves);
				} else if (cosin)
				{
					height = cosinInterpolation(i, j, frequence, ampl,
							persistence, octaves);
				} else if (cubic)
				{
					height = cubicInterpolation(i, j, frequence, ampl,
							persistence, octaves);
				}
				heights[i][j] = correctHeight(height);
			}
		}
		if (blur)
		{
			blur();
		}
	}

	// Integer Noise function by Ken Perlin
	private float noiseFunction(int x, int octave)
	{
		x = (x << 13) ^ x;
		return (float) (1.0 - ((x * (x * x * 15731 + 789221) + 1376312589) & 0x7fffffff) / 1073741824.0);
	}

	float linerarInterpolation(float a, float b, float x)
	{
		return a * (1 - x) + b * x;
	}

	private float cosinInterpolation(float a, float b, float x)
	{
		float ft = x * PI;
		ft = (1 - (float) Math.cos(ft)) * .5f;

		return a * (1 - ft) + b * ft;
	}

	private float cubitInterpolatione(float v0, float v1, float v2, float v3,
			float x)
	{
		float a = (v3 - v2) - (v0 - v1);
		float b = (v0 - v1) - a;
		float c = v2 - v0;
		float d = v1;

		return a * x * x * x + b * x * x + c * x + d;
	}

	private float linearInterpolation2D(float v00, float v01, float v10,
			float v11, float x, float y)
	{
		float x0 = linerarInterpolation(v00, v01, y);
		float x1 = linerarInterpolation(v10, v11, y);
		float result = linerarInterpolation(x0, x1, x);

		return result;
	}

	private float cosinInterpolation2D(float v00, float v01, float v10,
			float v11, float x, float y)
	{
		float x0 = cosinInterpolation(v00, v01, y);
		float x1 = cosinInterpolation(v10, v11, y);
		float result = cosinInterpolation(x0, x1, x);

		return result;
	}

	// Allain Persu
	private float cubicInterpolation2D(float v_12, float v02, float v12,
			float v22, float v_11, float v01, float v11, float v21, float v_10,
			float v00, float v10, float v20, float v_1_1, float v0_1,
			float v1_1, float v2_1, float x, float y)
	{
		float x_1 = cubitInterpolatione(v_1_1, v_10, v_11, v_12, y);
		float x0 = cubitInterpolatione(v0_1, v00, v01, v02, y);
		float x1 = cubitInterpolatione(v1_1, v10, v11, v12, y);
		float x2 = cubitInterpolatione(v2_1, v20, v21, v22, y);
		float result = cubitInterpolatione(x_1, x0, x1, x2, x);
		return result;
	}

	private float noiseFunction2D(int x, int y, int octave)
	{
		return noiseFunction(x * 46349 + y * 46351, octave);
	}

	private float linearInterpolation(int x, int y, float fFrequence,
			float fAmplitude, float fPersistence, float fOctaves)
	{
		float value = 0;
		fFrequence = 1 / fFrequence;
		float v00;
		float v01;
		float v10;
		float v11;
		for (int i = 0; i < fOctaves; i++)
		{
			v00 = noiseFunction2D((int) (x * fFrequence),
					(int) (y * fFrequence), i);
			v01 = noiseFunction2D((int) (x * fFrequence),
					(int) (y * fFrequence) + 1, i);
			v10 = noiseFunction2D((int) (x * fFrequence) + 1,
					(int) (y * fFrequence), i);
			v11 = noiseFunction2D((int) (x * fFrequence) + 1,
					(int) (y * fFrequence) + 1, i);
			value += linearInterpolation2D(v00, v01, v10, v11, x
					% (1 / fFrequence) * fFrequence, y % (1 / fFrequence)
					* fFrequence)
					* fAmplitude;
			fFrequence *= 2;
			fAmplitude *= fPersistence;
		}
		return value;
	}

	private float cosinInterpolation(int x, int y, float fFrequence,
			float fAmplitude, float fPersistence, float fOctaves)
	{
		float value = 0;
		fFrequence = 1 / fFrequence;
		float v00;
		float v01;
		float v10;
		float v11;
		for (int i = 0; i < fOctaves; i++)
		{
			v00 = noiseFunction2D((int) (x * fFrequence),
					(int) (y * fFrequence), i);
			v01 = noiseFunction2D((int) (x * fFrequence),
					(int) (y * fFrequence) + 1, i);
			v10 = noiseFunction2D((int) (x * fFrequence) + 1,
					(int) (y * fFrequence), i);
			v11 = noiseFunction2D((int) (x * fFrequence) + 1,
					(int) (y * fFrequence) + 1, i);
			value += cosinInterpolation2D(v00, v01, v10, v11, x
					% (1 / fFrequence) * fFrequence, y % (1 / fFrequence)
					* fFrequence)
					* fAmplitude;
			fFrequence *= 2;
			fAmplitude *= fPersistence;
		}
		return value;
	}

	// Allain Persu
	private float cubicInterpolation(int x, int y, float fFrequence,
			float fAmplitude, float fPersistence, float fOctaves)
	{
		float value = 0;
		fFrequence = 1 / fFrequence;
		float[][] v = new float[4][];
		for (int i = 0; i < 4; i++)
			v[i] = new float[4];
		for (int i = 0; i < fOctaves; i++)
		{
			v[0][3] = noiseFunction2D((int) (x * fFrequence) - 1,
					(int) (y * fFrequence) + 2, i);
			v[0][2] = noiseFunction2D((int) (x * fFrequence),
					(int) (y * fFrequence) + 2, i);
			v[0][1] = noiseFunction2D((int) (x * fFrequence) + 1,
					(int) (y * fFrequence) + 2, i);
			v[0][0] = noiseFunction2D((int) (x * fFrequence) + 2,
					(int) (y * fFrequence) + 2, i);

			v[3][3] = noiseFunction2D((int) (x * fFrequence) - 1,
					(int) (y * fFrequence) - 1, i);
			v[3][2] = noiseFunction2D((int) (x * fFrequence),
					(int) (y * fFrequence) - 1, i);
			v[3][1] = noiseFunction2D((int) (x * fFrequence) + 1,
					(int) (y * fFrequence) - 1, i);
			v[3][0] = noiseFunction2D((int) (x * fFrequence) + 2,
					(int) (y * fFrequence) - 1, i);

			v[1][3] = noiseFunction2D((int) (x * fFrequence) - 1,
					(int) (y * fFrequence) + 1, i);
			v[1][2] = noiseFunction2D((int) (x * fFrequence),
					(int) (y * fFrequence) + 1, i);
			v[1][1] = noiseFunction2D((int) (x * fFrequence) + 1,
					(int) (y * fFrequence) + 1, i);
			v[1][0] = noiseFunction2D((int) (x * fFrequence) + 2,
					(int) (y * fFrequence) + 1, i);

			v[2][3] = noiseFunction2D((int) (x * fFrequence) - 1,
					(int) (y * fFrequence), i);
			v[2][2] = noiseFunction2D((int) (x * fFrequence),
					(int) (y * fFrequence), i);
			v[2][1] = noiseFunction2D((int) (x * fFrequence) + 1,
					(int) (y * fFrequence), i);
			v[2][0] = noiseFunction2D((int) (x * fFrequence) + 2,
					(int) (y * fFrequence), i);

			value += cubicInterpolation2D(v[0][3], v[0][2], v[0][1], v[0][0],
					v[1][3], v[1][2], v[1][1], v[1][0], v[2][3], v[2][2],
					v[2][1], v[2][0], v[3][3], v[3][2], v[3][1], v[3][0], x
							% (1 / fFrequence) * fFrequence, y
							% (1 / fFrequence) * fFrequence)
					* fAmplitude;
			fFrequence *= 2;
			fAmplitude *= fPersistence;
		}
		return value;
	}

}
