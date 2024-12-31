/**
 * Represents a knot in a KDTree.
 */
public class KDTreeKnot {
    public int[] point;
    public KDTreeKnot left, right;
    public String label;

    /**
     * Creates a new KDTreeKnot.
     * 
     * @param point point coordinates
     * @param label label of the point
     */
    public KDTreeKnot(int[] point, String label) {
        this.point = point;
        this.left = null;
        this.right = null;
        this.label = label;
    }
}
