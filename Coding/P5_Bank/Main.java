package P5_Bank;

public class Main {
    public static void main(String[] args){
        BankAccount account = new BankAccount(1000);
        try{
            ATM.processWithdrawal(account,500);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try{
            ATM.processWithdrawal(account,1500);
        } catch (Exception e) {
            System.out.println("[ERROR] " + e.getMessage());
        }

    }
}
