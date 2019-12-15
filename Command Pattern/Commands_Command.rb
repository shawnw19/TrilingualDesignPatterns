require 'fileutils'

class Command
  attr_reader :description

  def initialize(description)
    @description = description
  end

  def execute
    #to be overloaded
  end
end

class CreateFile < Command
  def initialize(path, contents)
    super ("Create file: #{path}")
    ## it is the description that is supered
    @path = path
    @contents =contents
  end

  def execute
    f= File.open(@path,"w")
    f.write(@contents)
    f.close
  end
end

class DeleteFile < Command
  def initialize(path)
    super ("Delete file: #{path}")
    @path = path
  end

  def execute
    File.delete(@path)
  end
end



class CopyFile < Command
  def initialize(source, target)
    super ("Copy file: #{source} to #{target}")
    @source =source
    @target = target
  end

  def execute
    FileUtils.copy(@source,@target)
  end
end

class CompositeCommand <Command
  def initialize()
    @commands = []
  end

  def add_command(cmd)
    @commands <<cmd
  end

  def execute
    @commands.each { |cmd| cmd.execute}
  end

  def description
    description = ''
    @commands.each { |cmd| description += cmd.description + "\n"}
    description
  end
end

##driver
cmds = CompositeCommand.new
cmds.add_command(CreateFile.new("file1.txt","hello Ruby\n"))
cmds.add_command(CopyFile.new('file1.txt','file2.txt'))
cmds.add_command(DeleteFile.new('file1.txt'))

cmds.execute

puts(cmds.description)