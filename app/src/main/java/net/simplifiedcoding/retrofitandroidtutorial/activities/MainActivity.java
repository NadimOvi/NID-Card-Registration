package net.simplifiedcoding.retrofitandroidtutorial.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import net.simplifiedcoding.retrofitandroidtutorial.NIDScan.Utils;
import net.simplifiedcoding.retrofitandroidtutorial.NIDScan.parser.DataParser;
import net.simplifiedcoding.retrofitandroidtutorial.NIDScan.parser.NewNidDataParser;
import net.simplifiedcoding.retrofitandroidtutorial.NIDScan.parser.OldNidDataParser;
import net.simplifiedcoding.retrofitandroidtutorial.OTP.OtpVerification;
import net.simplifiedcoding.retrofitandroidtutorial.R;
import net.simplifiedcoding.retrofitandroidtutorial.api.Api;



import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 * Sign Up Activity
 * */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String userName;
    String passwordHash;
    String fullNameEng;
    String dateOfBirth;
    String newNID;
    String oldNID;

    private EditText editTextPhone, editTextPassword, editTextName, editTextDateOfBirth, editTextNewNID, editTextOldNID;
    private Button nidScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextPhone = findViewById(R.id.editTextPhone);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextName = findViewById(R.id.editTextName);
        editTextDateOfBirth = findViewById(R.id.editTextDOB);
        editTextNewNID = findViewById(R.id.editTextNewNID);
        editTextOldNID = findViewById(R.id.editTextOldNID);


        findViewById(R.id.buttonSignUp).setOnClickListener(this);
        findViewById(R.id.textViewLogin).setOnClickListener(this);

        //using for NID Scan
      /*  fullNameEng = editTextName.getText().toString().trim();
        dateOfBirth = editTextDateOfBirth.getText().toString().trim();
        newNID = editTextNewNID.getText().toString().trim();
        oldNID = editTextOldNID.getText().toString().trim();*/

        nidScan = findViewById(R.id.nidScan);
        nidScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startScan();
            }
        });
    }

    private void startScan() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.PDF_417);
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(false);
        integrator.setOrientationLocked(true);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() != null) {
                String rawData = result.getContents();
                Utils.CARD_TYPE cardType = Utils.getCardType(rawData);

                if ( cardType == Utils.CARD_TYPE.SMART_NID_CARD ) {
                    NewNidDataParser parser = new NewNidDataParser(this, rawData);

                    String nidNo = parser.getNidNo();
                    editTextNewNID.setText(nidNo);

                    String name = parser.getName();
                    editTextName.setText(name);

                    String dateOfBirth = parser.getDateOfBirth();
                    editTextDateOfBirth.setText(dateOfBirth);


                    /*saveCardData(parser);*/

                } else if (cardType == Utils.CARD_TYPE.OLD_NID_CARD) {
                    OldNidDataParser parser = new OldNidDataParser(this, rawData);

                    String nidNo = parser.getNidNo();
                    editTextOldNID.setText(nidNo);

                    String name = parser.getName();
                    editTextName.setText(name);

                    String dateOfBirth = parser.getDateOfBirth();
                    editTextDateOfBirth.setText(dateOfBirth);

                    /*saveCardData(parser);*/

                } else {
                    Toast.makeText(this, "Invalid NID Card", Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

   /* private void saveCardData(DataParser parser) {
        String name = parser.getName();
        editTextName.setText(name);
        String nidNo = parser.getNidNo();
        String dateOfBirth = parser.getDateOfBirth();
        String issueDate = parser.getIssueDate();
        String rawData = parser.getRawData();

    }*/

    private void userSignUp() {
        userName = editTextPhone.getText().toString().trim();
        passwordHash = editTextPassword.getText().toString().trim();
        fullNameEng = editTextName.getText().toString().trim();
        dateOfBirth = editTextDateOfBirth.getText().toString().trim();
        newNID = editTextNewNID.getText().toString().trim();
        oldNID = editTextOldNID.getText().toString().trim();

        if (userName.isEmpty()) {
            editTextPhone.setError("Phone is required");
            editTextPhone.requestFocus();
            return;
        }

        if (passwordHash.isEmpty()) {
            editTextPassword.setError("Password required");
            editTextPassword.requestFocus();
            return;
        }

        if (passwordHash.length() < 6) {
            editTextPassword.setError("Password should be atleast 6 character long");
            editTextPassword.requestFocus();
            return;
        }

        if (fullNameEng.isEmpty()) {
            editTextName.setError("Name required");
            editTextName.requestFocus();
            return;
        }

        if (dateOfBirth.isEmpty()) {
            editTextDateOfBirth.setError("School required");
            editTextDateOfBirth.requestFocus();
            return;
        }
        /*if (newNID.isEmpty()) {
            editTextNewNID.setError("School required");
            editTextNewNID.requestFocus();
            return;
        }
        if (oldNID.isEmpty()) {
            editTextOldNID.setError("School required");
            editTextOldNID.requestFocus();
            return;
        }*/

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://fuelmeterdev.azurewebsites.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        Api api = retrofit.create(Api.class);

        JsonObject jsonObjectFinal = new JsonObject();

        JSONObject jsonObjectName = new JSONObject();
        try {
            jsonObjectName.put("UserName", userName);
            jsonObjectName.put("FullNameEng", fullNameEng);
            jsonObjectName.put("DateOfBirth", dateOfBirth);
            jsonObjectName.put("NewNID", newNID);
            jsonObjectName.put("OldNID", oldNID);
            jsonObjectName.put("PasswordHash", passwordHash);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonParser jsonParser = new JsonParser();
        jsonObjectFinal = (JsonObject) jsonParser.parse(jsonObjectName.toString());
        Call<JsonObject> call = api.postData(jsonObjectFinal);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "SuccessFull", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MainActivity.this, OtpVerification.class)
                            .putExtra("userName", userName));
                } else {
                    Log.d("", "onResponse: ");
                    Toast.makeText(MainActivity.this, "UserName Already Exist, Please try again later", Toast.LENGTH_SHORT).show();

                }

                //StringtxtViewShow.setText(response.body().getJson().getFullNameEng());


                /*Toast.makeText(MainActivity.this, "SuccessFull", Toast.LENGTH_LONG).show();*/
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSignUp:
                userSignUp();
                break;
            case R.id.textViewLogin:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }
}
