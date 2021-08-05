
public class PrintFuncApp {
    public static void main(String[] args) {
        int size = 10;
        var inputArr = new int[size];
        for (int i = 0; i < size; i++) {
            inputArr[i] = i + 1;
        }

        var printer = new Print();

        System.out.print("Input:  ");
        Client.operateOnArr(inputArr, printer);
        System.out.println();

        var printHalver = new PrintFunc(new Half());

        System.out.print("Output: ");
        Client.operateOnArr(inputArr, printHalver);
        System.out.println();
    }

    static class PrintFunc extends Print {
        private Function fn;

        public PrintFunc(Function fn) {
            this.fn = fn;
        }

        @Override
        public int evaluate(int arg) {
            return super.evaluate(fn.evaluate(arg));
        }
    }
}

class Client {
    /**
     * Return an array of the same length as {@code arr}, with each element
     * being the result of applying {@code op.evaluate()} to the corresponding
     * element of the {@code arr}.
     *
     * @param arr the input array.
     * @return the resulting array.
     */
    static int[] operateOnArr(int[] arr, Function op) {
        var result = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            result[i] = op.evaluate(arr[i]);
        }

        return result;
    }
}

class Print implements Function {
    @Override
    public int evaluate(int arg) {
        System.out.print(arg + " ");
        return arg;
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
