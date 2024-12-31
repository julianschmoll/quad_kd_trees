import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.color.*;
import java.util.random.*;

public class QuadTree {
    private final int MAX_CAPACITY = 4;
    private List<QuadTreeKnot> knots;
    private int x, y, width, height;
    private boolean divided;
    private QuadTree northeast, northwest, southeast, southwest;

    public QuadTree(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.knots = new ArrayList<>();
        this.divided = false;
    }

    public void subdivide() {
        int halfWidth = width / 2;
        int halfHeight = height / 2;
        northeast = new QuadTree(x + halfWidth, y, halfWidth, halfHeight);
        northwest = new QuadTree(x, y, halfWidth, halfHeight);
        southeast = new QuadTree(x + halfWidth, y + halfHeight, halfWidth, halfHeight);
        southwest = new QuadTree(x, y + halfHeight, halfWidth, halfHeight);
        divided = true;

        for (QuadTreeKnot parentKnot : knots) {
            northeast.insert(parentKnot);
            northwest.insert(parentKnot);
            southeast.insert(parentKnot);
            southwest.insert(parentKnot);
        }
    }

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
        }
    }
}
