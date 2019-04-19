package cs3500.animator.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract implementation of an ObjectInstruction.
 */
public class AnimationObjectInstructionImpl implements AnimationObjectInstruction {
  protected List<AnimationMetadata> thingsToAnimate;
  private int startingTick;
  private int endingTick;
  private int startWidth;
  private int endWidth;
  private int startHeight;
  private int endHeight;
  private Point startPoint;
  private Point endPoint;
  private Color startColor;
  private Color endColor;

  /**
   * Constructor for an instruction that starts and end at startingTick and endingTick.
   *
   * @param startingTick Tick to start at.
   * @param endingTick   Tick to end at.
   * @throws IllegalArgumentException if starting tick isn't less than ending tick or if the tick is
   *                                  less than 1.
   */
  public AnimationObjectInstructionImpl(int startingTick, int endingTick, Point startPoint,
                                        Point endPoint, int startWidth, int endWidth,
                                        int startHeight, int endHeight, Color startColor,
                                        Color endColor) {
    if (startingTick > endingTick) {
      throw new IllegalArgumentException("Starting tick must be before ending tick.");
    }
    if (startingTick <= 0) {
      throw new IllegalArgumentException("Ticks must start before 0");
    }
    this.startingTick = startingTick;
    this.endingTick = endingTick;
    if (startWidth < 0 || endWidth < 0 || startHeight < 0 || endHeight < 0 || startPoint == null ||
            endPoint == null || startColor == null || endColor == null) {
      throw new IllegalArgumentException("Invalid Parameters");
    }
    this.startWidth = startWidth;
    this.endWidth = endWidth;
    this.startHeight = startHeight;
    this.endHeight = endHeight;
    this.startPoint = startPoint;
    this.endPoint = startPoint;
    this.startColor = startColor;
    this.endColor = endColor;
    thingsToAnimate = new ArrayList<>();
    thingsToAnimate.add(new PointMetadata(startPoint, endPoint));
    thingsToAnimate.add(new ColorMetadata(startColor, endColor));
    thingsToAnimate.add(new DimenMetadata(startWidth, endWidth, AnimationFields.WIDTH));
    thingsToAnimate.add(new DimenMetadata(startHeight, endHeight, AnimationFields.HEIGHT));
  }

  @Override
  public int getStartingTick() {
    return startingTick;
  }

  @Override
  public int getEndingTick() {
    return endingTick;
  }

  @Override
  public void update(AnimationObject object, int curTick) {
    for (AnimationMetadata type : thingsToAnimate) {

      double percentComplete = ((double) (endingTick - curTick))
              / ((double) (endingTick - startingTick));
      if (Double.isInfinite(percentComplete)) {
        percentComplete = 1;
      }

      type.updateObject(object, percentComplete);
    }
  }

  @Override
  public String printInstruction() {
    return String.format("            %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s\n" +
                    "            %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s", getStartingTick(),
            startPoint.x, startPoint.y, startWidth, startHeight, startColor.getRed(),
            startColor.getGreen(), startColor.getBlue(), getEndingTick(), endPoint.x, endPoint.y,
            endWidth, endHeight, endColor.getRed(), endColor.getGreen(), endColor.getBlue());
  }

  @Override
  public String getSVG(int millisPerTick, String shapeType) {
    if (shapeType.equals("rect")) {
      return getRectSVG(millisPerTick);
    } else if (shapeType.equals("circle")) {
      return getCircSVG(millisPerTick);
    } else {
      throw new IllegalArgumentException();
    }
  }

  private String getRectSVG(int millisPerTick) {
    StringBuilder animateList = new StringBuilder();

    for (AnimationMetadata a : thingsToAnimate) {
      animateList.append(a.getSVG(getStartingTick(), getEndingTick(), millisPerTick,
              "rect"));
    }


    return animateList.toString();
  }

  private String getCircSVG(int millisPerTick) {
    StringBuilder animateList = new StringBuilder();

    for (AnimationMetadata a : thingsToAnimate) {
      animateList.append(a.getSVG(getStartingTick(), getEndingTick(), millisPerTick,
              "circle"));
    }


    return animateList.toString();
  }
}
