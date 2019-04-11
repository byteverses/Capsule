package algorithm.vq;

@FunctionalInterface
public interface DistanceMeasurer<Point> {

    double distance(Point centroid, Point point);
}
