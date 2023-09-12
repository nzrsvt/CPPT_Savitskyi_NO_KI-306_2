/**
 * Клас, що представляє принтер.
 */
package KI306.Savitskyi.Lab2;

import java.io.*;

/**
 * Клас, що представляє принтер.
 */
public class Printer {
    /**
     * Модель принтера.
     */
    private String model;

    /**
     * Стан принтера (увімкнено або вимкнено).
     */
    private boolean isOn;

    /**
     * Картридж для принтера.
     */
    private Cartridge cartridge;

    /**
     * Лоток для паперу.
     */
    private PaperTray paperTray;

    /**
     * Сканер для принтера.
     */
    private Scanner scanner;

    /**
     * Потік для запису журналу подій.
     */
    private PrintWriter fout;

    /**
     * Конструктор за замовчуванням.
     *
     * @throws FileNotFoundException виникає, якщо не вдається створити файл для журналу.
     */
    public Printer() throws FileNotFoundException {
        model = "unknown";
        isOn = false;
        cartridge = new Cartridge();
        paperTray = new PaperTray();
        scanner = new Scanner();

        fout = new PrintWriter(new File("Log.txt"));
    }

    /**
     * Конструктор з параметрами.
     *
     * @param mod        Модель принтера.
     * @param multicolour Чи підтримується друк кольоровими чернилами.
     * @param ink        Рівень чернил у картриджі.
     * @throws FileNotFoundException виникає, якщо не вдається створити файл для журналу.
     */
    public Printer(String mod, boolean multicolour, int ink) throws FileNotFoundException {
        model = mod;
        isOn = false;
        cartridge = new Cartridge(multicolour, ink);
        paperTray = new PaperTray();
        scanner = new Scanner();

        fout = new PrintWriter(new File("Log.txt"));
    }

    /**
     * Увімкнути принтер.
     */
    public void turnOn() {
        isOn = true;
        fout.print("Printer was turned on successfully.\n");
    }

    /**
     * Вимкнути принтер.
     */
    public void turnOff() {
        isOn = false;
        fout.print("Printer was turned off successfully.\n");
    }

    /**
     * Вставити певну кількість аркушів паперу в лоток.
     *
     * @param n Кількість аркушів, яку потрібно вставити.
     */
    public void insertNPaperSheets(int n) {
        if (paperTray.getPaperCount() + n <= paperTray.getMaxPaperCount()) {
            for (int i = 0; i < n; i++) {
                paperTray.insertPaper();
            }
            fout.print(n + " sheets of paper were inserted successfully.\n");
        } else {
            fout.print("Unable to insert " + n + " sheets of paper.\n");
        }
    }

    /**
     * Роздрукувати документ.
     */
    public void print() {
        if (isOn == true) {
            if (cartridge.getInkLevel() != 0 && paperTray.getPaperCount() != 0) {
                paperTray.extractPaper();
                cartridge.decreaseInkLevel();
                fout.print("Printing was successful!\n");
            } else {
                fout.print("Print failed!\n");
            }
        } else {
            fout.print("Print failed!\n");
        }
        fout.print("Sheets of paper remaining: " + paperTray.getPaperCount() + "\nInk level: " + cartridge.getInkLevel() + "\n");
    }
	
    /**
     * Наповнює картридж новими чернилами та вставляє максимальну кількість паперу в лоток.
     */
    public void fillEverything() {
        cartridge.putNewCartridge(true);
        paperTray.insertAllPaper();
        fout.print("New cartridge was put, paper tray was filled.\n");
    }

    /**
     * Видаляє картридж та вилучає всі аркуші паперу з лотка.
     */
    public void removeEverything() {
        cartridge.removeCartridge();
        paperTray.extractAllPaper();
        fout.print("Cartridge was removed, paper tray is empty.\n");
    }

    /**
     * Виводить інформацію про стан принтера у файл журналу.
     */
    public void status() {
        fout.print("\n***" + model + " - status ***\n" +
                "Is on: " + isOn + "\n" +
                "Able to print multicolour: " + cartridge.getIsMulticolour() + "\n" +
                "Ink level: " + cartridge.getInkLevel() + "\n" +
                "Paper tray max capacity: " + paperTray.getMaxPaperCount() + "\n" +
                "Number of inserted sheets of paper: " + paperTray.getPaperCount() + "\n" +
                "Scanner DPI: " + scanner.getDPI() + "\n" +
                "Able to scan multicolour: " + scanner.getIsMulticolour() + "\n\n"
        );
    }

    /**
     * Виконує сканування та записує результат у файл журналу.
     */
    public void scan() {
        fout.print("Scanning was successful!\n");
    }

    /**
     * Виконує сканування та друк документа, викликаючи методи scan() та print().
     */
    public void scanAndPrint() {
        scan();
        print();
    }

    /**
     * Вилучає певну кількість аркушів паперу з лотка.
     *
     * @param n Кількість аркушів для вилучення.
     */
    public void extractNPaperSheets(int n) {
        if (paperTray.getPaperCount() - n >= 0) {
            for (int i = 0; i < n; i++) {
                paperTray.extractPaper();
            }
            fout.print(n + " sheets of paper were extracted successfully.\n");
        } else {
            fout.print("Unable to extract " + n + " sheets of paper.\n");
        }
    }

    /**
     * Отримує поточну кількість аркушів у лотку для паперу.
     *
     * @return Кількість аркушів у лотку.
     */
    public int getPaperTrayCapacity() {
        return paperTray.getPaperCount();
    }

    /**
     * Отримує рівень чернил у картриджі.
     *
     * @return Рівень чернил у картриджі.
     */
    public int getCartridgeInkLevel() {
        return cartridge.getInkLevel();
    }

    /**
     * Завершує роботу з принтером та закриває файл журналу.
     */
    public void dispose() {
        fout.close();
    }
}

/**
 * Клас, що представляє картридж для принтера.
 */
class Cartridge {
    /**
     * Поле, що вказує, чи підтримується друк кольоровими чернилами.
     */
    private boolean isMulticolour;

    /**
     * Поле, що зберігає рівень чернил у картриджі.
     */
    private int inkLevel;

    /**
     * Конструктор за замовчуванням, створює картридж з чорними чернилами та повним рівнем.
     */
    public Cartridge() {
        isMulticolour = false;
        inkLevel = 100;
    }

    /**
     * Конструктор з параметрами, створює картридж з вказаним типом чернил і рівнем.
     *
     * @param multicolour Тип чернил (кольорові або чорні).
     * @param ink         Рівень чернил у картриджі.
     */
    public Cartridge(boolean multicolour, int ink) {
        isMulticolour = multicolour;
        inkLevel = ink;
    }

    /**
     * Отримати рівень чернил у картриджі.
     *
     * @return Рівень чернил у картриджі.
     */
    public int getInkLevel() {
        return inkLevel;
    }

    /**
     * Перевірити, чи підтримується друк кольоровими чернилами.
     *
     * @return true, якщо підтримується друк кольоровими чернилами, в іншому випадку - false.
     */
    public boolean getIsMulticolour() {
        return isMulticolour;
    }

    /**
     * Зменшити рівень чернил у картриджі на одиницю.
     */
    public void decreaseInkLevel() {
        inkLevel--;
    }

    /**
     * Видалити картридж і встановити рівень чернил на нуль.
     */
    public void removeCartridge() {
        isMulticolour = false;
        inkLevel = 0;
    }

    /**
     * Встановити новий картридж з вказаним типом чернил та повним рівнем.
     *
     * @param multicolour Тип чернил (кольорові або чорні).
     */
    public void putNewCartridge(boolean multicolour) {
        isMulticolour = multicolour;
        inkLevel = 100;
    }
}

/**
 * Клас, що представляє лоток для паперу.
 */
class PaperTray {
    /**
     * Кількість аркушів паперу в лотку.
     */
    private int paperCount;

    /**
     * Максимальна кількість аркушів, яку може містити лоток.
     */
    private int maxPaperCount;

    /**
     * Конструктор за замовчуванням, створює лоток без паперу і з максимальною кількістю аркушів.
     */
    public PaperTray() {
        paperCount = 0;
        maxPaperCount = 35;
    }

    /**
     * Конструктор з параметрами, створює лоток з вказаною кількістю аркушів і максимальною кількістю аркушів.
     *
     * @param paperN      Початкова кількість аркушів у лотку.
     * @param maxPaperN   Максимальна кількість аркушів, яку може містити лоток.
     */
    public PaperTray(int paperN, int maxPaperN) {
        paperCount = paperN;
        maxPaperCount = maxPaperN;
    }

    /**
     * Вставити один аркуш паперу у лоток, якщо не досягнуто максимальну кількість.
     */
    public void insertPaper() {
        if (paperCount < maxPaperCount) {
            paperCount++;
        }
    }

    /**
     * Вилучити один аркуш паперу із лотка.
     */
    public void extractPaper() {
        paperCount--;
    }

    /**
     * Вставити максимальну кількість аркушів у лоток.
     */
    public void insertAllPaper() {
        paperCount = maxPaperCount;
    }

    /**
     * Вилучити всі аркуші паперу із лотка (зробити лоток порожнім).
     */
    public void extractAllPaper() {
        paperCount = 0;
    }

    /**
     * Отримати поточну кількість аркушів у лотку для паперу.
     *
     * @return Кількість аркушів у лотку.
     */
    public int getPaperCount() {
        return paperCount;
    }

    /**
     * Отримати максимальну кількість аркушів, яку може містити лоток.
     *
     * @return Максимальна кількість аркушів, яку може містити лоток.
     */
    public int getMaxPaperCount() {
        return maxPaperCount;
    }
}

/**
 * Клас, що представляє сканер для принтера.
 */
class Scanner {
    /**
     * Роздільна здатність сканера (DPI - dots per inch).
     */
    private int DPI;

    /**
     * Поле, що вказує, чи підтримується сканування кольорових зображень.
     */
    private boolean isMulticolour;

    /**
     * Конструктор за замовчуванням, створює сканер із роздільною здатністю 1600 DPI та підтримкою кольорових зображень.
     */
    public Scanner() {
        DPI = 1600;
        isMulticolour = true;
    }

    /**
     * Конструктор з параметрами, створює сканер із вказаною роздільною здатністю і підтримкою кольорових зображень.
     *
     * @param dpi         Роздільна здатність сканера (DPI).
     * @param multicolour Підтримка кольорових зображень (true - підтримується, false - не підтримується).
     */
    public Scanner(int dpi, boolean multicolour) {
        DPI = dpi;
        isMulticolour = multicolour;
    }

    /**
     * Встановити максимальну роздільну здатність сканера (2400 DPI).
     */
    public void setMaxDPI() {
        DPI = 2400;
    }

    /**
     * Встановити мінімальну роздільну здатність сканера (300 DPI).
     */
    public void setMinDPI() {
        DPI = 300;
    }

    /**
     * Перевірити, чи підтримується сканування кольорових зображень.
     *
     * @return true, якщо підтримується сканування кольорових зображень, в іншому випадку - false.
     */
    public boolean getIsMulticolour() {
        return isMulticolour;
    }

    /**
     * Отримати поточну роздільну здатність сканера (DPI).
     *
     * @return Роздільна здатність сканера (DPI).
     */
    public int getDPI() {
        return DPI;
    }
}