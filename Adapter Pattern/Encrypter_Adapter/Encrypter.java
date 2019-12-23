package Encrypter_Adapter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Encrypter {
    private String key=new String();

    public Encrypter(String key) {
        this.key = key;
    }

    public void encrypt(Scanner reader, FileWriter writer) throws IOException {
        int keyIndex =0;
        //there is a problem with key during tests: number of char must be at least 5 IF the String key is not created using "new" (it will be constant pool rather than heap then) to return the full new  encrypted string (reason not known yet)
        //the key cannot contain space or the encryption will not work properly
        //The encryption/decryption results were trialed to be seen First Letter Sensitive (reason not known yet)
        StringBuffer encryptedString = new StringBuffer();
        String clearString = reader.nextLine();//it can only encrypt one line, which is purely demonstration
        for (int i = 0; i <clearString.length() ; i++) {
            int encryptedChar= clearString.charAt(i)^key.charAt(keyIndex);
            encryptedString.append((char)encryptedChar);
            keyIndex= (keyIndex+1)%(key.length());
        }
        writer.write(String.valueOf(encryptedString));
    }
}
