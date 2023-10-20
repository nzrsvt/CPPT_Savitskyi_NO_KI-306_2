import os
from Cartridge import Cartridge
from PaperTray import PaperTray
from Scanner import Scanner

class Printer:
    def __init__(self, mod="невідомо", multicolour=False, ink=0):
        self.model = mod
        self.isOn = False
        self.cartridge = Cartridge(multicolour, ink)
        self.paperTray = PaperTray()
        self.scanner = Scanner()
        self.fout = open("Log.txt", "w")  # Відкрити файл журналу для запису

    # Метод для увімкнення принтера
    def turnOn(self):
        self.isOn = True
        self.fout.write("Принтер було успішно увімкнено.\n")

    # Метод для вимкнення принтера
    def turnOff(self):
        self.isOn = False
        self.fout.write("Принтер було успішно вимкнено.\n")

    # Метод для вставки певної кількості аркушів паперу
    def insertNPaperSheets(self, n):
        if self.paperTray.getPaperCount() + n <= self.paperTray.getMaxPaperCount():
            for _ in range(n):
                self.paperTray.insertPaper()
            self.fout.write(f"{n} аркушів паперу було успішно вставлено.\n")
        else:
            self.fout.write(f"Неможливо вставити {n} аркушів паперу.\n")

    # Метод для друку
    def print(self):
        if self.isOn:
            if self.cartridge.getInkLevel() != 0 and self.paperTray.getPaperCount() != 0:
                self.paperTray.extractPaper()
                self.cartridge.decreaseInkLevel()
                self.fout.write("Друк був успішним!\n")
            else:
                self.fout.write("Друк не вдався!\n")
        else:
            self.fout.write("Друк не вдався!\n")
        self.fout.write(f"Залишилося аркушів паперу: {self.paperTray.getPaperCount()}\nРівень чорнила: {self.cartridge.getInkLevel()}\n")

    # Метод для заповнення всього
    def fillEverything(self):
        self.cartridge.putNewCartridge(True)
        self.paperTray.insertAllPaper()
        self.fout.write("Було вставлено новий картридж та заповнено лоток для паперу.\n")

    # Метод для видалення всього
    def removeEverything(self):
        self.cartridge.removeCartridge()
        self.paperTray.extractAllPaper()
        self.fout.write("Картридж було вилучено, лоток для паперу порожній.\n")

    # Метод для виведення статусу принтера
    def status(self):
        self.fout.write(f"\n***{self.model} - стан ***\n" +
                f"Увімкнено: {self.isOn}\n" +
                f"Можливість друку кольорової графіки: {self.cartridge.getIsMulticolour()}\n" +
                f"Рівень чорнила: {self.cartridge.getInkLevel()}\n" +
                f"Максимальна ємність лотка для паперу: {self.paperTray.getMaxPaperCount()}\n" +
                f"Кількість вставлених аркушів паперу: {self.paperTray.getPaperCount()}\n" +
                f"DPI сканера: {self.scanner.getDPI()}\n" +
                f"Можливість сканування кольорової графіки: {self.scanner.getIsMulticolour()}\n\n")

    # Метод для сканування
    def scan(self):
        self.fout.write("Сканування було успішним!\n")

    # Метод для сканування та друку
    def scanAndPrint(self):
        self.scan()
        self.print()

    # Метод для вилучення певної кількості аркушів паперу
    def extractNPaperSheets(self, n):
        if self.paperTray.getPaperCount() - n >= 0:
            for _ in range(n):
                self.paperTray.extractPaper()
            self.fout.write(f"Було вилучено {n} аркушів паперу.\n")
        else:
            self.fout.write(f"Неможливо вилучити {n} аркушів паперу.\n")

    # Метод для отримання кількості аркушів паперу у лотку
    def getPaperTrayCapacity(self):
        return self.paperTray.getPaperCount()

    # Метод для отримання рівня чорнила в картриджі
    def getCartridgeInkLevel(self):
        return self.cartridge.getInkLevel()

    # Метод для завершення роботи з файлом журналу
    def dispose(self):
        self.fout.close()