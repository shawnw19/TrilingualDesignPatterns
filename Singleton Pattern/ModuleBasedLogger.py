# There is no counterpart of Ruby Module in Python! I used non-encapsulation in a class to imitate the module in Ruby
# which includes the variable and method but can never be initialised.

class ModuleBasedLogger:
    def __init__(self):
        self.ERROR = 0
        self.WARNING = 2
        self.INFO = 3
        self.log=open('log.txt','w')
        self.level=self.WARNING

    def error(self,msg):
        self.log.write(msg)

## driver

ModuleBasedLogger().error('Hey, lay, hey!!')