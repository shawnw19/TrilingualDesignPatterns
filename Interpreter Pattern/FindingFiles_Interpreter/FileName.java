package FindingFiles_Interpreter;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileName implements Expression{

    private List results = new ArrayList();

    public FileName(String pattern, String dir) {
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);

        File folder = new File(dir);
        for (File f : folder.listFiles()){
            Path filename= Paths.get(f.getName());
            if (matcher.matches(filename)){
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
