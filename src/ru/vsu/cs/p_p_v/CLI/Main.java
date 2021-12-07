package ru.vsu.cs.p_p_v.CLI;

import ru.vsu.cs.p_p_v.*;

import java.io.File;

public class Main {

//    Будем называть «дружественными» элементами для двумерного массива такие
//    элементы, которые соединяются с данным элементом одной из сторон и имеют одно и то
//    же значение, а также все дружественные элементы для дружественных элементов
//    данного элемента. Составить для переданного двумерного массива новый массив, в
//    котором каждый элемент будет содержать информацию о количестве дружественных
//    элементов для соответствующего элемента в переданном массиве. Пример:
//    { { 1, 2, 3, 9, 9 },   { { 0, 5, 0, 2, 2 },
//      { 2, 2, 2, 9, 2 }, →   { 5, 5, 5, 2, 0 },
//      { 1, 1, 2, 2, 4 } }    { 1, 1, 5, 5, 0 } }

    static class InputArgs {
        public String inputFilePath = null;
        public String outputFilePath = null;
    }

    public static void main(String[] args) {
        try {
            InputArgs processedArgs = parseCmdArgs(args);

            Integer[][] input = ArrayFile.twoDimensionalArrayRead(processedArgs.inputFilePath);
            Integer[][] answer = FriendlyArray.getFriendlyArray(input);

            ArrayFile.twoDimensionalArrayWrite(processedArgs.outputFilePath, answer);

            System.out.printf("The result is saved in the file %s", processedArgs.outputFilePath);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static InputArgs parseCmdArgs(String[] args) throws Exception {
        if (args.length != 4)
            throw new Exception("Invalid number of arguments");

        InputArgs myInputArgs = new InputArgs();

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-i", "--input-file" -> myInputArgs.inputFilePath = args[++i];
                case "-o", "--output-file" -> myInputArgs.outputFilePath = args[++i];
            }
        }

        if (myInputArgs.outputFilePath == null)
            throw new Exception("Invalid output file");

        if (myInputArgs.inputFilePath == null)
            throw new Exception("Invalid input file");

        File inputFile = new File(myInputArgs.inputFilePath);
        if (!inputFile.isFile() || !inputFile.exists())
            throw new Exception("Invalid input file");

        return myInputArgs;
    }

}
