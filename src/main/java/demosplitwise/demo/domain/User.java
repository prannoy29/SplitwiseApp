package demosplitwise.demo.domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="splitwiseusers")
public class User implements Serializable{
    private static final long serialVersionUID = -3009157732242249406L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="UserId", unique = true)
    private long userId;

    @Column(name="userName",unique = true)
    private String name;

    @Column(name="emailId")
    private String emailId;

    @Column(name="phoneNumber")
    private String phoneNumber;

    @Column(name="age")
    private int age;

    @Column(name="debt")
    private int debt;

    protected User(){}

    public User(String name,String emailid,String phone_number,int age){
        this.name=name;
        this.emailId=emailid;
        this.phoneNumber=phone_number;
        this.age=age;
    }

    public long getUserid() {
        return userId;
    }

    public void setUserid(long userid) {
        this.userId = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailID() {
        return emailId;
    }

    public void setEmailID(String emailID) {
        this.emailId = emailID;
    }

    public String getPhone_number() {
        return phoneNumber;
    }

    public void setPhone_number(String phone_number) {
        this.phoneNumber = phone_number;
    }



    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getDebt() {
        return debt;
    }

    public void setDebt(int debt) {
        this.debt = debt;
    }

    @Override
    public String toString(){
        return String.format("User[userid=%d,name=%s, emailid=%s, phone_number=%s, age=%d, debt=%d]",userId,name,
                emailId,phoneNumber,age,debt);
    }
}