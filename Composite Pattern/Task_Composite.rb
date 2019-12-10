class Task
  attr_accessor  :name, :parent

  def initialize(name)
    @name=name
    @parent =nil
  end

  def get_time_required
    0.0
  end
end


class AddDryIngerdientsTask <Task
  def initialize()
    super('Add dry ingredients')
  end

  def get_time_required
    1.0
  end
  end

class AddLiquidsTask <Task
  def initialize()
    super('Add liquids')
  end

  def get_time_required
    0.5
  end
  end


class MixTask <Task
  def initialize()
    super('Mix that batter up!')
  end

  def get_time_required
    3.0
  end
end

class CompositeTask < Task
  def initialize(name)
    super(name)
    @sub_tasks =[]
  end

  def add_sub_task(task)
    # double less than sign "<<" means to append a list in Ruby
    # actually it is also able to be override by new defined semantics to replace "add_sub_task"
    @sub_tasks << task
    task.parent=self
  end

  def remove_sub_task(task)
    @sub_tasks.delete(task)
    task.parent =nil
  end
  def get_time_required
    time =0.0
    @sub_tasks.each { |task|time += task.get_time_required}
    time
  end
end

class MakeBatterTask < CompositeTask
  def initialize
    super("Make batter")
    add_sub_task(AddDryIngerdientsTask.new)
    add_sub_task(AddLiquidsTask.new)
    add_sub_task(MixTask.new)
  end
end

class FillPan <Task
  def initialize()
    super('Fill the pan!')
  end

  def get_time_required
    1.0
  end
end

class BakeAndFrost < Task
  def initialize()
    super('Bake the batter and frost it')
  end

  def get_time_required
    30.0
  end
end


class AfterBatterMade < CompositeTask
  def initialize()
    super("Things after the batter")
    add_sub_task(FillPan.new)
    add_sub_task(BakeAndFrost.new)
  end
end


class MakeCake <CompositeTask
  def initialize
    super ("Make cake")
    add_sub_task(MakeBatterTask.new)
    add_sub_task(AfterBatterMade.new)
  end
end


##driver

composite1 = MakeBatterTask.new
puts("Time required for making the batter is: #{composite1.get_time_required}.")

composite2 = MakeCake.new
puts("Time required for making the whole cake is: #{composite2.get_time_required}.")