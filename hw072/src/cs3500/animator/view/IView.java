package cs3500.animator.view;

import cs3500.animator.controller.AnimationController;

/**
 * Represents a View that displays an animation to some out.
 */
public interface IView {

  /**
   * Update the view.
   */
  void update();

  /**
   * Output the textual representation of what an animation should look like.
   *
   * @param animation The string representation of what a animation should look like.
   */
  void getStringRepresentation(String animation);

  /**
   * Output the SVG representation of what an animation should look like.
   *
   * @param animation The output in SVG format.
   */
  void getSVGRepresentation(String animation);

  /**
   * Output the animation with the given width and height.
   *
   * @param width      Width of the animation.
   * @param height     Height of the animation.
   * @param controller Controller access to pass data back.
   */
  void showVisual(int width, int height, AnimationController controller);
}
