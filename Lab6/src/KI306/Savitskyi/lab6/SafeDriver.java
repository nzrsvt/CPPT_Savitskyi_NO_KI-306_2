package KI306.Savitskyi.lab6;

import java.util.*;
import java.io.*;

/**
 * The SafeDriver class is the main class for running the Safe program.
 * It demonstrates the usage of the Safe class to store and manipulate
 * data items of type Data, which can be either Jewel or Cash objects.
 */
public class SafeDriver {
    public static void main(String[] args) {
        // Create a Safe instance to store Data objects
        Safe<? super Data> safe = new Safe<Data>();

        // Add various Data objects to the safe
        safe.AddData(new Jewel("Golden ring", 4, 1500));
        safe.AddData(new Cash("US Dollar", 7200));
        safe.AddData(new Cash("Euro", 400));
        safe.AddData(new Jewel("Silver chain", 23, 150));

        // Find and print the Data object with the greatest value
        Data res = safe.findMax();
        res.print();

        // Print all items in the safe in descending order of value
        System.out.print("The greatest value in safe is: \n");
        safe.printAllDescendingByValue();

        // Print only the Jewel items in the safe
        safe.printJewelsOnly();
    }
}

/**
 * The Safe class represents a safe for storing data items of type T, where T
 * extends the Data interface. It provides methods for adding, deleting, and
 * performing operations on the stored data.
 */
class Safe<T extends Data> {
    private ArrayList<T> arr;

    /**
     * Constructor to initialize the Safe with an empty ArrayList.
     */
    public Safe() {
        arr = new ArrayList<T>();
    }

    /**
     * Find and return the Data object with the maximum value in the safe.
     *
     * @return The Data object with the maximum value, or null if the safe is empty.
     */
    public T findMax() {
        if (!arr.isEmpty()) {
            T max = arr.get(0);
            for (int i = 1; i < arr.size(); i++) {
                if (arr.get(i).compareTo(max) > 0)
                    max = arr.get(i);
            }
            return max;
        }
        return null;
    }

    /**
     * Add a Data object to the safe.
     *
     * @param data The Data object to add to the safe.
     */
    public void AddData(T data) {
        arr.add(data);
        System.out.print("Element added: ");
        data.print();
    }

    /**
     * Delete a Data object from the safe at the specified index.
     *
     * @param i The index of the Data object to delete.
     */
    public void DeleteData(int i) {
        arr.remove(i);
    }

    /**
     * Print all items in the safe in descending order of their values.
     */
    public void printAllDescendingByValue() {
        Collections.sort(arr, Collections.reverseOrder());
        System.out.println("Contents of the safe in descending order of value:");
        for (T data : arr) {
            data.print();
        }
    }

    /**
     * Print only the Jewel items in the safe.
     */
    public void printJewelsOnly() {
        System.out.println("Jewels in the safe:");
        for (T data : arr) {
            if (data instanceof Jewel) {
                data.print();
            }
        }
    }
}

/**
 * The Data interface represents a common interface for objects that have a
 * value and can be compared based on their values.
 */
interface Data extends Comparable<Data> {
    /**
     * Get the value of the data object.
     *
     * @return The value of the data object.
     */
    public int getValue();

    /**
     * Print information about the data object.
     */
    public void print();
}

/**
 * The Jewel class represents a type of Data object that represents a jewel.
 */
class Jewel implements Data {
    private String description;
    private int weight;
    private int value;

    public Jewel(String jDescription, int jWeight, int jValue) {
        description = jDescription;
        weight = jWeight;
        value = jValue;
    }

    public String getJewelDescription() {
        return description;
    }

    public void setJewelDescription(String jDescription) {
        description = jDescription;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int n) {
        weight = n;
    }

    public int getValue() {
        return value;
    }

    public int compareTo(Data p) {
        Integer s = value;
        return s.compareTo(p.getValue());
    }

    public void print() {
        System.out.print("Jewel description: " + description + ", Weight: " + weight + ", Jewel value: " + value + ";\n");
    }
}

/**
 * The Cash class represents a type of Data object that represents currency.
 */
class Cash implements Data {
    private String currencyName;
    private int value;

    public Cash(String cName, int cValue) {
        currencyName = cName;
        value = cValue;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String name) {
        currencyName = name;
    }

    public void setValue(int n) {
        value = n;
    }

    public int getValue() {
        return value;
    }

    public int compareTo(Data p) {
        Integer s = value;
        return s.compareTo(p.getValue());
    }

    public void print() {
        System.out.print("Cash Currency Name: " + currencyName + ", Cash value: " + value + ";\n");
    }
}