package triangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class SierpinskiTriangle {
  public static int SIZE = 1000;

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

    int length = size.width;
    if (size.height < length) length = size.height;
    //System.out.println("w: " + size.width + ", h: " + size.height + ", length: " + length + ", calculated height: " +  height(length));

    int[] xPoints;
    int[] yPoints;


    xPoints = new int[]{0, length / 2, length};
    yPoints = new int[]{size.height, (size.height - height(length)), size.height};
    g2.setColor(Color.black);
    g2.drawPolygon(xPoints, yPoints, 3);
    triangle(g2, size, length);
  }

  public int height(int length) {
    return (int) (length / 2 * Math.sqrt(3));
  }

  public void triangle(Graphics2D g2, Dimension size, int length) {
    int[] xPoints;
    int[] yPoints;
    if (length > 10) {
      g2.translate(0, 0);
      length /= 2;
      xPoints = new int[]{length / 2, length / 2 + length / 2, length + length / 2};
      yPoints = new int[]{size.height - height(length), size.height, size.height - height(length)};
      g2.setColor(Color.black);
      g2.drawPolygon(xPoints, yPoints, 3);
      triangle(g2, size, length);
    }
  }
}
