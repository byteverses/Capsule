package util;

import java.util.Arrays;

public class MathUtil {
    
    private MathUtil(){}


    /**
     * data = [a,b,c, ...]
     * devsq = sum((a-avg(a,b,c))^2, (b-avg(a,b,c))^2, (c-avg(a,b,c))^2)
     */
    public static double devsq(double[] data) {
        double average = Arrays.stream(data).average().getAsDouble();
        return Arrays.stream(data).map(num -> Math.pow((num - average), 2)).sum();
    }

    /**
     * data = [a,b,c, ...]
     * devsq2 = sum(a^2,b^2,c^2) - sum(a,b,c)^2/count(data)
     */
    public static double devsq2(double[] data) {
        
        double sum = 0.0;
        double powSum = 0.0;
        for(double val : data) {
            sum += val;
            powSum += Math.pow(val, 2);
        }
        return powSum - Math.pow(sum, 2)/data.length;
    }
}
