package tests;
import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;

public class Position2 {
	BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
	BoundingSphere bounds2 = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);

	public Position2() {
		SimpleUniverse universe = new SimpleUniverse();

		BranchGroup group = new BranchGroup();
		BranchGroup group2 = new BranchGroup();

		universe.getViewingPlatform().setNominalViewingTransform();

//cube 1
					ColorCube cub = new ColorCube(0.2f);
					TransformGroup tg = new TransformGroup();
					Transform3D transform = new Transform3D();
					
					Vector3f vector = new Vector3f(.2f,.1f , -.4f);
					transform.setTranslation(vector);
					tg.setTransform(transform);
					
					MouseRotate mouseRotate = new MouseRotate();
					mouseRotate.setTransformGroup(tg);
					mouseRotate.setSchedulingBounds(bounds);
					group.addChild(mouseRotate);
					MouseTranslate mouseTrans = new MouseTranslate();
					mouseTrans.setTransformGroup(tg);
					mouseTrans.setSchedulingBounds(bounds);
					group.addChild(mouseTrans);

//cube2
					ColorCube cub2 = new ColorCube(0.2f);
					TransformGroup tg2 = new TransformGroup();
					Transform3D transform2 = new Transform3D();
					Vector3f vector2 = new Vector3f(.8f,.8f , -.8f);
					transform2.setTranslation(vector2);
					tg2.setTransform(transform2);
					MouseTranslate mouseTrans2 = new MouseTranslate();

					mouseTrans2.setTransformGroup(tg2);

					mouseTrans2.setSchedulingBounds(bounds2);

					group2.addChild(mouseTrans2);

					tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
					tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
					tg2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
					tg2.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
					tg.addChild(cub);
					tg2.addChild(cub2);
					group.addChild(tg);
					group2.addChild(tg2);
				
			
		

		Color3f light1Color = new Color3f(.1f, 1.4f, 1.1f); // green light
		Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
		DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
		light1.setInfluencingBounds(bounds);
		group.addChild(light1);
		// add the group of objects to the Universe
		universe.addBranchGraph(group);
		universe.addBranchGraph(group2);
	}

	public static void main(String[] args) {
		System.setProperty("sun.awt.noerasebackground", "true");
		new Position2();

	}

}