package FindingFiles_Interpreter;

public class FileFinderTester {
    public static void main(String[] args) {
        Expression find1= new All("Files");
        System.out.println("All files: "+ ((All) find1).getResults());

        Expression find2 = new FileName("*.mp3","Files");
        System.out.println("Mp3 files: "+ ((FileName) find2).getResults());

        Expression find3 = new SizeFilter(1024,"Files");
        System.out.println("Files bigger than 1kb: "+ ((SizeFilter) find3).getResults());

        Expression find4 = new Writeable("Files");
        System.out.println("Writable files: "+ ((Writeable) find4).getResults());

        Expression find5 = new Not(new Writeable("Files"),"Files");
        System.out.println("Read-only files: " +((Not) find5).getResults());

    }
}
