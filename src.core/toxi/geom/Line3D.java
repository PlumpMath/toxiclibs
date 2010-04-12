package toxi.geom;

import java.util.ArrayList;
import java.util.List;

public class Line3D {

    /**
     * Splits the line between A and B into segments of the given length,
     * starting at point A. The tweened points are added to the given result
     * list. The last point added is B itself and hence it is likely that the
     * last segment has a shorter length than the step length requested. The
     * first point (A) can be omitted and not be added to the list if so
     * desired.
     * 
     * @param a
     *            start point
     * @param b
     *            end point (always added to results)
     * @param stepLength
     *            desired distance between points
     * @param segments
     *            existing array list for results (or a new list, if null)
     * @param addFirst
     *            false, if A is NOT to be added to results
     * @return list of result vectors
     */
    public static final List<Vec3D> splitIntoSegments(Vec3D a, Vec3D b,
            float stepLength, List<Vec3D> segments, boolean addFirst) {
        if (segments == null) {
            segments = new ArrayList<Vec3D>();
        }
        if (addFirst) {
            segments.add(a.copy());
        }
        float dist = a.distanceTo(b);
        if (dist > stepLength) {
            Vec3D pos = a.copy();
            Vec3D step = b.sub(a).limit(stepLength);
            while (dist > stepLength) {
                pos.addSelf(step);
                segments.add(pos.copy());
                dist -= stepLength;
            }
        }
        segments.add(b.copy());
        return segments;
    }

    public Vec3D a, b;

    public Line3D(Vec3D a, Vec3D b) {
        this.a = a;
        this.b = b;
    }

    public Line3D copy() {
        return new Line3D(a.copy(), b.copy());
    }

    public Vec3D getDirection() {
        return b.sub(a).normalize();
    }

    public float getLength() {
        return a.distanceTo(b);
    }

    public Vec3D getMidPoint() {
        return a.add(b).scaleSelf(0.5f);
    }

    public Vec3D getNormal() {
        return b.cross(a);
    }

    public List<Vec3D> splitIntoSegments(List<Vec3D> segments,
            float stepLength, boolean addFirst) {
        return splitIntoSegments(a, b, stepLength, segments, addFirst);
    }

    public Ray3D toRay3D() {
        return new Ray3D(a.copy(), b.sub(a).normalize());
    }

    public String toString() {
        return a.toString() + " -> " + b.toString();
    }
}
