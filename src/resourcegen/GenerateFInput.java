package resourcegen;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class GenerateFInput {
    private static class LineWriter {
        private final OutputStream outputStream;

        private LineWriter(OutputStream outputStream) {
            this.outputStream = outputStream;
        }

        private void writeInt(int intVal) throws IOException {
            outputStream.write(String.valueOf(intVal).getBytes());
        }

        private void writeNextLine() throws IOException {
            outputStream.write('\n');
        }

        private void writeSpace() throws IOException {
            outputStream.write(' ');
        }

        private void close() throws IOException {
            outputStream.close();
        }
    }

    public static void main(String[] args) throws IOException {
        LineWriter writer = new LineWriter(new BufferedOutputStream(new FileOutputStream("input.txt")));

        writer.writeInt(1024);
        writer.writeNextLine();

        for (int i = 0; i < 1024; ++i) {
            writer.writeInt(10240);
            writer.writeSpace();
            for (int j = 0; j < 10239; ++j) {
                writer.writeInt((int) ((100.f / 10240) * ((float) j)));
                writer.writeSpace();
            }
            writer.writeInt(100);
            writer.writeNextLine();
        }
        writer.close();
    }
}
