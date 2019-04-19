import org.junit.Test;

import java.awt.*;

import cs3500.animator.model.AnimationObject;
import cs3500.animator.model.AnimationObjectInstructionImpl;
import cs3500.animator.model.RectangleAnimationObject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * In charge of testing the cs3500.animator.model.AnimationObject interface.
 */
public class TestAnimationObject {
  /*  @Test
  public void testOnTick() {
    assertTrue(true);
    // Can't really test this either right now as we don't have any public facing methods as to
    // how an object should be displayed. We can update this when we know how to draw an object.
  }*/

  @Test
  public void testConstructor() {
    AnimationObjectInstructionImpl instruction = new AnimationObjectInstructionImpl(1, 3,
            new Point(1, 1), new Point(1, 1), 10, 10, 20, 40,
            Color.RED, Color.BLUE);
    try {
      new RectangleAnimationObject("test");
      fail();
    } catch (IllegalArgumentException ignored) {

    }
    try {
      new RectangleAnimationObject("test").addMotion(1, 3,
              new Point(1, 1), new Point(1, 1), 10, 10, 20, 40,
              Color.RED, Color.BLUE);
    } catch (IllegalArgumentException ignored) {
      fail();
    }
    try {
      new RectangleAnimationObject(null).addMotion(1, 3,
              new Point(1, 1), new Point(1, 1), 10, 10, 20, 40,
              Color.RED, Color.BLUE);
      fail();
    } catch (IllegalArgumentException ignored) {

    }
  }

  @Test
  public void testIsAnimationComplete() {
    AnimationObjectInstructionImpl instruction = new AnimationObjectInstructionImpl(1, 3,
            new Point(1, 1), new Point(1, 1), 10, 10, 20, 40,
            Color.RED, Color.BLUE);
    AnimationObject object = new RectangleAnimationObject("test");
    object.addMotion(1, 3,
            new Point(1, 1), new Point(1, 1), 10, 10, 20, 40,
            Color.RED, Color.BLUE);
    assertFalse(object.isAnimationComplete());
    object.onTick(2);
    assertFalse(object.isAnimationComplete());
    object.onTick(3);
    assertTrue(object.isAnimationComplete());
  }

  /*  @Test
  public void testUpdate() {
    assertTrue(true);
    // Can't really test this either right now as we don't have any public facing methods as to
    // how an object should be displayed. We can update this when we know how to draw an object.
  }*/

  /*  @Test
  public void testDraw() {
    assertTrue(true);
    // Can't really test this either right now as we don't have any public facing methods as to
    // how an object should be displayed. We can update this when we know how to draw an object.
  }*/

  @Test
  public void testPrintObject() {
    AnimationObjectInstructionImpl instruction = new AnimationObjectInstructionImpl(1, 3,
            new Point(1, 1), new Point(1, 1), 10, 10, 20, 40,
            Color.RED, Color.BLUE);
    AnimationObject object = new RectangleAnimationObject("test");
    assertEquals("motion Rect Time  X     Y     Width Heigh Red   Green Blue\n" +
            "            1     1     1     10    20    255   0     0    \n" +
            "            3     1     1     10    40    0     0     255  ", object.printObject());
  }


}
