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
import net.simplifiedcoding.retrofitandroidtutorial.activities.MainActivity;
import net.simplifiedcoding.retrofitandroidtutorial.api.Api;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OtpVerification extends AppCompatActivity {

    private String userName;
    private EditText pinNumberET;
    private Button verifyBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        init();

        verifyBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pinCode = pinNumberET.getText().toString();

                checkPinObserver(userName, pinCode);
            }
        });


    }

    private void checkPinObserver(final String user, String pin) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://fuelmeterdev.azurewebsites.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        Api api = retrofit.create(Api.class);

        JsonObject jsonObjectFinal = new JsonObject();

        JSONObject jsonObjectName = new JSONObject();
        try {
            jsonObjectName.put("userName", user);
            jsonObjectName.put("otp", pin);
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
                    Toast.makeText(OtpVerification.this, user + " is verified", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(OtpVerification.this, LoginActivity.class));


                } else {
                    Log.d("", "onResponse: ");
                    Toast.makeText(OtpVerification.this, user + " cant verify now", Toast.LENGTH_SHORT).show();

                }

                //StringtxtViewShow.setText(response.body().getJson().getFullNameEng());


            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                Toast.makeText(OtpVerification.this, "Failed", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void init() {

        pinNumberET = findViewById(R.id.pinNumberET);
        verifyBTN = findViewById(R.id.verifyBTN);

        if (getIntent().getExtras() != null) {
            userName = getIntent().getStringExtra("userName");
        }
    }


}
