
/**
 * KDTree Visualization
 */
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * A simple visualization of a KDTree.
 */
public class KDTreeVisualization extends JPanel {
    private KDTree kdTree;
    private static int SCALE_FACTOR = 2;
    private static int WIDTH = 700 * SCALE_FACTOR;
    private static int HEIGHT = 400 * SCALE_FACTOR;
    private static boolean showLabels = true;

    /**
     * Creates a new KDTreeVisualization and read points from a file.
     */
    public KDTreeVisualization() {
        kdTree = new KDTree(showLabels);
        readPointsFromFile("data/Points_10.txt");
    }

    /**
     * Reads points from a text file and insert them into the kdTree.
     * 
     * @param filename the name of the file
     */
    private void readPointsFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String label = parts[1];
                int x = Integer.parseInt(parts[2]) * SCALE_FACTOR;
                int y = Integer.parseInt(parts[3]) * SCALE_FACTOR;
                System.out.println("Inserting point: " + label + " (" + x + ", " + y + ")");
                kdTree.insert(new int[] { x, y }, label);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Paints the kdTree.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        kdTree.draw(g, 0, WIDTH, 0, HEIGHT, 0);
    }

    /**
     * Creates a JFrame and add the KDTreeVisualization panel to it.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        KDTreeVisualization panel = new KDTreeVisualization();
        frame.add(panel);
        frame.setTitle("KDTree Visualization");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
