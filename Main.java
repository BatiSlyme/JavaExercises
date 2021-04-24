package ReadFromFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader("SirmaReadFromText.txt")));
        BufferedReader buffer = new BufferedReader(new FileReader("SirmaReadFromText.txt"));
//      count how many rows we have
        int count = 0;
        String line1;
        try {
            while ((line1 = buffer.readLine()) != null) {
                count++;
                sc.hasNextLine();
            }
        } catch (IOException e) {

            e.printStackTrace();

        }
        int[] dates = new int[count];
        int rows = count;
        int columns = 4;
        String[][] myArray = new String[rows][columns];
        while (sc.hasNextLine()) {
            for (int i = 0; i < myArray.length; i++) {
                String[] line = sc.nextLine().trim().split(", ");
                for (int j = 0; j < line.length; j++) {
                    myArray[i][j] = (line[j]);
                }
            }
        }
        System.out.println(Arrays.deepToString(myArray));
        System.out.println(myArray[1][2]);
        Arrays.sort(myArray, (a, b) -> a[1].compareTo(b[1]));//sort by ProjectID
        System.out.println(Arrays.deepToString(myArray));


        calculateDate(myArray, dates, count);
        getMaxDate(dates);
    }

    public static int[] calculateDate(String[][] arr, int[] dates, int count) {
        String inputString1 = "2021-01-23";
        String inputString2 = "2021-04-27";
        Date date = new Date();
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        long diffDays = 0;
        for (int i = 0; i < count - 1; i++) {
            String dateFrom = arr[i][2];
            String dateTo = arr[i][3];
            if ((arr[i][3]).equals("NULL"))
                dateTo = currentDate;
            LocalDate dt1 = LocalDate.parse(dateFrom);
            LocalDate dt2 = LocalDate.parse(dateTo);
            diffDays = ChronoUnit.DAYS.between(dt1, dt2);
            dates[i] = (int) diffDays;
        }


        return dates;
    }

    public static int getMaxDate(int[] dates) {
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < dates.length; i++) {
            int value = dates[i];

            if (value > max) {
                max = value;
            }

        }
        return max;
    }

    public static void projectLegnth(String[][] arr, int count) {
//        int projects=0;
//        for (int i=1;i<count;i++){
//            if (arr[i-1][1]==arr[i][1]){
//                projects++;
//            }else
//                projects=1;
        int[] result = new int[count];
        int counter = 0, repeats = 0;
        for (int i = 0; i < count; i++) {
            boolean isDistinct = false;
            for (int j = 0; j < i; j++) {
                if (arr[i][1] == arr[j][1]) {
                    isDistinct = true;
                    break;
                }
            }
            if (!isDistinct) {
                result[counter++] = Integer.parseInt(arr[i][1]);
            }
        }
        for (int i = 0; i < counter; i++) {
            count = 0;
            for (int j = 0; j < arr.length; j++) {
                if (result[i] == arr[j][1]) {
                    count++;
                }

            }
            System.out.println(result[i] + " = " + count);

        }
    }
}


//    public static String[][] create2DIntMatrixFromFile(String filename) throws Exception {
//        String[][] matrix = null;
//
//        // If included in an Eclipse project.
////    InputStream stream = ClassLoader.getSystemResourceAsStream(filename);
////    BufferedReader buffer = new BufferedReader(new InputStreamReader(stream));
//
//        // If in the same directory - Probably in your case...
//        // Just comment out the 2 lines above this and uncomment the line
//        // that follows.
//        BufferedReader buffer = new BufferedReader(new FileReader(filename));
//
//        String line;
//        int row = 0;
//        int size = 0;
//
//        while ((line = buffer.readLine()) != null) {
//            String[] vals = line.trim().split(", ");
//
//            // Lazy instantiation.
//            if (matrix == null) {
//                size = vals.length;
//                matrix = new String[size][size];
//            }
//
//            for (int col = 0; col < size; col++) {
//                matrix[row][col] = vals[col];
//            }
//
//            row++;
//        }
//
//        return matrix;
//    }
//
//    public static void printMatrix(String[][] matrix) {
//        String str = "";
//        int size = matrix.length;
//
//        if (matrix != null) {
//            for (int row = 0; row < size; row++) {
//                str += " ";
//                for (int col = 0; col < size; col++) {
//                    str += (matrix[row][col]);
//                    if (col < size - 1) {
//                        str += " | ";
//                    }
//                }
//                if (row < size - 1) {
//                    str += "\n";
//                }
////                    for (int col = 0; col < size; col++) {
////                        for (int i = 0; i < 4; i++) {
//////                            str += "-";
////                        }
////                        if (col < size - 1) {
//////                            str += "+";
////                        }
////                    }
////                    str += "\n";
////                } else {
////                    str += "\n";
////                }
//            }
//        }
//
//        System.out.println(str);
//    }
//
//    public static void main(String[] args) {
//        String[][] matrix = null;
//
//        try {
//            matrix = create2DIntMatrixFromFile("SirmaReadFromText.txt");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        printMatrix(matrix);
//    }


