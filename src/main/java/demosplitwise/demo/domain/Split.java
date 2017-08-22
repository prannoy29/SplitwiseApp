package demosplitwise.demo.domain;

/**
 * Created by zemoso on 22/8/17.
 */
public class Split {

    private long creditorId;
    private long debtorId;
    private double amount;

    public long getCreditorId() {
        return creditorId;
    }

    public void setCreditorId(long creditorId) {
        this.creditorId = creditorId;
    }

    public long getDebtorId() {
        return debtorId;
    }

    public void setDebtorId(long debtorId) {
        this.debtorId = debtorId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
