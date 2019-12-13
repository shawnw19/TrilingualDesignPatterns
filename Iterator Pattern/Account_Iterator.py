class Account:
    # global name, balance
    def __init__(self,name,balance):
        self.name=name
        self.balance=balance

    def compare(self,account):
        if self.balance<account.balance:
            return -1
        elif self.balance==account.balance:
            return 0
        else:
            return 1

class Portforlio():

    def __init__(self):
        self.accounts = []

    def add_account(self,account):
        self.accounts.append(account)

    def each(self,block):
        ## a old-fashioned way
        # i=0
        # while i<len(self.accounts):
        #     block(self.accounts[i])
        #     i=i+1
        for account in self.accounts:
            block(account)


#driver
act1=Account("Joe",300)
act2=Account("Fay",350)
my_portforio = Portforlio()
my_portforio.add_account(act1)
my_portforio.add_account(act2)

def print_account(account):
    print("Account owner:",account.name,"balance:",account.balance)

my_portforio.each(print_account)