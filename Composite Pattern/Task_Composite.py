import sys
sys.setrecursionlimit(10**9)

class Task:

    def __init__(self,name):
        self.name=name
        self.parent= None

    def get_time_required(self):
        return 0.0


class CompositeTask(Task):
    def __init__(self,name):
        # self.name=name #an alternative for more objective-oriented way of the initialization below
        super().__init__(name)
        self.sub_tasks =[]

    def add_sub_task(self,task):
        self.sub_tasks.append(task)
        task.parent=self

    def get_time_required(self):
        self.time=0.0
        for task in self.sub_tasks:
            self.time = self.time+ task.get_time_required()
        return self.time


class AddDryIngerdientsTask(Task):
    def __init__(self):
        self.name="Add dry ingredients"

    def get_time_required(self):
        return 1.0


class AddLiquidsTask(Task):
    def __init__(self):
        self.name=("Add liquids")

    def get_time_required(self):
        ##demonstrate the usage of non-attribute variable, "time"
        time=0.5
        return time


class MakeBatterTask(CompositeTask):
    def __init__(self):
        self.name=("Make batter")
        self.sub_tasks =[]
        self.add_sub_task(AddDryIngerdientsTask())
        self.add_sub_task(AddLiquidsTask())


class FillPanTask(Task):
    def __init__(self):
        self.name = "Fill the pan!"

    def get_time_required(self):
        return 1.0


class BakeAndFrost(Task):
    def __init__(self):
        self.name= ("Bake the batter and frost it")

    def get_time_required(self):
        return 30.0


class AfterBatterMade(CompositeTask):
    def __init__(self):
        self.name= ("Things after the batter")
        self.sub_tasks = []
        self.add_sub_task(FillPanTask())
        self.add_sub_task(AfterBatterMade())


class MakeCake(CompositeTask):
    def __init__(self):
        self.name= ("Make the whole cake")
        self.sub_tasks = []
        self.add_sub_task(MakeBatterTask())
        self.add_sub_task(AfterBatterMade())

#driver
makeBatter = MakeBatterTask()
print("The time needed to make the baltter is:",makeBatter.get_time_required())

## the following codes cannot be executed due to Python's limitation
makeCake =MakeCake()
print("The time needed to make the whole cake is:",makeCake.get_time_required())




