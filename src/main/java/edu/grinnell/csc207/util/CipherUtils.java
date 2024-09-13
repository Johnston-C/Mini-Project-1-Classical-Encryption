package edu.grinnell.csc207.util;

/**
 * A variety of methods for use in encoding methods.
 *
 * @author Cade Johnston
 */
public class CipherUtils {
  /**
   * The number of lowercase letters.
   */
  private static final int NUM_LETTERS = 26;

  /**
   * The ASCII value of 'a'.
   */
  private static final int MAGIC_A = 97;

  /**
   * letter2int
   *
   * Convert a lowercase letter char to an integer from 0 to 25.
   * @param letter
   *  The char to be converted.
   * @return an int representing the char, where 0 = 'a', 1 = 'b'...
   */
  private static int letter2int(char letter) {
    return (int) letter - MAGIC_A;
  } // letter2int

  /**
   * int2letter
   *
   * Convert an integer from 0 to 25 to a lowercase letter char.
   * @param i
   *  The int to be converted.
   * @return an char representing the int, where 0 = 'a', 1 = 'b'...
   */
  private static char int2letter(int i) {
    return (char) (i + MAGIC_A);
  } // int2letter

  /**
   * caesarEncrypt
   *
   * Encrypt 'str' using the Caesar Cipher with key 'letter'.
   * @param str
   *  The message to be encrypted.
   * @param letter
   *  A char representing the key to encrypt with.
   * @return The encrypted string.
   */
  public static String caesarEncrypt(String str, char letter) {
    return caesarCipher(str, letter, false);
  } // caesarEncrypt

  /**
   * caesarDecrypt
   *
   * Decrypt 'str' using the Caesar Cipher with key 'letter'.
   * @param str
   *  The message to be decrypted.
   * @param letter
   *  A char representing the key to decrypt with.
   * @return The decrypted string.
   */
  public static String caesarDecrypt(String str, char letter) {
    return caesarCipher(str, letter, true);
  } // caesarDecrypt

  /**
   * caesarCipher
   *
   * Encode 'str' using the Caesar Cipher with key 'letter', increasing
   * or decreasing the letters based on the inverse boolean.
   * @param str
   *  The message to be encoded.
   * @param letter
   *  A char representing the key to encode with.
   * @param inverse
   *  A boolean representing wheter or not to encrypt or decrypt the message.
   *  True is decode, False is encode.
   * @return The encoded string.
   */
  private static String caesarCipher(String str, char letter, boolean inverse) {
    int[] intRep = new int[str.length()];
    int delta = letter2int(letter);
    if (inverse) {
      delta *= -1;
    } // if
    for (int i = 0; i < str.length(); i++) {
      intRep[i] = letter2int(str.charAt(i));
      intRep[i] = (intRep[i] + delta + NUM_LETTERS) % NUM_LETTERS;
    } // for [i]
    String output = "";
    for (int j = 0; j < str.length(); j++) {
      output += int2letter(intRep[j]);
    } // for [j]
    return output;
  } // caesarCipher

  /**
   * vigenereEncrypt
   *
   * Encrypt 'str' using the Vigenère Cipher with key 'key'.
   * @param str
   *  The message to be encrypted.
   * @param key
   *  A string representing the key to encrypt with.
   * @return The encrypted string.
   */
  public static String vigenereEncrypt(String str, String key) {
    return vigenereCipher(str, key, false);
  } // vigenereEncrypt

  /**
   * vigenereDecrypt
   *
   * Decrypt 'str' using the Caesar Cipher with key 'key'.
   * @param str
   *  The message to be decrypted.
   * @param key
   *  A string representing the key to decrypt with.
   * @return The decrypted string.
   */
  public static String vigenereDecrypt(String str, String key) {
    return vigenereCipher(str, key, true);
  } // vigenereDecrypt

  /**
   * vigenereCipher
   *
   * Encode 'str' using the Vigenère Cipher with key 'key', increasing
   * or decreasing the letters based on the inverse boolean.
   * @param str
   *  The message to be encoded.
   * @param key
   *  A string representing the key to encode with.
   * @param inverse
   *  A boolean representing wheter or not to encrypt or decrypt the message.
   *  True is decode, False is encode.
   * @return The encoded string.
   */
  private static String vigenereCipher(String str, String key, boolean inverse) {
    int[] intRep = new int[str.length()];
    int[] deltas = new int[key.length()];
    for (int i = 0; i < key.length(); i++) {
      deltas[i] = letter2int(key.charAt(i));
    } // for [i]
    if (inverse) {
      for (int j = 0; j < key.length(); j++) {
        deltas[j] *= -1;
      } // for [j]
    } // if
    int keyIndex = 0;
    for (int k = 0; k < str.length(); k++) {
      intRep[k] = letter2int(str.charAt(k));
      intRep[k] = (intRep[k] + deltas[keyIndex] + NUM_LETTERS) % NUM_LETTERS;
      keyIndex = (keyIndex + 1) % key.length();
    } // for [k]
    String output = "";
    for (int l = 0; l < str.length(); l++) {
      output += int2letter(intRep[l]);
    } //for [l]
    return output;
  } // vigenereCipher
} // Cipher Utils
