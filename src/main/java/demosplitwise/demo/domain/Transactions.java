package demosplitwise.demo.domain;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Transactions class mapped to transactions to store the
 * information about the transfer between users
 */
@Entity
@Table(name="transactions")
public class Transactions implements Serializable{
    public static final long serialVersionUID = -3009157732242239406L;

    /**
     * Unique id for each transaction to distinguish the transactions
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transId")
    private long transID;

    /**
     * Getter method for getting the id of the transaction
     * @return Transaction id of the transaction
     */
    public long getTransID() {
        return transID;
    }

    /**
     * field mapped to attribute groupId in transaction row to specify the group
     * in which the transaction is happening
     */
    @Column(nullable = true,name="groupId")
    private Long groupId;


    /**
     * Getter method for groupId
     * @return groupId in Long data type
     */
    public Long getGroupId() {
        return groupId;
    }

    /**
     * Setter for the groupId
     * @param groupId Long group Id
     */
    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }


    /**
     * String field to contain description about a transaction
     */
    @Column(name = "Description")
    String description;

    /**
     * Getter for description of a transaction
     * @return String description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for description of a transaction
     * @param description of a transaction
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * Field to store amount transferred in a transaction
     */
    @Column(name = "Transaction_Amount")
    int amount;

    /**
     * Setter to set the amount in a transaction
     * @param amount involved in a transaction
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Getter to get the amount
     * @return amount
     */
    public int getAmount() {
        return amount;
    }


    /**
     * Field to specify the mode of payment for a transaction
     */
    @Column(name="Mode_of_Payment")
    String mop;

    /**
     * Getter to get the mode of payment
     * @return Mode of payment
     */

    public String getMop() {
        return mop;
    }

    /**
     * Setter to set the Mode of payment for a transaction
     * @param mop
     */
    public void setMop(String mop) {
        this.mop = mop;
    }

    /**
     * Field to contain the date on which transaction is made
     */
    @Column(name = "Date_of_Transaction",updatable = false)
    private Date dot= new Date();


    /**
     * Field to contain the date on which transaction is last modified
     */
    @Column(name = "Date_of_Modification")
    private Date dom = new Date();

    @PreUpdate
    public void setLastUpdate() { this.dom = new Date();}

    /**
     * list users who lended the money in the transaction
     */
    @Transient
    public List<Long> lender = new ArrayList<Long>();

    /**
     * list users who borrowed the money in the transaction
     */
    @Transient
    public List<Long> borrower = new ArrayList<Long>();


    /**
     * protected constructor used by the Spring jpa
     */
    protected Transactions(){}


    /**
     * public constructor for creating instances
     * @param description transaction information
     * @param amount money involved in the transaction
     * @param lender list of all the lenders user ids
     * @param borrower list of all the borrower user ids
     * @param mop mode of payment of the transaction
     */
    public Transactions(String description, int amount,List<Long> lender, List<Long> borrower,String mop){

      this.groupId = groupId;
        this.description = description;
        this.amount = amount;
        this.mop = mop;
        this.lender = lender;
        this.borrower = borrower;
    }

    /**
     * Method to convert the transaction into a string
     * @return Details of a transaction in a string format
     */
    @Override
    public String toString(){
        return String.format("Transactions[transid=%d,description=%s " +
                "Amount=%d, Mode of payment=%s]",transID,description,amount,mop );
    }



}
