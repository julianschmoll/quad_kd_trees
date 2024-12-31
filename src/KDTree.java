/**
 * KDTree
 */
import java.awt.Graphics;


/**
 * Data structure creating and drawing a KD Tree.
 */
public class KDTree {
    private KDTreeKnot root;
    private boolean showLabels;

    /**
     * Creates a new KDTree.
     * 
     * @param showLabels show labels of the points
     */
    public KDTree(boolean showLabels) {
        this.root = null;
        this.showLabels = showLabels;
    }

    /**
     * Inserts a new point into the KDTree.
     * @param point coordinates of the point
     * @param label label of the point
     */
    public void insert(int[] point, String label) {
        root = insertRec(root, point, 0, label);
    }

    /**
     * Recursively inserts a new point into the KDTree.
     * 
     * @param root root of the KDTree
     * @param point coordinates of the point
     * @param depth depth of the KDTree
     * @param label label of the point
     * 
     * @return the root of the KDTree
     */
    private KDTreeKnot insertRec(KDTreeKnot root, int[] point, int depth, String label) {
        if (root == null) {
            return new KDTreeKnot(point, label);
        }

        int cd = depth % 2;
        if (point[cd] < root.point[cd]) {
            root.left = insertRec(root.left, point, depth + 1, label);
        } else {
            root.right = insertRec(root.right, point, depth + 1, label);
        }

        return root;
    }

    /**
     * Draws the KDTree.
     * 
     * @param g graphics object
     * @param xMin minimum x coordinate
     * @param xMax maximum x coordinate
     * @param yMin minimum y coordinate
     * @param yMax maximum y coordinate
     * @param depth depth of the KDTree
     */
    public void draw(Graphics g, int xMin, int xMax, int yMin, int yMax, int depth) {
        draw(g, root, xMin, xMax, yMin, yMax, depth);
    }

    /**
     * Recursively draws the KDTree.
     * 
     * @param g graphics object
     * @param node current node
     * @param xMin minimum x coordinate
     * @param xMax  maximum x coordinate
     * @param yMin minimum y coordinate
     * @param yMax maximum y coordinate
     * @param depth depth of the KDTree
     */
    private void draw(Graphics g, KDTreeKnot node, int xMin, int xMax, int yMin, int yMax, int depth) {
        if (node == null) {
            return;
        }

        int x = node.point[0];
        int y = node.point[1];

        int cd = depth % 2;
        if (cd == 0) {
            g.drawLine(x, yMin, x, yMax);
            draw(g, node.left, xMin, x, yMin, yMax, depth + 1);
            draw(g, node.right, x, xMax, yMin, yMax, depth + 1);
        } else {
            g.drawLine(xMin, y, xMax, y);
            draw(g, node.left, xMin, xMax, yMin, y, depth + 1);
            draw(g, node.right, xMin, xMax, y, yMax, depth + 1);
        }

        g.fillOval(x - 2, y - 2, 4, 4);

        if (showLabels) {
            g.drawString(node.label, x, y);
        }
    }
}
