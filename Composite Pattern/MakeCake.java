package Task_Composite;

public class MakeCake {
    public static void main(String[] args) {
        //generate objects of leaves for MakeCake
        Task addDryIngredientsTask= new Task("Add dry ingredients");
        addDryIngredientsTask.setRequiredTime(1.0);
        Task addLiquids = new Task("Add Liquids");
        addLiquids.setRequiredTime(1.0);
        Task mixTask = new Task("Mix that batter up");
        mixTask.setRequiredTime(3.0);

        //create first composite task
        CompositeTask makeBatterTask = new CompositeTask("Make batter");
        makeBatterTask.addSubTask(addDryIngredientsTask);
        makeBatterTask.addSubTask(addLiquids);
        makeBatterTask.addSubTask(mixTask);

        System.out.println("Total required time for making the batter is: "+makeBatterTask.getRequiredTime());

        //create another composite task that is composed of another three leaves
        Task filllPan= new Task("Fill in the pan");
        filllPan.setRequiredTime(0.5);
        Task bakeAndFrost =new Task("Bake the bread and frost it");
        bakeAndFrost.setRequiredTime(30);
        CompositeTask afterBatterMade = new CompositeTask("other tasks after the batter is made");
        afterBatterMade.addSubTask(filllPan);
        afterBatterMade.addSubTask(bakeAndFrost);

        System.out.println("Time required after batter is made: "+afterBatterMade.getRequiredTime());

        //combine makeBatter and otherTasks into makeCake
        CompositeTask makeCake = new CompositeTask("Make a cake");
        makeCake.addSubTask(makeBatterTask);
        makeCake.addSubTask(afterBatterMade);

        System.out.println("Total required time for making the whole cake is: "+makeCake.getRequiredTime());
    }
}
