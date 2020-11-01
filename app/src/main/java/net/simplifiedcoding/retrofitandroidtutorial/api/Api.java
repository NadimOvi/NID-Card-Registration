package net.simplifiedcoding.retrofitandroidtutorial.api;


import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {

    //Register
    @POST("api/CreateUser?code=i/kyTGtfacscDrfRGXnWjUXiRszKxNr1R3WpeYdWmV62xEft2OqJxg==")
    Call<JsonObject> postData(@Body JsonObject ussdObject);

    //Login
    @POST("api/StartSession?code=aC5zctuH0EC1ZbL401z3V5e2rLNPBalZs2q7j0XQIujKhJttuEVn8A==")
    Call<JsonObject> loginData(@Body JsonObject ussdObject);

    //OTP Pin
    @POST("api/ValidateMobileNumber?code=rrvpo/YQLF2fAd7OF1Lh8YsRSllUbcQcgDaZLsxuO39fjcAhm/13/A==")
    Call<JsonObject> pinCheck(@Body JsonObject ussdObject);



   /* @POST("api/CreateUser?code=i/kyTGtfacscDrfRGXnWjUXiRszKxNr1R3WpeYdWmV62xEft2OqJxg==")
    Call<String>PostData(
            @Body String string);*/


/*    @FormUrlEncoded
    @POST("api/CreateUser?code=i/kyTGtfacscDrfRGXnWjUXiRszKxNr1R3WpeYdWmV62xEft2OqJxg==")
    Call<ResponseBody> createUser(
           *//* @Field("email") String email,
            @Field("password") String password,
            @Field("name") String name,
            @Field("school") String school,*//*


            @Field("FullNameEng") String name,
            @Field("UserName") String phone,
            @Field("PasswordHash") String password,


            @Field("DateOfBirth") String dateOfBirth,
            @Field("NewNID") String newNID,
            @Field("OldNID") String oldNID
    );*/
/*
    @FormUrlEncoded
    @POST("createuser")
    Call<DefaultResponse> createUser(
            @Field("email") String email,
            @Field("password") String password,
            @Field("name") String name,
            @Field("school") String school
    );

    @FormUrlEncoded
    @POST("userlogin")
    Call<LoginResponse> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("allusers")
    Call<UsersResponse> getUsers();

    @FormUrlEncoded
    @PUT("updateuser/{id}")
    Call<LoginResponse> updateUser(
            @Path("id") int id,
            @Field("email") String email,
            @Field("name") String name,
            @Field("school") String school
    );

    @FormUrlEncoded
    @PUT("updatepassword")
    Call<DefaultResponse> updatePassword(
            @Field("currentpassword") String currentpassword,
            @Field("newpassword") String newpassword,
            @Field("email") String email
    );

    @DELETE("deleteuser/{id}")
    Call<DefaultResponse> deleteUser(@Path("id") int id);*/

}
