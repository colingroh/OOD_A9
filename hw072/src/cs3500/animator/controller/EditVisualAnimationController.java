package cs3500.animator.controller;

import java.awt.*;

import javax.swing.*;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.view.AbstractView;
import cs3500.animator.view.IView;

/**
 * Controller for VisualAnimations.
 */
public class EditVisualAnimationController extends AbstractController {
  public EditVisualAnimationController(IView view, AnimationModel model) {
    super(view, model);
  }

  @Override
  public void start() {
    view.showVisual(model.getWidth(), model.getHeight(), this);
    timer.start();

    JPanel bottom = new JPanel();
    bottom.setLayout(new FlowLayout(FlowLayout.CENTER));

    JButton resume = new JButton("Start/Resume");
    JButton pause = new JButton("Pause");
    JButton restart = new JButton("Restart");
    JButton loop = new JButton("Not Looping");
    JButton increase = new JButton("Increase Speed");
    JButton decrease = new JButton("Decrease Speed");

    bottom.add(resume);
    bottom.add(pause);
    bottom.add(restart);
    bottom.add(loop);
    bottom.add(increase);
    bottom.add(decrease);

    increase.addActionListener(e -> {
      ticks = Math.min(999, ticks + 1);
      setTicksPerSecond(ticks);
      timer.start();
    });

    decrease.addActionListener(e -> {
      ticks = Math.max(ticks - 1, 1);
      setTicksPerSecond(ticks);
      timer.start();
    });

    resume.addActionListener(e -> {
      this.pause = false;
    });

    pause.addActionListener(e -> {
      this.pause = true;
    });

    restart.addActionListener(e -> {
      super.restart();
    });

    loop.addActionListener(e -> {
      shouldLoop ^= true;
      loop.setText(shouldLoop ? "Looping" : "Not looping");
    });

    ((AbstractView) view).add(bottom, BorderLayout.SOUTH);

    showAddShape();
    showAddKeyFrame();

    JPanel left = new JPanel();
    left.setPreferredSize(new Dimension(280, model.getHeight()));
    left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));

    keyFrames = new JList();
    keyFrames.setFixedCellWidth(300);
    keyFrames.setFixedCellHeight(25);
    keyFrames.setBackground(new Color(225, 225, 225));

    JButton removeKeyFrame = new JButton("Remove Selected KeyFrame");
    removeKeyFrame.setPreferredSize(new Dimension());

    removeKeyFrame.addActionListener(e -> {
      if (keyFrames.getSelectedIndex() != -1) {
        if (selectedObject != null) {
          selectedObject.getKeyFrames().remove(keyFrames.getSelectedIndex());
          updateKeyFrameData();
          restart();
        }
      }
    });

    left.add(removeKeyFrame);
    left.add(keyFrames);
    ((AbstractView) view).add(left, BorderLayout.WEST);

    model.updateObjects(true, true);
    view.update();
  }
}
