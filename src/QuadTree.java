/**
 * Quad Tree data structure for 2D points.
 */
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import java.awt.Color;

/**
 * QuadTree data structure for 2D points.
 */
public class QuadTree {
    private final int MAX_CAPACITY = 4;
    private List<QuadTreeKnot> knots;
    private int x, y, width, height;
    private boolean divided, showLabels;
    private QuadTree northeast, northwest, southeast, southwest;

    /**
     * Constructor for QuadTree.
     * @param x x-coordinate of the QuadTree.
     * @param y y-coordinate of the QuadTree.
     * @param width width of the QuadTree.
     * @param height height of the QuadTree.
     */
    public QuadTree(int x, int y, int width, int height, boolean showLabels) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.knots = new ArrayList<>();
        this.divided = false;
        this.showLabels = showLabels;
    }

    /**
     * Subdivide the QuadTree into four quadrants.
     */
    public void subdivide() {
        int halfWidth = width / 2;
        int halfHeight = height / 2;

        int xHalf = x + halfWidth;
        int yHalf = y + halfHeight;

        northeast = new QuadTree(xHalf, y, halfWidth, halfHeight, showLabels);
        northwest = new QuadTree(x, y, halfWidth, halfHeight, showLabels);
        southeast = new QuadTree(xHalf, yHalf, halfWidth, halfHeight, showLabels);
        southwest = new QuadTree(x, yHalf, halfWidth, halfHeight, showLabels);

        // Insert points here as well so capacity can be calculated correctly
        for(QuadTreeKnot pKnot : knots){
            northeast.insert(pKnot);
            northwest.insert(pKnot);
            southeast.insert(pKnot);
            southwest.insert(pKnot);
        }

        divided = true;
    }

    /**
     * Insert a point into the QuadTree.
     * @param knot the point to insert.
     */
    public void insert(QuadTreeKnot knot) {
        if (!contains(knot)) {
            return;
        }

        if (knots.size() < MAX_CAPACITY) {
            knots.add(knot);
        } else {
            if (!divided) {
                subdivide();
            }
            northeast.insert(knot);
            northwest.insert(knot);
            southeast.insert(knot);
            southwest.insert(knot);
        }
    }

    public boolean contains(QuadTreeKnot knot) {
        return knot.x >= x && knot.x < x + width && knot.y >= y && knot.y < y + height;
    }

    /**
     * Draw the QuadTree.
     * @param g the Graphics object to draw on.
     */
    public void draw(Graphics g) {
        Color red = Color.RED;
        Color black = Color.BLACK;

        g.setColor(black);
        g.drawRect(x, y, width, height);

        if (divided) {
            northeast.draw(g);
            northwest.draw(g);
            southeast.draw(g);
            southwest.draw(g);
        }

        for (QuadTreeKnot knot : knots) {
            g.setColor(red);
            g.fillOval(knot.x, knot.y, 4, 4);

            if (showLabels) {
                g.setColor(black);
                g.drawString(knot.label, knot.x, knot.y);
            }
        }
    }
}
