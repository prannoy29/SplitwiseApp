package demosplitwise.demo.domain;



import javax.persistence.*;


@Entity
@Table(name = "User_Transaction")
public class UserTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;

    public long getId() {
        return id;
    }

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

    public double getPartialAmount() {
        return Math.round(partialAmount*100.00)/100.00;
    }

    public void setPartialAmount(double partialAmount) {
        this.partialAmount = partialAmount;
    }
    @Column(name = "transId")
    private long transID;

    @Column(name="userId")
    private long userId;

    @Column(name="groupId",nullable = false)
    private Long groupId;

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public long getGroupId() {
        return groupId;
    }

    @Column(name = "partial_amount")
    double partialAmount;

    protected UserTransaction(){
    }

    public UserTransaction (long groupId,long transID,long userId,double partialAmount){

        this.groupId = groupId;
        this.transID=transID;
        this.userId = userId;
        this.partialAmount = partialAmount;
    }
}
