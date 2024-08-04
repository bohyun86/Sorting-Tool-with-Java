package sorting;

import java.util.*;
import java.util.stream.Collectors;

public class LineType implements ClassType {

    private List<String> lines;
    private String sortingType;
    private Map<String, Integer> countNumbers = new LinkedHashMap<>();
    private Map<String, Integer> sortedCountNumbers = new HashMap<>();
    private long size;

    public LineType(String sortingType, List<String> lines) {
        this.lines = lines;
        this.sortingType = sortingType;
        this.size = lines.size();
    }

    private void sorting() {
        this.lines = lines.stream().sorted().toList();
    }

    private void count() {
        lines.forEach(number -> {
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
        lines.forEach(System.out::println);
    }

    public void printCount() {
        System.out.printf("Total numbers: %d.\n", + size);
        sortedCountNumbers.forEach((key, value) -> {
            System.out.printf("%s: %d time(s), %d%%\n", key, value, value * 100 / size);
        });
    }
}
