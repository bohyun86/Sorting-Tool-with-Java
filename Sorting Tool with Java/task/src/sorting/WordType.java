package sorting;

import java.util.*;
import java.util.stream.Collectors;

public class WordType implements ClassType {

    private long size;
    List<String> words;
    String sortingType;
    Map<String, Integer> countNumbers = new HashMap<>();
    private Map<String, Integer> sortedCountNumbers = new LinkedHashMap<>();

    public WordType(String sortingType, List<String> words) {
        this.words = words;
        this.sortingType = sortingType;
        this.size = words.size();
    }

    private void sorting() {
        this.words = words.stream().sorted().toList();
    }

    private void count() {
        words.forEach(number -> {
            countNumbers.put(number, countNumbers.getOrDefault(number, 0) + 1);
        });
        sortedCountNumbers();
    }

    private void sortedCountNumbers() {
        sortedCountNumbers = countNumbers.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    @Override
    public void printInfo() {
        switch (sortingType) {
            case "natural" -> {
                sorting();
                printSorting();
            }
            case "byCount" -> {
                count();
                printCount();
            }
        }
    }

    public void printSorting() {
        System.out.printf("Total numbers: %d.\n", + size);
        System.out.println("Sorted data:");
        System.out.printf("Sorted data: %s\n", words.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }

    public void printCount() {
        System.out.printf("Total numbers: %d.\n", + size);
        sortedCountNumbers.forEach((key, value) -> {
            System.out.printf("%s: %d time(s), %d%%\n", key, value, value * 100 / size);
        });
    }
}
