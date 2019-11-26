package math;

/**
 * https://en.wikipedia.org/wiki/Newton%27s_method
 */
public class NewtonRaphsonMethod {
    
    private static double DEFAULT_ACCURACY = 0.000001;
    
    public static double calcRoot(double number, int root) {
        return calcRoot(number, root, DEFAULT_ACCURACY);
    }
    
    public static double calcRoot(double number, int root, double accuracy) {
        if(root == 0 || (number < 0 && root % 2 == 0)) {
            return Double.NaN;
        }
        
        double temp = 1.0;
        double rootResult = temp - ((Math.pow(temp, root) - number) / (root * Math.pow(temp, root - 1)));
        
        while(Math.abs(rootResult - temp) > accuracy) {
            temp = rootResult;
            rootResult = temp - ((Math.pow(temp, root) - number) / (root * Math.pow(temp, root - 1)));
        }
        
        return rootResult;
    }
    
    public static double squareRoot(double number) {
        double temp = 1.0;
        double root = temp - ((temp * temp - number) / (2 * temp));
        
        while(Math.abs(root - temp) > DEFAULT_ACCURACY) {
            temp = root;
            root = temp - ((temp * temp - number) / (2 * temp));
            
        }
        
        return root;
    }
    
    public static double cubeRoot(double number) {
        double root = 0.0;
        if(number < 0) {
            
            root = Double.NaN;
        }
        else {
            double temp = 1.0;
            root = temp - ((temp * temp * temp - number) / (3 * temp * temp));
            while(Math.abs(root - temp) > DEFAULT_ACCURACY) {
                temp = root;
                root = temp - ((temp * temp * temp - number) / (3 * temp * temp));
            }
        }
        return root;
    }
    
}
