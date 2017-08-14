package demosplitwise.demo.domain;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="transactions")
public class Transactions implements Serializable{
    public static final long serialVersionUID = -3009157732242239406L;

    public long getTransID() {
        return transID;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transId")
    private long transID;

    @Column(nullable = true,name="groupId")
    private Long groupId;


    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }


    @Column(name = "Description")
    String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Column(name = "Transaction_Amount")
    int amount;

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    @Column(name="Mode_of_Payment")
    String mop;

    public String getMop() {
        return mop;
    }

    public void setMop(String mop) {
        this.mop = mop;
    }

    @Column(name = "Date_of_Transaction",updatable = false)
    private Date dot= new Date();

    @Column(name = "Date_of_Modification")
    private Date dom = new Date();

    @PreUpdate
    public void setLastUpdate() { this.dom = new Date();}

    @Transient
    public List<Long> lender = new ArrayList<Long>();

    @Transient
    public List<Long> borrower = new ArrayList<Long>();


    protected Transactions(){}

    public Transactions(long groupId,String description, int amount,List<Long> lender, List<Long> borrower,String mop){

        this.groupId = groupId;
        this.description = description;
        this.amount = amount;
        this.mop = mop;
        this.lender = lender;
        this.borrower = borrower;
    }

    @Override
    public String toString(){
        return String.format("Transactions[transid=%d,description=%s " +
                "Amount=%d, Mode of payment=%s]",transID,description,amount,mop );
    }



}
