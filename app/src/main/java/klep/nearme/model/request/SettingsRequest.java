package klep.nearme.model.request;

/**
 * Created by klep on 17.04.16 with love.
 */
public class SettingsRequest {
    private int ageFrom;
    private int ageTo;
    private int myAge;
    private int sexNeed;
    private String wish;

    public SettingsRequest(int ageFrom, int ageTo, int myAge, int sexNeed, String wish) {
        this.ageFrom = ageFrom;
        this.ageTo = ageTo;
        this.myAge = myAge;
        this.sexNeed = sexNeed;
        this.wish = wish;
    }


    public int getAgeFrom() {
        return ageFrom;
    }

    public String getWish() {
        return wish;
    }

    public int getAgeTo() {
        return ageTo;
    }

    public int getMyAge() {
        return myAge;
    }

    public int getSexNeed() {
        return sexNeed;
    }
}
