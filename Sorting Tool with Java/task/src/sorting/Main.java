package sorting;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        File file = new File("out.txt");
        file.createNewFile();

        PrintStream console = System.out;
        PrintStream filePrint = null;
        Classifier classifier;

        CheckValidation checkValidation = new CheckValidation();

        checkValidation.checkArgs(args);

        String type = "";
        String sortingType = "";
        boolean isSortingType = false;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-dataType")) {
                type = args[i + 1];
            } else if (args[i].equals("-sortingType")) {
                sortingType = args[i + 1];
                isSortingType = true;
            } else if (args[i].equals("-inputFile")) {
                String inputFileName = args[i + 1];
                File inputFile = new File(inputFileName);
                if (inputFile.exists()) {
                    try (FileInputStream fis = new FileInputStream(inputFile)) {
                        System.setIn(fis);
                    }
                }
            } else if (args[i].equals("-outputFile")) {
                String outputFileName = args[i + 1];
                File outputFile = new File(outputFileName);
                if (outputFile.exists()) {
                    filePrint = new PrintStream(new FileOutputStream(outputFile));
                    System.setOut(filePrint);
                }
            }
        }

        if (!isSortingType) {
            sortingType = "natural";
        }

        try {
            if (filePrint == null) {
                classifier = new Classifier(type, sortingType);
            } else {
                classifier = new Classifier(type, sortingType, console, filePrint);
            }
            classifier.printInfo();
        } finally {
            if (filePrint != null) {
                filePrint.close();
            }
        }
    }
}
