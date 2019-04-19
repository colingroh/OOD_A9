package cs3500.animator.model;

/**
 * Default implementation of an cs3500.animator.model.AnimationModel.
 */
public class BaseAnimationModel extends AbstractAnimationModel {

  /**
   * Constructor for an cs3500.animator.model.AnimationModel with given AnimationObjects.
   *
   * @param objs Objects to be represented in this model.
   */
  public BaseAnimationModel(AnimationObject... objs) {
    super(objs);
  }
}
