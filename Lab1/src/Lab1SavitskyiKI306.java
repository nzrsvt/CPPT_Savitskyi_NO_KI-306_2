import java.io.*;
import java.util.*;

/**
 * This class generates a square matrix with a diagonal pattern of characters
 * and writes it to a text file.
 */
public class Lab1SavitskyiKI306 {

    /**
     * The main method of the program.
     * 
     * @param args Command line arguments (not used in this program).
     * @throws FileNotFoundException If the specified file cannot be created.
     */
    public static void main(String[] args) throws FileNotFoundException {
        int nRows;
        char[][] arr;
        String filler;

        Scanner in = new Scanner(System.in);
        File dataFile = new File("MyFile.txt");
        PrintWriter fout = new PrintWriter(dataFile);

        System.out.print("Enter the nRows of the square matrix: ");
        nRows = in.nextInt();
        in.nextLine();

        arr = new char[nRows][];

        System.out.print("\nEnter the filler character: ");
        filler = in.nextLine();

        int lenghtPointer = 0;
        int mid = nRows / 2;

        for (int i = 0; i <= mid; i++) {
        	lenghtPointer++;
            arr[i] = new char[mid + lenghtPointer];
            Arrays.fill(arr[i], ' ');
            for (int j = mid - i; j <= mid + i; j++) {
                arr[i][j] = filler.toCharArray()[0];
            }        
        }

        for (int i = mid + 1; i < nRows; i++) {
            arr[i] = new char[mid + --lenghtPointer];
            Arrays.fill(arr[i], ' ');
            for (int j = i - mid; j < nRows - (i - mid); j++) {
                arr[i][j] = filler.toCharArray()[0];
            }
        }

        for (char[] charArr : arr) {
            for (char ch : charArr) {
                System.out.print(ch + " ");
                fout.print(ch + " ");
            }
            System.out.println();
            fout.println();
        }

        fout.flush();
        fout.close();
        in.close();
    }
}
