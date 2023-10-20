class Cartridge:
    def __init__(self, multicolour=False, ink=0):
        self.isMulticolour = multicolour
        self.inkLevel = ink

    # Метод для отримання рівня чорнила
    def getInkLevel(self):
        return self.inkLevel

    # Метод для отримання можливості друку кольорової графіки
    def getIsMulticolour(self):
        return self.isMulticolour

    # Метод для зменшення рівня чорнила
    def decreaseInkLevel(self):
        self.inkLevel -= 1

    # Метод для вилучення картриджу
    def removeCartridge(self):
        self.isMulticolour = False
        self.inkLevel = 0

    # Метод для вставки нового картриджу
    def putNewCartridge(self, multicolour):
        self.isMulticolour = multicolour
        self.inkLevel = 100