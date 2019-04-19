package cs3500.animator.model;

/**
 * Abstract implementation of cs3500.animator.model.AnimationMetadata of Type T.
 *
 * @param <T> The type of cs3500.animator.model.AnimationMetadata
 */
public abstract class AbstractAnimationMetadata<T> implements AnimationMetadata {
  protected T to;
  protected T from;
  protected AnimationFields type;

  /**
   * Constructor for an cs3500.animator.model.AnimationMetadata going from from to to and of type
   * type.
   *
   * @param from The starting value.
   * @param to   The ending value.
   * @param type The Type of Value.
   * @throws IllegalArgumentException If any of the values are null.
   */
  public AbstractAnimationMetadata(T from, T to, AnimationFields type) {
    if (to == null || from == null || type == null) {
      throw new IllegalArgumentException("Null input to metadata");
    }
    this.to = to;
    this.from = from;
    this.type = type;
  }

  /**
   * Checks to see if the given percent is valid from 0.0 to 1.0.
   *
   * @param percentComplete value of completion.
   * @throws IllegalArgumentException if the perecentComplete is less than 0.0 or greater than 1.0.
   */
  protected void checkPercentComplete(double percentComplete) {
    if (percentComplete < 0 || percentComplete > 1) {
      throw new IllegalArgumentException("Cannot update that object with percent "
              + percentComplete);
    }
  }

  /**
   * Generate a value of the metadata with the given percentComplete.
   *
   * @param percentComplete How far complete the animation is.
   * @return An in-between value of type T from the given percent.
   */
  protected T updateValue(double percentComplete) {
    checkPercentComplete(percentComplete);
    return null;
  }
}
