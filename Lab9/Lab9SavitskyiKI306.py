from MFP import MFP

if __name__ == '__main__':
    # Створюємо екземпляр MFP з можливістю факсу та електронної пошти
    mfp = MFP(fax=True, mail=True)

    # Увімкнути принтер
    mfp.turnOn()

    # Заповнити все: вставити новий картридж і заповнити лоток для паперу
    mfp.fillEverything()

    # Відправити факс за вказаним номером телефону
    mfp.sendByFax("766435")

    # Відправити факс на вказану електронну адресу
    mfp.sendByEmail("example@organisation.com")

    # Вимкнути принтер
    mfp.turnOff()

    # Завершити роботу з файлом журналу
    mfp.dispose()
