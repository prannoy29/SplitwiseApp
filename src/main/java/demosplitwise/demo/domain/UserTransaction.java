package demosplitwise.demo.domain;



import javax.persistence.*;


@Entity
@Table(name = "User_Transaction")
public class UserTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;

    public long getTransID() {
        return transID;
    }

    public void setTransID(long transID) {
        this.transID = transID;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getPartialAmount() {
        return partialAmount;
    }

    public void setPartialAmount(int partialAmount) {
        this.partialAmount = partialAmount;
    }
    @Column(name = "transId")
    private long transID;

    @Column(name="userId")
    private long userId;

    @Column(name = "partial_amount")
    int partialAmount;

    protected UserTransaction(){
    }

    public UserTransaction (long transID,long userId,int partialAmount){
        this.transID=transID;
        this.userId = userId;
        this.partialAmount = partialAmount;
    }
}
