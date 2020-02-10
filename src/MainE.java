import java.io.*;

public class MainE {
    private static class LineReader {
        private final BufferedReader reader;

        private LineReader(Reader reader) {
            this.reader = new BufferedReader(reader);
        }

        private int readSymbol() throws IOException {
            return reader.read();
        }

        private void close() throws IOException {
            reader.close();
        }
    }

    private static class LineWriter {
        private final BufferedWriter writer;

        private LineWriter(Writer writer) {
            this.writer = new BufferedWriter(writer);
        }

        private void writeLine(String s) throws IOException {
            writer.write(s);
            writer.newLine();
        }

        private void close() throws IOException {
            writer.close();
        }
    }

    public static void main(String[] args) throws IOException {
        LineReader reader = new LineReader(new InputStreamReader(System.in));
        LineWriter writer = new LineWriter(new OutputStreamWriter(System.out));

        int[] amountsFirstLine = new int[255];
        while (true) {
            int symbol = reader.readSymbol();
            if (symbol == '\n' || symbol == -1) {
                break;
            }
            amountsFirstLine[symbol]++;
        }

        int[] amountsSecondLine = new int[255];
        while (true) {
            int symbol = reader.readSymbol();
            if (symbol == '\n' || symbol == -1) {
                break;
            }
            amountsSecondLine[symbol]++;
        }

        boolean differenceFound = false;
        for (int i = 0; i < 255; ++i) {
            if (amountsFirstLine[i] != amountsSecondLine[i]) {
                differenceFound = true;
                break;
            }
        }

        writer.writeLine(differenceFound ? "0" : "1");

        reader.close();
        writer.close();
    }
}
