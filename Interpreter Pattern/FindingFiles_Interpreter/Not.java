package FindingFiles_Interpreter;

import java.util.ArrayList;
import java.util.List;

public class Not implements Expression{
    private List results = new ArrayList();

    public Not(Expression expression,String dir) {
        List all= new All(dir).getResultsList();
        all.removeAll(expression.getResultsList());
        results=all;
    }

    public String getResults(){
        return results.toString();
    }

    @Override
    public List getResultsList() {
        return results;
    }
}
