package net.simplifiedcoding.retrofitandroidtutorial.OTP;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.simplifiedcoding.retrofitandroidtutorial.R;
import net.simplifiedcoding.retrofitandroidtutorial.activities.LoginActivity;
import net.simplifiedcoding.retrofitandroidtutorial.api.Api;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LaterOTPVerification extends AppCompatActivity implements View.OnClickListener {

    String userName,pinNumber;
    private EditText pinNumberET,phoneNumberET;
    private Button verifyBTN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_later_otpverification);
        pinNumberET = findViewById(R.id.pinNumberET);
        phoneNumberET = findViewById(R.id.phoneNumberET);


        findViewById(R.id.verifyBTN).setOnClickListener(this);

    }

    private void userOTP(){
        userName = phoneNumberET.getText().toString().trim();
        pinNumber = pinNumberET.getText().toString().trim();

        if (userName.isEmpty()) {
            phoneNumberET.setError("Phone is required");
            phoneNumberET.requestFocus();
            return;
        }

        if (pinNumber.isEmpty()) {
            pinNumberET.setError("Password required");
            pinNumberET.requestFocus();
            return;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://fuelmeterdev.azurewebsites.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        Api api = retrofit.create(Api.class);

        JsonObject jsonObjectFinal = new JsonObject();

        JSONObject jsonObjectName = new JSONObject();
        try {
            jsonObjectName.put("userName", userName);
            jsonObjectName.put("otp", pinNumber);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonParser jsonParser = new JsonParser();
        jsonObjectFinal = (JsonObject) jsonParser.parse(jsonObjectName.toString());
        Call<JsonObject> call = api.pinCheck(jsonObjectFinal);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if (response.isSuccessful()) {
                    Toast.makeText(LaterOTPVerification.this, " is verified", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LaterOTPVerification.this, LoginActivity.class));


                } else {
                    Log.d("", "onResponse: ");
                    Toast.makeText(LaterOTPVerification.this, " cant verify now", Toast.LENGTH_SHORT).show();

                }

                //StringtxtViewShow.setText(response.body().getJson().getFullNameEng());


            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                Toast.makeText(LaterOTPVerification.this, "Failed", Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.verifyBTN:
                userOTP();
                break;
        }
    }



}
