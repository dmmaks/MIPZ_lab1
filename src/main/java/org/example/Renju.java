package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Renju {

    public static final String FILE_PATH = "src/main/java/org/example/input3.txt";

    public static final int SIZE = 19;

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));

            int numTestCases = Integer.parseInt(reader.readLine());

            parseInput(reader, numTestCases);

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void parseInput(BufferedReader reader, int numTestCases) throws IOException {
        for (int t = 0; t < numTestCases; t++) {
            int[][] board = new int[SIZE][SIZE];

            for (int i = 0; i < SIZE; i++) {
                String[] line = reader.readLine().split(" ");
                for (int j = 0; j < SIZE; j++) {
                    board[i][j] = Integer.parseInt(line[j]);
                }
            }

            int winner = checkWinner(board);

            System.out.println(winner);
            if (winner != 0) {
                int[] coordinates = findLeftmostStone(board, winner);
                System.out.println((coordinates[0] + 1) + " " + (coordinates[1] + 1));
            }
        }
    }

    private static int checkWinner(int[][] board) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] != 0) {
                    int color = board[i][j];

                    if (checkFollowingStones(i, j, 0, 1, color, board)) {
                        return returnWinningColor(i, j, 0, 1, color, board);
                    }

                    if (checkFollowingStones(i, j, 1, 0, color, board)) {
                        return returnWinningColor(i, j, 1, 0, color, board);
                    }

                    if (checkFollowingStones(i, j, 1, 1, color, board)) {
                        return returnWinningColor(i, j, 1, 1, color, board);
                    }

                    if (checkFollowingStones(i, j, -1, 1, color, board)) {
                        return returnWinningColor(i, j, -1, 1, color, board);
                    }
                }
            }
        }
        return 0;
    }

    private static boolean checkFollowingStones (int i, int j, int iDirection, int jDirection, int color, int[][] board) {
        return i + 4 * iDirection >= 0
                && i + 4 * iDirection < SIZE
                && j + 4 * jDirection < SIZE
                && board[i + iDirection][j + jDirection] == color
                && board[i + 2 * iDirection][j + 2 * jDirection] == color
                && board[i + 3 * iDirection][j + 3 * jDirection] == color
                && board[i + 4 * iDirection][j + 4 * jDirection] == color;
    }

    private static int returnWinningColor (int i, int j, int iDirection, int jDirection, int color, int[][] board) {
        if (i + 5 * iDirection >= 0 && i + 5 * iDirection != SIZE && j + 5 * jDirection != SIZE && board[i + 5 * iDirection][j + 5 * jDirection] == color) {
            return 0;
        }
        return color;
    }

    private static int[] findLeftmostStone(int[][] board, int color) {
        int[] coordinates = new int[2];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[j][i] == color) {
                    coordinates[0] = j;
                    coordinates[1] = i;
                    return coordinates;
                }
            }
        }

        return coordinates;
    }
}
