package Login_Singleton;
//references: https://medium.com/@ronnieschaniel/object-oriented-design-patterns-explained-using-practical-examples-84807445b092
//https://www.geeksforgeeks.org/singleton-class-java/

public class Login {
    //declare a (and probably the only) private and static variable as the singleton with belonging class as type
    private static Login login;
    //and a private constructor
    private String message;
    private int password;
    private Login(){
        message= "You got a promotion!";
        password= 123;
    }
    //public static method to create instance of Singleton class
    public static Login getInstance(){
        if (login==null){
            login=new Login();
        }
        return login;
    }

    public String getMessage() {
        return message;
    }

    //verify the password without sending it to the client for the security (in case it is intercepted during the transmission)
    public boolean verifyPassword(int input){
        return input==password;
    }
}
