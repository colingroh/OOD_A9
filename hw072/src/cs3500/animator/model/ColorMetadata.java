package cs3500.animator.model;

import java.awt.*;

/**
 * Class that represent an animation the changes the Color.
 */
public class ColorMetadata extends AbstractAnimationMetadata<Color> {

  /**
   * Constructor for changing from a color to a color.
   *
   * @param from The starting Color.
   * @param to   The ending Color.
   */
  public ColorMetadata(Color from, Color to) {
    super(from, to, AnimationFields.COLOR);
  }

  @Override
  public Color updateValue(double percentComplete) {
    super.updateValue(percentComplete);
    return new Color(((int) (to.getRed() + ((from.getRed() - to.getRed()) * percentComplete))),
            ((int) (to.getGreen() + ((from.getGreen() - to.getGreen()) * percentComplete))),
            ((int) (to.getBlue() + ((from.getBlue() - to.getBlue()) * percentComplete))));
  }

  @Override
  public String getSVG(int startTick, int endTick, int millisPerTick, String shapeType) {
    int msStart = startTick * millisPerTick;
    int msDur = (endTick * millisPerTick) - msStart;
    int fromR = from.getRed();
    int fromG = from.getGreen();
    int fromB = from.getBlue();
    int toR = from.getRed();
    int toG = from.getGreen();
    int toB = from.getBlue();

    StringBuilder sb = new StringBuilder();
    if (!from.equals(to)) {
      sb.append("    <animate attributeType=\"xml\" begin=\"")
              .append(msStart)
              .append(".0ms\" dur=\"")
              .append(msDur)
              .append(".0ms\" attributeName=\"fill\" from=\"rgb(")
              .append(fromR)
              .append(",")
              .append(fromG)
              .append(",")
              .append(fromB)
              .append(")\" to=\"rgb(")
              .append(toR)
              .append(",")
              .append(toG)
              .append(",")
              .append(toB)
              .append(")\"/>\n");
    }

    return sb.toString();
  }

  @Override
  public void updateObject(AnimationObject object, double percentComplete) {
    object.setColor(updateValue(percentComplete));
  }
}
