package cs3500.animator.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cs3500.animator.util.AnimationBuilder;

/**
 * Abstract implementation of an cs3500.animator.model.AnimationModel.
 */
public abstract class AbstractAnimationModel implements AnimationModel {

  protected List<AnimationObject> objects;
  protected int curTick;
  private int x;
  private int y;
  private int width;
  private int height;

  /**
   * Constructor of an Animation model with given objects.
   *
   * @param objs AnimationObjects to be represented in this model.
   * @throws IllegalArgumentException if any of the AnimationObjects are null.
   */
  public AbstractAnimationModel(AnimationObject... objs) {
    objects = new LinkedList<>();
    for (AnimationObject obj : objs) {
      if (obj == null) {
        throw new IllegalArgumentException("Cannot give a null object");
      }
      ((LinkedList<AnimationObject>) objects).addLast(obj);
    }
    curTick = 1;
  }

  @Override
  public String getSVG(int millisPerTick) {
    StringBuilder sb = new StringBuilder();
    sb.append("<svg width=\"")
            .append(width)
            .append("\" height=\"")
            .append(height)
            .append("\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n");
    for (AnimationObject object : objects) {
      sb.append(object.getSVG(millisPerTick)); //TODO
    }
    sb.append("\n</svg>");
    return sb.toString();
  }

  @Override
  public void updateObjects(boolean restart, boolean increaseTick) {
    if (restart) {
      curTick = 1;
    }
    for (AnimationObject anim : objects) {
      anim.onTick(curTick);
    }
    if (increaseTick) {
      curTick++;
    }
  }

  @Override
  public boolean isFinished() {
    for (AnimationObject object : objects) {
      if (!object.isAnimationComplete()) {
        return false;
      }
    }
    return true;
  }

  @Override
  public String printAnimation() {
    StringBuilder sb = new StringBuilder();
    sb.append("canvas ")
            .append(x)
            .append(" ")
            .append(y)
            .append(" ")
            .append(width)
            .append(" ")
            .append(height)
            .append("\n");
    sb.append("model Print:\n");
    for (int i = 0; i < objects.size(); i++) {
      sb.append(objects.get(i).printObject());
      if (i < objects.size() - 1) {
        sb.append("\n");
      }
    }
    return sb.toString();
  }

  @Override
  public void setBounds(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  @Override
  public int getX() {
    return x;
  }

  @Override
  public int getY() {
    return y;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public void drawObject(Graphics g) {
    for (AnimationObject o : objects) {
      o.draw(g);
    }
  }

  @Override
  public AnimationObject getObjectInRange(int x, int y) {
    for (AnimationObject object : objects) {
      if (object.hasCoords(x, y)) {
        return object;
      }
    }
    return null;
  }

  @Override
  public void addObject(AnimationObject object) {
    if (object != null) {
      objects.add(object);
    }
  }

  @Override
  public void removeObject(AnimationObject object) {
    if (object != null) {
      objects.remove(object);
    }
  }

  /**
   * This Builder is used to create an instance of animation model from a file. It is able to add
   * bounds, objects, and motions to objects.
   */
  public static final class Builder implements AnimationBuilder<AnimationModel> {

    private List<AnimationObject> objects = new ArrayList<>();
    private int x;
    private int y;
    private int width;
    private int height;

    @Override
    public AnimationModel build() {
      AnimationObject[] arr = new AnimationObject[objects.size()];
      AnimationModel model = new BaseAnimationModel();
      model.setBounds(x, y, width, height);
      return new BaseAnimationModel(objects.toArray(arr));
    }

    @Override
    public AnimationBuilder<AnimationModel> setBounds(int x, int y, int width, int height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      return this;
    }

    @Override
    public AnimationBuilder<AnimationModel> declareShape(String name, String type) {
      AnimationObject obj;
      if (type.equals("ellipse")) {
        obj = new CircleAnimationObject(name);
      } else if (type.equals("rectangle")) {
        obj = new RectangleAnimationObject(name);
      } else {
        throw new IllegalArgumentException();
      }

      objects.add(obj);

      return this;
    }

    @Override
    public AnimationBuilder<AnimationModel> addMotion(String name, int t1, int x1, int y1, int w1,
                                                      int h1, int r1, int g1, int b1, int t2,
                                                      int x2, int y2, int w2, int h2, int r2,
                                                      int g2, int b2) {
      for (AnimationObject object : objects) {
        if (object.getObjectName().equals(name)) {
          object.addMotion(t1, t2,
                  new Point(x1, y1), new Point(x2, y2), w1, w2, h1, h2, new Color(r1, g1, b1),
                  new Color(r2, g2, b2));
          return this;

        }
      }
      throw new IllegalArgumentException("no shape with name: " + name);
    }

    @Override
    public AnimationBuilder<AnimationModel> addKeyframe(String name, int t, int x, int y, int w,
                                                        int h, int r, int g, int b) {
      return null;
    }
  }
}
