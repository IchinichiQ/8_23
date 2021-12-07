package ru.vsu.cs.p_p_v;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FriendlyArray {
    public static Integer[][] getFriendlyArray(Integer[][] input) {
        Integer[][] answer = new Integer[input.length][];
        boolean[][] visited = new boolean[input.length][];
        for (int i = 0; i < input.length; i++) {
            visited[i] = new boolean[input[i].length];
            answer[i] = new Integer[input[i].length];
        }

        for (int row = 0; row < input.length; row++) {
            for (int column = 0; column < input[row].length; column++) {
                if (!visited[row][column]) {
                    List<Integer[]> friendlyCells = checkForFriendlinessRecursive(input, visited, row, column);

                    for (int i = 0; i < friendlyCells.size(); i++)
                        answer[friendlyCells.get(i)[0]][friendlyCells.get(i)[1]] = friendlyCells.size() - 1;
                }
            }
        }

        return answer;
    }

    private static List<Integer[]> checkForFriendlinessRecursive(Integer[][] input, boolean[][] visited, int row, int column) {
        List<Integer[]> friendlyCells = new ArrayList<>();
        friendlyCells.add(new Integer[] {row, column});
        visited[row][column] = true;

        int[][] moveOffsets = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
        for (int[] moveOffset : moveOffsets) {
            Integer[] cellMoveTo = new Integer[]{row + moveOffset[0], column + moveOffset[1]};
            if (isMoveCorrect(input, cellMoveTo[0], cellMoveTo[1]) && Objects.equals(input[row][column], input[cellMoveTo[0]][cellMoveTo[1]]) && !visited[cellMoveTo[0]][cellMoveTo[1]])
                friendlyCells.addAll(checkForFriendlinessRecursive(input, visited, cellMoveTo[0], cellMoveTo[1]));
        }

        return friendlyCells;
    }

    private static boolean isMoveCorrect(Integer[][] input, int row, int column) {
        return 0 <= row && row < input.length && 0 <= column && column < input[row].length;
    }
}
