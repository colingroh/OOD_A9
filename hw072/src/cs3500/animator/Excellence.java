package cs3500.animator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.AnimationControllerFactory;
import cs3500.animator.model.AbstractAnimationModel;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.AnimationViewFactory;
import cs3500.animator.view.IView;

/**
 * This class is the entry point for using the software.
 */
public class Excellence {

  /**
   * Main method for using this entire assignment. Uses the variable passed in to manipulate and
   * create different views.
   *
   * @param args The args passed in to manipulate how to program should act. These mush have "-in"
   *             followed by an input file and a "-view" followed by one of "text", "visual", or
   *             "svg", showing which type of animation they want. Optional parameters are "-out"
   *             and "-speed". "-out" should be followed by an output file. By default, this is
   *             System.out. "-speed" the ticks per second as an Integer.
   */
  public static void main(String[] args) {
    Set<String> specialChars = new HashSet<>();
    specialChars.add("-in");
    specialChars.add("-view");
    specialChars.add("-out");
    specialChars.add("-speed");
    Map<String, String> map = new HashMap<>();

    for (int i = 0; i < args.length; i++) {
      if (specialChars.contains(args[i])) {
        map.put(args[i], args[i + 1]);
        i++;
      }
    }

    if (!map.containsKey("-view") || !map.containsKey("-in")) {
      throw new IllegalArgumentException();
    }

    BufferedReader br;
    String fullDir = System.getProperty("user.dir") + "\\src\\cs3500\\animator\\" + map.get("-in");
    try {
      br = new BufferedReader(new FileReader(new File(fullDir)));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File does not exist");
    }
    AnimationModel model = AnimationReader.parseFile(br, new AbstractAnimationModel.Builder());
    model.setBounds(0, 0, 1400, 800);

    PrintStream out = System.out;
    if (map.containsKey("-out")) {
      String fileName = map.get("-out");
      try {
        out = new PrintStream(new File(System.getProperty("user.dir")
                + "\\src\\cs3500\\animator\\" + fileName));
      } catch (FileNotFoundException e) {
        throw new IllegalArgumentException("Error finding file");
      }
    }

    IView view;
    view = AnimationViewFactory.getInstance(map.get("-view"), out);

    AnimationController controller = AnimationControllerFactory.getInstance(map.get("-view"),
            view, model);

    Integer speed = null;
    if (map.containsKey("-speed")) {
      try {
        speed = Integer.parseInt(map.get("-speed"));
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Speed must be an integer");
      }
    }
    if (speed != null) {
      controller.setTicksPerSecond(speed);
    }

    controller.start();
  }
}

