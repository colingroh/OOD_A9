package cs3500.animator.model;

import java.awt.*;

/**
 * Abstract implementation of an ObjectInstruction.
 */
public class KeyFrame {
  protected int tick;
  protected Point point;
  protected int width;
  protected int height;
  protected Color color;

  /**
   * This is a keyFrame.
   * @param tick Tick
   * @param point Point
   * @param width Width
   * @param height Height
   * @param color Color
   */
  public KeyFrame(int tick, Point point, int width, int height, Color color) {
    if (tick <= 0) {
      throw new IllegalArgumentException("Ticks must start before 0");
    }
    if (width < 0 || height < 0 || point == null || color == null) {
      throw new IllegalArgumentException("Invalid Parameters");
    }
    this.tick = tick;
    this.point = point;
    this.width = width;
    this.height = height;
    this.color = color;
  }

  public int getTick() {
    return tick;
  }

  /**
   * Combine the color of two KeyFrames.
   * @param other KeyFrame
   * @param curTick Current tick.
   * @return Combined Color
   */
  public Color combineColor(KeyFrame other, int curTick) {
    double percentComplete = getPercentComplete(curTick, other.getTick());
    return new Color(((int) (color.getRed() + ((other.getColor().getRed() - color.getRed())
            * percentComplete))),
            ((int) (color.getGreen() + ((other.getColor().getGreen() - color.getGreen())
                    * percentComplete))),
            ((int) (color.getBlue() + ((other.getColor().getBlue() - color.getBlue())
                    * percentComplete))));
  }

  public int combineWidth(KeyFrame other, int curTick) {
    double percentComplete = getPercentComplete(curTick, other.getTick());
    return (int) (width + ((other.getWidth() - width) * percentComplete));
  }

  public int combineHeight(KeyFrame other, int curTick) {
    double percentComplete = getPercentComplete(curTick, other.getTick());
    return (int) (height + ((other.getHeight() - height) * percentComplete));
  }

  public Point combinePoint(KeyFrame other, int curTick) {
    double percentComplete = getPercentComplete(curTick, other.getTick());
    return new Point((int) (point.x + ((other.getPoint().x - point.x) * percentComplete)),
            (int) (point.y + ((other.getPoint().y - point.y) * percentComplete)));
  }

  private double getPercentComplete(int curTick, int otherTick) {
    double percent = (curTick - this.tick) / (double) (otherTick - tick);
    if (percent > 1 || percent < 0) {
      throw new IllegalArgumentException();
    }
    return percent;
  }

  public Point getPoint() {
    return point;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public Color getColor() {
    return color;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof KeyFrame) {
      KeyFrame frame = (KeyFrame) obj;
      return frame.getPoint().equals(point) && frame.getHeight() == height && frame.getWidth()
              == width && frame.getColor().equals(color) && frame.getTick() == tick;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public String toString() {
    return "T: " + tick + " x: " + point.x + " y: " + point.y + " w: " + width + " h: " + height +
            " r: " + color.getRed() + " g: " + color.getGreen() + " b: " + color.getBlue();
  }
}