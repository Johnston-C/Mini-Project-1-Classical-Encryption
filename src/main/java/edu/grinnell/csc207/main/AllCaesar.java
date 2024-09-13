package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import edu.grinnell.csc207.util.CipherUtils;

/**
 * A program to show all possible encodings/decodings
 * of a message with a Caesar Cipher.
 *
 * @author Cade Johnston
 */
public class AllCaesar {
  /**
   * Encode a message using all possible Ceasar Ciphers.
   *
   * @param args
   *   Command-line arguments
   *   args[0] should instruct whether to encrypt or decrypt
   *   args[1] should be the message to encode
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    if (args.length != 2) {
      System.err.println("Error: Incorrect number of parameters.");
    } else {
      String str = args[1];
      if (args[0].equals("encode")) {
        for (char ch = 'a'; ch <= 'z'; ch++) {
          pen.printf("n = %c: %s\n", ch, CipherUtils.caesarEncrypt(str, ch));
        } // for [ch]
      } else if (args[0].equals("decode")) {
        for (char ch = 'a'; ch <= 'z'; ch++) {
          pen.printf("n = %c: %s\n", ch, CipherUtils.caesarDecrypt(str, ch));
        } // for [ch]
      } else {
        System.err.printf(
            "Error: Invalid option: \"%s\". Valid options are \"encode or \"decode\".\n", args[0]);
      } // if / if else / else
    } // if / else
    pen.close();
  } // main
} // AllCaesar
