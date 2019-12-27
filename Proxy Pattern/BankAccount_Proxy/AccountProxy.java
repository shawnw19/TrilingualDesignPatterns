package BankAccount_Proxy;

public class AccountProxy {
    private BankAccount realAccount;

    public AccountProxy(BankAccount realAccount) {
        this.realAccount = realAccount;
    }

    public void deposit(double amount){
        realAccount.deposit(amount);
    }

    public double getBalance() {
        return realAccount.getBalance();
    }

    public void withdraw(double amount) {
        realAccount.withdraw(amount);
    }

}
