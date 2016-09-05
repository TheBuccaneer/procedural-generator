package tool.util;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import tool.heightmap.classes.EmptyClass;
import tool.mvc.model.interfaces.MyModel;

/**
 * 
 * 
 * 
 * @author Tihomir Bicanic
 * 
 */
public class MyMenuBar extends JMenuBar implements ActionListener
{

	private JFrame main;
	private MyModel model;
	private String filePath = null;

	private final static String[] MENUNAMES = { "Datei", "Hilfe" };
	private final static String[] BARNAMES = { "Load", "Save", "Save As",
			"Close" };

	public MyMenuBar(JFrame main, MyModel model)
	{
		this.main = main;
		this.model = model;

		JMenu fileMenu = new JMenu(MENUNAMES[0]);
		JMenu helpMenu = new JMenu(MENUNAMES[1]);
		JMenuItem temp = null;
		for (int i = 0; i < BARNAMES.length; i++)
		{
			temp = new JMenuItem(BARNAMES[i]);
			temp.addActionListener(this);
			fileMenu.add(temp);

		}
		// temp = new JMenuItem("Hilfe");
		// helpMenu.add(temp);
		temp.addActionListener(this);
		this.add(fileMenu);
		//
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String name = arg0.getActionCommand();

		switch (name)
		{
		case "Close":
			this.close();
			break;
		case "Save":
			this.save();
			break;
		case "Save As":
			this.saveAs(false);
			break;
		case "Load":
			this.load();
			break;
		case "Hilfe":
			this.help();
		}

	}

	private void help()
	{
		try
		{
			Desktop.getDesktop().open(new File("u.pdf"));
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void close()
	{
		System.exit(ABORT);
	}

	private void save()
	{
		if (filePath == null)
		{
			this.saveAs(false);
		}
		this.saveAs(true);
	}

	private void saveAs(boolean alreadyDone)
	{
		int[][] array = null;

		try
		{
			array = model.getMap();
		} catch (NullPointerException e)
		{
			this.showErrorDialog(false, "save");
			return;
		}
		if (!alreadyDone)
		{
			JFileChooser chooser = new JFileChooser();
			int initValue = chooser.showSaveDialog(main);
			if (hasToClose(initValue))
			{
				return;
			}
			filePath = chooser.getSelectedFile().getAbsolutePath();
		}

		boolean b = Utilities.mapToFile(array, filePath);
		showErrorDialog(b, "save");
	}

	private void load()
	{
		JFileChooser chooser = new JFileChooser();
		int initValue = chooser.showOpenDialog(main);
		if (hasToClose(initValue))
		{
			return;
		}
		String path = chooser.getSelectedFile().getAbsolutePath();
		float[][] floatMap = Utilities.fileToMap(path);
		if (floatMap == null)
		{
			showErrorDialog(false, "load");
		}
		model.newHeightmap(new EmptyClass());
		model.setHeightMap(floatMap);
	}

	private boolean hasToClose(int initValue)
	{
		return initValue == JFileChooser.ERROR_OPTION
				|| initValue == JFileChooser.CANCEL_OPTION;
	}

	private void showErrorDialog(boolean b, String whatToDo)
	{
		if (!b)
		{
			JOptionPane.showMessageDialog(main, "Could not " + whatToDo
					+ " the image. Something went perfectly wrong! ");
		}
	}
}
