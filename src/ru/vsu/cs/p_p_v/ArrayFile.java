package ru.vsu.cs.p_p_v;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayFile {
    public static Integer[][] twoDimensionalArrayRead(String path) throws FileNotFoundException {
        List<Integer[]> nums = new ArrayList<>();

        Scanner fileScan = new Scanner(new File(path));
        while (fileScan.hasNextLine()) {
            List<Integer> currentNums = new ArrayList<>();

            Scanner lineScan = new Scanner(fileScan.nextLine());
            while (lineScan.hasNextInt())
                currentNums.add(lineScan.nextInt());

            nums.add(currentNums.toArray(new Integer[0]));
        }

        return nums.toArray(new Integer[0][0]);
    }

    public static void twoDimensionalArrayWrite(String path, Integer[][] input) throws IOException {
        FileWriter writer = new FileWriter(path);
        for (int row = 0; row < input.length; row++) {
            for (int column = 0; column < input[row].length; column++)
                writer.append(String.valueOf(input[row][column])).append(column != input[row].length - 1 ? " " : "");

            if (row != input.length - 1)
                writer.append("\r\n");
        }
        writer.close();
    }
}
