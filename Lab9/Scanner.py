class Scanner:
    def __init__(self, dpi=1600, multicolour=True):
        self.DPI = dpi
        self.isMulticolour = multicolour

    # Метод для встановлення максимального DPI
    def setMaxDPI(self):
        self.DPI = 2400

    # Метод для встановлення мінімального DPI
    def setMinDPI(self):
        self.DPI = 300

    # Метод для отримання можливості сканування кольорової графіки
    def getIsMulticolour(self):
        return self.isMulticolour

    # Метод для отримання поточного DPI
    def getDPI(self):
        return self.DPI