package Task_Composite;

public class Task {
    String name;
    double requiredTime;
//    Task parent;

    public Task(String name) {
        this.name = name;
//        parent=null;
        requiredTime=0;
    }

    public double getRequiredTime() {
        return requiredTime;
    }

    public void setRequiredTime(double requiredTime) {
        this.requiredTime = requiredTime;
    }
}
