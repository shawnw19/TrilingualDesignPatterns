package Task_Composite;

import java.util.Vector;

public class CompositeTask extends Task {
    Vector<Task> subTasks;

    String name;

    public CompositeTask(String name) {
        super(name);
        subTasks = new Vector();
    }

    public void addSubTask(Task task) {
        subTasks.add(task);
//        task.parent=  ;
        requiredTime +=task.getRequiredTime();
    }

    public void removeSubTask(Task task) {
        subTasks.remove(task);
//        task.parent=null;
    }

    /*the following two methods are considered as failed.
    They both used a loop to calculate the total time of the composite task from each required time of subtasks.
    The problem is the result is inevitably a double of the correct answer if the depth of the composite task is two (it becomes larger multiples as the depth grows) because each layer of subtasks is recalculated during the loop*/

/*    @Override
    public double getRequiredTime() {
        for (Task task : subTasks)
            requiredTime +=task.getRequiredTime();
        return requiredTime;
    }*/

/*    public double getRequiredTime() {
        for (int i = 0; i < subTasks.size(); i++) {
            requiredTime += subTasks.elementAt(i).getRequiredTime();
        }
        return requiredTime;
    }*/
}
