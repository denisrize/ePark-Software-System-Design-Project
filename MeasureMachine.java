import java.util.Random;

public class MeasureMachine {
    public static Random random = new Random();

    public static float getWeight() {
        return 40 + random.nextFloat()*(60-40);//random.nextFloat(40, 60);
    }

    public static float getHeight() {
        return (float)1.4 + random.nextFloat()*((float)1.6-(float)1.4);
    }
}
