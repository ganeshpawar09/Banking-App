package com.learninghub.bankingapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Deposite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposite);
        getSupportActionBar().setTitle("Deposite");

        String accountHolderName = getIntent().getStringExtra("AccountHolderName");
        String accountType = getIntent().getStringExtra("AccountType");


        TextView t=findViewById(R.id.accountHolderName);
        t.setText(accountHolderName);
        TextView t1=findViewById(R.id.accountType);
        t1.setText(accountType);
        EditText depositAmountEditText = findViewById(R.id.editTextDepositAmount);


        Button submitDepositButton = findViewById(R.id.buttonSubmitDeposit);
        submitDepositButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String depositAmountStr = depositAmountEditText.getText().toString().trim();

                if (!depositAmountStr.isEmpty()) {
                    double depositAmount = Double.parseDouble(depositAmountStr);
                    String confirmationMessage = "Deposit of $" + depositAmount + " has been processed.";
                    Toast.makeText(Deposite.this, confirmationMessage, Toast.LENGTH_SHORT).show();
                    depositAmountEditText.setText("0.00");
                }
            }
        });
    }
}
