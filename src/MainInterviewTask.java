import java.io.*;
import java.util.Objects;

public class MainInterviewTask {

    private static class Console {

        private final InputStreamReader reader;
        private final OutputStreamWriter writer;
        private Character nextSymbol;

        public Console(InputStreamReader reader, OutputStreamWriter writer) {
            this.reader = reader;
            this.writer = writer;
        }


        private void printResult(int res) throws IOException {
            writer.write(String.valueOf(res));
            writer.flush();
        }

        private boolean hasNextSymbol() throws IOException {
            nextSymbol = (char) reader.read();
            return nextSymbol != '\n' && nextSymbol != '\r';
        }

        private Character readNextSymbol() {
            return nextSymbol;
        }

        void close() throws IOException {
            reader.close();
            writer.close();
        }
    }

    public static void main(String[] args) throws IOException {
        Console console = new Console(
                new InputStreamReader(System.in),
                new OutputStreamWriter(System.out)
        );

        Character prevSymbol = null;
        int max = 0;
        int currentCount = 1;
        while (console.hasNextSymbol()) {
            Character symbol = console.readNextSymbol();
            if (Objects.equals(symbol, prevSymbol)) {
                currentCount++;
            } else {
                currentCount = 1;
            }
            if (currentCount > max) {
                max = currentCount;
            }
            prevSymbol = symbol;
        }

        console.printResult(max);

        console.close();
    }
}
