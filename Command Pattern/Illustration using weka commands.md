### Weka is written in Java,
#### and I chose to use it to illustrate an implementation of command pattern in Machine Learning.
#### The nitty-gritty of Command Pattern is to seprate the commands from the bodies of them. E.g. the behaviour of button vs button themselves.
#### Is there some popular yet nontrivial usage of such pattern that silhouettes certain level of technical subtleness? Yes, of course, ML is an example: there are lots of commands for data processing and they are all encapsulted in different classes. The body/target of these commands, the data model is separated from these operations.
#### See commands like Filter.useFilter() and objects created using classes of CSVLoader, Instances, Remove, Classifier, StratifiedRemoveFolds, Resample etc: Classifier model is in the centre with commands like remove, stratified validation.

#### In the file Fraud that is based on same file in book Machine Learning in Java 2015, I further tried to encapsulate a single command [1]"print the evaluation results" and a compound command "iterate the models, do[1] and save the results" into methods within a seprate class. This is a trial and it's up to you to decide whether it is a good example or parody.
Other technique details include lexical scoping as there are methods inside a method. It is worth mentioned again that this is an example and in actual job using programming langauges in such a subtle way is not good because of lacking of maintainability and sometimes readibility.
