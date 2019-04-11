package algorithm.vq;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class KmeansTest {
    
    private static double distance(double[] centroid, double[] point) {
        return Math.pow(Math.abs(centroid[0] - point[0]), 2) + Math.pow(Math.abs(centroid[1] - point[1]), 2);
    }
    
    private static double[] centre(Collection<double[]> pointSet) {
        double xSum = 0.0;
        double ySum = 0.0;
        for(double[] point : pointSet) {
            xSum += point[0];
            ySum += point[1];
        }
        return pointSet.size() == 0 ? new double[] {0, 0}
                                    : new double[] {xSum / pointSet.size(), ySum / pointSet.size()};
    }
    
    @Test
    public void cluster() {
        
        double[][] points = {{30, 2}, {1, 1}, {2, 2}, {33, 3}, {44, 3}, {55, 5}, {55, 6}};
    
        printResult(new Kmeans<>(2, points, KmeansTest::distance, KmeansTest::centre).cluster());
    
        printResult(new Kmeans<>(3, points, KmeansTest::distance, KmeansTest::centre).cluster());
    
        printResult(new Kmeans<>(4, points, KmeansTest::distance, KmeansTest::centre).cluster());
    
    }
    
    private void printResult(TreeMap<Integer, Set<double[]>> cluster) {
        cluster.entrySet()
               .stream()
               .map(entry -> entry.getKey() + " = " + entry.getValue()
                                                           .stream()
                                                           .map(Arrays::toString)
                                                           .collect(Collectors.joining(", ")))
               .forEach(System.out::println);
        System.out.println();
    }
}