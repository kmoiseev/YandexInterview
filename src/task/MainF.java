package task;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.max;

public class MainF {
    private static class LineReader {
        private final InputStream inputStream;

        private LineReader(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        private int readNextInt() throws IOException {
            int res = 0;
            while (true) {
                int c = inputStream.read();
                if (c == ' ' || c == '\n' || c == -1) {
                    break;
                } else if (c == '\r') {
                    continue;
                } else {
                    res = res * 10 + fromAsciiCode(c);
                }
            }
            return res;
        }

        private static int fromAsciiCode(int asciiCode) {
            return asciiCode - 48;
        }

        private void close() throws IOException {
            inputStream.close();
        }
    }

    private static class LineWriter {
        private final OutputStream outputStream;

        private LineWriter(OutputStream outputStream) {
            this.outputStream = outputStream;
        }

        private void writeInt(int intVal) throws IOException {
            if (intVal < 10) {
                outputStream.write(toAsciiCode(intVal));
            } else {
                outputStream.write(toAsciiCode(intVal / 10));
                outputStream.write(toAsciiCode(intVal % 10));
            }
        }

        private void writeSpace() throws IOException {
            outputStream.write(' ');
        }

        private void flush() throws IOException {
            outputStream.flush();
        }

        private static int toAsciiCode(int intVal) {
            return intVal + 48;
        }

        private void close() throws IOException {
            outputStream.close();
        }
    }

    private static int maximumNumberPlusOne = 101;
    private static boolean debugEnabled = false;
    private static Map<String, Long> timeMeasures = new HashMap<>();

    public static void main(String[] args) throws IOException {
        double maxMemUsage = 0;
        timeMeasureStart("whole task");
        LineReader reader = new LineReader(new BufferedInputStream(new FileInputStream("input.txt")));
        LineWriter writer = new LineWriter(new BufferedOutputStream(new FileOutputStream("output.txt")));

        int[] result = new int[maximumNumberPlusOne];

        int arraysAmount = reader.readNextInt();

        timeMeasureStart("read");
        maxMemUsage = getMemUsageMax(maxMemUsage);
        for (int arrayIndex = 0; arrayIndex < arraysAmount; ++arrayIndex) {
            int arrayLength = reader.readNextInt();
            for (int index = 0; index < arrayLength; ++index) {
                int intVal = reader.readNextInt();
                result[intVal]++;
            }
        }
        timeMeasureEnd("read");

        maxMemUsage = getMemUsageMax(maxMemUsage);
        timeMeasureStart("write");
        for (int i = 0; i < maximumNumberPlusOne; ++i) {
            for (int j = 0; j < result[i]; ++j) {
                writer.writeInt(i);
                writer.writeSpace();
            }
            writer.flush();
        }
        maxMemUsage = getMemUsageMax(maxMemUsage);
        writer.close();
        timeMeasureEnd("write");
        reader.close();

        maxMemUsage = getMemUsageMax(maxMemUsage);
        timeMeasureEnd("whole task");
        printMemUsageMax(maxMemUsage);
    }

    private static void timeMeasureStart(String label) {
        if (!debugEnabled) {
            return;
        }
        timeMeasures.put(label, System.nanoTime());
    }

    private static void timeMeasureEnd(String label) {
        if (!debugEnabled) {
            return;
        }
        debug(
                "Time measure for " + label + " is " + doubleToReadable((System.nanoTime() - timeMeasures.get(label)) / 1000000000.d) + " s"
        );
    }

    private static void printMemUsageMax(double memUsageMax) {
        if (!debugEnabled) {
            return;
        }
        debug(
                "Max mem usage is " + doubleToReadable(memUsageMax / 1024.d / 1024.d) + " MB"
        );
    }

    private static double getMemUsageMax(double currentMax) {
        if (!debugEnabled) {
            return currentMax;
        }
        return max(currentMax, Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
    }

    private static double doubleToReadable(double initial) {
        return BigDecimal.valueOf((initial)).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private static void debug(String debug) {
        if (debugEnabled) {
            System.out.println(debug);
        }
    }
}
