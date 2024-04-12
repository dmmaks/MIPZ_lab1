package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Renju {

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/java/org/example/input3.txt"));

            int numTestCases = Integer.parseInt(reader.readLine());

            for (int t = 0; t < numTestCases; t++) {
                int[][] board = new int[19][19];

                for (int i = 0; i < 19; i++) {
                    String[] line = reader.readLine().split(" ");
                    for (int j = 0; j < 19; j++) {
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

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int checkWinner(int[][] board) {
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                if (board[i][j] != 0) {
                    int color = board[i][j];

                    if (j + 4 < 19
                            && board[i][j + 1] == color
                            && board[i][j + 2] == color
                            && board[i][j + 3] == color
                            && board[i][j + 4] == color) {
                        if (j + 5 != 19 && board[i][j + 5] == color) {
                            return 0;
                        }
                        return color;
                    }

                    if (i + 4 < 19
                            && board[i + 1][j] == color
                            && board[i + 2][j] == color
                            && board[i + 3][j] == color
                            && board[i + 4][j] == color) {
                        if (i + 5 != 19 && board[i + 5][j] == color) {
                            return 0;
                        }
                        return color;
                    }

                    if (i + 4 < 19
                            && j + 4 < 19
                            && board[i + 1][j + 1] == color
                            && board[i + 2][j + 2] == color
                            && board[i + 3][j + 3] == color
                            && board[i + 4][j + 4] == color) {
                        if (i + 5 != 19 && j + 5 != 19 && board[i + 5][j + 5] == color) {
                            return 0;
                        }
                        return color;
                    }

                    if (i - 4 >= 0
                            && j + 4 < 19
                            && board[i - 1][j + 1] == color
                            && board[i - 2][j + 2] == color
                            && board[i - 3][j + 3] == color
                            && board[i - 4][j + 4] == color) {
                        if (i - 5 >= 0 && j + 5 != 19 && board[i - 5][j + 5] == color) {
                            return 0;
                        }
                        return color;
                    }
                }
            }
        }
        return 0;
    }

    public static int[] findLeftmostStone(int[][] board, int color) {
        int[] coordinates = new int[2];

        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
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
