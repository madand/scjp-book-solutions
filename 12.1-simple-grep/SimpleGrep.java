import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class SimpleGrep {
    private static final String DEFAULT_INPUT_CHARSET = "UTF8";
    private static final int ERROR_EXIT_STATUS = 1;

    public static void main(String[] args) {
        if (args.length == 0 || args.length > 2) {
            printErrorWithHelpAndExit("Invalid number of arguments (" + args.length + ").");
        }

        if (args.length == 1 && (args[0].equals("-h") || args[0].equals("--help"))) {
            printHelpAndExit();
        }

        String regex = args[0];
        String fileName = args[1];

        try {
            grepFile(fileName, regex);
        } catch (PatternSyntaxException e) {
            printErrorAndExit("Invalid pattern.\n" + e.getMessage());
        } catch (IOException e) {
            printErrorAndExit(e.getMessage());
        }
    }

    /**
     * Find and print matches for the regex in the text file.
     *
     * @param fileName
     * @param regex
     * @throws IOException
     */
    public static void grepFile(String fileName, String regex) throws IOException {
        Pattern pattern = Pattern.compile(regex);
        var scanner = new Scanner(new File(fileName), DEFAULT_INPUT_CHARSET);

        int lineNo = 1;
        while (scanner.hasNextLine()) {
            List<String> matches = grepLine(pattern, scanner.nextLine());

            if (!matches.isEmpty()) {
                System.out.println(lineNo + ": " + matches);
            }

            lineNo++;
        }
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

    static void printHelpAndExit() {
        printHelp();
        System.exit(0);
    }

    /**
     * Print the help message to the stdout.
     */
    static void printHelp() {
        String help = "Usage: java " + SimpleGrep.class.getName() + " regexp [inFile]\n" +
                "    If inFile is omitted, the standard input is used.\n"+
                "    The charset used for both input and output is \"" + DEFAULT_INPUT_CHARSET + "\".\n";
        System.out.print(help);
    }
}
