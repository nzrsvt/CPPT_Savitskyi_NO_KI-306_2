from Printer import Printer

class MFP(Printer):
    def __init__(self, fax=False, mail=False):
        super().__init__()
        self.faxable = fax
        self.mailable = mail

    # Метод для відправлення факсу за вказаним номером телефону
    def sendByFax(self, phoneNumber):
        if self.faxable:
            super().scan()
            self.fout.write(f"Факс було успішно відправлено на номер: {phoneNumber}\n")
        else:
            self.fout.write("MFP не може відправити факс.\n")

    # Метод для відправлення факсу на вказану електронну адресу
    def sendByEmail(self, email):
        if self.mailable:
            super().scan()
            self.fout.write(f"Факс було успішно відправлено на електронну адресу: {email}\n")
        else:
            self.fout.write("MFP не може відправити електронного листа.\n")