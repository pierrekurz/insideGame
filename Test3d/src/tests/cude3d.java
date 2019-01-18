package tests;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;

//classes Java 3d
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.*;
import javax.media.j3d.TransformGroup;
import java.applet.Applet;
import java.awt.BorderLayout;
import javax.media.j3d.BranchGroup;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.ColorCube;

@SuppressWarnings("serial")
public class cude3d extends Applet {
	SimpleUniverse universe; // this is the SimpleUniverse Class that is used for Java3D
	final int[][][] valide = { // les 1 correspondent à des cases où on peut placer les cubes
			// les 0
{ // en x = 0
{ 1, 1, 1, 1, 1/* -> sommet pyramide */ }, /* y=0 */
{ 1, 1, 1, 1, 0 }, /* y=1 */
{ 1, 1, 1, 0, 0 }, /* y=2 */
{ 1, 1, 0, 0, 0 }, /* y=3 */
{ 1, 0, 0, 0, 0 } /* y=4 */
}, { // en x = 1
{ 1, 1, 1, 1, 0 }, /* y=0 */
{ 1, 1, 1, 0, 0 }, /* y=1 */
{ 1, 1, 0, 0, 0 }, /* y=2 */
{ 1, 0, 0, 0, 0 }, /* y=3 */
{ 0, 0, 0, 0, 0 } /* y=4 */
}, { // en x = 2
{ 1, 1, 1, 0, 0 }, /* y=0 */
{ 1, 1, 0, 0, 0 }, /* y=1 */
{ 1, 0, 0, 0, 0 }, /* y=2 */
{ 0, 0, 0, 0, 0 }, /* y=3 */
{ 0, 0, 0, 0, 0 } /* y=4 */
}, { // en x = 3
{ 1, 1, 0, 0, 0 }, /* y=0 */
{ 1, 0, 0, 0, 0 }, /* y=1 */
{ 0, 0, 0, 0, 0 }, /* y=2 */
{ 0, 0, 0, 0, 0 }, /* y=3 */
{ 0, 0, 0, 0, 0 } /* y=4 */
}, { // en x = 4
{ 1, 0, 0, 0, 0 }, /* y=0 */
{ 0, 0, 0, 0, 0 }, /* y=1 */
{ 0, 0, 0, 0, 0 }, /* y=2 */
{ 0, 0, 0, 0, 0 }, /* y=3 */
{ 0, 0, 0, 0, 0 } /* y=4 */
} };
	/**
	 * Create the applet.
	 */
	public cude3d() {
		// Appearance ap = new Appearance();
		// Color3f col = new Color3f(0.0f, 0.0f, 1.0f);
		// ColoringAttributes ca = new ColoringAttributes(col,
		// ColoringAttributes.NICEST);
		// ap.setColoringAttributes(ca);

		// Color3f light1Color = new Color3f(.1f, 1.4f, .1f); // green light
		// BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),
		// 100.0);
		// Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
		// DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
		// light1.setInfluencingBounds(bounds);
		// group.addChild(light1);
		// universe.getViewingPlatform().setNominalViewingTransform();
		// add the group of objects to the Universe
		// universe.addBranchGraph(group);
	}

	public void init() {
		// this function will be called by both applications and applets
		// this is usually the first function to write
		setLayout(new BorderLayout()); // standard Java code for BorderLayout

		// Canvas3D is where all the action will be taking place, don't worry, after
		// adding it
		// to your layout, you don't have to touch it.
		Canvas3D c = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
		// add Canvas3D to center of BorderLayout
		add("Center", c);
		universe = new SimpleUniverse(c); // setup the SimpleUniverse, attach the Canvas3D
		// This is very important, the SceneGraph (where all the action takes place) is
		// created
		// by calling a function which here is called 'createSceneGraph'.
		// The function is not necessary, you can put all your code here, but it is a
		// standard in Java3D to create your SceneGraph contents in the function
		// 'createSceneGraph'
		BranchGroup scene = createSceneGraph();
		// set the ViewingPlatform (where the User is) to nominal, more on this in the
		// next lesson
		universe.getViewingPlatform().setNominalViewingTransform();
		// this will optimize your SceneGraph, not necessary, but it will allow your
		// program to run faster.
		scene.compile();
		universe.addBranchGraph(scene); // add your SceneGraph to the SimpleUniverse
	}

	private BranchGroup createSceneGraph() {
		BranchGroup group = new BranchGroup();
		// Vector3f vector = new Vector3f(x, y, 0f);
		// transform.setTranslation(vector);
		
		ColorCube[][][] board = new ColorCube[5][5][5];
		for (int x = 0; x < 5; ++x) {
			for (int y = 0; y < 5; ++y) {
				for (int z = 0; z < 5; ++z) {
					if (valide[x][y][z] == 1) {
						ColorCube cub = new ColorCube(0.1f);
						board[x][y][z] = cub;

					      TransformGroup tg = new TransformGroup();

					      Transform3D transform = new Transform3D();

					      Vector3d vector = new Vector3d( x*0.1f, 0f, 0f);

					     transform.setTranslation(vector);

					      tg.setTransform(transform);

					      tg.addChild(board[x][y][z]);

					      group.addChild(tg);

					} else if (valide[x][y][z] == 0) {
						board[x][y][z] = null;

					      TransformGroup tg = new TransformGroup();

					      Transform3D transform = new Transform3D();

					      Vector3f vector = new Vector3f( x, y, z);

					      transform.setTranslation(vector);

					      tg.setTransform(transform);

					     // tg.addChild(board[x][y][z]);

					      group.addChild(tg);

					}
				}
			}
		}
		return group;
	}

	public void destroy() {
		universe.removeAllLocales();
	}

	public static void main(String[] args) {
		System.setProperty("sun.awt.noerasebackground", "true");
		MainFrame frame = new MainFrame(new cude3d(), 800, 800);
	}

}