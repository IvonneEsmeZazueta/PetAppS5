package com.example.petapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.petapp.R;
import com.example.petapp.domain.JavaMailAPI;
import com.google.android.material.button.MaterialButton;

public class MailActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etMail;
    private EditText etMessage;
    private MaterialButton btnSend;
    private ProgressBar progressBar;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etName = (EditText) findViewById(R.id.etName);
        etMail = (EditText) findViewById(R.id.etMail);
        etMessage = (EditText) findViewById(R.id.etMessage);
        btnSend = findViewById(R.id.btnSend);
        progressBar = findViewById(R.id.progressBar);

        btnSend.setOnClickListener(v -> {
            sendEMail();
        });
    }

    private void sendEMail() {
        String mail = etMail.getText().toString().trim();
        String name = etName.getText().toString().trim();
        String message = etMessage.getText().toString();

        // Send eMail
        JavaMailAPI javaMailAPI = new JavaMailAPI(getApplicationContext(), mail, name, message, progressBar);
        javaMailAPI.execute();
    }
}