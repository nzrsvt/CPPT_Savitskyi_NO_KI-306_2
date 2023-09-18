package KI306.Savitskyi.lab4;
import java.util.Scanner;
import java.io.*;
import static java.lang.System.out;

/**
 * This class represents an application for calculating and storing equation results in a file.
 * It takes user input for a filename and calculates the result of an equation based on user input X.
 */
public class EquationsApp {
    /**
     * The main method of the application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        try {
            // Prompt the user to enter a file name.
            out.print("Enter file name: ");
            Scanner in = new Scanner(System.in);
            String fName = in.nextLine();
            PrintWriter fout = new PrintWriter(new File(fName));
            try {
                try {
                    Equations eq = new Equations();
                    // Prompt the user to enter the value of X.
                    out.print("Enter X: ");
                    fout.print(eq.calculate(in.nextInt()));
                } finally {
                    // This block will be executed under all circumstances.
                    fout.flush();
                    fout.close();
                }
            } catch (CalcException ex) {
                // Catch and handle calculation errors.
                out.print(ex.getMessage());
            }
        } catch (FileNotFoundException ex) {
            // Catch and handle file-related errors, even if they occur in the finally block.
            out.print("Exception reason: Perhaps wrong file path");
        }
    }
}

/**
 * Custom exception class for handling calculation errors.
 */
class CalcException extends ArithmeticException {
    /**
     * Default constructor for CalcException.
     */
    public CalcException() {
    }

    /**
     * Constructor for CalcException with a custom error message.
     *
     * @param cause The error message describing the cause of the exception.
     */
    public CalcException(String cause) {
        super(cause);
    }
}

/**
 * This class represents mathematical equations and provides a method for calculating a result.
 */
class Equations {
    /**
     * Calculate the result of the equation based on the given input X.
     *
     * @param x The input value for the equation.
     * @return The calculated result of the equation.
     * @throws CalcException If a calculation error occurs, this exception is thrown.
     */
    public double calculate(int x) throws CalcException {
        double y, rad;
        rad = x * Math.PI / 180.0;
        try {
            // Since we are using 1/tan instead of cotan, additional exceptions need to be handled.
            if (rad == Math.PI || rad == 0.0 || rad == -Math.PI)
                throw new CalcException();

            y = (1.0 / Math.tan(rad)) / (Math.sin(2 * rad) + 4 * Math.cos(rad));

            // If the result is not a valid number, generate an exception.
            if (y == Double.NaN || y == Double.NEGATIVE_INFINITY || y == Double.POSITIVE_INFINITY || x == 90 || x == -90)
                throw new ArithmeticException();
        } catch (ArithmeticException ex) {
            // Create a higher-level exception with an explanation of the error cause.
            if (rad == Math.PI || rad == 0.0 || rad == -Math.PI)
                throw new CalcException("Exception reason: Illegal value of X for cotangent calculation");
            else if (rad == Math.PI / 2.0 || rad == -Math.PI / 2.0)
                throw new CalcException("Exception reason: Illegal value of X for tangent calculation, which is necessary for finding the cotangent in Java");
            else
                throw new CalcException("Unknown reason of the exception during exception calculation");
        }
        return y;
    }
}
