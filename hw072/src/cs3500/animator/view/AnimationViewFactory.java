package cs3500.animator.view;

import java.io.PrintStream;

/**
 * This class is used to be a Factory to make IViews.
 */
public class AnimationViewFactory {
  /**
   * This method gets an instance of IView based on what type is passed in.
   *
   * @param type Type of view wanted. One of "text", "svg", or "visual"
   * @return an IView based on the standards.
   */
  public static IView getInstance(String type, PrintStream in) {
    switch (type) {
      case "text":
        return new TextualAnimationViewImpl(in);
      case "svg":
        return new SVGAnimationViewImpl(in);
      case "visual":
        return new VisualAnimationViewImpl(in);
      case "edit":
        return new VisualAnimationViewImpl(in);
      default:
        throw new IllegalArgumentException("Not a valid type");
    }
  }
}
