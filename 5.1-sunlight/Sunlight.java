
public class Sunlight {
    public static void main(String[] args) {
        // Distance from sun (150 million kilometers)
        int kmFromSun = 150_000_000;

        int lightSpeed = 299_792_458;

        // Convert distance to meters.
        long mFromSun = kmFromSun * 1000L;

        int seconds = (int) (mFromSun / lightSpeed);

        System.out.print("Light will use ");
        printTime(seconds);
        System.out.println(" to travel from the sun to the earth.");
    }

    public static void printTime(int sec) {
        int min = sec / 60;
        sec = sec - (min * 60);
        System.out.print(min + " minute(s) and " + sec + " second(s)");
    }
}
