package task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public class MainC {

    private static final short MAX_LINE_SIZE = 15;

    private static class LineReader {
        private final BufferedReader reader;
        private final int maxReadLineSize;

        private LineReader(Reader reader, int maxReadLineSize) {
            this.reader = new BufferedReader(reader);
            this.maxReadLineSize = maxReadLineSize;
        }

        private char[] readLine() throws IOException {
            char[] res = new char[maxReadLineSize];
            int count = 0;
            while (true) {
                int b = reader.read();
                if (b == '\n' || b == -1) {
                    break;
                }
                if (b == '\r') {
                    continue;
                }
                res[count] = (char) b;
                count++;
            }
            return res;
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

        private void writeLine(char[] line) throws IOException {
            writer.write(line);
            writer.newLine();
        }

        private void close() throws IOException {
            writer.close();
        }
    }

    public static void main(String[] args) throws IOException {
        LineReader reader = new LineReader(new InputStreamReader(System.in), MAX_LINE_SIZE);
        LineWriter writer = new LineWriter(new OutputStreamWriter(System.out));

        int amount = Integer.parseInt(String.valueOf(reader.readLine()).trim());
        if (amount == 0) {
            return;
        }

        char[] lastLine = {'n'};
        for (int i = 0; i < amount; ++i) {
            char[] line = reader.readLine();
            if (!equals(line, lastLine, MAX_LINE_SIZE)) {
                writer.writeLine(lastLine = line);
            }
        }

        writer.close();
        reader.close();
    }

    private static boolean equals(char[] left, char[] right, short size) {
        for (int i = 0; i < size; ++i) {
            if (left[i] != right[i]) {
                return false;
            }
        }
        return true;
    }
}
