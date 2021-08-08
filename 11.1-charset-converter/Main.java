
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

public final class Main {
    private static final String DEFAULT_INPUT_CHARSET = "8859_1";
    private static final String DEFAULT_OUTPUT_CHARSET = "UTF8";

    private static Charset inputCharset;
    private static Charset outputCharset;
    private static String inputFileName = "";
    private static String outputFileName = "";

    public static void main(String[] args) {
        if (args.length > 4) {
            printErrorAndDie("Too many arguments (" + args.length + ").");
        }

        if (args.length == 1 && (args[0].equals("-h") || args[0].equals("--help"))) {
            printHelp();
            System.exit(0);
        }

        // Read charset and file names from the arguments.
        try {
            processArgs(args);
        } catch (UnsupportedCharsetException e) {
            printErrorAndDie("Unsupported charset " + e.getCharsetName());
        }

        var converter = new CharsetConverter(inputCharset, outputCharset);

        // Preform the conversion. Note that the output file will be crated if not exists, or overwritten otherwise.
        try (var inputStream = getFileInputStream(inputFileName);
             var outputStream = getFileOutputStream(outputFileName)) {
            converter.convert(inputStream, outputStream);
        } catch (IOException e) {
            printErrorAndDie(e.getMessage());
        }
    }

    /**
     * Initialize the application from the given arguments.
     *
     * @param args the command line arguments.
     */
    static void processArgs(String[] args) {
        String inputCharsetName = "";
        String outputCharsetName = "";

        if (args.length > 0) {
            inputCharsetName = args[0];
        }
        if (args.length > 1) {
            outputCharsetName = args[1];
        }
        if (args.length > 2) {
            inputFileName = args[2];
        }
        if (args.length > 3) {
            outputFileName = args[3];
        }

        inputCharset = tryLoadCharset(inputCharsetName, DEFAULT_INPUT_CHARSET);
        outputCharset = tryLoadCharset(outputCharsetName, DEFAULT_OUTPUT_CHARSET);
    }

    /**
     * Return the input stream for the given file name. If the file name is empty, standard input is returned.
     *
     * @param fileName the file name or the empty string.
     * @return
     * @throws FileNotFoundException
     */
    static InputStream getFileInputStream(String fileName) throws FileNotFoundException {
        if (fileName.isEmpty()) {
            return System.in;
        }

        return new FileInputStream(fileName);
    }

    /**
     * Return the output stream for the given file name. If the file name is empty, standard output is returned.
     *
     * @param fileName the file name or the empty string.
     * @return
     * @throws FileNotFoundException
     */
    static OutputStream getFileOutputStream(String fileName) throws FileNotFoundException {
        if (fileName.isEmpty()) {
            return System.out;
        }

        return new FileOutputStream(fileName);
    }

    /**
     * Return a charset designated by the given name. If the {@code charsetName} is empty, a charset designated by
     * {@code fallbackCharsetName} is returned instead.
     *
     * @param charsetName the name of a charset.
     * @param fallbackCharsetName the name of a fallback charset.
     * @return
     */
    static Charset tryLoadCharset(String charsetName, String fallbackCharsetName) {
        if (charsetName.isEmpty()) {
            return Charset.forName(fallbackCharsetName);
        }

        return Charset.forName(charsetName);
    }

    /**
     * Print the given error message to stderr and terminate the app with non-zero exit code.
     *
     * @param message the error message.
     */
    static void printErrorAndDie(String message) {
        System.err.println("Error: " + message);
        System.exit(1);
    }

    /**
     * Print the help message to stderr.
     */
    static void printHelp() {
        String help = "Usage: java " + Main.class.getName() + " [inCharset] [outCharset] [inFile] [outFile]\n" +
                "If you want to skip an argument, specify \"\" as its value.\n"+
                "The default values for arguments are: \n" +
                "  inCharset   = " + DEFAULT_INPUT_CHARSET + "\n" +
                "  outCharset  = " + DEFAULT_OUTPUT_CHARSET + "\n" +
                "  inFile      = standard input\n" +
                "  outFile     = standard output\n";
        System.err.print(help);
    }
}

