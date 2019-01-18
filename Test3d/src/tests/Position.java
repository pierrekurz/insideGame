package tests;
import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;

public class Position {
	BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);

	public Position() {
		SimpleUniverse universe = new SimpleUniverse();
		BranchGroup group = new BranchGroup();
		universe.getViewingPlatform().setNominalViewingTransform();

		for (float x = -1.0f; x <= 1.0f; x = x + 0.5f) {
			for (float y = -1.0f; y <= 1.0f; y = y + 0.5f) {
				for (float z = -1.0f; z <= 1.0f; z = z + 0.5f) {
					ColorCube cub = new ColorCube(0.2f);
					TransformGroup tg = new TransformGroup();
					Transform3D transform = new Transform3D();
					Vector3f vector = new Vector3f(x, y, z);
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
					tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
					tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
					tg.addChild(cub);
					group.addChild(tg);
				}
			}
		}

		Color3f light1Color = new Color3f(.1f, 1.4f, 1.1f); // green light
		Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
		DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
		light1.setInfluencingBounds(bounds);
		group.addChild(light1);
		// add the group of objects to the Universe
		universe.addBranchGraph(group);
	}

	public static void main(String[] args) {
		System.setProperty("sun.awt.noerasebackground", "true");
		new Position();

	}

}