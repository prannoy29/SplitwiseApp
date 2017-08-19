package demosplitwise.demo.domain;


import javax.persistence.*;

@Entity
@Table(name = "settleupdata")
public class SettleUp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name="groupId")
    private long groupId;

    public long getUserIdCreditor() {
        return userIdCreditor;
    }

    @Column(name = "UserCreditor")
    private long userIdCreditor;

    @Column(name = "UserIdDebtor")
    private long userIdDebtor;

    @Column(name = "AmountOwed")
    private double amountOwed;

    protected SettleUp(){}

    public SettleUp(long userIdCreditor,long userIdDebtor,double amountOwed){
        this.userIdCreditor = userIdCreditor;
        this.userIdDebtor = userIdDebtor;
        this.amountOwed = amountOwed;
    }

}
