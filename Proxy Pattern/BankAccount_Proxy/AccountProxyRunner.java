package BankAccount_Proxy;

public class AccountProxyRunner {
    public static void main(String[] args) {
        AccountProxy firstAccount = new AccountProxy(new BankAccount("Shawn"));
        firstAccount.deposit(35.0);
        firstAccount.withdraw(12.3);//through e-wallet
        System.out.println("The balance is: "+ firstAccount.getBalance());
    }
}
