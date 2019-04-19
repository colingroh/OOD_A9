package cs3500.animator.model;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Represents a Rectangle of type cs3500.animator.model.AnimationObject.
 */
public class CircleAnimationObject extends AbstractAnimationObject {

  /**
   * Constructor for cs3500.animator.model.RectangleAnimationObject with the given instructions.
   *
   * @param name Name of the animationObject.
   */
  public CircleAnimationObject(String name) {
    super(name);
  }

  @Override
  public void draw(Graphics g) {
    if (show && position != null && color != null) {
      Graphics2D g2 = (Graphics2D) g;
      g2.setColor(color);
      Ellipse2D circ = new Ellipse2D.Double(position.x, position.y, width, height);
      g2.fill(circ);
      super.drawIfSelected(g2, circ);
    }
  }

  @Override
  public String printObject() {
    StringBuilder sb = new StringBuilder(super.printObject());
    sb.append("Circ Time  X     Y     Width Heigh Red   Green Blue\n");
    return super.printObjectHelper(sb);
  }


  @Override
  public String getSVG(int millisPerTick) {
    StringBuilder sb = new StringBuilder("\n<ellipse ");
    super.getSVGHelper();
    int colorR = getColor().getRed();
    int colorG = getColor().getGreen();
    int colorB = getColor().getBlue();
    sb.append("id=\"")
            .append(getObjectName())
            .append("\" cx=\"")
            .append(getPosition().x)
            .append("\" cy=\"")
            .append(getPosition().y)
            .append("\" rx=\"")
            .append(getWidth())
            .append("\" ry=\"")
            .append(getHeight())
            .append("\" fill=\"rgb(")
            .append(colorR)
            .append(",")
            .append(colorG)
            .append(",")
            .append(colorB)
            .append(")")
            .append("\" visibility=\"visible")
            .append("\" >\n\n");

    for (AnimationObjectInstruction a : animations) {
      sb.append(a.getSVG(millisPerTick, "circle"));
    }

    sb.append("</ellipse>\n");
    return sb.toString();
  }

}
