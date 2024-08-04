package sorting;

public class CheckValidation {

    public void checkArgs(String[] args) {
        // 인수가 없는 경우 처리
        if (args.length == 0) {
            System.out.println("No arguments");
            System.exit(0);
        }

        boolean isDataType = false;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-dataType")) {
                isDataType = true;
                if (i == args.length - 1) {
                    System.out.println("No data type defined!");
                    System.exit(0);
                } else
                if (!args[i + 1].equals("long") && !args[i + 1].equals("word") && !args[i + 1].equals("line")) {
                    System.out.println("No data type defined!");
                    System.exit(0);
                }
            } else if (args[i].equals("-sortingType")) {
                if (i == args.length - 1) {
                    System.out.println("No sorting type defined!");
                    System.exit(0);
                } else
                if (!args[i + 1].equals("natural") && !args[i + 1].equals("byCount")) {
                    System.out.println("No sorting type defined!");
                    System.exit(0);
                }
            }
        }

        if (!isDataType) {
            System.out.println("No data type defined!");
            System.exit(0);
        }


    }
}
