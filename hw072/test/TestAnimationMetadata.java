import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import cs3500.animator.model.AnimationFields;
import cs3500.animator.model.ColorMetadata;
import cs3500.animator.model.DimenMetadata;
import cs3500.animator.model.PointMetadata;

import static junit.framework.Assert.fail;
import static org.junit.Assert.assertEquals;

/**
 * In charge of testing the cs3500.animator.model.AnimationMetadata interface.
 */
public class TestAnimationMetadata {
  private ColorMetadata greenToBlue;
  private ColorMetadata greenToRed;
  private DimenMetadata zeroToPositiveH;
  private DimenMetadata zeroToPositiveW;
  private DimenMetadata twoPositiveIncH;
  private DimenMetadata twoPositiveIncW;
  private DimenMetadata twoPositiveDecH;
  private DimenMetadata twoPositiveDecW;
  private PointMetadata zeroToPositivePt;
  private PointMetadata zeroToNegativePt;
  private PointMetadata negativeToPositivePt;

  private Point zero;
  private Point positivePt;
  private Point negativePt;

  @Before
  public void init() {
    greenToBlue = new ColorMetadata(Color.GREEN, Color.BLUE);
    greenToRed = new ColorMetadata(Color.GREEN, Color.RED);

    zeroToPositiveH = new DimenMetadata(0, 150, AnimationFields.HEIGHT);
    zeroToPositiveW = new DimenMetadata(0, 150, AnimationFields.WIDTH);
    twoPositiveIncH = new DimenMetadata(0, 20, AnimationFields.HEIGHT);
    twoPositiveIncW = new DimenMetadata(0, 20, AnimationFields.WIDTH);
    twoPositiveDecH = new DimenMetadata(300, 200, AnimationFields.HEIGHT);
    twoPositiveDecW = new DimenMetadata(300, 200, AnimationFields.WIDTH);

    zero = new Point(0, 0);
    positivePt = new Point(100, 100);
    negativePt = new Point(-100, -100);
    zeroToPositivePt = new PointMetadata(zero, positivePt);
    zeroToNegativePt = new PointMetadata(zero, negativePt);
    negativeToPositivePt = new PointMetadata(negativePt, positivePt);

  }

  @Test
  public void testColorMetadata() {

    try {
      greenToBlue = new ColorMetadata(Color.GREEN, Color.BLUE);
      greenToRed = new ColorMetadata(Color.GREEN, Color.RED);
    } catch (Exception e) {
      fail();
    }


    //Testing updateValue
    assertEquals(127, greenToRed.updateValue(.5).getGreen(), 3);
    assertEquals(63, greenToRed.updateValue(.75).getRed(), 3);

    assertEquals(127, greenToBlue.updateValue(.5).getGreen(), 3);
    assertEquals(63, greenToBlue.updateValue(.75).getBlue(), 3);


  }

  @Test
  public void testInvalidColor() {
    //Test null constructor
    try {
      new ColorMetadata(null, Color.RED);
      fail();
    } catch (IllegalArgumentException ignored) {
    }

    try {
      new ColorMetadata(Color.GREEN, null);
      fail();
    } catch (IllegalArgumentException ignored) {
    }


    //test invalid inputs for Update Value
    try {
      greenToBlue.updateValue(1.5);
      fail();
    } catch (IllegalArgumentException ignored) {
    }

    try {
      greenToBlue.updateValue(-3);
      fail();
    } catch (IllegalArgumentException ignored) {
    }
  }

  @Test
  public void testDimenMetadata() {

    try {
      zeroToPositiveH = new DimenMetadata(0, 150, AnimationFields.HEIGHT);
      zeroToPositiveW = new DimenMetadata(0, 150, AnimationFields.WIDTH);
      twoPositiveIncH = new DimenMetadata(0, 20, AnimationFields.HEIGHT);
      twoPositiveIncW = new DimenMetadata(0, 20, AnimationFields.WIDTH);
      twoPositiveDecH = new DimenMetadata(300, 200, AnimationFields.HEIGHT);
      twoPositiveDecW = new DimenMetadata(300, 200, AnimationFields.WIDTH);
    } catch (Exception e) {
      fail();

      //test Update Value
      assertEquals(75, zeroToPositiveH.updateValue(.5), 1);
      assertEquals(75, zeroToPositiveW.updateValue(.5), 1);
      assertEquals(10, twoPositiveIncH.updateValue(.5), 1);
      assertEquals(10, twoPositiveIncW.updateValue(.5), 1);
      assertEquals(250, twoPositiveDecH.updateValue(.5), 1);
      assertEquals(250, twoPositiveDecW.updateValue(.5), 1);
    }
  }

  @Test
  public void testInvalidDimen() {

    //invalidConstructor
    try {
      new DimenMetadata(null, 4, AnimationFields.HEIGHT);
      fail();
    } catch (IllegalArgumentException ignored) {
    }

    try {
      new DimenMetadata(5, null, AnimationFields.WIDTH);
      fail();
    } catch (IllegalArgumentException ignored) {
    }

    try {
      new DimenMetadata(5, 7, AnimationFields.COLOR);
      fail();
    } catch (IllegalArgumentException ignored) {
    }

    try {
      new DimenMetadata(7, -8, null);
      fail();
    } catch (IllegalArgumentException ignored) {
    }


    try {
      zeroToPositiveH.updateValue(-1);
      fail();
    } catch (IllegalArgumentException ignored) {

    }

    try {
      zeroToPositiveH.updateValue(2);
      fail();
    } catch (IllegalArgumentException ignored) {

    }

    try {
      zeroToPositiveW.updateValue(2);
      fail();
    } catch (IllegalArgumentException ignored) {

    }

    try {
      zeroToPositiveW.updateValue(-1);
      fail();
    } catch (IllegalArgumentException ignored) {

    }

  }

  @Test
  public void testPositionMetadata() {

    try {
      zeroToPositivePt = new PointMetadata(zero, positivePt);
      zeroToNegativePt = new PointMetadata(zero, negativePt);
      negativeToPositivePt = new PointMetadata(negativePt, positivePt);
    } catch (Exception e) {
      fail();
    }

    assertEquals(zero, negativeToPositivePt.updateValue(.5));
    assertEquals(new Point(50, 50), zeroToPositivePt.updateValue(.5));
    assertEquals(new Point(-50, -50), zeroToNegativePt.updateValue(.5));
    assertEquals(new Point(25, 25), zeroToPositivePt.updateValue(.25));
  }

}