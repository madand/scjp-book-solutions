
public class HalfApp1 {
    private static final int[] input = { 3, 10, 50, 14, 33 };

    public static void main(String[] args) {
        System.out.println("Input:  " + java.util.Arrays.toString(input));
        System.out.println("Output: " + java.util.Arrays.toString(Client.halfArray(input)));
    }
}

class Client {
    /**
     * Return an array of the same length as {@code arr}, with each element being
     * the half of the corresponding value of the {@code arr}.
     *
     * @param arr the input array.
     * @return the resulting array.
     */
    static int[] halfArray(int[] arr) {
        Function halfFn = new Half();
        var result = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            result[i] = halfFn.evaluate(arr[i]);
        }

        return result;
    }
}

class Half implements Function {
    @Override
    public int evaluate(int arg) {
        return arg / 2;
    }
}

interface Function {
    int evaluate(int arg);
}
