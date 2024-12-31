/**
 * Quad Tree Visualization.
 */
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Class for visualizing a QuadTree.
 */
public class QuadTreeVisualization extends JPanel {
    private QuadTree quadTree;
    private static int WIDTH = 700;
    private static int HEIGHT = 400;
    private static boolean showLabels = false;

    /**
     * Constructor for QuadTreeVisualization.
     */
    public QuadTreeVisualization() {
        quadTree = new QuadTree(0, 0, WIDTH, HEIGHT, showLabels);
        readPointsFromFile("data/Points_100.txt");
    }

    /**
     * Read points from a text file and insert them into the QuadTree.
     * 
     * @param filename
     */
    private void readPointsFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String label = parts[1];
                int x = Integer.parseInt(parts[2]);
                int y = Integer.parseInt(parts[3]);
                System.out.println("Inserting point: " + label + " (" + x + ", " + y + ")");
                quadTree.insert(new QuadTreeKnot(x, y, label));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Paint the QuadTree.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        quadTree.draw(g);
    }

    /**
     * Main method for QuadTreeVisualization.
     * 
     * @param args
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        QuadTreeVisualization panel = new QuadTreeVisualization();
        frame.add(panel);
        frame.setSize(WIDTH, HEIGHT);
        frame.setTitle("QuadTree Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
