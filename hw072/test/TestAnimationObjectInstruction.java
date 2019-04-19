import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import cs3500.animator.model.AnimationObjectInstructionImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * In charge of testing the AnimationObjectInstructions interface.
 */
public class TestAnimationObjectInstruction {

  private AnimationObjectInstructionImpl instruction;

  @Before
  public void init() {
    instruction = new AnimationObjectInstructionImpl(1, 3,
            new Point(1, 1), new Point(1, 1), 10, 10,
            20, 40,
            Color.RED, Color.BLUE);
  }

  @Before
  public void testInvalidConstructor() {
    try {
      new AnimationObjectInstructionImpl(0, 3,
              new Point(1, 1), new Point(1, 1), 10, 10,
              20, 40,
              Color.RED, Color.BLUE);
      fail();
    } catch (IllegalArgumentException ignored) {

    }
    try {
      new AnimationObjectInstructionImpl(1, 1,
              new Point(1, 1), new Point(1, 1), 10, 10,
              20, 40,
              Color.RED, Color.BLUE);
      fail();
    } catch (IllegalArgumentException ignored) {

    }
    try {
      new AnimationObjectInstructionImpl(1, -5,
              new Point(1, 1), new Point(1, 1), 10, 10,
              20, 40,
              Color.RED, Color.BLUE);
      fail();
    } catch (IllegalArgumentException ignored) {

    }
    try {
      new AnimationObjectInstructionImpl(1, 3,
              null, new Point(1, 1), 10, 10,
              20, 40,
              Color.RED, Color.BLUE);
      fail();
    } catch (IllegalArgumentException ignored) {

    }
    try {
      new AnimationObjectInstructionImpl(1, 3,
              new Point(1, 1), null, 10, 10,
              20, 40,
              Color.RED, Color.BLUE);
      fail();
    } catch (IllegalArgumentException ignored) {

    }
    try {
      new AnimationObjectInstructionImpl(1, 3,
              new Point(1, 1), new Point(1, 1), 0, 10,
              20, 40,
              Color.RED, Color.BLUE);
    } catch (IllegalArgumentException ignored) {
      fail();
    }
    try {
      new AnimationObjectInstructionImpl(1, 3,
              new Point(1, 1), new Point(1, 1), -1, 10,
              20, 40,
              Color.RED, Color.BLUE);
      fail();
    } catch (IllegalArgumentException ignored) {

    }
    try {
      new AnimationObjectInstructionImpl(1, 3,
              new Point(1, 1), new Point(1, 1), 10, -1,
              20, 40,
              Color.RED, Color.BLUE);
      fail();
    } catch (IllegalArgumentException ignored) {

    }
    try {
      new AnimationObjectInstructionImpl(1, 3,
              new Point(1, 1), new Point(1, 1), 10, 0,
              20, 40,
              Color.RED, Color.BLUE);
    } catch (IllegalArgumentException ignored) {
      fail();
    }
    try {
      new AnimationObjectInstructionImpl(1, 3,
              new Point(1, 1), new Point(1, 1), 10, 10,
              -1, 40,
              Color.RED, Color.BLUE);
      fail();
    } catch (IllegalArgumentException ignored) {

    }
    try {
      new AnimationObjectInstructionImpl(1, 3,
              new Point(1, 1), new Point(1, 1), 10, 10,
              20, -1,
              Color.RED, Color.BLUE);
      fail();
    } catch (IllegalArgumentException ignored) {

    }
    try {
      new AnimationObjectInstructionImpl(1, 3,
              new Point(1, 1), new Point(1, 1), 10, 10,
              20, 40,
              null, Color.BLUE);
      fail();
    } catch (IllegalArgumentException ignored) {

    }
    try {
      new AnimationObjectInstructionImpl(1, 3,
              new Point(1, 1), new Point(1, 1), 10, 10,
              20, 40,
              Color.RED, null);
      fail();
    } catch (IllegalArgumentException ignored) {

    }
  }

  @Test
  public void testgetStartingTick() {
    assertEquals(instruction.getStartingTick(), 1);
  }

  @Test
  public void testGetEndingTick() {
    assertEquals(instruction.getEndingTick(), 3);
  }

  /*@Test
  public void testUpdate() {
    // Can't really test this either right now as we don't have any public facing methods as to
    // how an object should be displayed. We can update this when we know how to draw an object.
  }*/

  @Test
  public void testPrintInstruction() {
    assertEquals("            1     1     1     10    20    255   0     0    \n" +
                    "            3     1     1     10    40    0     0     255  ",
            instruction.printInstruction());
  }
}
