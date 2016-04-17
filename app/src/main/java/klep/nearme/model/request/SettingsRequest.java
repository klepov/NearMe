package klep.nearme.model.request;

/**
 * Created by klep on 17.04.16 with love.
 */
public class SettingsRequest {
    private int ageFrom;
    private int ageTo;
    private int myAge;
    private int sexNeed;

    public SettingsRequest(int ageFrom, int ageTo, int myAge, int sexNeed) {
        this.ageFrom = ageFrom;
        this.ageTo = ageTo;
        this.myAge = myAge;
        this.sexNeed = sexNeed;
    }


    public int getAgeFrom() {
        return ageFrom;
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
