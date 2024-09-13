package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import edu.grinnell.csc207.util.CipherUtils;

/**
 * A program to encode a message with either the Caesar or Vigenère Cipher.
 *
 * @author Cade Johnston
 */
public class Cipher {
  /**
   * The expected length of the input array.
   */
  private static final int INPUT = 4;
  /**
   * The index of the key in the input array. (Magic numbers required this)
   */
  private static final int KEY = 3;
  /**
   * Encode a message using a specified cipher and key.
   *
   * @param args
   *   Command-line arguments (May be unordered, so long as 2 comes before 3)
   *   args[0] should instruct whether to use Ceasar or Vigenere
   *   args[1] should instruct whether to encrypt or decrypt
   *   args[2] should be the message to encode
   *   args[3] should be the key to encode with
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    if (args.length == INPUT) {
      String temp;
      String output = "";
      if (!(args[0].equals("-caesar") || args[0].equals("-vigenere"))) {
        for (int i = 1; i < INPUT; i++) {
          if (args[i].equals("-caesar") || args[i].equals("-vigenere")) {
            for (int j = 0; j < i; j++) {
              temp = args[i];
              args[i] = args[j];
              args[j] = temp;
            } // for [j]
            i = INPUT;
          } // if
        } // for [i]
      } // if
      if (!(args[1].equals("-encode") || args[1].equals("-decode"))) {
        for (int i = 2; i < INPUT; i++) {
          if (args[i].equals("-encode") || args[i].equals("-decode")) {
            for (int j = 1; j < i; j++) {
              temp = args[i];
              args[i] = args[j];
              args[j] = temp;
            } // for [j]
            i = INPUT;
          } // if
        } // for [i]
      } // if
      String cipher = args[0];
      String method = args[1];
      String str = args[2];
      String key = args[KEY];
      boolean onlyLower = false;
      if (str.equals(CipherUtils.caesarEncrypt(str, 'a'))
          && key.equals(CipherUtils.caesarEncrypt(key, 'a'))) {
        onlyLower = true;
      } // if
      if ((cipher.equals("-caesar") || cipher.equals("-vigenere"))
          && (method.equals("-encode") || method.equals("-decode"))
          && onlyLower) {
        if (cipher.equals("-caesar")) {
          if (key.length() == 1) {
            if (method.equals("-encode")) {
              output = CipherUtils.caesarEncrypt(str, key.charAt(0));
            } else {
              output = CipherUtils.caesarDecrypt(str, key.charAt(0));
            } // if / else
          } else {
            System.err.printf("Error: Caesar ciphers require a one-character key\n");
          } // if / else
        } else {
          if (key.length() != 0) {
            if (method.equals("-encode")) {
              output = CipherUtils.vigenereEncrypt(str, key);
            } else {
              output = CipherUtils.vigenereDecrypt(str, key);
            } // if / else
          } else {
            System.err.printf("Error: Empty keys are not permitted\n");
          } // if / else
        } // if / else
        if (output.length() != 0) {
          pen.println(output);
        } // if
      } else {
        if (!(onlyLower)) {
          System.err.printf("Error: strings must be only lowercase letters\n");
        } else if (method.equals("-encode") || method.equals("-decode")) {
          System.err.printf(
              "Error: No valid action specified.  Legal values are '-caesar' and '-vigenere'\n");
        } else {
          System.err.printf(
              "Error: No valid action specified.  Legal values are '-encode' and '-decode'\n");
        } // if / else if / else
      } // if / else
    } else {
      System.err.printf("Error: Expected 4 parameters, received %d\n", args.length);
    } // if / else
  } // main
} // Cipher
