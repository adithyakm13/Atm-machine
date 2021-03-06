import java.util.ArrayList;

public class Account {
    //acc name,id,acc holder and transactions
    private String name;
    private String uuid;
    private User holder;
    private ArrayList<Transaction> transactions;

    public Account(String name,User holder,Bank theBank){
        //set account name and holder
        this.name = name;
        this.holder = holder;

        //get new account uuid
        this.uuid = theBank.getNewAccountUUID();

        //init transactions
        this.transactions = new ArrayList<Transaction>();


    }

    public String getUUID() {
        return this.uuid;
    }

    public String getSummaryLine() {
        //get account's balance
        double balance = this.getBalance();

        //format the summary line , depending on the whether the balance is negative
        if(balance>=0){
            return String.format("%s : Rs%.02f : %s",this.uuid,balance,this.name);
        }
        else{
            return String.format("%s : Rs(%.02f) : %s",this.uuid,balance,this.name);

        }
    }

    double getBalance() {
        double balance = 0;
        for(Transaction t:transactions){
            balance+=t.getAmount();
        }
        return balance;
    }

    public void printTransHistory() {
        System.out.printf("\n Transaction history for account %s\n",this.uuid);
        for (int i = this.transactions.size()-1; i>=0 ; i--) {
            System.out.println(this.transactions.get(i).getSummaryLine());
        }
        System.out.println();
    }

    public void addTransaction(double amount, String memo) {
        // create new transaction object and add it to our list

        Transaction newTrans  = new Transaction(amount,memo,this);
        this.transactions.add(newTrans);
    }
}
