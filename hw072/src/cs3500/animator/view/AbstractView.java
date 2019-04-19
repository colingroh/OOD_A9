package cs3500.animator.view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.PrintStream;

import javax.swing.*;

import cs3500.animator.controller.AnimationController;

/**
 * Most of the work for a View is tucked away in here.
 */
public abstract class AbstractView extends JFrame implements IView {

  protected PrintStream out;
  protected BufferedReader in;

  public AbstractView(PrintStream out) {
    super();
    this.out = out;
  }

  @Override
  public void getStringRepresentation(String animation) {
    out.print(animation);
  }

  @Override
  public void update() {
    revalidate();
    repaint();
  }

  @Override
  public void showVisual(int width, int height, AnimationController controller) {
    setLayout(new BorderLayout());
    add(new ShapeCanvas(controller), BorderLayout.CENTER);

    setSize(width, height);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setVisible(true);
  }

  @Override
  public void getSVGRepresentation(String animation) {
    out.print(animation);
  }

  /**
   * This class is in charge of drawing all of the objects onto the view.
   */
  private class ShapeCanvas extends JPanel implements MouseListener {

    private AnimationController controller;

    ShapeCanvas(AnimationController controller) {
      setBackground(Color.WHITE);
      addMouseListener(this);
      this.controller = controller;
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      controller.passGraphics(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
      controller.clicked(e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
      // We don't use this.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      // We don't use this.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
      // We don't use this.
    }

    @Override
    public void mouseExited(MouseEvent e) {
      // We don't use this.
    }
  }
}
