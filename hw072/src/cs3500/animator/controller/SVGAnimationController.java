package cs3500.animator.controller;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.view.IView;

/**
 * Controller for SVG animations.
 */
public class SVGAnimationController extends AbstractController {
  public SVGAnimationController(IView view, AnimationModel model) {
    super(view, model);
  }

  @Override
  public void start() {
    view.getSVGRepresentation(model.getSVG(1000 / ticks));
  }
}
