package Report_Strategy;

public class PlainTextFormatter implements Formatter {

    public void outputReport(String title, String [] text){

        System.out.println("*****" + title +" *****");
        for (int i = 0; i <text.length ; i++) {
            System.out.println(text[i]);
        }
    }
}
