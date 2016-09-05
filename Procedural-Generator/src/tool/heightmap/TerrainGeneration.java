package tool.heightmap;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.terrain.geomipmap.TerrainLodControl;
import com.jme3.terrain.geomipmap.TerrainQuad;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;

import tool.mvc.model.interfaces.MyModel;
import tool.util.Utilities;

/**
 * 
 * 
 * Klasse für die JMonkey Engine. Benötigt JMonkey Engine. Klasse basiert auf
 * JMonkey Tutorial Code
 * 
 * Verwendete Texturen wurden nicht von mir erstellt sondern gehören ebenfalls
 * der JMonkey Bibliothek an
 * 
 * @see <a
 *      href="http://hub.jmonkeyengine.org/wiki/doku.php/jme3">http://hub.jmonkeyengine.org/wiki/doku.php/jme3</a>
 * 
 * @author Tihomir Bicanic
 * 
 */
public class TerrainGeneration extends SimpleApplication
{

	private MyModel model;

	public TerrainGeneration(MyModel m)
	{
		this.model = m;
	}

	@Override
	public void simpleInitApp()
	{
		TerrainQuad terrain;
		Material mat_terrain;

		flyCam.setMoveSpeed(500);
		mat_terrain = new Material(assetManager,
				"Common/MatDefs/Terrain/Terrain.j3md");
		mat_terrain
				.setTexture("Alpha", assetManager
						.loadTexture("Textures/Terrain/splat/alphamap.png"));
		Texture grass = assetManager
				.loadTexture("Textures/Terrain/splat/grass.jpg");
		grass.setWrap(WrapMode.Repeat);
		mat_terrain.setTexture("Tex1", grass);
		mat_terrain.setFloat("Tex1Scale", 64f);
		Texture grass2 = assetManager
				.loadTexture("Textures/Terrain/splat/grass.jpg");
		grass2.setWrap(WrapMode.Repeat);
		mat_terrain.setTexture("Tex2", grass2);
		mat_terrain.setFloat("Tex2Scale", 32f);
		Texture rock = assetManager
				.loadTexture("Textures/Terrain/splat/road.jpg");
		rock.setWrap(WrapMode.Repeat);
		mat_terrain.setTexture("Tex3", rock);
		mat_terrain.setFloat("Tex3Scale", 128f);
		int patchSize = 65;
		terrain = new TerrainQuad("my terrain", patchSize, model.getMapSize(),
				Utilities.make2Dto1D(model));

		terrain.setMaterial(mat_terrain);
		terrain.setLocalTranslation(0, -100, 0);
		terrain.setLocalScale(2f, 2f, 2f);
		rootNode.attachChild(terrain);

		TerrainLodControl control = new TerrainLodControl(terrain, getCamera());
		terrain.addControl(control);
	}
}