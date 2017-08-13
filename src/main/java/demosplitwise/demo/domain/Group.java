package demosplitwise.demo.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="splitwisegroup")
public class Group implements Serializable {
    public static final long serialVersionUID = -3109157732242239406L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "group_id")
    private long groupId;

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    @Column(name = "group_name")
    private String groupName;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }


    @Column(name = "date_of_creation",updatable = false)
    private Date dateOfCreation = new Date();

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    //public void setDateOfCreation(Date dateOfCreation) {this.dateOfCreation = dateOfCreation;}


    @Column(name = "date_of_update")
    private Date dateOfUpdate= new Date();

    @PreUpdate
    public void setLastUpdate() {  this.dateOfUpdate = new Date();}

    @Column(name = "Total_Members")
    private int totalMembers;

    public int getTotalMembers() {
        return totalMembers;
    }

    public void setTotalMembers(int totalMembers) {
        this.totalMembers = totalMembers;
    }

    @Column(name = "created_by")
    private String createdBy;


    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }



    protected Group() {
    }

    public Group(String groupName, String createdBy) {
        this.groupName = groupName;
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return String.format("group[groupid=%d,createdBy=%s, Date of Creation=%s]",
                groupId, createdBy, dateOfCreation);
    }

}