package KI306.Savitskyi.Lab2;

import static java.lang.System.out;
import java.io.*;

/**
 * Клас, що містить метод `main` для демонстрації роботи класу `Printer`.
 */
public class PrinterApp {

    /**
     * Головний метод програми, який виконує демонстрацію функціоналу класу `Printer`.
     *
     * @param args Масив аргументів командного рядка (не використовується в цьому випадку).
     */
    public static void main(String[] args) throws FileNotFoundException {
        String model = "Canon MF3010";
        boolean multicolour = true;
        int inkLevel = 97;

        // Створення об'єкта принтера з вказаними параметрами.
        Printer printer = new Printer(model, multicolour, inkLevel);

        // Виведення статусу принтера.
        printer.status();

        // Вставлення 14 аркушів паперу та виведення кількості залишених аркушів та рівня чернил.
        printer.insertNPaperSheets(14);
        out.print("Paper sheets inserted: " + printer.getPaperTrayCapacity() + "\n");
        out.print("Current ink level: " + printer.getCartridgeInkLevel() + "\n");

        // Увімкнення принтера, виконання друку та виведення стану паперу та рівня чернил.
        printer.turnOn();
        printer.print();
        out.print("Paper sheets inserted: " + printer.getPaperTrayCapacity() + "\n");
        out.print("Current ink level: " + printer.getCartridgeInkLevel() + "\n");

        // Виконання сканування та друку, заповнення картриджу і виведення стану паперу та рівня чернил.
        printer.scanAndPrint();
        printer.fillEverything();
        out.print("Paper sheets inserted: " + printer.getPaperTrayCapacity() + "\n");
        out.print("Current ink level: " + printer.getCartridgeInkLevel() + "\n");

        // Завершення роботи з принтером.
        printer.dispose();
    }
}