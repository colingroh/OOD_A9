import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import cs3500.animator.Excellence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests the textual view interface.
 */
public class TestTextualView {

  private final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private final PrintStream originalErr = System.err;

  String[] simpleCL;
  String[] tohCL;
  String[] simpleCLWithOut;
  String[] simpleCLWithSpeed;

  String[] invView;
  String[] noView;
  String[] invInput;
  String[] noArgIn;
  String[] noInput;
  String[] invSpeed;
  String[] negSpeed;
  String[] invOutput;
  String[] invCase;
  String simpleOut;
  String tohOut;

  @Before
  public void setUpStreams() {
    System.setOut(new PrintStream(buffer));
    System.setErr(new PrintStream(errContent));
  }

  @After
  public void restoreStreams() {
    System.setOut(originalOut);
    System.setErr(originalErr);
  }

  @Before
  public void init() {

    tohOut = "oOo";

    simpleCL = "-in smalldemo.txt -view text".split(" ");
    simpleCLWithOut = "-in smalldemo.txt -view text -out testText.txt".split(" ");
    simpleCLWithSpeed = "-in smalldemo.txt -view text -speed 20".split(" ");
    tohCL = "-in toh-3.txt -view text".split(" ");

    invView = "-in smalldemo.txt -view gorgeous".split(" ");
    noView = "-in smalldemo.txt ".split(" ");
    invInput = "-in FastNFurious.txt -view text".split(" ");
    noArgIn = "-in -view text".split(" ");
    noInput = "-view text".split(" ");
    invSpeed = "-in smalldemo.txt  -view text -speed 1001".split(" ");
    negSpeed = "-in smalldemo.txt -view text -speed -100".split(" ");
    invOutput = "-in smalldemo.txt -view text -out ".split(" ");
    invCase = "-in smalldemo.txt -view TEXT".split(" ");

  }


  @Test
  public void out() {
    System.out.print("hello");
    assertEquals("hello", buffer.toString());
  }

  @Test
  public void err() {
    System.err.print("hello again");
    assertEquals("hello again", errContent.toString());
  }

  @Test
  public void validInputBase() {

    Excellence.main(simpleCL);

    simpleOut = buffer.toString();
    assertEquals(("canvas 0 0 100 100\n" +
            "model Print:\n" +
            "motion Rect Time  X     Y     Width Heigh Red   Green Blue\n" +
            "            1     200   200   50    100   255   0     0    \n" +
            "            10    200   200   50    100   255   0     0    \n" +
            "\n" +
            "            10    200   200   50    100   255   0     0    \n" +
            "            50    200   200   50    100   255   0     0    \n" +
            "\n" +
            "            50    300   300   50    100   255   0     0    \n" +
            "            51    300   300   50    100   255   0     0    \n" +
            "\n" +
            "            51    300   300   50    100   255   0     0    \n" +
            "            70    300   300   25    100   255   0     0    \n" +
            "\n" +
            "            70    300   300   25    100   255   0     0    \n" +
            "            100   300   300   25    100   255   0     0    \n" +
            "motion Circ Time  X     Y     Width Heigh Red   Green Blue\n" +
            "            6     440   70    120   60    0     0     255  \n" +
            "            20    440   70    120   60    0     0     255  \n" +
            "\n" +
            "            20    440   70    120   60    0     0     255  \n" +
            "            50    440   70    120   60    0     0     255  \n" +
            "\n" +
            "            50    440   250   120   60    0     0     255  \n" +
            "            70    440   250   120   60    0     170   85   \n" +
            "\n" +
            "            70    440   370   120   60    0     170   85   \n" +
            "            80    440   370   120   60    0     255   0    \n" +
            "\n" +
            "            80    440   370   120   60    0     255   0    \n" +
            "            100   440   370   120   60    0     255   0    \r\n"), simpleOut);
  }

  @Test
  public void validInputOutFile() {

    Excellence.main(simpleCLWithOut);

    String tmpVar = "canvas 0 0 100 100\n" +
            "model Print:\n" +
            "motion Rect Time  X     Y     Width Heigh Red   Green Blue\n" +
            "            1     200   200   50    100   255   0     0    \n" +
            "            10    200   200   50    100   255   0     0    \n" +
            "\n" +
            "            10    200   200   50    100   255   0     0    \n" +
            "            50    200   200   50    100   255   0     0    \n" +
            "\n" +
            "            50    300   300   50    100   255   0     0    \n" +
            "            51    300   300   50    100   255   0     0    \n" +
            "\n" +
            "            51    300   300   50    100   255   0     0    \n" +
            "            70    300   300   25    100   255   0     0    \n" +
            "\n" +
            "            70    300   300   25    100   255   0     0    \n" +
            "            100   300   300   25    100   255   0     0    \n" +
            "motion Circ Time  X     Y     Width Heigh Red   Green Blue\n" +
            "            6     440   70    120   60    0     0     255  \n" +
            "            20    440   70    120   60    0     0     255  \n" +
            "\n" +
            "            20    440   70    120   60    0     0     255  \n" +
            "            50    440   70    120   60    0     0     255  \n" +
            "\n" +
            "            50    440   250   120   60    0     0     255  \n" +
            "            70    440   250   120   60    0     170   85   \n" +
            "\n" +
            "            70    440   370   120   60    0     170   85   \n" +
            "            80    440   370   120   60    0     255   0    \n" +
            "\n" +
            "            80    440   370   120   60    0     255   0    \n" +
            "            100   440   370   120   60    0     255   0    \r\n";
    simpleOut = buffer.toString();

    assertEquals(tmpVar, simpleOut);
  }

  @Test
  public void validInputSpeed() {

    Excellence.main(simpleCLWithSpeed);

    simpleOut = buffer.toString();
    assertEquals(("canvas 0 0 100 100\n" +
            "model Print:\n" +
            "motion Rect Time  X     Y     Width Heigh Red   Green Blue\n" +
            "            1     200   200   50    100   255   0     0    \n" +
            "            10    200   200   50    100   255   0     0    \n" +
            "\n" +
            "            10    200   200   50    100   255   0     0    \n" +
            "            50    200   200   50    100   255   0     0    \n" +
            "\n" +
            "            50    300   300   50    100   255   0     0    \n" +
            "            51    300   300   50    100   255   0     0    \n" +
            "\n" +
            "            51    300   300   50    100   255   0     0    \n" +
            "            70    300   300   25    100   255   0     0    \n" +
            "\n" +
            "            70    300   300   25    100   255   0     0    \n" +
            "            100   300   300   25    100   255   0     0    \n" +
            "motion Circ Time  X     Y     Width Heigh Red   Green Blue\n" +
            "            6     440   70    120   60    0     0     255  \n" +
            "            20    440   70    120   60    0     0     255  \n" +
            "\n" +
            "            20    440   70    120   60    0     0     255  \n" +
            "            50    440   70    120   60    0     0     255  \n" +
            "\n" +
            "            50    440   250   120   60    0     0     255  \n" +
            "            70    440   250   120   60    0     170   85   \n" +
            "\n" +
            "            70    440   370   120   60    0     170   85   \n" +
            "            80    440   370   120   60    0     255   0    \n" +
            "\n" +
            "            80    440   370   120   60    0     255   0    \n" +
            "            100   440   370   120   60    0     255   0    \r\n"), simpleOut);
  }


  @Test
  public void invalidCases() {
    try {
      Excellence.main(invView);
      fail();
    } catch (IllegalArgumentException ignored) {
    }

    try {
      Excellence.main(noView);
      fail();
    } catch (IllegalArgumentException ignored) {
    }

    try {
      Excellence.main(invInput);
      fail();
    } catch (NullPointerException ignored) {
    }

    try {
      Excellence.main(noInput);
      fail();
    } catch (IllegalArgumentException ignored) {
    }

    try {
      Excellence.main(negSpeed);
      fail();
    } catch (UnsupportedOperationException ignored) {
    }
    try {
      Excellence.main(invSpeed);
      fail();
    } catch (UnsupportedOperationException ignored) {
    }
    try {
      Excellence.main(invOutput);
      fail();
    } catch (IllegalArgumentException ignored) {
    }
    try {
      Excellence.main(invCase);
      fail();
    } catch (IllegalArgumentException ignored) {
    }
  }

}
