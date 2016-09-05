package tool.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import tool.mvc.model.interfaces.MyModel;

public class Utilities
{

	public static boolean mapToFile(int[][] array, String path)
	{
		int size = array.length;
		BufferedImage bufferedImage = new BufferedImage(size, size,
				BufferedImage.TYPE_3BYTE_BGR);
		int value = 0;
		for (int y = 0; y < size; y++)
			for (int x = 0; x < size; x++)
			{
				value = array[y][x];
				value = value << 16 | value << 8 | value;
				bufferedImage.setRGB(y, x, value);
			}

		File outputfile = new File(path);
		if (outputfile.exists())
		{
			int initValue = JOptionPane.showConfirmDialog(null,
					"File already exists. Confirm?", "",
					JOptionPane.YES_NO_CANCEL_OPTION);
			if (initValue == 1 || initValue == 2)
			{
				return true;
			}
		}

		if (!outputfile.getName().endsWith(".png")
				&& !outputfile.getName().endsWith(".PNG"))
		{
			outputfile = new File(outputfile.getAbsoluteFile() + ".png");
		}
		try
		{
			ImageIO.write(bufferedImage, "png", outputfile);
		} catch (IOException e)
		{
			return false;
		}

		return true;

	}

	public static float[][] fileToMap(String path)
	{
		BufferedImage bi;
		try
		{
			bi = ImageIO.read(new File(path));
		} catch (IOException e)
		{
			return null;
		}
		int width = bi.getWidth();
		int height = bi.getHeight();

		if (!(width == 513 || width == 1025)
				&& !(height == 513 || height == 1025) && width != height)
		{
			return null;
		}

		float[][] result = new float[width][height];
		int i = 0;
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				i = bi.getRGB(x, y);
				result[x][y] = correctHeight((i >> 16) & 0x000000FF);
			}
		}
		return result;
	}

	public static float correctHeight(float heightValue)
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

	public static float[] make2Dto1D(MyModel model)
	{
		int mapSize = model.getMapSize() - 1;
		float[] newMap = new float[mapSize * mapSize];
		int[][] grayMap = model.getMap();
		int y = 0;
		int x = 0;
		for (int i = 0; i < newMap.length; i++, x++)
		{
			newMap[i] = grayMap[y][x];
			if (x % mapSize == 0 && i != 0)
			{
				x = 0;
				y++;
			}

		}
		return newMap;
	}
}
