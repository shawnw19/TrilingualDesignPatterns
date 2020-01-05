# an implementation of class-based singleton pattern

class ClassBasedLogger
  ERROR=0
  WARNING=2
  INFO=3

  ## double at symbol declares class variable like "static" pp208
  @@log= File.open('log.txt','w')
  @@level= WARNING

  def self.error(msg)
    @@log.puts(msg)
    @@log.flush
  end

  def self.warning(msg)
    @@log.puts(msg) if @@level >= WARNING
    @@log.flush
  end

  def self.info(msg)
    @@log.puts(msg) if @@level >= INFO
    @@log.flush
  end

  def self.level=(new_level)
    @@level= new_level
  end

  def self.level
    @@level ##returns it
  end

end


## driver

ClassBasedLogger.level = ClassBasedLogger::INFO

ClassBasedLogger.info('Computer wins chess game.')
ClassBasedLogger.warning('AE-35 hardware failure predicted.')
ClassBasedLogger.error('HAL-900 malfunction, take emergency action!')
