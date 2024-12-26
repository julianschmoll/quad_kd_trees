import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.Random;

public class KDTreeVisualization extends JPanel {
    private KDTree kdTree;

    public KDTreeVisualization() {
        kdTree = new KDTree(2);
        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            int x = rand.nextInt(800);
            int y = rand.nextInt(800);
            kdTree.insert(new int[]{x, y});
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        kdTree.draw(g, 0, 800, 0, 800, 0);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        KDTreeVisualization panel = new KDTreeVisualization();
        frame.add(panel);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}