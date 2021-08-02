
/**
 * Generate all prime numbers below 100 using a for(;;) loop.
 *
 * We employ trial division (brute force) approach for the sake of simplicity.
 */
public class PrimesFor {
    /**
     * Lowest known prime number.
     */
    private static final int lowestPrime = 2;

    /**
     * Main loop's upper bound.
     */
    private static final int upperBound = 100;

    public static void main(String[] args) {
        main_loop: for (int maybePrime = lowestPrime; maybePrime < upperBound; maybePrime++) {
            for (int maybeDivisor = lowestPrime; maybeDivisor < maybePrime; maybeDivisor++) {
                if ((maybePrime % maybeDivisor) == 0) {
                    continue main_loop;
                }
            }

            // If control reached this point, maybePrime is a prime.
            System.out.print(maybePrime + " ");
       }

        System.out.println();
    }
}
