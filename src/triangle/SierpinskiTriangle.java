package triangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class SierpinskiTriangle {
  public static int SIZE = 500;

  JFrame frame;
  JPanel panel;

  public static void main(String[] args) {
    SierpinskiTriangle triangle = new SierpinskiTriangle();
    triangle.display();
  }

  @SuppressWarnings("serial")

  public void display() {
    frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    panel = new JPanel() {
      @Override
      public void paint(Graphics g) {
        super.paint(g);
        paintSierpinskiTriangle(g, getSize());
      }
    };
    panel.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        panel.repaint();
      }
    });
    frame.setLayout(new BorderLayout());
    frame.add(panel, BorderLayout.CENTER);
    frame.pack();
    frame.setSize(SIZE, SIZE);
    frame.setVisible(true);
  }

  public void paintSierpinskiTriangle(Graphics g, Dimension size) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setBackground(Color.white);
    g2.clearRect(0, 0, size.width, size.height);

    double length, h;
    if (size.width <= size.height) {
      length = size.width;
      h = Math.sqrt(Math.pow(length, 2) - Math.pow(length / 2, 2));
    } else {
      h = size.height;
      length = h * 2 / Math.sqrt(3);
    }

    double Ax = 0;
    double Ay = (size.height - 1);
    double Bx = Ax + length;
    double By = Ay;
    double Cx = Ax + length / 2;
    double Cy = size.height - h - 1;
    //System.out.println("w: " + size.width + ", h: " + size.height + ", length: " + length + ", calculated height: " +  height(length));
    triangle(g, length, Ax, Ay, Bx, By, Cx, Cy);
  }

  public int height(int length) {
    return (int) (length / 2 * Math.sqrt(3));
  }

  private void triangle(Graphics g, double length, double ax, double ay, double bx, double by, double cx, double cy) {
    if (length >= 5) {
      double[] a = {ax, ay};
      double[] b = {bx, by};
      double[] c = {cx, cy};

      // paint actual triangle (actual size)
      int[] xPoints = {(int) ax, (int) bx, (int) cx};
      int[] yPoints = {(int) ay, (int) by, (int) cy};
      g.drawPolygon(xPoints, yPoints, 3);

      // draw 3 little triangles (recursive calls with smaller size)
      double[] a1 = a;
      double[] b1 = {ax + length / 2, ay};
      double[] c1 = {ax + length / 4, ay - (ay - cy) / 2};
      //left smaller triangle
      triangle(g, length / 2, a1[0], a1[1], b1[0], b1[1], c1[0], c1[1]);

      double[] a2 = b1;
      double[] b2 = b;
      double[] c2 = {ax + length * 3 / 4, ay - (ay - cy) / 2};
      //right smaller triangle
      triangle(g, length / 2, a2[0], a2[1], b2[0], b2[1], c2[0], c2[1]);

      double[] a3 = c1;
      double[] b3 = c2;
      double[] c3 = c;
      //right smaller triangle
      triangle(g, length / 2, a3[0], a3[1], b3[0], b3[1], c3[0], c3[1]);
    }
  }
}
