package sorting;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Classifier {


    ClassType classType;
    private Type type;
    private String sortingType;
    Scanner scanner = new Scanner(System.in);
    PrintStream console = null;
    PrintStream filePrint = null;

    public Classifier(String type, String sortingType, PrintStream console, PrintStream filePrint) {
        this.type = Type.valueOf(type.toUpperCase());
        this.sortingType = sortingType;
        this.console = console;
        this.filePrint = filePrint;
        classify();
    }

    public Classifier(String type, String sortingType) {
        this.type = Type.valueOf(type.toUpperCase());
        this.sortingType = sortingType;
        classify();
    }

    public void classify() {
        this.classType = switch (type) {
            case LONG -> new LongType(sortingType, getMaterial());
            case LINE -> new LineType(sortingType, getMaterial());
            case WORD -> new WordType(sortingType, getMaterial());
        };
    }

    public List<String> getMaterial() {
        return switch (type) {
            case WORD -> getWords();
            case LINE -> getLines();
            case LONG -> getLongs();
        };
    }


    public List<String> getLines() {
        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
        return lines;
    }

    public List<String> getWords() {
        List<String> words = new ArrayList<>();
        while (scanner.hasNext()) {
            words.add(scanner.next());
        }
        return words;
    }

    public List<String> getLongs() {
        List<String> longs = new ArrayList<>();
        while (scanner.hasNext()) {
            String num = scanner.next();
            if (num.matches("^-?[1-9][0-9]*$")) {
                longs.add(num);
            } else {
                if (console != null) System.setOut(console);
                System.out.println("\"" + num + "\" is not a long. It will be skipped.");
                if (filePrint != null) System.setOut(filePrint);
            }
        }
        return longs;
    }


    public void printInfo() {
        classType.printInfo();
    }
}

enum Type {
    LONG, LINE, WORD
}
