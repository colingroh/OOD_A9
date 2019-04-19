package cs3500.animator.model;

import java.awt.*;
import java.util.List;

/**
 * An Object that can be animated.
 */
public interface AnimationObject {
  /**
   * Updates the current object to what it should look like on the given tick.
   *
   * @param curTick The current tick of the animation.
   */
  void onTick(int curTick);

  /**
   * Returns whether or not the Object is finished animating.
   *
   * @return true if the animation is complete, false if not.
   */
  boolean isAnimationComplete();

  /**
   * Draw the object onto the given Graphics.
   *
   * @param g Graphics to be drawn upon.
   */
  void draw(Graphics g);

  /**
   * Gives a String representation of an cs3500.animator.model.AnimationObject.
   *
   * @return A String representation of an cs3500.animator.model.AnimationObject.
   */
  String printObject();

  /**
   * Used to add a motion to an objects keyframes.
   *
   * @param startingTick the starting tick.
   * @param endingTick   the ending tick.
   * @param startPoint   the Point it starts at
   * @param endPoint     the Point it ends at
   * @param startWidth   starting width
   * @param endWidth     ending width
   * @param startHeight  starting height
   * @param endHeight    ending height
   * @param startColor   initial color
   * @param endColor     ending color
   */
  void addMotion(int startingTick, int endingTick, Point startPoint,
                 Point endPoint, int startWidth, int endWidth,
                 int startHeight, int endHeight, Color startColor,
                 Color endColor);

  /**
   * gives the name of the object.
   *
   * @returns object name
   */
  String getObjectName();

  /**
   * gives the color of the object.
   *
   * @returns the color
   */
  Color getColor();


  /**
   * sets the color of the object.
   *
   * @param c the Color to set it to
   */
  void setColor(Color c);


  /**
   * gets the width of the object.
   *
   * @returns the width
   */
  int getWidth();

  /**
   * sets the width of the object.
   *
   * @param w the width
   */
  void setWidth(int w);

  /**
   * gets the height of the object.
   *
   * @returns the height
   */
  int getHeight();

  /**
   * sets the height of the object.
   *
   * @param h the height to set it to
   */
  void setHeight(int h);

  /**
   * gets the position of the object.
   *
   * @returns the position Point
   */
  Point getPosition();

  /**
   * sets the position of the object.
   *
   * @param p the Point to set the position to
   */
  void setPosition(Point p);

  /**
   * starts building the outside wrapper of the SVG and calls all of the objects to be animated.
   *
   * @param millisPerTick passes down so that the shapes know how to animate themselves
   * @returns the finished SVG
   */
  String getSVG(int millisPerTick);

  /**
   * Checks if there is a shape that covers a given position
   *
   * @param x x coord of the Point
   * @param y y coord of the Point
   */
  boolean hasCoords(int x, int y);

  /**
   * Sets the selection status of an object. This is used for keeping track of which objects
   * keyframes we are currently editing.
   *
   * @param selected the objects new selection status.
   */
  void setSelected(boolean selected);


  /**
   * Adds a keyframe with the given state into the existing sequence.
   *
   * @param tick   the tick of the keyframe
   * @param point  the point of the object at that tick
   * @param width  the width of the object at that tick
   * @param height the height of the object at that tick
   * @param color  the color of the object at that tick
   */
  void addKeyFrame(int tick, Point point, int width, int height, Color color);

  /**
   * Get a list of this object's KeyFrame.
   * @return List of this object's KeyFrames.
   */
  List<KeyFrame> getKeyFrames();
}
