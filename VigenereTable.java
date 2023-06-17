
import java.util.Scanner;

public class VigenereTable {

    private static final int ALPHABET_SIZE = 26;

    public static String encrypt(String plaintext, String keyword, int shift) {
        String vigenereEncrypted = vigenereEncrypt(plaintext, keyword);
        return caesarEncrypt(vigenereEncrypted, shift);
    }

    public static String decrypt(String ciphertext, String keyword, int shift) {
        String caesarDecrypted = caesarDecrypt(ciphertext, shift);
        return vigenereDecrypt(caesarDecrypted, keyword);
    }

    private static String vigenereEncrypt(String plaintext, String keyword) {
        StringBuilder ciphertext = new StringBuilder();
        String processedKeyword = processKeyword(keyword);

        int keywordLength = processedKeyword.length();
        int plaintextLength = plaintext.length();

        for (int i = 0; i < plaintextLength; i++) {
            char currentChar = plaintext.charAt(i);
            char keywordChar = processedKeyword.charAt(i % keywordLength);

            if (Character.isAlphabetic(currentChar)) {
                char encryptedChar = encryptChar(currentChar, keywordChar);
                ciphertext.append(encryptedChar);
            } else {
                ciphertext.append(currentChar);
            }
        }

        return ciphertext.toString();
    }

    private static char encryptChar(char plainChar, char keywordChar) {
        int plainValue = plainChar - 'A';
        int keywordValue = keywordChar - 'A';
        int encryptedValue = (plainValue + keywordValue) % ALPHABET_SIZE;
        return (char) ('A' + encryptedValue);
    }

    private static String vigenereDecrypt(String ciphertext, String keyword) {
        StringBuilder plaintext = new StringBuilder();
        String processedKeyword = processKeyword(keyword);

        int keywordLength = processedKeyword.length();
        int ciphertextLength = ciphertext.length();

        for (int i = 0; i < ciphertextLength; i++) {
            char currentChar = ciphertext.charAt(i);
            char keywordChar = processedKeyword.charAt(i % keywordLength);

            if (Character.isAlphabetic(currentChar)) {
                char decryptedChar = decryptChar(currentChar, keywordChar);
                plaintext.append(decryptedChar);
            } else {
                plaintext.append(currentChar);
            }
        }

        return plaintext.toString();
    }

    private static char decryptChar(char cipherChar, char keywordChar) {
        int cipherValue = cipherChar - 'A';
        int keywordValue = keywordChar - 'A';
        int decryptedValue = (cipherValue - keywordValue + ALPHABET_SIZE) % ALPHABET_SIZE;
        return (char) ('A' + decryptedValue);
    }

    private static String processKeyword(String keyword) {
        return keyword.toUpperCase().replaceAll("[^A-Z]", "");
    }

    private static String caesarEncrypt(String text, int shift) {
        StringBuilder encryptedText = new StringBuilder();
        int length = text.length();

        for (int i = 0; i < length; i++) {
            char currentChar = text.charAt(i);

            if (Character.isAlphabetic(currentChar)) {
                char encryptedChar = encryptCharCaesar(currentChar, shift);
                encryptedText.append(encryptedChar);
            } else {
                encryptedText.append(currentChar);
            }
        }

        return encryptedText.toString();
    }

    private static char encryptCharCaesar(char plainChar, int shift) {
        int plainValue = plainChar - 'A';
        int encryptedValue = (plainValue + shift) % ALPHABET_SIZE;
        return (char) ('A' + encryptedValue);
    }

    private static String caesarDecrypt(String text, int shift) {
        StringBuilder decryptedText = new StringBuilder();
        int length = text.length();

        for (int i = 0; i < length; i++) {
            char currentChar = text.charAt(i);

            if (Character.isAlphabetic(currentChar)) {
                char decryptedChar = decryptCharCaesar(currentChar, shift);
                decryptedText.append(decryptedChar);
            } else {
                decryptedText.append(currentChar);
            }
        }

        return decryptedText.toString();
    }

    private static char decryptCharCaesar(char cipherChar, int shift) {
        int cipherValue = cipherChar - 'A';
        int decryptedValue = (cipherValue - shift + ALPHABET_SIZE) % ALPHABET_SIZE;
        return (char) ('A' + decryptedValue);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean start = true;

        while (start) {
            System.out.println("PLEASE ENTER ALL WITH UPPERCASE");
            System.out.println("1-Encrypt; 2-Decrypt; 3-Exit");
            int input = scanner.nextInt();

            if (input == 1) {
                System.out.println("Enter the plaintext: ");
                String plaintext = scanner.next();

                System.out.println("Enter the keyword: ");
                String keyword = scanner.next();

                System.out.println("Enter the Caesar shift: ");
                int shift = scanner.nextInt();

                String ciphertext = encrypt(plaintext, keyword, shift);
                System.out.println("Ciphertext: " + ciphertext + "\n");
            } else if (input == 2) {
                System.out.println("Enter the ciphertext: ");
                String cText = scanner.next();

                System.out.println("Enter the keyword: ");
                String keyword = scanner.next();

                System.out.println("Enter the Caesar shift: ");
                int shift = scanner.nextInt();

                String decryptedText = decrypt(cText, keyword, shift);
                System.out.println("Decrypted Text: " + decryptedText + "\n");
            } else if (input == 3) {
                start = false;
            } else {
                System.out.print("Wrong input!!!");
            }
        }
        scanner.close();
    }
}
