package demosplitwise.demo.domain;


import javax.persistence.*;
import java.io.Serializable;

/**
 * User entity having primary key as userId, and other attributes userName, emailId, phoneNumber,
 * age, debt
 */
@Entity
@Table(name="splitwiseusers")
public class User implements Serializable{
    //private static final long serialVersionUID = -3009157732242249406L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="userId")
    private long userId;

    /**
     * getter for userId
     * @return long UserId
     */
    public long getUserId() {
        return userId;
    }

    /**
     * setter for userId
     * @param userId long UserId
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Column(name="userName", unique = true)
    private String name;

    /**
     * getter for Name
     * @return String Name
     */
    public String getName() {
        return name;
    }

    /**
     * setter for name
     * @param name String Name
     */
    public void setName(String name) {
        this.name = name;
    }




    @Column(name="emailId", unique = true)
    private String emailId;

    /**
     * getter for EmailId
     * @return String EmailId
     */
    public String getEmailId() {
        return emailId;
    }

    /**
     * setter for emailId
     * @param emailId String emailId
     */
    public void setEmailID(String emailId) {
        this.emailId = emailId;
    }

    @Column(name="phoneNumber")
    private String phoneNumber;

    /**
     * getter phoneNumber
     * @return String phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * setter for phoneNumber
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name="age")
    private int age;

    /**
     * getter for age
     * @return String age
     */
    public int getAge() {
        return age;
    }

    /**
     * setter for age
     * @param age String age
     */
    public void setAge(int age) {
        this.age = age;
    }

    @Column(name="debt")
    private double debt;

    /**
     * getter debt
     * @return int debt
     */
    public double getDebt() {
        return Math.round(debt*100.00)/100.00;
    }

    /**
     * setter debt
     * @param debt int debt
     */
    public void setDebt(double debt) {
        this.debt = debt;
    }

    @Column(name="displaypicture")
    private String url;

    /**
     * Getter for url of the user image
     * @return url of the image
     */
    public String getUrl() {
        return url;
    }

    /**
     * Setter for url of the user image
     * @param url - String of the url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    protected User(){}

    /**
     * public constructor for User
     * @param name
     * @param emailId
     * @param phone_number
     * @param age
     */
    public User(String name,String emailId,String phone_number,int age){
            this.name=name;
            this.emailId=emailId;
            this.phoneNumber=phone_number;
            this.age=age;
            this.debt= 0;
            this.url = "https://lh3.googleusercontent.com/EV-uBBMTPwvvEhdjSkfHpKs0ONPOc2DzihERK-2E2yA";
    }

    /**
     * Overriden toString method to convert object to string
     * @return String details of User
     */
    @Override
    public String toString(){
        return String.format("User[userid=%d,name=%s, emailid=%s, phone_number=%s, age=%d, debt=%d]",userId,name,
                emailId,phoneNumber,age,debt);
    }
}