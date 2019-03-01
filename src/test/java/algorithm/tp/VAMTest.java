package algorithm.tp;

import data.KV;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class VAMTest {
    
    private double[]   supply;
    private double[]   demand;
    private double[][] cost;
    
    @Before public void setUp() throws Exception {
        supply = new double[] { 10, 20, 30};
        demand = new double[] { 20, 20, 20 };
        cost = new double[][] { new double[] { 2, 4, 8 },
                                new double[] { 4, 5, 1 },
                                new double[] { 3, 7, 8 } };
    }
    
    @Test public void execute() {
        VAM vam = new VAM(supply, demand, cost);
        vam.execute();
        Map<KV<Integer, Integer>, Double> results = vam.getResults();
        System.out.println("results = " + results);
        
        Integer minIdx = Stream.of(0, 1, 2).min(Comparator.comparing(idx -> this.cost[0][idx])).get();
        System.out.println("minIdx = " + minIdx);
    }
    
    @Test public void rowMinDiff() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method rowMinDiff = VAM.class.getDeclaredMethod("rowMinDiff", double[][].class, Set.class, Set.class);
        System.out.println("rowMinDiff = " + rowMinDiff);
        
        rowMinDiff.setAccessible(true);
        VAM vam = new VAM(supply, demand, cost);
        
        Object invoke = rowMinDiff.invoke(vam,
                                          cost,
                                          IntStream.range(0, supply.length).boxed().collect(Collectors.toSet()),
                                          IntStream.range(0, demand.length).boxed().collect(Collectors.toSet()));
        System.out.println("invoke = " + invoke);
    }
    
    @Test public void colMinDiff() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method colMinDiff = VAM.class.getDeclaredMethod("colMinDiff", double[][].class, Set.class, Set.class);
        System.out.println("colMinDiff = " + colMinDiff);
        
        colMinDiff.setAccessible(true);
        VAM vam = new VAM(supply, demand, cost);
        Object invoke = colMinDiff.invoke(vam,
                                          cost,
                                          IntStream.range(0, supply.length).boxed().collect(Collectors.toSet()),
                                          IntStream.range(0, demand.length).boxed().collect(Collectors.toSet()));
        System.out.println("invoke = " + invoke);
    }
}