package Report_Strategy;

public class GenerateReport {
    private static final String  title= new String("Monthly Report");
    private  static final String[] text={"Things are going","really, really well."};

    public static void main(String[] args) {
        HTMLFormatter report1 = new HTMLFormatter();
        report1.outputReport(title,text);
        System.out.println("\nanother output: \n");
        PlainTextFormatter report2= new PlainTextFormatter();
        report2.outputReport(title,text);
    }

}
