package cs3500.animator.controller;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.view.IView;

/**
 * Controllers for TextualAnimations.
 */
public class TextualAnimationController extends AbstractController {
  public TextualAnimationController(IView view, AnimationModel model) {
    super(view, model);
  }

  @Override
  public void start() {
    view.getStringRepresentation(model.printAnimation());
  }
}
