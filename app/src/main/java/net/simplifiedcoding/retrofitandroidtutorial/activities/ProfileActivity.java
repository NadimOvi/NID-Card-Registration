package net.simplifiedcoding.retrofitandroidtutorial.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import net.simplifiedcoding.retrofitandroidtutorial.R;

public class ProfileActivity extends AppCompatActivity {

    TextView userInfoEmail,tvEmpDate;
    Button signOutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

       /* userInfoEmail = (TextView) findViewById(R.id.tvEmpName);
        tvEmpDate = (TextView) findViewById(R.id.tvEmpDate);

        accessUserInformation();*/

    }
}
