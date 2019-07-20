package com.example.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Button login_btn;
    private Button register_btn;
    private EditText password;
    private EditText account;
    private MemberInfoModel memberInfoModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"Enter onCreate");
        refreshUI();
        findViews();
        btnClickFunction();
    }

    private void refreshUI() {
        memberInfoModel = ViewModelProviders.of(this).get(MemberInfoModel.class);
        memberInfoModel.clearAccountPassword();
        memberInfoModel.getAccountLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String acc) {
                if(acc.equals(""))
                    account.setText("");
            }
        });
        memberInfoModel.getPasswordLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String pass) {
                if(pass.equals(""))
                    password.setText("");
            }
        });
        memberInfoModel.getResponseMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String message) {
                if( message.contains("Incorrect") || message.contains("not") || message.contains("exceed")){
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Result")
                            .setMessage(message)
                            .setNeutralButton("OK",(dialog, which)->{
                                memberInfoModel.clearAccountPassword();
                            })
                            .show();

                }else {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Result")
                            .setMessage(message)
                            .setNeutralButton("OK",null)
                            .show();
                }
            }
        });
    }

    private void btnClickFunction() {
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                memberInfoModel.registerAccount(account.getText().toString(),password.getText().toString());
            }
        });
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                memberInfoModel.login(account.getText().toString(),password.getText().toString());
            }
        });
    }

    private void findViews() {
        account = findViewById(R.id.ed_account);
        password = findViewById(R.id.ed_password);
        register_btn = findViewById(R.id.id_register);
        login_btn = findViewById(R.id.id_login);
    }
}
