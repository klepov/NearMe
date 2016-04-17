
package klep.nearme.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;


@ParcelablePlease()
public class Person implements Parcelable {

    public Person() {

    }

    @SerializedName("photo_max")
    @Expose
    public String photoId;

    @SerializedName("sex")
    @Expose
    public Integer sex;
    @SerializedName("id")
    @Expose
    public Integer userId;
    @SerializedName("last_name")
    @Expose
    public String lastName;

    @SerializedName("first_name")
    @Expose
    public String firstName;

    @SerializedName("interest")
    @Expose
    public Integer interest;

    @SerializedName("group")
    @Expose

    public Integer group;

    @SerializedName("wish")
    @Expose

    public String wish;

    @SerializedName("age")
    @Expose

    public Integer age;


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
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param id The id
     */
    public void setUserId(Integer id) {
        this.userId = id;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAgeAndName() {
        return getUserName() + ", " + getAge() + " лет";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        PersonParcelablePlease.writeToParcel(this, dest, flags);
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        public Person createFromParcel(Parcel source) {
            Person target = new Person();
            PersonParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public Person[] newArray(int size) {
            return new Person[size];
        }
    };


}
