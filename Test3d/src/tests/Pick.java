package tests;
import com.sun.j3d.utils.picking.*;

import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.geometry.*;

import javax.media.j3d.*;

import javax.vecmath.*;

import java.awt.event.*;

import java.awt.*;

public class Pick extends MouseAdapter {

private PickCanvas pickCanvas;

public Pick()

{

    Frame frame = new Frame("Box and Sphere");

    GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();

    Canvas3D canvas = new Canvas3D(config);

    canvas.setSize(400, 400);

    SimpleUniverse universe = new SimpleUniverse(canvas);

    BranchGroup group = new BranchGroup();
    BranchGroup group2 = new BranchGroup();


    // create a color cube

    Vector3f vector = new Vector3f(-0.3f, 0.0f, 0.0f);

    Transform3D transform = new Transform3D();

    transform.setTranslation(vector);

    TransformGroup transformGroup = new TransformGroup(transform);

    ColorCube cube = new ColorCube(0.3);

    transformGroup.addChild(cube);

    group.addChild(transformGroup);
	MouseTranslate mouseTrans = new MouseTranslate();
	mouseTrans.setTransformGroup(transformGroup);
	mouseTrans.setSchedulingBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0));
	transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	group.addChild(mouseTrans);
	
	
    //create a sphere

    Vector3f vector2 = new Vector3f(+0.3f, 0.0f, 0.0f);

    Transform3D transform2 = new Transform3D();

    transform2.setTranslation(vector2);

    TransformGroup transformGroup2 = new TransformGroup(transform2);

    Appearance appearance = new Appearance();

    appearance.setPolygonAttributes(

       new PolygonAttributes(PolygonAttributes.POLYGON_LINE,

       PolygonAttributes.CULL_BACK,0.0f));

    Sphere sphere = new Sphere(0.3f,appearance);
    MouseRotate mouseRotate = new MouseRotate();
	mouseRotate.setTransformGroup(transformGroup2);
	mouseRotate.setSchedulingBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0));
	transformGroup2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	transformGroup2.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	MouseTranslate mouseTrans2 = new MouseTranslate();
	mouseTrans2.setTransformGroup(transformGroup2);
	mouseTrans2.setSchedulingBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0));
	transformGroup2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	transformGroup2.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	group2.addChild(mouseTrans2);
	group2.addChild(mouseRotate);
    transformGroup2.addChild(sphere);

    group2.addChild(transformGroup2);

    universe.getViewingPlatform().setNominalViewingTransform();

    universe.addBranchGraph(group);
    universe.addBranchGraph(group2);

    frame.addWindowListener(new WindowAdapter() {

       public void windowClosing(WindowEvent winEvent) {

          System.exit(0);

       }

    });

    frame.add(canvas);

    pickCanvas = new PickCanvas(canvas, group);

    pickCanvas.setMode(PickCanvas.BOUNDS);

    canvas.addMouseListener(this);

    frame.pack();

    frame.setVisible(true);

}

public static void main( String[] args ) {

    System.setProperty("sun.awt.noerasebackground", "true");

    new Pick();

}

public void mouseClicked(MouseEvent e)

{

    pickCanvas.setShapeLocation(e);

    PickResult result = pickCanvas.pickClosest();

    if (result == null) {

       System.out.println("Nothing picked");

    } else {

       Primitive p = (Primitive)result.getNode(PickResult.PRIMITIVE);

       Shape3D s = (Shape3D)result.getNode(PickResult.SHAPE3D);

       if (p != null) {

          System.out.println(p.getClass().getName());

       } else if (s != null) {

             System.out.println(s.getClass().getName());
             System.out.println(s.getBounds().getClass());


       } else{

          System.out.println("null");

       }

    }

}

} // end of class Pick 