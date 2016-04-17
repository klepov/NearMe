
package klep.nearme.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Person {

    @SerializedName("photo_max")
    @Expose
    private String photoId;
    @SerializedName("sex")
    @Expose
    private Integer sex;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("last_name")
    @Expose
    private String lastName;

    @SerializedName("first_name")
    @Expose
    private String firstName;

    @SerializedName("interest")
    @Expose
    private Integer interest;

    @SerializedName("group")
    @Expose
    private Integer group;

    @SerializedName("wish")
    @Expose
    private String wish;


    public Integer getInterest() {
        return interest;
    }

    public void setInterest(Integer interest) {
        this.interest = interest;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    /**
     * @return The photoId
     */
    public String getPhotoId() {
        return photoId;
    }

    /**
     * @param photoId The photo_id
     */
    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    /**
     * @return The sex
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * @param sex The sex
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName The last_name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return The firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName The first_name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUserName() {
        return firstName + " " + lastName;
    }

    public String getWish() {
        return wish;
    }

    public void setWish(String wish) {
        this.wish = wish;
    }
}
