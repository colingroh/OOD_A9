package cs3500.animator.controller;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.Timer;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationObject;
import cs3500.animator.model.RectangleAnimationObject;
import cs3500.animator.view.AbstractView;
import cs3500.animator.view.IView;

/**
 * Basic implementation of how a Controller should control the model and view.
 */
public abstract class AbstractController implements AnimationController, ActionListener {

  protected IView view;
  protected AnimationModel model;
  protected Timer timer;
  protected int ticks;
  protected boolean pause;
  protected boolean shouldLoop;
  protected AnimationObject selectedObject;
  protected JList keyFrames;
  private JPanel keyFrame;
  private JPanel top;

  /**
   * Super controller for initialization.
   *
   * @param view  View to controller.
   * @param model Model to controller.
   */
  public AbstractController(IView view, AnimationModel model) {
    this.view = view;
    this.model = model;
    this.timer = new Timer(1, this);
    this.pause = true;
    this.shouldLoop = false;
    this.keyFrame = new JPanel();
    this.top = new JPanel();
  }

  /**
   * Is our listener to repaint the canvas on every tick.
   *
   * @param e is our ActionEvent which is continuously re-evaluated every tick
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == timer) {
      if (!pause) {
        model.updateObjects(false, true);
        view.update();
        if (model.isFinished() && shouldLoop) {
          model.updateObjects(true, true);
        }
      }
    }
  }

  /**
   * sets the number of ticks per second to be in the dictated range, as long as it is reasonable.
   *
   * @param ticks ticks per second.
   */
  @Override
  public void setTicksPerSecond(int ticks) {
    this.ticks = ticks;
    if (ticks <= 0 || ticks > 999) {
      throw new UnsupportedOperationException("Ticks per Second must be a positive Integer (> 0)" +
              " less than 1000");
    }
    timer = new javax.swing.Timer(1000 / ticks, this);
  }

  @Override
  public void passGraphics(Graphics g) {
    model.drawObject(g);
  }

  @Override
  public void clicked(int x, int y) {
    if (selectedObject != null) {
      selectedObject.setSelected(false);
    }
    selectedObject = model.getObjectInRange(x, y);
    if (selectedObject != null) {
      updateKeyFrameData();
    }
    model.updateObjects(false, false);
    view.update();
  }


  protected void restart() {
    model.updateObjects(true, true);
    view.update();
  }

  protected void showAddKeyFrame() {
    keyFrame = new JPanel();
    keyFrame.setLayout(new BoxLayout(keyFrame, BoxLayout.Y_AXIS));
    JButton addKeyFrame = new JButton("New KeyFrame");
    JTextField tick = new HintTextField("Tick");
    JTextField red = new HintTextField("Red");
    JTextField green = new HintTextField("Green");
    JTextField blue = new HintTextField("Blue");
    JTextField x = new HintTextField("   X   ");
    JTextField y = new HintTextField("   Y   ");
    JTextField width = new HintTextField("Width");
    JTextField height = new HintTextField("Height");

    addKeyFrame.addActionListener(e -> {
      int fTick = Integer.parseInt(tick.getText());
      int fRed = Integer.parseInt(red.getText());
      int fGreen = Integer.parseInt(green.getText());
      int fBlue = Integer.parseInt(blue.getText());
      int fX = Integer.parseInt(x.getText());
      int fY = Integer.parseInt(y.getText());
      int fWidth = Integer.parseInt(width.getText());
      int fHeight = Integer.parseInt(height.getText());
      selectedObject.addKeyFrame(fTick, new Point(fX, fY), fWidth, fHeight,
              new Color(fRed, fGreen, fBlue));
      restart();
    });

    keyFrame.add(addKeyFrame);
    keyFrame.add(tick);
    keyFrame.add(red);
    keyFrame.add(green);
    keyFrame.add(blue);
    keyFrame.add(x);
    keyFrame.add(y);
    keyFrame.add(width);
    keyFrame.add(height);
    ((AbstractView) view).add(keyFrame, BorderLayout.EAST);
    ((AbstractView) view).revalidate();
  }

  /**
   * gives us our box for taking in new Shape metadata in the editor view.
   */

  protected void showAddShape() {
    top = new JPanel();
    top.setLayout(new FlowLayout(FlowLayout.CENTER));
    JButton addShape = new JButton("New Shape");
    JButton removeShape = new JButton("Remove shape");
    JTextField name = new HintTextField("  Name  ");
    JTextField shape = new HintTextField("R or C");

    JLabel to = new JLabel("to");
    JTextField toTick = new HintTextField("Tick");
    JTextField fromTick = new HintTextField("Tick");
    JTextField toRed = new HintTextField("Red");
    JTextField fromRed = new HintTextField("Red");
    JTextField toGreen = new HintTextField("Green");
    JTextField fromGreen = new HintTextField("Green");
    JTextField toBlue = new HintTextField("Blue");
    JTextField fromBlue = new HintTextField("Blue");
    JTextField toX = new HintTextField("   X   ");
    JTextField fromX = new HintTextField("   X   ");
    JTextField toY = new HintTextField("   Y   ");
    JTextField fromY = new HintTextField("   Y   ");
    JTextField toWidth = new HintTextField("Width");
    JTextField fromWidth = new HintTextField("Width");
    JTextField toHeight = new HintTextField("Height");
    JTextField fromHeight = new HintTextField("Height");

    addShape.addActionListener(e -> {
      int fTick = Integer.parseInt(fromTick.getText());
      int tTick = Integer.parseInt(toTick.getText());
      int fRed = Integer.parseInt(fromRed.getText());
      int tRed = Integer.parseInt(toRed.getText());
      int fGreen = Integer.parseInt(fromGreen.getText());
      int tGreen = Integer.parseInt(toGreen.getText());
      int fBlue = Integer.parseInt(fromBlue.getText());
      int tBlue = Integer.parseInt(toBlue.getText());
      int fX = Integer.parseInt(fromX.getText());
      int tX = Integer.parseInt(toX.getText());
      int fY = Integer.parseInt(fromY.getText());
      int tY = Integer.parseInt(toY.getText());
      int fWidth = Integer.parseInt(fromWidth.getText());
      int tWidth = Integer.parseInt(toWidth.getText());
      int fHeight = Integer.parseInt(fromHeight.getText());
      int tHeight = Integer.parseInt(toHeight.getText());
      if (shape.getText().toLowerCase().equals("r")) {
        AnimationObject obj = new RectangleAnimationObject(name.getText());
        obj.addMotion(fTick, tTick, new Point(fX, fY), new Point(tX, tY), fWidth, tWidth,
                fHeight, tHeight, new Color(fRed, fGreen, fBlue), new Color(tRed, tGreen, tBlue));
        model.addObject(obj);
        restart();
      }
    });

    removeShape.addActionListener(e -> {
      model.removeObject(selectedObject);
      restart();
    });

    top.add(addShape);
    top.add(name);
    top.add(shape);
    top.add(fromTick);
    top.add(fromRed);
    top.add(fromGreen);
    top.add(fromBlue);
    top.add(fromX);
    top.add(fromY);
    top.add(fromWidth);
    top.add(fromHeight);
    top.add(to);
    top.add(toTick);
    top.add(toRed);
    top.add(toGreen);
    top.add(toBlue);
    top.add(toX);
    top.add(toY);
    top.add(toWidth);
    top.add(toHeight);
    top.add(removeShape);

    ((AbstractView) view).add(top, BorderLayout.NORTH);
    ((AbstractView) view).revalidate();
  }

  protected void updateKeyFrameData() {
    String[] keyFrameArr = new String[selectedObject.getKeyFrames().size()];
    for (int i = 0; i < keyFrameArr.length; i++) {
      keyFrameArr[i] = selectedObject.getKeyFrames().get(i).toString();
    }
    keyFrames.setListData(keyFrameArr);
  }

  /**
   * Taken from: https://stackoverflow.com/questions/1738966/java-jtextfield-with-input-hint.
   */
  class HintTextField extends JTextField implements FocusListener {

    private final String hint;
    private boolean showingHint;

    public HintTextField(final String hint) {
      super(hint);
      this.hint = hint;
      this.showingHint = true;
      super.addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {
      if (this.getText().isEmpty()) {
        super.setText("");
        showingHint = false;
      }
    }

    @Override
    public void focusLost(FocusEvent e) {
      if (this.getText().isEmpty()) {
        super.setText(hint);
        showingHint = true;
      }
    }

    @Override
    public String getText() {
      return showingHint ? "" : super.getText();
    }
  }

}
