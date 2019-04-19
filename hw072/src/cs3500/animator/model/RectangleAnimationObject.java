package cs3500.animator.model;

import java.awt.*;

/**
 * Represents a Rectangle of type cs3500.animator.model.AnimationObject.
 */
public class RectangleAnimationObject extends AbstractAnimationObject {

  /**
   * Constructor for cs3500.animator.model.RectangleAnimationObject with the given instructions.
   *
   * @param name Name of the Object
   */
  public RectangleAnimationObject(String name) {
    super(name);
  }

  @Override
  public void draw(Graphics g) {
    if (show && position != null && color != null) {
      Graphics2D g2 = (Graphics2D) g;
      g2.setColor(color);
      Rectangle rect = new Rectangle(position, new Dimension(width, height));
      g2.fill(rect);
      super.drawIfSelected(g2, rect);
    }
  }

  @Override
  public String printObject() {
    StringBuilder sb = new StringBuilder(super.printObject());
    sb.append("Rect Time  X     Y     Width Heigh Red   Green Blue\n");
    return super.printObjectHelper(sb);
  }

  @Override
  public String getSVG(int millisPerTick) {
    StringBuilder sb = new StringBuilder("\n<rect ");
    super.getSVGHelper();
    int colorR = getColor().getRed();
    int colorG = getColor().getGreen();
    int colorB = getColor().getBlue();
    sb.append("id=\"" + getObjectName() + "\" x=\"" + getPosition().x + "\" y=\"" + getPosition().y
            + "\" width=\"" + getWidth() + "\" height=\"" + getHeight() + "\" fill=\"rgb("
            + colorR + "," + colorG + "," + colorB + ")"
            + "\" visibility=\"visible" + "\" >\n\n");

    for (AnimationObjectInstruction a : animations) {
      sb.append(a.getSVG(millisPerTick, "rect"));
    }

    sb.append("</rect>\n");
    return sb.toString();

  }

}
