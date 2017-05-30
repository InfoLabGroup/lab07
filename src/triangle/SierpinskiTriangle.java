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
    //g2.draw3DRect(20, 20, size.width - 40, size.height - 40, true);

    int length = size.width;
    if (size.height < length) length = size.height;
    System.out.println("w: " + size.width + ", h: " + size.height + ", length: " + length);
    //int length = (int) Math.sqrt(Math.pow(smallest, 2) - Math.pow((smallest / 2), 2));
    //g2.drawLine(0, size.height, length, size.height); // bottom
    //g2.drawLine(0, size.height, length / 2, (int) (size.height - length / 2 * Math.sqrt(3))); // left
    //g2.drawLine(length, size.height, length / 2, (int) (size.height - length / 2 * Math.sqrt(3))); // right

    int[] xPoints = {0, length / 2, length};
    int[] yPoints = {size.height, (int) (size.height - length / 2 * Math.sqrt(3)), size.height};
    g2.setColor(Color.white);
    g2.drawPolygon(xPoints, yPoints, 3);
    g2.setColor(Color.black);
    g2.fillPolygon(xPoints, yPoints, 3);

    length /= 2;
    xPoints = new int[]{0 + length / 2, length / 2 + length / 2, length + length / 2};
    yPoints = new int[]{(int) (size.height - (length / 2 * Math.sqrt(3))), size.height, (int) (size.height - (length / 2 * Math.sqrt(3)))};
    //g2.setColor(Color.black);
    //g2.drawPolygon(xPoints, yPoints, 3);
    g2.setColor(Color.white);
    g2.fillPolygon(xPoints, yPoints, 3);

    // g2.drawLine(20, size.height - 20, size.width - 20, size.height - 20);
    // g2.drawLine(20, size.height - 20, size.width / 2, 20);
    // g2.drawLine(size.width - 20, size.height - 20, size.width / 2, 20);
  }

}
