package demosplitwise.demo.domain;

/**
 * Created by zemoso on 22/8/17.
 */
public class Split {

    private long creditorId;
    private String creditorName;
    private long debtorId;
    private String debtorName;
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

    public String getCreditorName() {
        return creditorName;
    }

    public void setCreditorName(String creditorName) {
        this.creditorName = creditorName;
    }

    public String getDebtorName() {
        return debtorName;
    }

    public void setDebtorName(String debtorName) {
        this.debtorName = debtorName;
    }
}
