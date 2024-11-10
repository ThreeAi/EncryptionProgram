package edu.program.encryptionprogram;

public class VigenereCipher {
    private static final int ALPHABET_SIZE = 26; // Размер английского алфавита
    private static final char BASE_CHAR = 'a';   // Начальный символ для сдвигов

    // Метод для шифрования текста
    public static String encrypt(String text, String key) {
        String sanitizedText = text.replaceAll("[^a-zA-Z ]", "").toLowerCase();
        StringBuilder encryptedText = new StringBuilder();
        String extendedKey = extendKey(sanitizedText, key);  // Расширяем ключ

        for (int i = 0; i < sanitizedText.length(); i++) {
            char textChar = sanitizedText.charAt(i);
            char keyChar = extendedKey.charAt(i);
            if (Character.isLetter(textChar)) {
                int encryptedChar = (textChar - BASE_CHAR + keyChar - BASE_CHAR) % ALPHABET_SIZE;
                encryptedText.append((char) (encryptedChar + BASE_CHAR));
            } else {
                encryptedText.append(textChar);
            }
        }
        return encryptedText.toString();
    }

    // Метод для дешифрования текста
    public static String decrypt(String encryptedText, String key) {
        StringBuilder decryptedText = new StringBuilder();
        String extendedKey = extendKey(encryptedText, key);

        for (int i = 0; i < encryptedText.length(); i++) {
            char encryptedChar = Character.toLowerCase(encryptedText.charAt(i));
            char keyChar = Character.toLowerCase(extendedKey.charAt(i));
            if (Character.isLetter(encryptedChar)) {
                int decryptedChar = (encryptedChar - keyChar + ALPHABET_SIZE) % ALPHABET_SIZE;
                decryptedText.append((char) (decryptedChar + BASE_CHAR));
            } else {
                decryptedText.append(encryptedChar);
            }
        }
        return decryptedText.toString();
    }

    // Вспомогательный метод для повторения ключа до нужной длины
    private static String extendKey(String text, String key) {
        StringBuilder extendedKey = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            extendedKey.append(key.charAt(i % key.length()));
        }
        return extendedKey.toString();
    }

}
