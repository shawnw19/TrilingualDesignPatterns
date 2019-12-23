package Encrypter_Adapter;

import java.io.*;
import java.util.Scanner;

public class EncrypterRunner {
    public static void main(String[] args) throws IOException {
        //encrypt
        Scanner reader= new Scanner(new FileReader("message.txt"));
        FileWriter writer = new FileWriter("message_encrypted.txt");
        Encrypter encrypter= new Encrypter("My secret key");
        encrypter.encrypt(reader,writer);
        reader.close();
        writer.close();

        //decrypt back
        Scanner reader2= new Scanner(new FileReader("message_encrypted.txt"));
        FileWriter writer2 = new FileWriter("message_decrypted.txt");
        encrypter.encrypt(reader2,writer2);

        reader2.close();
        writer2.close();

        //using adapter
        Scanner reader3 = new StringIOAdapter("I am using an adapter.").adapt();
        FileWriter writer3 = new FileWriter("message_adapted_encrypted.txt");
        encrypter.encrypt(reader3,writer3);
        reader3.close();
        writer3.close();

        Scanner reader4= new Scanner(new FileReader("message_adapted_encrypted.txt"));
        FileWriter writer4 = new FileWriter("message_adapted_decrypted.txt");
        encrypter.encrypt(reader4,writer4);

        reader4.close();
        writer4.close();

    }
}
