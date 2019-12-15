package Chapter7;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;

import java.util.List;
import java.util.ListIterator;

public class OutputEvaluation {
    //make the method static in order to recall it directly from the class (rather than an object)
    public static void print(Classifier model, Evaluation eval,int target){
        System.out.println(model.getClass().getName());
        System.out.println("\tRecall:    "+eval.recall(target));
        System.out.println("\tPrecision:    "+eval.precision(target));
        System.out.println("\tF-measure:    "+eval.fMeasure(target));
        System.out.println();
    }

    public static void interateModels(List<Classifier> models, Instances balancedTrain, Instances test, Evaluation eval, int target,double[][] measures) throws Exception{
        for (ListIterator<Classifier> it = models.listIterator(); it.hasNext(); ) {
            Classifier model = it.next();
            model.buildClassifier(balancedTrain);
            eval = new Evaluation(balancedTrain);
            eval.evaluateModel(model, test);

            OutputEvaluation.print(model, eval, target);
            saveResults(it,measures,eval,target);
        }
    }

    public static void saveResults(ListIterator<Classifier> it, double[][] measures,Evaluation eval,int target){
        //save results for average
        measures[it.previousIndex()][0] += eval.recall(target);
        measures[it.previousIndex()][1] += eval.precision(target);
        measures[it.previousIndex()][2] += eval.fMeasure(target);
    }
}
