package cs3500.animator.controller;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.view.IView;

/**
 * Controller for VisualAnimations.
 */
public class VisualAnimationController extends AbstractController {
  public VisualAnimationController(IView view, AnimationModel model) {
    super(view, model);
  }

  @Override
  public void start() {
    view.showVisual(model.getWidth(), model.getHeight(), this);
    timer.start();
    pause = false;

    model.updateObjects(true, true);
    view.update();
  }
}
