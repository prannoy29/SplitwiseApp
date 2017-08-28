package demosplitwise.demo.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zemoso on 9/8/17.
 */
@Entity
@Table(name = "usergroups")
public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "group_id")
    private long groupId;

    @Column(name = "user_id")
    private long userId;

    @Transient
    private long transId;

    public long getTransId() {
        return transId;
    }

    @Column(name = "date_of_joining")

    private Date doj;

    @Column(name = "group_debt")
    private double debt;



    protected UserGroup() {

    }

    public UserGroup(long groupId, long userId, Date doj, double debt) {
        this.groupId = groupId;
        this.userId = userId;
        this.doj = doj;
        this.debt = debt;
    }

    public long getUid() {
        return userId;
    }

    public void setUid(long uid) {
        this.userId = uid;
    }

    public long getGid() {

        return groupId;
    }

    public void setGid(long gid) {
        this.groupId = gid;
    }

    public Date getDoj() {
        return doj;
    }

    public void setDoj(Date doj) {
        this.doj = doj;
    }

    public double getDebt() {
        return Math.round(debt*100.00)/100.00;    }

    public void setDebt(double debt) {
        this.debt = debt;
    }

    @Override
    public String toString() {
        return "UserGroup{" +
                "gid=" + groupId +
                ", uid=" + userId +
                ", doj=" + doj +
                ", debt=" + debt +
                '}';
    }
}

