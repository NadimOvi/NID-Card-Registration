package net.simplifiedcoding.retrofitandroidtutorial.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.simplifiedcoding.retrofitandroidtutorial.OTP.LaterOTPVerification;
import net.simplifiedcoding.retrofitandroidtutorial.OTP.OtpVerification;
import net.simplifiedcoding.retrofitandroidtutorial.R;
import net.simplifiedcoding.retrofitandroidtutorial.api.Api;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextEmail;
    private EditText editTextPassword;

    private String phone;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.editTextPhone);
        editTextPassword = findViewById(R.id.editTextPassword);

        findViewById(R.id.buttonLogin).setOnClickListener(this);
        findViewById(R.id.textViewRegister).setOnClickListener(this);
        findViewById(R.id.textViewOTP).setOnClickListener(this);
    }

    private void userLogin() {

         phone = editTextEmail.getText().toString().trim();
         password = editTextPassword.getText().toString().trim();

        if (phone.isEmpty()) {
            editTextEmail.setError("Phone is required");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editTextPassword.setError("Password required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Password should be atleast 6 character long");
            editTextPassword.requestFocus();
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
            jsonObjectName.put("UserName", phone);
            jsonObjectName.put("PasswordHash", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonParser jsonParser = new JsonParser();
        jsonObjectFinal = (JsonObject) jsonParser.parse(jsonObjectName.toString());
        Call<JsonObject> call = api.loginData(jsonObjectFinal);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if (response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "SuccessFull", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this, ProfileActivity.class)
                            .putExtra("UserName", phone));
                } else {
                    Log.d("", "onResponse: ");
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();

                }

                //StringtxtViewShow.setText(response.body().getJson().getFullNameEng());


                /*Toast.makeText(MainActivity.this, "SuccessFull", Toast.LENGTH_LONG).show();*/
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Failed", Toast.LENGTH_LONG).show();
            }
        });



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonLogin:
                userLogin();
                break;
            case R.id.textViewRegister:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.textViewOTP:
                startActivity(new Intent(this, LaterOTPVerification.class));
                break;
        }
    }
}
