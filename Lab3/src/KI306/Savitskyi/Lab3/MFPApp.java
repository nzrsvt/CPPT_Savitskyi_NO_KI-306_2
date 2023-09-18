package KI306.Savitskyi.Lab3;

import java.io.*;

/**
 * Клас представляє програму для управління багатофункціональним пристроєм MFP (МФП).
 * Він включає MFP, виконує різні операції та вимикає його.
 */
public class MFPApp {

    /**
     * Головний метод програми, який викликається при запуску.
     * Виконує послідовність дій з MFP.
     *
     * @param args Масив аргументів командного рядка (не використовується в даному випадку).
     * @throws FileNotFoundException Виникає, якщо не вдається знайти файл.
     */
    public static void main(String[] args) throws FileNotFoundException {
        // Створення об'єкта MFP з можливістю використовувати факс та електронну пошту.
        MFP mfp = new MFP(true, true);

        // Увімкнення MFP.
        mfp.turnOn();

        // Заповнення MFP папером та тонером.
        mfp.fillEverything();

        // Відправлення факсу за вказаним номером.
        mfp.sendByFax("766435");

        // Відправлення електронного листа на вказану адресу.
        mfp.sendByEmail("example@organisation.com");

        // Вимкнення MFP.
        mfp.turnOff();

        // Звільнення ресурсів, пов'язаних з MFP.
        mfp.dispose();
    }
}