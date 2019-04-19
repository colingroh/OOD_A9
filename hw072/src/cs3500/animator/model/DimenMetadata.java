package cs3500.animator.model;

/**
 * Class that represent an animation the changes the Dimensions.
 */
public class DimenMetadata extends AbstractAnimationMetadata<Integer> {

  /**
   * Constructor for changing from an Integer to an Integer.
   *
   * @param from The starting Integer.
   * @param to   The ending Integer.
   * @param type The type it is changing, either WIDTH or HEIGHT.
   */
  public DimenMetadata(Integer from, Integer to, AnimationFields type) {
    super(from, to, type);
    if (type != AnimationFields.WIDTH && type != AnimationFields.HEIGHT) {
      throw new IllegalArgumentException("Type mush be WIDTH or HEIGHT");
    }
    if (from < 0 || to < 0) {
      throw new IllegalArgumentException("Cannot change the dimensions to less than 0");
    }
  }

  @Override
  public Integer updateValue(double percentComplete) {
    super.updateValue(percentComplete);
    return (int) (to + ((from - to) * percentComplete));
  }

  @Override
  public String getSVG(int start, int end, int millisPerTick, String shapeType) {
    int msStart = start * millisPerTick;
    int msDur = (end * millisPerTick) - msStart;
    StringBuilder sb = new StringBuilder();
    if (type == AnimationFields.HEIGHT && (!from.equals(to))) {
      sb.append("    <animate attributeType=\"xml\" begin=\"")
              .append(msStart)
              .append(".0ms\" dur=\"")
              .append(msDur)
              .append(".0ms\" attributeName=\"")
              .append(shapeType.equals("circle") ?
                      "ry" :
                      "y")
              .append("\" from=\"")
              .append(from)
              .append("\" to=\"")
              .append(to)
              .append("\" />\n");
    }

    if (type == AnimationFields.WIDTH && (!from.equals(to))) {
      sb.append("    <animate attributeType=\"xml\" begin=\"")
              .append(msStart)
              .append(".0ms\" dur=\"")
              .append(msDur)
              .append(".0ms\" attributeName=\"")
              .append(shapeType.equals("circle") ?
                      "rx" :
                      "x")
              .append("\" from=\"")
              .append(from)
              .append("\" to=\"")
              .append(to)
              .append("\" />\n");
    }

    return sb.toString();
  }

  @Override
  public void updateObject(AnimationObject object, double percentComplete) {
    if (type == AnimationFields.HEIGHT) {
      object.setHeight(updateValue(percentComplete));
    } else {
      object.setWidth(updateValue(percentComplete));
    }
  }
}
