	package KI306.Savitskyi.Lab3;
	
	import java.io.FileNotFoundException;
	
	/**
	 * Інтерфейс, що представляє собою можливість відправки документів.
	 */
	interface Sendable {	 
		/**
		 * Метод для відправки документу факсом за вказаним номером телефону.
		 *
		 * @param phoneNumber Номер телефону, на який відправляється факс.
		 */
		void sendByFax(String phoneNumber);
	
		/**
		 * Метод для відправки документу електронною поштою на вказаний адресу електронної пошти.
		 *
		 * @param email Адреса електронної пошти, на яку відправляється документ.
		 */
		void sendByEmail(String email);
	}
	
	/**
	 * Клас MFP (Multi-Function Printer), який представляє собою багатофункціональний принтер і є реалізацією інтерфейсу Sendable.
	 */
	public class MFP extends Printer implements Sendable {
		/**
		 * Конструктор за замовчуванням для MFP.
		 *
		 * @throws FileNotFoundException Виняток, який виникає, якщо не вдається знайти файл для запису.
		 */
		public MFP() throws FileNotFoundException {
			faxable = true;
			mailable = false;	
		}
		
		/**
		 * Конструктор MFP з можливістю налаштування параметрів факсу та електронної пошти.
		 *
		 * @param fax    Параметр, який вказує, чи підтримує MFP факс.
		 * @param mail   Параметр, який вказує, чи підтримує MFP відправку електронною поштою.
		 * @throws FileNotFoundException Виняток, який виникає, якщо не вдається знайти файл для запису.
		 */
		public MFP(boolean fax, boolean mail) throws FileNotFoundException {
			faxable = fax;
			mailable = mail;
		}
		
		/**
		 * Метод для відправки документу факсом за вказаним номером телефону.
		 *
		 * @param phoneNumber Номер телефону, на який відправляється факс.
		 */
		public void sendByFax(String phoneNumber) {
			if (faxable == true) {
				super.scan();
				super.fout.print("The fax was successfully sent to the number: " + phoneNumber + "\n");
			} else {
				super.fout.print("The MFP is unable to send fax.\n");
			}
		}
		
		/**
		 * Метод для відправки документу електронною поштою на вказаний адресу електронної пошти.
		 *
		 * @param email Адреса електронної пошти, на яку відправляється документ.
		 */
		public void sendByEmail(String email) {
			if (mailable == true) {
				super.scan();
				super.fout.print("The fax was successfully sent to the email: " + email + "\n");
			} else {
				super.fout.print("The MFP is unable to send email.\n");
			}
		}
		
		private boolean faxable;
		private boolean mailable;
	}
