package algorithm.tp;

import data.KV;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class MODITest {
    private double[]   supply;
    private double[]   demand;
    private double[][] cost;
    
    @Before public void setUp() throws Exception {
        
        supply = new double[] { 1, 1, 1, 1, 1, 1, 1, 3, 2, 2, 2, 1, 1, 1, 2, 1, 1, 1 };
        demand = new double[] { 1, 1, 1, 1, 1, 1, 1, 17 };
        cost = new double[][] { new double[] { 3, 3, 3, 3, 3, 3, 3, 0 },
                                new double[] { 3, 3, 3, 3, 3, 3, 3, 0 },
                                new double[] { 3, 3, 2, 3, 2, 3, 2, 0 },
                                new double[] { 3, 3, 3, 3, 3, 3, 3, 0 },
                                new double[] { 3, 3, 3, 3, 3, 3, 3, 0 },
                                new double[] { 3, 3, 3, 3, 3, 3, 3, 0 },
                                new double[] { 1, 1, 3, 2, 3, 3, 3, 0 },
                                new double[] { 1, 1, 3, 2, 3, 3, 3, 0 },
                                new double[] { 3, 3, 1, 3, 3, 2, 3, 0 },
                                new double[] { 2, 2, 3, 1, 3, 3, 3, 0 },
                                new double[] { 2, 2, 3, 1, 3, 3, 3, 0 },
                                new double[] { 3, 3, 3, 3, 1, 2, 1, 0 },
                                new double[] { 3, 3, 3, 3, 3, 3, 3, 0 },
                                new double[] { 3, 3, 3, 3, 3, 3, 3, 0 },
                                new double[] { 3, 3, 3, 3, 3, 3, 3, 0 },
                                new double[] { 3, 3, 3, 3, 3, 3, 3, 0 },
                                new double[] { 3, 3, 3, 3, 2, 3, 2, 0 },
                                new double[] { 3, 3, 3, 3, 2, 3, 2, 0 } };
    }
    
    @Test public void optimize() {
        VAM vam = new VAM(supply, demand, cost);
        vam.execute();
        Map<KV<Integer, Integer>, Double> results = vam.getResults();
        System.out.println("results = " + results);
        
        MODI modi = new MODI(supply, demand, cost, results);
        Map<KV<Integer, Integer>, Double> optimize = modi.optimize();
        System.out.println("optimize = " + optimize);
        
    }
}