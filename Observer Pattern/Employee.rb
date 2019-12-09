require "observer"

class Employee
  include Observable

  attr_reader  :name, :title
  attr_reader  :salary

  def initialize(name, title, salary)
    @name =name
    @title =title
    @salary = salary
  end

  def salary=(new_salary)
    old_salary = @salary
    @salary = new_salary
    if old_salary!= new_salary
    changed
    end
  end

  def title=(new_title)
    old_title = @title
    @title = new_title
    if old_title!= new_title
      changed
    end
  end


  def changes_complete
    notify_observers(self)
  end
end

class Payroll
  def update(changed_employee)
    puts("Cut a new check for #{changed_employee.name}!")
    puts("His salary is now #{changed_employee.salary}!")
  end
end

class HR
  def update(changed_employee)
    puts("The position of #{changed_employee.name} has been changed to")
    puts("#{changed_employee.title}!")
  end
end

fred = Employee.new('Fred','Crane Operator',3000)
payroll =Payroll.new
hr =HR.new
fred.add_observer(payroll)
fred.add_observer(hr)
fred.salary=3500

fred.title ="Vice President of Sales"
fred.salary=4000

#fred.title ="Crane Operator"
fred.changes_complete