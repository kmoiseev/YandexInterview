package task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public class MainD {

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
        LineReader reader = new LineReader(new InputStreamReader(System.in), 5);
        LineWriter writer = new LineWriter(new OutputStreamWriter(System.out));

        int inputDepth = Integer.parseInt(String.valueOf(reader.readLine()).trim());
        if (inputDepth == 0) {
            return;
        }

        genBrackets(inputDepth, inputDepth, 0, 0, 0, writer);

        writer.close();
        reader.close();
    }

    private static void genBrackets(int left, int right, int leftAdded, int rightAdded, int arr, LineWriter writer) throws IOException {
        if (left == 0 && right == 0) {
            writer.writeLine(toCharArr(arr, leftAdded + rightAdded));
            return;
        }

        // 1 - left, 0 - right
        int alreadyAdded = leftAdded + rightAdded;
        if (left > 0) {
            int newArr = markNthBitTrue(arr, alreadyAdded);
            genBrackets(left - 1, right, leftAdded + 1, rightAdded, newArr, writer);
        }
        if (right > 0 && leftAdded > rightAdded) {
            genBrackets(left, right - 1, leftAdded, rightAdded + 1, arr, writer);
        }
    }

    private static char[] toCharArr(int arr, int size) {
        char[] res = new char[size];

        for (int i = 0; i < size; ++i) {
            res[i] = isNthBitTrue(arr, i) ? '(' : ')';
        }
        return res;
    }

    private static int markNthBitTrue(int original, int bitIdx) {
        return original | (1 << bitIdx);
    }

    private static boolean isNthBitTrue(int original, int bitIdx) {
        return ((original >> bitIdx) & 1) == 1;
    }
}