package task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Math.max;

public class MainB {
    public static void main(String[] args) throws IOException {
        int[] input = readInput();

        int currentLength = 0;
        int maximumLength = 0;
        for (int value : input) {
            if (value == 0) {
                currentLength = 0;
            } else {
                maximumLength = max(++currentLength, maximumLength);
            }
        }

        printResAndExit(maximumLength);
    }

    private static int[] readInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int arrayLength = Integer.parseInt(reader.readLine());
        if (arrayLength == 0) {
            printResAndExit(0);
        }
        int[] input = new int[arrayLength];
        for (int i = 0 ; i < arrayLength ; ++i ) {
            input[i] = Integer.parseInt(reader.readLine());
        }
        return input;
    }

    private static void printResAndExit(int res) {
        System.out.println(res);
        System.exit(0);
    }
}