package cs3500.animator.controller;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.view.IView;

/**
 * Factory responsible for making Controllers.
 */
public class AnimationControllerFactory {
  /**
   * This method gets an instance of IView based on what type is passed in.
   *
   * @param type Type of view wanted. One of "text", "svg", or "visual"
   * @return an IView based on the standards.
   */
  public static AnimationController getInstance(String type, IView view, AnimationModel model) {
    switch (type) {
      case "text":
        return new TextualAnimationController(view, model);
      case "svg":
        return new SVGAnimationController(view, model);
      case "visual":
        return new VisualAnimationController(view, model);
      case "edit":
        return new EditVisualAnimationController(view, model);
      default:
        throw new IllegalArgumentException("Not a valid type");
    }
  }
}

