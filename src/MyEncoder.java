import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/** 
 * This code creates a cipher based on a text file input describing ccorrespondence of characters and integers. Then, the 
 * application enables users to initiate the cipher and encrypt/decrypt text based on encryption/decryption key.
 * Due to time constraints, this program does not weed out bad input i.e. text input to encrypt/decrypt containing charahcters 
 * that do not have a matching character in the cipher. 
 * @author maithilitole
 *
 */

public class MyEncoder {

	
	public static Scanner scanner = new Scanner(System.in).useDelimiter("\n");
	public static HashMap<String, Integer> hashMap;
	public static boolean contPlay;
	
	public static void main(String[] args) {
		Cipher cipher = new Cipher();
		cipher.setMap();
		cipher.setHash("Hash.txt");
		hashMap = cipher.getMap();
		contPlay = true;
		System.out.println("Welcome to Encoder.");
		while (contPlay == true) {
			checkUserEntry();
			contPlay = playAgain();
		}
		System.out.print("Thank you for encrypting/decrypting with us, have a good day.");
		return;
	}
	
	public static void printMenu() {
		System.out.println("Please enter your cipher key of choice.");
		System.out.print("> ");
	}
	
	/**
	 * This method prompts the user to input the encryption key. Then evaluates if key is valid, and based on check re-prompts
	 * for valid key if required. Then, it prompts user for input string to be encrypted.
	 * The method iterates through each word in the input string, calls the encodeText method to encode the word, and joins 
	 * the encrypted words into a string.
	 * It then prints out the final encrypted message.
	 */
	public static void checkUserEntry() {
		printMenu();
		String entry = scanner.next();
		if (!hashMap.containsKey(entry)) {
			System.out.println("You entered " + entry + " . That is not a valid key. Please enter a valid key.");
			System.out.print("> ");
			checkUserEntry();
		}
		else {
			String choice = chooseMode();
			while (!choice.equals("e") || !choice.equals("d")) {
				if (choice.equals("e")) {
					System.out.println("Please enter text to encrypt: ");
					System.out.print("> ");
					String stringToEncrypt = scanner.next();
					String[] arrToEncrypt = stringToEncrypt.split(" ");
					
					ArrayList<String> arrEncrypted = new ArrayList<String>();
					for (int i = 0; i < arrToEncrypt.length; i++) {
						arrEncrypted.add(encodeText(entry, arrToEncrypt[i]));
					}
						
					String outputToPrint = String.join(" ", arrEncrypted);
					System.out.println(outputToPrint);
					return;
				}

			if (choice.equals("d")) {
					System.out.println("Please enter text to decrypt: ");
					System.out.print("> ");
					String stringToDecrypt = scanner.next();
					String[] arrToDecrypt = stringToDecrypt.split(" ");
					
					ArrayList<String> arrDecrypted = new ArrayList<String>();
					for (int i = 0; i < arrToDecrypt.length; i++) {
						arrDecrypted.add(decodeText(entry, arrToDecrypt[i]));
					}
						
					String outputToPrint = String.join(" ", arrDecrypted);
					System.out.println(outputToPrint);
					return;
				} 
				//choice = chooseMode();
			}
		}
	}
	
	public static String chooseMode() {
		System.out.println("Please enter (e) to Encode, or (d) to decode.");
		System.out.println("> ");
		String choice = scanner.next();
		return choice;
	}

	/**
	 * The method takes in the encryption character and string to encrypt. It then calls another method to calculate the 
	 * encrypted character integer and returns the encrypted string based on the corresponding character encryptions.
	 * @param entry
	 * @param stringToEncrypt
	 * @return encrypted string
	 */
	public static String encodeText(String entry, String stringToEncrypt) {
		int hashInt = hashMap.get(entry);
		ArrayList<String> encryptedStringArr = new ArrayList<String>();
		String[] splitString = stringToEncrypt.split("");
		for (int i = 0; i < splitString.length; i++) {
			// Get value based on key
			int input = hashMap.get(splitString[i].toUpperCase());
			// Calculate new value based on cipher
			int output = hashCalculator(hashInt, input);
			for (Map.Entry<String, Integer> mapEntry: hashMap.entrySet()) {
				if (mapEntry.getValue().equals(output)) {
					encryptedStringArr.add(mapEntry.getKey());
				}
			}
		}
		String encryptedString = String.join("",encryptedStringArr);

		return encryptedString;
	}
	
	/**
	 * The method takes in the decryption character and string to decrypt. It then calls another method to calculate the 
	 * decrypted character integer and returns the decrypted string based on the corresponding character decryptions.
	 * @param entry
	 * @param stringToDecrypt
	 * @return decrypted string
	 */
	public static String decodeText(String entry, String stringToDecrypt) {
		int hashInt = hashMap.get(entry);
		ArrayList<String> decryptedStringArr = new ArrayList<String>();
		String[] splitString = stringToDecrypt.split("");
		for (int i = 0; i < splitString.length; i++) {
			// Get value based on key
			int input = hashMap.get(splitString[i].toUpperCase());
			// Calculate new value based on cipher
			int output = hashCalculator2(hashInt, input);
			for (Map.Entry<String, Integer> mapEntry: hashMap.entrySet()) {
				if (mapEntry.getValue().equals(output)) {
					decryptedStringArr.add(mapEntry.getKey());
				}
			}
		}
		String decryptedString = String.join("",decryptedStringArr);

		return decryptedString;
	}
	
	/**
	 * The hashCalculator 
	 * @param hashInt takes in an integer based on the encryption character input by user. 
	 * The output is the post encryption integer, which is used to find the corresponding letter/character to encrypt to.
	 * @param integer corresponding to character for encryption
	 * @return integer value after encryption applied
	 */
	public static int hashCalculator(int hashInt, int input) {
		int output;
		output = input - hashInt;
		if (output < 0) {
			output = output + 44;
		}
		return output;
	}
	
	public static int hashCalculator2(int hashInt, int input) {
		int output;
		output = hashInt + input;
		if (output > 44) {
			output = output - 44;
		}
		return output;
	}
	
	/** 
	 * This method prompts the player to enter Y/N to indicate whether they would like to encrypt another statement or exit the program.
	 * @return boolean true or false
	 */
	public static boolean playAgain() {
		System.out.println("Would you like to encrypt/decrypt another statement? (Y/N)");
		System.out.println("> ");
		String answer = scanner.next();
		if (answer.equals("Y")) {
			boolean result = true;
			return result;
		}
		else if (answer.equals("N")) {
			boolean result = false;
			return result;
		}
		else {
			return playAgain();
		}
	}
}
