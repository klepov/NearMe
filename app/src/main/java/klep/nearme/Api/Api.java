package klep.nearme.Api;

import klep.nearme.model.ErrorCode;
import klep.nearme.model.Person;
import klep.nearme.model.Persons;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by klep.io on 25.03.16.
 */
public interface Api {
    @FormUrlEncoded
    @POST("auth/")
    Observable<ErrorCode> auth(@Field("token") String token,
                               @Field("latitude") double latitude,
                               @Field("longitude") double longitude);

    @FormUrlEncoded
    @POST("get_people/")
    Observable<Persons> getPeople(@Field("token") String token);

    @FormUrlEncoded
    @POST("update_filter/")
    Observable<ErrorCode> updateFilter(@Field("token") String token,
                                       @Field("age_from") int ageFrom,
                                       @Field("age_to") int ageTo,
                                       @Field("my_age") int myAge,
                                       @Field("sex_want") int sexNeed,
                                       @Field("wish") String wish);


    @FormUrlEncoded
    @POST("auth/")
    Observable<Object> updateLocation(@Field("token") String token,
                                      @Field("latitude") float latitude,
                                      @Field("longitude") float longitude);



    @FormUrlEncoded
    @POST("get_my_profile/")
    Observable<Person> getMyProfile(@Field("token") String token);


    @FormUrlEncoded
    @POST("wish/execute_wish/")
    Observable<ErrorCode> executeWish(@Field("token") String token,
                                      @Field("user_id") int idVk);

    @FormUrlEncoded
    @POST("wish/black_list/")
    Observable<ErrorCode> addBlackList(@Field("token") String token,
                                       @Field("user_id") int idVk);

    @FormUrlEncoded
    @POST("get_my_execute_wish/")
    Observable<Persons> getExecutWishList(@Field("token") String token);

}
