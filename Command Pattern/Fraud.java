package Chapter7;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.Logistic;
import weka.classifiers.meta.AdaBoostM1;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.filters.Filter;
import weka.filters.supervised.instance.Resample;
import weka.filters.supervised.instance.StratifiedRemoveFolds;
import weka.filters.unsupervised.attribute.NumericToNominal;
import weka.filters.unsupervised.attribute.Remove;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class Fraud {
    public static void main(String[] args) throws Exception {

        String filePath = "data/claims.csv";

        CSVLoader loader = new CSVLoader();
        loader.setFieldSeparator(",");
        loader.setSource(new File(filePath));
        Instances data = loader.getDataSet();

        /*
        configure the dataset
         */

        //attribute indices
        int CLASS_INDEX = 15;//fraud or not labelled
        int POLICY_INDEX = 17;
        int NO_FRUAD = 0, FRAUD = 1;
        int FOLDS = 3;

        //convert all to nominal
        NumericToNominal toNominal = new NumericToNominal();
        toNominal.setInputFormat(data);
        data = Filter.useFilter(data, toNominal);

        //set class index
        data.setClassIndex(CLASS_INDEX);

        //remove policy attribute (unrelevant)
        Remove remove = new Remove();
        remove.setInputFormat(data);
        remove.setOptions(new String[]{"-R", "" + POLICY_INDEX});//https://weka.sourceforge.io/doc.dev/weka/filters/unsupervised/attribute/Remove.html#setOptions-java.lang.String:A- //
        //Policy index is passed in a string
        data = Filter.useFilter(data, remove);

        //print the attributes
        System.out.println(data.toSummaryString());
        System.out.println("Class attribute: \n" + data
                .attributeStats(data.classIndex()));

        /*
        Vanilla approach
         */
        List<Classifier> models = new ArrayList<Classifier>();
        models.add(new J48());
        models.add(new RandomForest());
        models.add(new NaiveBayes());
        models.add(new AdaBoostM1());
        models.add(new Logistic());

        //evaluation section
        Evaluation eval = new Evaluation(data);
        System.out.println("Vanilla approach\n-----------");
        for (Classifier model :
                models) {
            eval.crossValidateModel(model, data, FOLDS, new Random(1), new String[]{});
            //call class-ised/object-embodied command
            OutputEvaluation.print(model, eval, FRAUD);
        }

        /*
        Perform manual k-fold cross-validation
         */
        StratifiedRemoveFolds kFold = new StratifiedRemoveFolds();
        kFold.setInputFormat(data);

        double measures[][] = new double[models.size()][3];
        System.out.println("\nData rebalancing\n----------");

        for (int k = 1; k <= FOLDS; k++) {
            //split data into test and train folds
            //https://weka.sourceforge.io/doc.dev/weka/filters/supervised/instance/StratifiedRemoveFolds.html#setOptions-java.lang.String:A-
            kFold.setOptions(new String[]{"-N", ""+ FOLDS, "-F", ""+ k, "-S", "1"});
            Instances test = Filter.useFilter(data, kFold);
            kFold.setOptions(new String[]{"-N", "" + FOLDS, "-F", "" + k, "-S", "1", "-V"});// inverse "-V"
            Instances train = Filter.useFilter(data, kFold);
            System.out.println("Fold " + k + "\n\ttrain: " + train.size() + "\n\ttest: " + test.size());

            //re-balance train dataset
            Resample resample = new Resample();
            resample.setInputFormat(data);
            resample.setOptions(new String[]{"-Z", "100", "-B", "1"});//with replacement
            Instances balancedTrain = Filter.useFilter(train, resample);
            //a super compound command
            OutputEvaluation.interateModels(models, balancedTrain, test, eval, FRAUD, measures);
            for (ListIterator<Classifier> it = models.listIterator(); it.hasNext(); ) {
                Classifier model = it.next();
                model.buildClassifier(balancedTrain);
                eval = new Evaluation(balancedTrain);
                eval.evaluateModel(model, test);

//                OutputEvaluation.print(model, eval, FRAUD);
                //
//                measures[it.previousIndex()][0] += eval.recall(FRAUD);
//                measures[it.previousIndex()][1] += eval.precision(FRAUD);
//                measures[it.previousIndex()][2] += eval.fMeasure(FRAUD);
            }
        }

        // calculate average
        for(int i = 0; i < models.size(); i++){
            measures[i][0] /= 1.0 * FOLDS;
            measures[i][1] /= 1.0 * FOLDS;
            measures[i][2] /= 1.0 * FOLDS;
        }

        //out put results and select best model
        Classifier bestModel = null;
        double bestScore = -1;
        for (ListIterator<Classifier> it = models.listIterator(); it.hasNext(); ) {
            Classifier model = it.next();
            double fMeasure = measures[it.previousIndex()][2];
            System.out.println(
                    model.getClass().getName() + "\n" +
                            "\tRecall:    " + measures[it.previousIndex()][0] + "\n" +
                            "\tPrecision: " + measures[it.previousIndex()][1] + "\n" +
                            "\tF-measure: " + fMeasure);
            if (fMeasure > bestScore) {
                bestScore = fMeasure;
                bestModel = model;
            }
        }

        System.out.println("Best model: "+bestModel.getClass().getName());

    }
}
