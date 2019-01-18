package tests;
import java.applet.Applet;
import java.awt.BorderLayout;

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.*;


public class CubeTest extends Applet{
	BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);

public CubeTest() {

	 Canvas3D canvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
	 BranchGroup scene = createSceneGraph();
	 scene.compile();
	 SimpleUniverse simpleU = new SimpleUniverse(canvas3D); 
	 simpleU.getViewingPlatform().setNominalViewingTransform(); 
	 simpleU.addBranchGraph(scene); 

}

private BranchGroup createSceneGraph() {
	 BranchGroup objRoot=new BranchGroup(); 
	 Transform3D translate1 = new Transform3D();
	 translate1.set(new Vector3f(0.4f, 0.4f, 0.0f)); 
	 Transform3D rotate = new Transform3D();
	//(X represente la vericale orientée vers le bas,Y represente l' horizontale orientée vers la gauche,Z) 
	 rotate.rotX(Math.PI/3.0d);//rotation d'angle Pi/3 
	 translate1.mul(rotate);
	 // on crée un groupe de transformation rotate suivant la matrice de transformation translate1 
	 TransformGroup TG1 = new TransformGroup(translate1);
	 TG1.addChild(new ColorCube(0.3));// de rayon 30 cm
	 TG1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	 TG1.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		MouseTranslate mouseTrans = new MouseTranslate();
		MouseRotate mouseRotate = new MouseRotate();
		mouseTrans.setTransformGroup(TG1);
		mouseTrans.setSchedulingBounds(bounds);

		mouseRotate.setTransformGroup(TG1);
		mouseRotate.setSchedulingBounds(bounds);

		objRoot.addChild(mouseTrans);
		objRoot.addChild(mouseRotate);
	 objRoot.addChild(TG1);
	 return objRoot;
}
public static void main(String[] args)
{
	System.setProperty("sun.awt.noerasebackground", "true");

   CubeTest myApp=new CubeTest();
  myApp.setSize(600,600);
 myApp.setVisible(true);

} 
}
