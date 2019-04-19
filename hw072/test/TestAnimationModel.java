import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationObject;
import cs3500.animator.model.AnimationObjectInstructionImpl;
import cs3500.animator.model.BaseAnimationModel;
import cs3500.animator.model.RectangleAnimationObject;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

/**
 * In charge of testing the cs3500.animator.model.AnimationModel interface.
 */
public class TestAnimationModel {
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

  AnimationObjectInstructionImpl instruction1;
  AnimationObjectInstructionImpl instruction2;
  AnimationObjectInstructionImpl instruction3;
  AnimationObjectInstructionImpl instruction4Overlap;

  @Before
  public void init() {
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
  public void testInstructions() {
    try {
      instruction1 = new AnimationObjectInstructionImpl(1, 10, point1, point2,
              10, 10, 10, 20, black, red);
      instruction2 = new AnimationObjectInstructionImpl(10, 20, point2, point3,
              10, 10, 10, 20, black, red);
      instruction3 = new AnimationObjectInstructionImpl(20, 30, point3, point4,
              10, 10, 10, 69, pink, red);
    } catch (Exception e) {
      fail();
    }
  }

  @Test
  public void testInvalidInstructions() {
    //tick of 0 invalid
    try {
      new AnimationObjectInstructionImpl(0, 10, point1, point2,
              10, 10, 10, 20, black, red);
      fail();
    } catch (IllegalArgumentException ignored) {

    }
    //tick of negative value invalid
    try {
      new AnimationObjectInstructionImpl(-1, 10, point1, point2,
              10, 10, 10, 20, black, red);
      fail();
    } catch (IllegalArgumentException ignored) {

    }
    //tick of same start and end invalid
    try {
      new AnimationObjectInstructionImpl(10, 10, point1, point2,
              10, 10, 10, 20, black, red);
      fail();
    } catch (IllegalArgumentException ignored) {

    }
    //ending tick before starting tick invalid
    try {
      new AnimationObjectInstructionImpl(10, 9, point1, point2,
              10, 10, 10, 20, black, red);
      fail();
    } catch (IllegalArgumentException ignored) {

    }

    //begin testing Null in non-primitives
    try {
      new AnimationObjectInstructionImpl(1, 10, null, point2,
              10, 10, 10, 20, black, red);
      fail();
    } catch (IllegalArgumentException ignored) {

    }

    try {
      new AnimationObjectInstructionImpl(1, 10, point1, null,
              10, 10, 10, 20, black, red);
      fail();
    } catch (IllegalArgumentException ignored) {

    }

    try {
      new AnimationObjectInstructionImpl(1, 10, point1, point2,
              10, 10, 10, 20, null, red);
      fail();
    } catch (IllegalArgumentException ignored) {

    }

    try {
      new AnimationObjectInstructionImpl(1, 10, point1, point2,
              10, 10, 10, 20, black, null);
      fail();
    } catch (IllegalArgumentException ignored) {

    }

    //invalid dimension params
    try {
      new AnimationObjectInstructionImpl(1, 10, point1, point2,
              -1, 10, 10, 20, black, red);
      fail();
    } catch (IllegalArgumentException ignored) {

    }

    try {
      new AnimationObjectInstructionImpl(1, 10, point1, point2,
              10, -1, 2, 20, black, red);
      fail();
    } catch (IllegalArgumentException ignored) {

    }

    try {
      new AnimationObjectInstructionImpl(1, 10, point1, point2,
              10, 10, -2, 20, black, red);
      fail();
    } catch (IllegalArgumentException ignored) {

    }

    try {
      new AnimationObjectInstructionImpl(1, 10, point1, point2,
              10, 10, 10, -1, black, red);
      fail();
    } catch (IllegalArgumentException ignored) {

    }

    //both ticks negative but increasing
    try {
      new AnimationObjectInstructionImpl(-4, -2, point1, point2,
              10, 10, 10, 20, black, red);
      fail();
    } catch (IllegalArgumentException ignored) {

    }

    //all invalid
    try {
      new AnimationObjectInstructionImpl(-1, -10, null, null,
              -10, -100, -10,
              -20, null, null);
      fail();
    } catch (IllegalArgumentException ignored) {

    }

    //Test invalid creation with 3 valid but overlapping instructions
    try {
      AnimationObject object = new RectangleAnimationObject("test");
      /*
      object.addMotion(instruction1);
      object.addMotion(instruction2);
      object.addMotion(instruction4Overlap);
      */
      fail();
    } catch (IllegalArgumentException ignored) {

    }


  }

  @Test
  public void testValidConstructions() {
    try {
      new BaseAnimationModel();
    } catch (Exception e) {
      fail();
    }
    try {
      new BaseAnimationModel(object1);
    } catch (Exception e) {
      fail();
    }
  }

  @Test
  public void testPrintAnimation() {
    assertEquals("model Print:\n" +
            "motion Rect Time  X     Y     Width Heigh Red   Green Blue\n" +
            "            1     6     6     10    10    0     0     0    \n" +
            "            10    6     6     10    20    255   0     0    \n" +
            "\n" +
            "            10    20    20    10    10    0     0     0    \n" +
            "            20    20    20    10    20    255   0     0    \n" +
            "\n" +
            "            20    30    20    10    10    255   175   175  \n" +
            "            30    30    20    10    69    255   0     0    ", model2.printAnimation());
    assertEquals("model Print:\n", new BaseAnimationModel().printAnimation());
    assertEquals("model Print:\n" +
                    "motion Rect Time  X     Y     Width Heigh Red   Green Blue\n" +
                    "            1     6     6     10    10    0     0     0    \n" +
                    "            10    6     6     10    20    255   0     0    \n" +
                    "\n" +
                    "            10    20    20    10    10    0     0     0    \n" +
                    "            20    20    20    10    20    255   0     0    \n" +
                    "\n" +
                    "            20    30    20    10    10    255   175   175  \n" +
                    "            30    30    20    10    69    255   0     0    \n" +
                    "motion Rect Time  X     Y     Width Heigh Red   Green Blue\n" +
                    "            1     6     6     10    10    0     0     0    \n" +
                    "            10    6     6     10    20    255   0     0    ",
            new BaseAnimationModel(object1, object2).printAnimation());
  }

  /*
  @Test
  public void testUpdateObejcts() {
    try {
      model2.updateObjects();
    } catch (Exception ignored) {
      fail();
    }
    // It's hard to test this rn bc we don't have any public facing methods that can look at the
    // actual values of what an object should appear like.
  }
  */

}