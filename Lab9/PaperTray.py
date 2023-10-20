class PaperTray:
    def __init__(self, paperN=0, maxPaperN=35):
        self.paperCount = paperN
        self.maxPaperCount = maxPaperN

    # Метод для вставки аркуша паперу
    def insertPaper(self):
        if self.paperCount < self.maxPaperCount:
            self.paperCount += 1

    # Метод для вилучення аркуша паперу
    def extractPaper(self):
        self.paperCount -= 1

    # Метод для вставки всіх аркушів паперу
    def insertAllPaper(self):
        self.paperCount = self.maxPaperCount

    # Метод для вилучення всіх аркушів паперу
    def extractAllPaper(self):
        self.paperCount = 0

    # Метод для отримання кількості аркушів паперу в лотку
    def getPaperCount(self):
        return self.paperCount

    # Метод для отримання максимальної кількості аркушів у лотку
    def getMaxPaperCount(self):
        return self.maxPaperCount