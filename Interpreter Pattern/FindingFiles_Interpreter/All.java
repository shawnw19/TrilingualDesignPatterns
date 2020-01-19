package FindingFiles_Interpreter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class All implements Expression{
    //list all the files under a folder

    private List results = new ArrayList();

    public All(String dir) {

        File folder = new File(dir);//name of folder only
        for (File f : folder.listFiles()){
            results.add(f.getName());
        }
    }

    public String getResults(){
        return results.toString();
    }

    public List getResultsList(){
        return results;
    }
}
