# The major part of this code, def proxy is provided by 0x709394 (https://github.com/fengkx) from https://0xffff.one/d/447
## The key idea behind this design is the callable attribute of object which applies on method/function itself with thorough consideration of form and invocation commands though.

from BankAccount import *

class AccountProxy_Proxy:
    def __init__(self,real_account):
        self.subject = real_account

    def proxy(self, method_name, *argv):
        print("Delegating method: ", method_name)
        try:
            method =self.subject.__getattribute__(method_name)
        except:
            method = None
        if callable(method):
            method(*argv)
        else:
            raise Exception(f"{method_name} is not callable")



# driver
ap= AccountProxy_Proxy(BankAccount("Shawn"))

ap.proxy('deposit',22)
ap.proxy('printBalance')
ap.proxy('calculateInterest') #will raise an error
