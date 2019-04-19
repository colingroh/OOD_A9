package cs3500.animator.model;

/**
 * A changing characteristic for an Object.
 */
public interface AnimationMetadata {
  /**
   * Update the given object with the percent completion of the animation.
   *
   * @param object          The Object that is being animated.
   * @param percentComplete How far along the animation is to being complete, from range 0.0 - 1.0
   */
  void updateObject(AnimationObject object, double percentComplete);

  /**
   * Get the SVG of this metadata.
   *
   * @param startTick     Start tick of this animation.
   * @param endTick       End tick of this animation.
   * @param millisPerTick Time covered for each tick.
   * @param shapeType     Type of Shape.
   * @return String representation of the SVG format.
   */
  String getSVG(int startTick, int endTick, int millisPerTick, String shapeType);
}
