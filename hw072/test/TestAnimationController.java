import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.AnimationControllerFactory;
import cs3500.animator.controller.EditVisualAnimationController;
import cs3500.animator.controller.SVGAnimationController;
import cs3500.animator.controller.TextualAnimationController;
import cs3500.animator.controller.VisualAnimationController;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationObject;
import cs3500.animator.model.AnimationObjectInstructionImpl;
import cs3500.animator.model.BaseAnimationModel;
import cs3500.animator.model.RectangleAnimationObject;
import cs3500.animator.view.SVGAnimationViewImpl;
import cs3500.animator.view.TextualAnimationViewImpl;
import cs3500.animator.view.VisualAnimationViewImpl;

import static org.junit.Assert.assertTrue;

public class TestAnimationController {
  AnimationObject object1;
  AnimationObject object2;
  AnimationModel model1;
  AnimationModel model2;
  Point point1;
  Point point2;
  Point point3;
  Point point4;
  Color black;
  Color red;
  Color pink;
  AnimationControllerFactory control1;
  AnimationObjectInstructionImpl instruction1;
  AnimationObjectInstructionImpl instruction2;
  AnimationObjectInstructionImpl instruction3;
  AnimationObjectInstructionImpl instruction4Overlap;
  TextualAnimationViewImpl textView1;
  VisualAnimationViewImpl visView1;
  SVGAnimationViewImpl svgView1;


  @Before
  public void init() {
    textView1 = new TextualAnimationViewImpl(System.out);
    visView1 = new VisualAnimationViewImpl(System.out);
    svgView1 = new SVGAnimationViewImpl(System.out);

    control1 = new AnimationControllerFactory();

    model1 = new BaseAnimationModel();

    point1 = new Point(6, 6);
    point2 = new Point(20, 20);
    point3 = new Point(30, 20);
    point4 = new Point(10, 10);

    black = Color.BLACK;
    red = Color.RED;
    pink = Color.PINK;

    instruction1 = new AnimationObjectInstructionImpl(1, 10, point1, point2,
            10, 10, 10, 20, black, red);
    instruction2 = new AnimationObjectInstructionImpl(10, 20, point2, point3,
            10, 10, 10, 20, black, red);
    instruction3 = new AnimationObjectInstructionImpl(20, 30, point3, point4,
            10, 10, 10, 69, pink, red);

    instruction4Overlap = new AnimationObjectInstructionImpl(19, 30,
            point3, point4,
            10, 10, 10, 69, pink, red);

    object1 = new RectangleAnimationObject("object1");
    /*
    object1.addMotion(instruction1);
    object1.addMotion(instruction2);
    object1.addMotion(instruction3);
    */

    object2 = new RectangleAnimationObject("object2");
    /*
    object2.addMotion(instruction1);
    */
    model2 = new BaseAnimationModel(object1);
  }


  @Test
  public void testACFactory() {
    AnimationController dogs = control1.getInstance("text", textView1, model1);
    AnimationController cats = control1.getInstance("visual", visView1, model1);
    AnimationController rats = control1.getInstance("svg", svgView1, model1);
    AnimationController mice = control1.getInstance("edit", visView1, model1);
    assertTrue(dogs instanceof TextualAnimationController);
    assertTrue(cats instanceof VisualAnimationController);
    assertTrue(rats instanceof SVGAnimationController);
    assertTrue(mice instanceof EditVisualAnimationController);

  }


}
