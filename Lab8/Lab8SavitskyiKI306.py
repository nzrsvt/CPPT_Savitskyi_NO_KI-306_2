import math
import os
import struct

# Функція для запису результату у текстовий файл
def write_res_txt(result, file_name):
    with open(file_name, "w") as f:
        f.write(f"{result} ")

# Функція для зчитування результату з текстового файлу
def read_res_txt(file_name):
    try:
        if os.path.exists(file_name):
            with open(file_name, "r") as f:
                return float(f.read())
        else:
            raise FileNotFoundError(f"Файл {file_name} не знайдено")
    except FileNotFoundError as ex:
        print(ex)

# Функція для запису результату у бінарний файл
def write_res_bin(result, file_name):
    with open(file_name, "wb") as f:
        f.write(struct.pack('f', result))

# Функція для зчитування результату з бінарного файлу
def read_res_bin(file_name):
    try:
        if os.path.exists(file_name):
            with open(file_name, "rb") as f:
                return struct.unpack('f', f.read())[0]
        else:
            raise FileNotFoundError(f"Файл {file_name} не знайдено")
    except FileNotFoundError as ex:
        print(ex)

# Клас для власних виключень під час обчислень
class CalcException(Exception):
    def __init__(self, cause=""):
        super().__init__(cause)

# Функція для виконання обчислення
def calculate(x):
    rad = x * math.pi / 180.0
    try:
        # Перевірка на специфічні значення, які викликають виключення
        if rad == math.pi or rad == 0.0 or rad == -math.pi:
            raise CalcException()
        
        if rad == math.pi / 2.0 or rad == -math.pi / 2.0:
            raise CalcException()

        # Виконання обчислення
        res = (1.0 / math.tan(rad)) / (math.sin(2 * rad) + 4 * math.cos(rad))

        # Перевірка на результати NaN або нескінченність
        if math.isnan(res) or math.isinf(res):
            raise CalcException()

    except ZeroDivisionError:
        print("Знаменник рівний нулю, обчислення неможливе")

    except CalcException:
        # Обробка специфічних випадків неприпустимих вхідних значень
        if rad == math.pi or rad == 0.0 or rad == -math.pi:
            raise CalcException("Причина виключення: Неприпустиме значення X для обчислення котангенсу")
        elif rad == math.pi / 2.0 or rad == -math.pi / 2.0:
            raise CalcException("Причина виключення: Неприпустиме значення X для обчислення тангенсу, яке необхідне для обчислення котангенсу в Python")
        else:
            raise CalcException("Невідома причина виникнення виключення під час обчислення")

    # Повернення результату обчислення
    return res

# Головний блок виконання сценарію
if __name__ == "__main__":
    # Отримання від користувача вхідних даних для обчислення
    data = int(input("Введіть дані: "))

    # Виконання обчислення
    result = calculate(data)

    # Виведення результату
    print(f"Результат: {result}")

    # Запис результату у текстовий і бінарний файли
    write_res_txt(result, "textRes.txt")
    write_res_bin(result, "BinRes.bin")

    # Зчитування та виведення результату з бінарного файлу
    print(f"Результат: {read_res_bin('BinRes.bin')}")

    # Зчитування та виведення результату з текстового файлу
    print(f"Результат: {read_res_txt('textRes.txt')}")