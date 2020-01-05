package Login_Singleton;

import java.util.Scanner;

public class LoginToGetMessage {
    public static void main(String[] args) {
        boolean verified = false;
        int input = 1;
        int noTrial = 1;
        Login theLogin = Login.getInstance();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input the password to see the message: ");
        input = scanner.nextInt();
        verified = theLogin.verifyPassword(input);


        while (noTrial < 4 && !verified) {
            System.out.println("Try again: ");
            input = scanner.nextInt();
            noTrial++;
            verified = theLogin.verifyPassword(input);
        }
        if (verified) {
            System.out.println("Correct password, the message is: ");
            System.out.println(theLogin.getMessage() + '\n');
        }

        System.out.println("End of the program.");
    }
}
