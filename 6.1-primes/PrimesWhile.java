
/**
 * Generate all prime numbers below 100 using a while loop.
 *
 * We employ trial division (brute force) approach for the sake of simplicity.
 */
public class PrimesWhile {
    /**
     * Lowest known prime number.
     */
    private static final int lowestPrime = 2;

    /**
     * Main loop's upper bound.
     */
    private static final int upperBound = 100;

    public static void main(String[] args) {
        int maybePrime = lowestPrime;
        main_loop: while (maybePrime < upperBound) {
            int maybeDivisor = lowestPrime;
            while (maybeDivisor < maybePrime) {
                if ((maybePrime % maybeDivisor) == 0) {
                    // We must increment here because we skip the rest of the main loop iteration.
                    maybePrime++;
                    continue main_loop;
                }

                maybeDivisor++;
            }

            // If control reached this point, maybePrime is a prime.
            System.out.print(maybePrime + " ");
            maybePrime++;
        }

        System.out.println();
    }
}
