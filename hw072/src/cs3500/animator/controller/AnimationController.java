package cs3500.animator.controller;

import java.awt.*;

/**
 * Controls how the view and model should interact together.
 */
public interface AnimationController {
  /**
   * Start the controller to animate the view and model together.
   */
  void start();

  /**
   * Tell the controller where the view was clicked.
   *
   * @param x X coord
   * @param y Y Coord
   */
  void clicked(int x, int y);

  /**
   * Set the ticks per second of the animation.
   *
   * @param ticks ticks per second.
   */
  void setTicksPerSecond(int ticks);

  /**
   * Pass the Graphics to the model for drawing.
   *
   * @param g Graphics to be drawn upon.
   */
  void passGraphics(Graphics g);
}
