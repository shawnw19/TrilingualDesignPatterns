require 'find'

# suppose there is an abstract class/interface
# called "Expression" and all query classes are
# its subclasses or implement it
class All
  def evaluate(dir)
    results=[]
    Find.find(dir) do |p|
      next unless File.file?(p)
      results << p
    end
    results
  end
end

class FileName
  def initialize(pattern)
    @pattern = pattern
  end

  def evaluate(dir)
    results=[]
    Find.find(dir) do |p|
      next unless File.file?(p)
      name = File.basename(p) #without path
      results << p if File.fnmatch(@pattern,name)
    end
    results
  end
end

class Bigger
  def initialize(size)
    @size = size
  end

  def evaluate(dir)
    results=[]
    Find.find(dir) do |p|
      next unless File.file?(p)
      results << p if (File.size(p) > @size)#unit is byte
    end
    results
  end
end

class Writable
  def evaluate(dir)
    results=[]
    Find.find(dir) do |p|
      next unless File.file?(p)
      results << p if File.writable?(p)
    end
    results
  end
end

class Not
  def initialize(expression)
    @expression = expression #a search class
  end

  def evaluate(dir)
    All.new.evaluate(dir) - @expression.evaluate(dir)
  end
end

class Or
  def initialize(expression1, expression2)
    @expression1 = expression1
    @expression2 = expression2
  end

  def evaluate(dir)
    result1= @expression1.evaluate(dir)
    result2= @expression2.evaluate(dir)
    (result1 + result2).sort.uniq
  end
end

class And
  def initialize(expression1, expression2)
    @expression1 = expression1
    @expression2 = expression2
  end

  def evaluate(dir)
    result1= @expression1.evaluate(dir)
    result2= @expression2.evaluate(dir)
    (result1 & result2)
  end
end

# driver

find1= FileName.new('*.mp3')
results1= find1.evaluate("Files")
puts "All mp3 files:"
puts results1
puts

find2= Not.new(Writable.new)
results2 = find2.evaluate("Files")
puts "Read-only file(s): "
puts results2
puts

find3= Not.new(Bigger.new(1024))
results3= find3.evaluate("Files")
puts "File(s) not bigger than 1k:"
puts results3
puts

find4= Or.new(Bigger.new(1024),FileName.new("*.mp3"))
results4 = find4.evaluate("Files")
puts "File(s) bigger than 1k or of mp3 format:"
puts results4
puts

find5= And.new(And.new(Bigger.new(1024),FileName.new("*.mp3")),Not.new(Writable.new))
results5 = find5.evaluate("Files")
puts "File(s) bigger than 1k, of mp3 format and not writable:"
puts results5
