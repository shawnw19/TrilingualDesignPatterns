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
