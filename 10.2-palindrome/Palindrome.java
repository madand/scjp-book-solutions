
public class Palindrome {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please provide exactly one argument - a word.");
            System.exit(1);
        }

        String inputWord = args[0];

        System.out.println("The word \"" + inputWord + "\" " +
                (isPalindrome(inputWord) ? "IS" : "IS NOT") + " a palindrome.");
    }

    /**
     * Check whether the given word is a palindrome. A word is a palindrome if it reads the same backwards.
     * Example: madam.
     *
     * @param word the input word.
     * @return true if the word is a palindrome, false - otherwise.
     */
    static boolean isPalindrome(String word) {
        String reversed = new StringBuilder(word).reverse().toString();

        return word.equals(reversed);
    }

    /**
     * Check whether the given number is odd.
     *
     * @param n the input number.
     * @return true if the number is odd, false - if even.
     */
    private static boolean isOdd(int n) {
        return n % 2 != 0;
    }
}
