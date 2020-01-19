package FindingFiles_Interpreter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Writeable implements Expression {
    private List results = new ArrayList();

    public Writeable(String dir) {

        File folder = new File(dir);
        for (File f : folder.listFiles()){
            if (f.canWrite()){
                results.add(f.getName());
            }
        }
    }

    public String getResults(){
        return results.toString();
    }

    public List getResultsList(){
        return results;
    }
}
