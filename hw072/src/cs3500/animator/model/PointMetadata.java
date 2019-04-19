package cs3500.animator.model;

import java.awt.*;

/**
 * Class that represent an animation the changes the Location.
 */
public class PointMetadata extends AbstractAnimationMetadata<Point> {

  /**
   * Constructor for changing from a Location to a Location.
   *
   * @param from The starting Location.
   * @param to   The ending Location.
   */
  public PointMetadata(Point from, Point to) {
    super(from, to, AnimationFields.LOCATION);
  }

  @Override
  public Point updateValue(double percentComplete) {
    super.updateValue(percentComplete);
    return new Point((int) (to.x + ((from.x - to.x) * percentComplete)),
            (int) (to.y + ((from.y - to.y) * percentComplete)));
  }

  @Override
  public String getSVG(int start, int end, int millisPerTick, String shapeType) {
    int msStart = start * millisPerTick;
    int msDur = (end * millisPerTick) - msStart;
    StringBuilder sb = new StringBuilder();
    if (from.x != to.x) {
      sb.append("    <animate attributeType=\"xml\" begin=\"")
              .append(msStart)
              .append(".0ms\" dur=\"")
              .append(msDur)
              .append(".0ms\" attributeName=\"")
              .append(shapeType.equals("circle") ?
                      "cx" :
                      "x")
              .append("\" from=\"")
              .append(from.x)
              .append("\" to=\"")
              .append(to.x)
              .append("\" />\n");
    }

    if (from.y != to.y) {
      sb.append("    <animate attributeType=\"xml\" begin=\"")
              .append(msStart)
              .append(".0ms\" dur=\"")
              .append(msDur)
              .append(".0ms\" attributeName=\"")
              .append(shapeType.equals("circle") ?
                      "cy" :
                      "y")
              .append("\" from=\"")
              .append(from.y)
              .append("\" to=\"")
              .append(to.y)
              .append("\" />\n");
    }

    return sb.toString();
  }

  @Override
  public void updateObject(AnimationObject object, double percentComplete) {
    object.setPosition(updateValue(percentComplete));
  }
}
