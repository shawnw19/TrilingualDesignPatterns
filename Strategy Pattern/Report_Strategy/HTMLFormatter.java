package Report_Strategy;

public class HTMLFormatter implements Formatter {

//    String title= new String("Monthly Report");
//    String[] text={"Things are going","really, really well."};

    public void outputReport(String title, String [] text){
        System.out.println("<html>");
        System.out.println("    <head>");
        System.out.println("      <title>"+ title +"</title>");
        System.out.println("    </head>");
        System.out.println("    <body>");

        for (int i = 0; i <text.length ; i++) {
            System.out.println("    <p>"+text[i]+"</p>");
        }

        System.out.println("    </body>");
        System.out.println("    </html>");
    }
}
