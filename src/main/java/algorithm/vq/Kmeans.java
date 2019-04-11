package algorithm.vq;

import util.ArrayUtil;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Kmeans<Point> {
    
    private int     k;
    private Point[] points;
    private Point[] centroids;
    private Centralizer<Point> centralizer;
    private DistanceMeasurer<Point> euclideanDistance;
    
    public Kmeans(int k, Point[] points, DistanceMeasurer<Point> euclideanDistance, Centralizer<Point> centralizer) {
        this.k = k;
        this.points = points;
        this.centralizer = centralizer;
        this.euclideanDistance = euclideanDistance;
        
        this.centroids = (Point[]) new Object[k];
        // Initialize centroids.
        for (int i = 0; i < k; i++) {
            centroids[i] = points[((i + 1) * points.length / (k + 1))];
        }
        
    }
    
    public TreeMap<Integer, Set<Point>> cluster() {
    
        TreeMap<Integer, Set<Point>> assignments = new TreeMap<>();
    
        for(int i = 0; i < 100; i++) {
            //Assignment step: Assign each observation to the cluster whose mean has the least squared Euclidean distance
            double[][] distance = this.calculateDistance(centroids, points, euclideanDistance);
    
            assignments = this.assignPoints(centroids, points, distance);
    
            // Update step: Calculate the new means (centroids) of the observations in the new clusters.
            this.moveCentroids(centralizer, points, assignments);
        }
        
        return assignments;
    }
    
    private double[][] calculateDistance(Point[] centroids,
                                         Point[] points,
                                         DistanceMeasurer<Point> distanceCalculator) {
        double[][] centroidDistance = new double[points.length][centroids.length];
        for(int cIdx = 0; cIdx < centroids.length; cIdx++) {
            Point centroid = centroids[cIdx];
            for(int pIdx = 0; pIdx < points.length; pIdx++) {
                Point point = points[pIdx];
                // calculate distance
                centroidDistance[pIdx][cIdx] = distanceCalculator.distance(centroid, point);
            }
        }
        
        return centroidDistance;
    }
    
    private TreeMap<Integer, Set<Point>> assignPoints(Point[] centroids, Point[] points, double[][] distance) {
    
        TreeMap<Integer, Set<Point>> assignment = new TreeMap<>();
        for(int i = 0; i < points.length; i++) {
            Point point = points[i];
            double[] pd = distance[i];
            Integer minIdx = ArrayUtil.findMin(pd).getX();
    
            Set<Point> pointSet = assignment.computeIfAbsent(minIdx, idx -> new HashSet<>());
            pointSet.add(point);
        }
        
        return assignment;
    }
    
    private void moveCentroids(Centralizer<Point> centralizer, Point[] points, TreeMap<Integer, Set<Point>> assignments) {
        
        
        for(Map.Entry<Integer, Set<Point>> entry : assignments.entrySet()) {
            Integer centroidIdx = entry.getKey();
            Set<Point> pointSet = entry.getValue();
            
            // calculate a new centroid in pointSet.
            Point centroid = centralizer.centre(pointSet);
            this.centroids[centroidIdx] = centroid;
        }
    
    }
}
