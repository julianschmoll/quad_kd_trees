import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.Random;

public class QuadTreeVisualization extends JPanel {
    private QuadTree quadTree;

    public QuadTreeVisualization() {
        quadTree = new QuadTree(0, 0, 800, 800);
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            int x = rand.nextInt(800);
            int y = rand.nextInt(800);
            quadTree.insert(new QuadTreeKnot(x, y));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        quadTree.draw(g);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        QuadTreeVisualization panel = new QuadTreeVisualization();
        frame.add(panel);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}