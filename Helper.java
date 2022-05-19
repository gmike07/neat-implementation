import java.util.Random;

public class Helper {
    //--------------------------------------------------------------------------------------------------------
    /**
     * @param value the value to clamp
     * @param min the minimum value
     * @param max the maximum value
     * @return the value to be between min amd max
     */
    public static double clamp(double value, double min, double max){
        if(value < min)
            return min;
        if(value > max)
            return max;
        return value;
    }
    //--------------------------------------------------------------------------------------------------------
    /**
     * @param random the random object
     * @param min the minimum value
     * @param max the maximum value
     * @return a random number between min and max
     */
    public static double getRandomBetween(Random random, double min, double max){
        return random.nextDouble() * (max - min) + min;
    }

    //--------------------------------------------------------------------------------------------------------
    /**
     * sigmoid activation function
     */
    public static double sigmoid(double x) {
        return 1 / (1 + Math.exp(-4.9 * x));
    }

    //--------------------------------------------------------------------------------------------------------
    /**
     * step activation function
     */
    public static double stepFunction(float x) {
        if (x < 0)
            return 0;
        return 1;
    }
}
