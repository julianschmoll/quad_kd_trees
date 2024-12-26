import java.awt.Graphics;

public class KDTree {
    private KDTreeKnot root;
    private int k;

    public KDTree(int k) {
        this.k = k;
        this.root = null;
    }

    public void insert(int[] point) {
        root = insertRec(root, point, 0);
    }

    private KDTreeKnot insertRec(KDTreeKnot root, int[] point, int depth) {
        if (root == null) {
            return new KDTreeKnot(point);
        }

        int cd = depth % k;
        if (point[cd] < root.point[cd]) {
            root.left = insertRec(root.left, point, depth + 1);
        } else {
            root.right = insertRec(root.right, point, depth + 1);
        }

        return root;
    }

    public void draw(Graphics g, int xMin, int xMax, int yMin, int yMax, int depth) {
        drawRec(g, root, xMin, xMax, yMin, yMax, depth);
    }

    private void drawRec(Graphics g, KDTreeKnot node, int xMin, int xMax, int yMin, int yMax, int depth) {
        if (node == null) {
            return;
        }

        int cd = depth % k;
        if (cd == 0) {
            g.drawLine(node.point[0], yMin, node.point[0], yMax);
            drawRec(g, node.left, xMin, node.point[0], yMin, yMax, depth + 1);
            drawRec(g, node.right, node.point[0], xMax, yMin, yMax, depth + 1);
        } else {
            g.drawLine(xMin, node.point[1], xMax, node.point[1]);
            drawRec(g, node.left, xMin, xMax, yMin, node.point[1], depth + 1);
            drawRec(g, node.right, xMin, xMax, node.point[1], yMax, depth + 1);
        }

        g.fillOval(node.point[0] - 2, node.point[1] - 2, 4, 4);
    }
}