package cs3500.animator.model;

/**
 * A representation of one complete sub-part of an animation of a cs3500.animator.model.AnimationObject.
 */
public interface AnimationObjectInstruction {
  /**
   * Get the starting tick of this Instruction.
   *
   * @return the starting tick.
   */
  int getStartingTick();

  /**
   * Get the ending tick of this Instruction.
   *
   * @return the ending tick.
   */
  int getEndingTick();

  /**
   * Update the given cs3500.animator.model.AnimationObject to the current tick.
   *
   * @param object  Animation Object to be updated.
   * @param curTick Current tick of the Animation.
   */
  void update(AnimationObject object, int curTick);

  /**
   * Gives a representation of what an cs3500.animator.model.AnimationObjectInstruction should look
   * like.
   *
   * @return a String representation of an cs3500.animator.model.AnimationObjectInstruction.
   */
  String printInstruction();

  /**
   * Creates a SVG representation of the keyframes we have.
   *
   * @param millisPerTick tells us how to modify the tick instructions to fit SVG
   * @param shapeType     lets us know if we should use the syntax of ellipses or rectangles
   */
  String getSVG(int millisPerTick, String shapeType);
}
