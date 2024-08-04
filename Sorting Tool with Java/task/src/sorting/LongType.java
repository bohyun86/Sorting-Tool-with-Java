package sorting;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LongType implements ClassType {

    List<Long> numbers;
    Map<Long, Integer> countNumbers = new LinkedHashMap<>();
    private Map<Long, Integer> sortedCountNumbers;
    String sortingType;
    int size;

    public LongType(String sortingType, List<String> numbers) {
        this.numbers = numbers.stream().mapToLong(Long::parseLong).boxed().toList();
        this.sortingType = sortingType;
        this.size = numbers.size();
    }

    private void sorting() {
        this.numbers = numbers.stream().sorted(Long::compare).toList();
    }

    private void count() {
        numbers.forEach(number -> {
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
        System.out.printf("Total numbers: %d.\n", size);
        System.out.printf("Sorted data: %s\n", numbers.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }

    public void printCount() {
        System.out.printf("Total numbers: %d.\n", size);
        sortedCountNumbers.forEach((key, value) -> {
            System.out.printf("%d: %d time(s), %d%%\n", key, value, value * 100 / size);
        });
    }
}