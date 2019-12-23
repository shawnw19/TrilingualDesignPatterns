package Encrypter_Adapter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class StringIOAdapter {
    private String message;

    public StringIOAdapter(String message) throws IOException {
        this.message = message;
        FileWriter adaptedWriter = new FileWriter("adapted_message.txt");
        adaptedWriter.write(message);
        adaptedWriter.close();
    }

    public Scanner adapt() throws FileNotFoundException {
        Scanner adaptedReader = new Scanner(new FileReader("adapted_message.txt"));
        return adaptedReader;
    }
}
