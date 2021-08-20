import java.util.*;

public class ToMultiMap {
    private static final int ERROR_EXIT_STATUS = 1;

    public static void main(String[] args) {
        if (args.length == 0 || (args.length == 1 && (args[0].equals("-h") || args[0].equals("--help")))) {
            printHelpAndExit();
        }

        if (args.length % 2 != 0) {
            printErrorWithHelpAndExit("Invalid number of arguments. Please supply even number of arguments.");
        }

        Map<String, String> map = new HashMap<>();
        int i = 0;
        do {
            map.put(args[i++], args[i++]);
        } while (i < args.length);

        Map<String, List<String>> multiMap = toMultiMap(map);

        System.out.println("Input map: " + map);
        System.out.println("Multi map: " + multiMap);
    }

    /**
     * Returns the multimap from the given map.
     *
     * The multimap is a map that has the values of the original map as keys, and its values are lists of keys of the
     * original map.
     *
     * @param origMap the map to convert.
     * @param <K> type of the keys in the {@code origMap}.
     * @param <V> type of the values in the {@code origMap}.
     * @return the resulting multimap.
     */
    public static <K,V> Map<V, List<K>> toMultiMap(Map<K, V> origMap) {
        Map<V, List<K>> multimap = new HashMap<V, List<K>>();

        for (K key : origMap.keySet()) {
            V value = origMap.get(key);

            if (!multimap.containsKey(value)) {
                multimap.put(value, new ArrayList<>());
            }

            multimap.get(value).add(key);
        }

        return multimap;
    }


    /**
     * Print the given error message to the stderr.
     *
     * @param message the error message.
     */
    static void printError(String message) {
        System.err.println("Error: " + message);
    }

    /**
     * Print the given error message to the stderr, then print help mesaage to the stdout and finally terminate the app
     * with a non-zero exit code.
     *
     * @param message the error message.
     */
    static void printErrorWithHelpAndExit(String message) {
        printError(message);
        printHelp();
        System.exit(ERROR_EXIT_STATUS);
    }

    static void printHelpAndExit() {
        printHelp();
        System.exit(0);
    }

    /**
     * Print the help message to the stdout.
     */
    static void printHelp() {
        String help = "Usage: java " + ToMultiMap.class.getName() + " key value [key2 value2]...\n";
        System.out.print(help);
    }
}
