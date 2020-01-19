package FindingFiles_Interpreter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SizeFilter implements Expression{
    private List results = new ArrayList();

    public SizeFilter(int size, String dir) {

        File folder = new File(dir);
        for (File f : folder.listFiles()){
            if (f.length()>size){
                results.add(f.getName());
            }

        }
    }

    public String getResults(){
        return results.toString();
    }

    @Override
    public List getResultsList() {
        return results;
    }
}
