package com.learninghub.bankingapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Create New Account");


        Spinner spinner = findViewById(R.id.spinnerAccountType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.account_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Spinner genderSpinner = findViewById(R.id.spinnerGender);
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this, R.array.genders, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderAdapter);

        EditText dateOfBirthEditText = findViewById(R.id.editTextDateOfBirth);
        dateOfBirthEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });



        Button createAccountButton = findViewById(R.id.buttonCreateAccount);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = ((EditText) findViewById(R.id.editTextFirstName)).getText().toString();
                String middleName = ((EditText) findViewById(R.id.editTextMiddleName)).getText().toString();
                String lastName = ((EditText) findViewById(R.id.editTextLastName)).getText().toString();
                String phoneNumber = ((EditText) findViewById(R.id.editTextPhoneNumber)).getText().toString();
                String gender = ((Spinner) findViewById(R.id.spinnerGender)).getSelectedItem().toString();
                String address = ((EditText) findViewById(R.id.editTextAddress)).getText().toString();
                String dateOfBirth = ((EditText) findViewById(R.id.editTextDateOfBirth)).getText().toString();
                String accountType = ((Spinner) findViewById(R.id.spinnerAccountType)).getSelectedItem().toString();

                if (firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty() || address.isEmpty() || dateOfBirth.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Invalid data. Please fill in all fields.", Toast.LENGTH_SHORT).show();
                } else if (phoneNumber.length() != 10) {
                    Toast.makeText(MainActivity.this, "Phone number must be 10 digits.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, Deposite.class);
                    intent.putExtra("AccountHolderName", firstName+middleName+lastName);
                    intent.putExtra("PhoneNumber", phoneNumber);
                    intent.putExtra("Gender", gender);
                    intent.putExtra("Address", address);
                    intent.putExtra("DateOfBirth", dateOfBirth);
                    intent.putExtra("AccountType", accountType);

                    startActivity(intent);
                }
            }
        });
    }

    // Method to show the DatePickerDialog
    private void showDatePickerDialog() {
        int year = 1990; // Default year
        int month = 0;   // Default month (January, 0-based)
        int day = 1;     // Default day (1st of the month)

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // Display the selected date in the EditText
                EditText dateOfBirthEditText = findViewById(R.id.editTextDateOfBirth);
                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year; // Month is zero-based
                dateOfBirthEditText.setText(selectedDate);
            }
        };

        // Create and show the DatePickerDialog with the default date
        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, dateSetListener, year, month, day);
        datePickerDialog.show();
    }
}