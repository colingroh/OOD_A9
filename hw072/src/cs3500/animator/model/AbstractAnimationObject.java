package cs3500.animator.model;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Abstract implementation of an cs3500.animator.model.AnimationObject.
 */
public abstract class AbstractAnimationObject implements AnimationObject {
  protected List<AnimationObjectInstruction> animations;
  protected List<KeyFrame> keyFrames;
  //protected AnimationObjectInstruction curAnimation;
  protected KeyFrame curKeyFrame;
  protected KeyFrame nextKeyFrame;
  protected Map<Integer, AnimationObjectInstruction> map;
  // This will be used when it comes to drawing the object.
  protected int width;
  protected int height;
  protected Point position;
  protected Color color;
  protected boolean show;
  protected boolean selected;
  private int index;
  private boolean isFinished;
  private String name;

  /**
   * Constructor for an cs3500.animator.model.AnimationObject with the given instructions.
   *
   * @param name the name of the object
   * @throws IllegalArgumentException if there are no details, if any detail is null, or if the
   *                                  details don't all line up, with one detail starting at tick 1
   *                                  and the next starting and the end of the first details and the
   *                                  third starting at the end of the second detail, etc.
   */
  public AbstractAnimationObject(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Name can't be null");
    }
    this.name = name;
    map = new HashMap<>();
    isFinished = false;
    animations = new LinkedList<>();
    keyFrames = new LinkedList<>();
    show = false;
    index = 0;
  }

  @Override
  public void onTick(int curTick) {
    if (curTick == 1) {
      index = 0;
      show = false;
      isFinished = false;
    }
    if (!isFinished) {
      curKeyFrame = keyFrames.get(index);
      getNextKeyFrame();

      if (nextKeyFrame == null) {
        isFinished = true;
        return;
      }

      while ((nextKeyFrame.getTick() < curTick || curKeyFrame.getTick() > curTick) && index != 0) {
        index++;
        curKeyFrame = keyFrames.get(index);
        getNextKeyFrame();
        if (nextKeyFrame == null) {
          isFinished = true;
          return;
        }
      }

      if (curKeyFrame.getTick() == curTick) {
        show = true;
      }

      if (show) {
        if (nextKeyFrame.getTick() == curTick) {
          index++;
          curKeyFrame = nextKeyFrame;
          getNextKeyFrame();
          if (nextKeyFrame == null) {
            isFinished = true;
            return;
          }
        }
        this.color = curKeyFrame.combineColor(nextKeyFrame, curTick);
        this.width = curKeyFrame.combineWidth(nextKeyFrame, curTick);
        this.height = curKeyFrame.combineHeight(nextKeyFrame, curTick);
        this.position = curKeyFrame.combinePoint(nextKeyFrame, curTick);
      }
    }
  }

  private void getNextKeyFrame() {
    if (index + 1 < keyFrames.size()) {
      nextKeyFrame = keyFrames.get(index + 1);
    } else {
      nextKeyFrame = null;
    }
  }

  @Override
  public void setSelected(boolean selected) {
    this.selected = selected;
  }

  @Override
  public boolean isAnimationComplete() {
    return isFinished;
  }

  @Override
  public Color getColor() {
    return color;
  }

  @Override
  public void setColor(Color c) {
    this.color = c;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public void setWidth(int w) {
    this.width = w;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public void setHeight(int h) {
    this.height = h;
  }

  @Override
  public Point getPosition() {
    return position;
  }

  @Override
  public void setPosition(Point p) {
    this.position = p;
  }

  @Override
  public String printObject() {
    return "motion ";
  }

  @Override
  public String getObjectName() {
    return name;
  }


  protected String printObjectHelper(StringBuilder sb) {
    for (int i = 0; i < animations.size(); i++) {
      AnimationObjectInstruction instruction = animations.get(i);
      if (i != 0) {
        sb.append("\n");
      }
      sb.append(instruction.printInstruction());
      if (i < animations.size() - 1) {
        sb.append("\n");
      }
    }

    return sb.toString();
  }

  protected void drawIfSelected(Graphics2D g2, Shape shape) {
    if (selected) {
      g2.setColor(color.darker().equals(color) ? color.brighter() : color.darker());
      g2.setStroke(new BasicStroke(5));
      g2.draw(shape);
    }
  }

  @Override
  public void addMotion(int startingTick, int endingTick, Point startPoint,
                        Point endPoint, int startWidth, int endWidth,
                        int startHeight, int endHeight, Color startColor,
                        Color endColor) {
    KeyFrame first = new KeyFrame(startingTick, startPoint, startWidth, startHeight, startColor);
    KeyFrame next = new KeyFrame(endingTick, endPoint, endWidth, endHeight, endColor);
    AnimationObjectInstruction instruction = new AnimationObjectInstructionImpl(startingTick,
            endingTick, startPoint, endPoint, startWidth, endWidth, startHeight, endHeight,
            startColor, endColor);
    addMotionHelper(instruction);
    addMotionHelper(first);
    addMotionHelper(next);
  }

  protected void addMotionHelper(AnimationObjectInstruction instruction) {
    animations.add(instruction);
  }

  protected void addMotionHelper(KeyFrame frame) {
    if (!keyFrames.contains(frame)) {
      keyFrames.add(frame);
    }
  }

  protected void getSVGHelper() {
    if (getColor() == null) {
      AnimationObjectInstruction temp = animations.get(0);
      temp.update(this, temp.getStartingTick());
    }
  }

  @Override
  public void addKeyFrame(int tick, Point point, int width, int height, Color color) {
    for (int i = 0; i < keyFrames.size(); i++) {
      if (keyFrames.get(i).getTick() > tick) {
        keyFrames.add(i, new KeyFrame(tick, point, width, height, color));
        return;
      }
      if (keyFrames.get(i).getTick() == tick) {
        keyFrames.set(i, new KeyFrame(tick, point, width, height, color));
      }
    }
    keyFrames.add(new KeyFrame(tick, point, width, height, color));
  }

  @Override
  public boolean hasCoords(int x, int y) {
    if (position == null) {
      return false;
    }
    if (this.position.x <= x && this.position.x + width >= x &&
            this.position.y <= y && this.position.y + height >= y) {
      this.selected = true;
      return true;
    }
    return false;
  }

  @Override
  public List<KeyFrame> getKeyFrames() {
    return keyFrames;
  }
}
