/**
 * Quad Tree data structure for 2D points.
 */
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

        for (QuadTreeKnot parentKnot : knots) {
            northeast.insert(parentKnot);
            northwest.insert(parentKnot);
            southeast.insert(parentKnot);
            southwest.insert(parentKnot);
        }
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

        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);

        if (divided) {
            northeast.draw(g);
            northwest.draw(g);
            southeast.draw(g);
            southwest.draw(g);
        }

        for (QuadTreeKnot knot : knots) {

            Random rand = new Random();
            // Java 'Color' class takes 3 floats, from 0 to 1.
            float red = rand.nextFloat();
            float green = rand.nextFloat();
            float blue = rand.nextFloat();
            Color randomColor = new Color(red, green, blue);
            g.setColor(randomColor);
            g.fillOval(knot.x, knot.y, 4, 4);

            if (showLabels) {
                g.setColor(Color.BLACK);
                g.drawString(knot.label, knot.x, knot.y);
            }
        }
    }
}
