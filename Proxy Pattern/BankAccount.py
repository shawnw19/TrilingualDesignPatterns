class BankAccount:
    def __init__(self,account_owner):
        self.owner= account_owner
        self.balance=0
    def deposit(self,amount):
        self.balance=self.balance+amount
    def withdraw(self,amount):
        self.balance=self.balance-amount

    def printBalance(self):
        print("The balance: ",self.balance)

