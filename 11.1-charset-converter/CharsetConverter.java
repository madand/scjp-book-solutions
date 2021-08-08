
import java.io.*;
import java.nio.charset.Charset;

/**
 * Helper class for converting
 */
public final class CharsetConverter {
    public static final int BUFFER_SIZE = 512;

    private final Charset inputCharset;
    private final Charset outputCharset;

    /**
     * Create the new converter that can convert streams between the given charsets.
     *
     * @param inputCharset the charset to convert input streams from.
     * @param outputCharset the charset to convert output streams to.
     */
    public CharsetConverter(Charset inputCharset, Charset outputCharset) {
        this.inputCharset = inputCharset;
        this.outputCharset = outputCharset;
    }

    /**
     * Convert contents of the given {@code inputStream} into different charset and write the result into the given
     * {@code outputStream}.
     * <p>
     * The input and output charsets are specified via the class constructor.
     *
     * @param inputStream  the input (source) stream.
     * @param outputStream the output (destination) stream.
     * @throws IOException
     */
    public void convert(InputStream inputStream, OutputStream outputStream) throws IOException {
        try (Reader reader = makeBufferedReader(inputStream);
             Writer writer = makeBufferedWriter(outputStream)) {
            // Transfer data from the reader to the writer in a char-by-char manner.
            int ch = reader.read();
            while (ch != -1) {
                writer.write(ch);
                ch = reader.read();
            }
        }
    }

    /**
     * Return new Reader that reads from the given input stream with the given charset.
     * <p>
     * The reading operations from {@code inputStream} are buffered.
     *
     * @param inputStream
     * @return
     */
    private Reader makeBufferedReader(InputStream inputStream) {
        var bufferedInputStream = new BufferedInputStream(inputStream, BUFFER_SIZE);
        return new InputStreamReader(bufferedInputStream, inputCharset);
    }

    /**
     * Return new Writer that writes to the given output stream with the given charset.
     * <p>
     * The writing operations into {@code outputStream} are buffered.
     *
     * @param outputStream
     * @return
     */
    private Writer makeBufferedWriter(OutputStream outputStream) {
        var bufferedOutputStream = new BufferedOutputStream(outputStream, BUFFER_SIZE);
        return new OutputStreamWriter(bufferedOutputStream, outputCharset);
    }
}
