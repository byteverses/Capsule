package util;

public class MathUtil {
    
    private MathUtil(){}
    
    public static double devsq(double[] data) {
        
        double sum = 0.0;
        double powSum = 0.0;
        for(double val : data) {
            sum += val;
            powSum += Math.pow(val, 2);
        }
        return powSum - Math.pow(sum, 2)/data.length;
    }
}
