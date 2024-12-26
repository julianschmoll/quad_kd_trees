public class KDTreeKnot {
    public int[] point;
    public KDTreeKnot left, right;

    public KDTreeKnot(int[] point) {
        this.point = point;
        this.left = null;
        this.right = null;
    }
}
