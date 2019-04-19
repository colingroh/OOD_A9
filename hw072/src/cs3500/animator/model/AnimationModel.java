package cs3500.animator.model;

import java.awt.*;

/**
 * A model that represents any number of objects being animated.
 */
public interface AnimationModel {
  /**
   * Update the model's objects to the next state in their animation.
   */
  void updateObjects(boolean restart, boolean increaseTick);

  /**
   * Print a String representation of what the model should look like.
   *
   * @return a String of the Animation.
   */
  String printAnimation();

  /**
   * sets the bounding box for the screen size.
   *
   * @param x      starting x co-ord for the view
   * @param y      starting y co-ord for the view
   * @param width  width of the window itself
   * @param height height of the window itself
   */
  void setBounds(int x, int y, int width, int height);

  /**
   * provides the x co-ord that the window is built from.
   *
   * @returns that x-coord.
   */
  int getX();

  /**
   * * provides the y co-ord that the window is built from.
   *
   * @returns that y coord
   */
  int getY();

  /**
   * * provides the width of the window.
   *
   * @returns the width
   */
  int getWidth();

  /**
   * * provides the height of the window.
   *
   * @returns the height
   */
  int getHeight();

  /**
   * starts building the outside wrapper of the SVG and calls all of the objects to be animated.
   *
   * @param millisPerTick passes down so that the shapes know how to animate themselves
   * @returns the finished SVG
   */
  String getSVG(int millisPerTick);

  /**
   * Draws all our objects onto the provided Graphics object.
   *
   * @param g the object we will be rendering all of our AnimationObjects on.
   */
  void drawObject(Graphics g);

  /**
   * Checks if an Animation Object is done executing actions.
   *
   * @returns True if the AO is done, false otherwise.
   */
  boolean isFinished();

  /**
   * Checks if the given posn has an object that covers that space, if so returns the object.
   *
   * @param x given x-coord
   * @param y given y-coord
   * @returns either the object or null.
   *
   * This is used for our methodology of clicking on a shape to add or remove a keyframe
   */
  AnimationObject getObjectInRange(int x, int y);

  /**
   * Supports adding an Object to our existing list of Objects.
   *
   * @param object the object to be added.
   */
  void addObject(AnimationObject object);

  /**
   * Remove the given object from the model.
   * @param object Object to remove.
   */
  void removeObject(AnimationObject object);
}
