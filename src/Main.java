import java.util.*;

public class Main {

    private static final String romanNumbers = "I=1,II=2,III=3,IV=4,V=5,IX=9,X=10,XL=40,L=50,XC=90,C=100,CD=400,D=500,CM=900,M=1000";

    public static void main(String[] args) {
        System.out.println(convertRomanNumbersInNumbers("IX"));
        System.out.println(convertNumbersInRomanNumbers(9));
    }

    /**
     * Method to convert a roman number in number
     *
     * @param romanNumber - string with the roman number
     * @return long - as we don't know the large of the number
     */
    public static long convertRomanNumbersInNumbers(String romanNumber) {
        Map<String, Integer> map = new HashMap<>();
        String[] pairs = romanNumbers.split(",");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            map.put(keyValue[0], Integer.parseInt(keyValue[1]));
        }
        int sum = 0;
        char[] arrayNum = romanNumber.toCharArray();
        for (int i = 0; i < romanNumber.length(); i++) {
            sum += switch (arrayNum[i]) {
                case 'I' -> checkFirstRomanNumber(i, romanNumber.length(), arrayNum) ? 0 : map.get("I");
                case 'V' -> checkNextRomanNumber(i, arrayNum) ? map.get("V") - 1 : map.get("V");
                case 'X' -> checkNextRomanNumber(i, arrayNum) ? map.get("X") - 1 : map.get("X");
                case 'L' -> checkNextRomanNumberL(i, arrayNum) ? map.get("L") - 10 : map.get("L");
                case 'C' -> checkNextRomanNumberC(i, arrayNum) ? map.get("C") - 10 : map.get("C");
                case 'D' -> checkNextRomanNumberD(i, arrayNum) ? map.get("D") - 100 : map.get("C");
                case 'M' -> checkNextRomanNumberM(i, arrayNum) ? map.get("M") - 100 : map.get("M");

                default -> throw new IllegalStateException("Unexpected value: " + romanNumber);
            };

        }
        return sum;
    }

    /**
     * Method to convert a number to roman number
     *
     * @param romanNumber - long
     * @return String - the value of the number
     */
    public static String convertNumbersInRomanNumbers(long romanNumber) {
        // Split the array between numbers and strings
        String[] pairs = romanNumbers.split(",");

        // Initialize arrays
        String[] roman_numbers = new String[15];
        Integer[] number_values = new Integer[15];

        //Fill the array
        for (int index = 0; index < pairs.length; index++) {
            String[] keyValue = pairs[index].split("=");
            roman_numbers[index] = keyValue[0];
            number_values[index] = Integer.parseInt(keyValue[1]);
        }

        //Reverse the arrays as we want to start checking
        // 1000 until 1
        invertUsingCollectionsReverse(roman_numbers);
        invertUsingCollectionsReverse(number_values);

        //We use StringBuilder to concatenate the values using appending
        StringBuilder roman = getStringBuilder(romanNumber, number_values, roman_numbers);

        return roman.toString();
    }

    /**
     * Method to retrieve directly StringBuilder
     * @param romanNumber - long value entered by user
     * @param number_values - array of numbers
     * @param roman_numbers - array of roman numbers
     * @return StringBuilder - result before convert to string
     */
    private static StringBuilder getStringBuilder(long romanNumber, Integer[] number_values, String[] roman_numbers) {
        StringBuilder roman = new StringBuilder();

        //We use these variables to subtract the values
        long remainingValue = romanNumber;

        //For example, 3000
        // First iteration 3000>=1000(First value of the array)
        // Then first roman number M, and we subtract 3000-1000
        //remaining variable is 2000, and we iterate the while statement
        for (int i = 0; i < number_values.length; i++) {
            while (remainingValue >= number_values[i]) {
                roman.append(roman_numbers[i]);
                remainingValue = remainingValue - number_values[i];
            }
        }
        return roman;
    }

    /**
     * Method to reverse using collection
     * We could also use String builder
     * or use for statement to reverse it
     *
     * @param array - the array to reverse
     */
    public static void invertUsingCollectionsReverse(Object[] array) {
        List<Object> list = Arrays.asList(array);
        Collections.reverse(list);
    }


    /**
     * Method to check the next roman number to subtract or not
     *
     * @param i             - index
     * @param arrayNumRoman - cad split by char
     * @return boolean - the value of the sentence
     */
    private static boolean checkNextRomanNumber(int i, char[] arrayNumRoman) {
        return (i > 0 && arrayNumRoman[i - 1] == 'I');
    }

    /**
     * Method to check the next roman number to subtract or not
     *
     * @param i             - index
     * @param arrayNumRoman - cad split by char
     * @return boolean - the value of the sentence
     */
    private static boolean checkNextRomanNumberL(int i, char[] arrayNumRoman) {
        return (i > 0 && arrayNumRoman[i - 1] == 'X');
    }

    /**
     * Method to check the next roman number to subtract or not
     *
     * @param i             - index
     * @param arrayNumRoman - cad split by char
     * @return boolean - the value of the sentence
     */
    private static boolean checkNextRomanNumberC(int i, char[] arrayNumRoman) {
        return (i > 0 && arrayNumRoman[i - 1] == 'C');
    }

    /**
     * Method to check the next roman number to subtract or not
     *
     * @param i             - index
     * @param arrayNumRoman - cad split by char
     * @return boolean - the value of the sentence
     */
    private static boolean checkNextRomanNumberD(int i, char[] arrayNumRoman) {
        return (i > 0 && arrayNumRoman[i - 1] == 'C');
    }

    /**
     * Method to check the next roman number to subtract or not
     *
     * @param i             - index
     * @param arrayNumRoman - cad split by char
     * @return boolean - the value of the sentence
     */
    private static boolean checkNextRomanNumberM(int i, char[] arrayNumRoman) {
        return (i > 0 && arrayNumRoman[i - 1] == 'M');
    }

    /**
     * Method to check the first roman number to subtract or not
     *
     * @param i             - index
     * @param length        - length of the String
     * @param arrayNumRoman - cad split by char
     * @return boolean - the value of the sentence
     */
    private static boolean checkFirstRomanNumber(int i, int length, char[] arrayNumRoman) {
        return (i == 0 && length != 1 && arrayNumRoman[i + 1] != 'I');
    }
}