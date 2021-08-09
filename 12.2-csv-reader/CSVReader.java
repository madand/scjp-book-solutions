import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class CSVReader {
    private static final Charset DEFAULT_INPUT_CHARSET = StandardCharsets.UTF_8;
    private static final int ERROR_EXIT_STATUS = 1;

    public static void main(String[] args) {
        if (args.length == 0 || args.length > 2) {
            printErrorWithHelpAndExit("Invalid number of arguments (" + args.length + ").");
        }

        if (args.length == 1 && (args[0].equals("-h") || args[0].equals("--help"))) {
            printHelpAndExit();
        }

        String fileName = args[0];
        int numOfFields = Integer.parseInt(args[1]);

        try (var reader = new BufferedReader(new FileReader(fileName, DEFAULT_INPUT_CHARSET))) {
            readCSV(reader, numOfFields);
        } catch (IOException e) {
            printErrorAndExit(e.getMessage());
        }
    }

    /**
     * Read values in CSV format.
     *
     * @param source
     * @param numOfFields
     * @throws IOException
     */
    public static void readCSV(Readable source, int numOfFields) throws IOException {
        try (var scanner = new Scanner(source)) {
            readCSVInternal(scanner, numOfFields);
        }
    }

    private static void readCSVInternal(Scanner scanner, int numOfFields) {
        Pattern pattern = compilePattern(numOfFields);
        int lineNo = 1;
        var currRecord = new ArrayList<String>();

        while (scanner.hasNextLine()) {
            Matcher matcher = pattern.matcher(scanner.nextLine());

            if (!matcher.matches()) {
                printErrorAndExit("Line " + lineNo + " has an invalid number of fields.");
            }

            currRecord.clear();
            for (int groupNum = 1; groupNum <= numOfFields; groupNum++) {
                currRecord.add(matcher.group(groupNum));
            }
            System.out.println(currRecord);

            lineNo++;
        }
    }

    /**
     * Creates a pattern that corresponds to the number of fields specified in CSV format on each line/record.
     *
     * @param numOfFields
     * @return
     */
    public static Pattern compilePattern(int numOfFields) {
        if (numOfFields < 1) {
            throw new IllegalArgumentException("numOfFields must be 1 or greater");
        }

        final String firstFieldRegex = "([^,]*)";
        final String otherFieldsRegex = "," + firstFieldRegex;

        return Pattern.compile("^" + firstFieldRegex + otherFieldsRegex.repeat(numOfFields - 1) + "$");
    }

    /**
     * Find the matches of the pattern in the target.
     *
     * @param pattern
     * @param target
     * @return the list with the matches found in the {@code target}
     */
    public static List<String> grepLine(Pattern pattern, String target) {
        var result = new ArrayList<String>();
        Matcher matcher = pattern.matcher(target);

        while (matcher.find()) {
            result.add(String.format("(%d,%d:%s)", matcher.start(), matcher.end() - 1, matcher.group()));
        }

        return result;
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
     * Print the given error message to the stderr and terminate the app with a non-zero exit code.
     *
     * @param message the error message.
     */
    static void printErrorAndExit(String message) {
        printError(message);
        System.exit(ERROR_EXIT_STATUS);
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

    /**
     * Print the help text to stdout, then exit.
     */
    static void printHelpAndExit() {
        printHelp();
        System.exit(0);
    }

    /**
     * Print the help message to the stdout.
     */
    static void printHelp() {
        String help = "Usage: java " + CSVReader.class.getName() + " csvFile numOfFields\n" +
                "    csvFile      is an input file in CSV format.\n" +
                "    numOfFields  is a number of fields on every line in the csvFile.\n" +
                "The charset used for both input and output is \"" + DEFAULT_INPUT_CHARSET + "\".\n";
        System.out.print(help);
    }
}
