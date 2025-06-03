package com.example.dividendcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private EditText investedFundEditText;
    private EditText annualRateEditText;
    private EditText numberOfMonthsEditText;
    private Button calculateButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        investedFundEditText = findViewById(R.id.editTextInvestedFund);
        annualRateEditText = findViewById(R.id.editTextAnnualRate);
        numberOfMonthsEditText = findViewById(R.id.editTextMonths);
        calculateButton = findViewById(R.id.buttonCalculate);
        resultTextView = findViewById(R.id.textViewResult);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateInterest();
            }
        });



    }

    private void calculateInterest() {
        float investedFund = Float.parseFloat(investedFundEditText.getText().toString());
        float annualRate = Float.parseFloat(annualRateEditText.getText().toString());
        int numberOfMonths = Integer.parseInt(numberOfMonthsEditText.getText().toString());
        float monthlyDividend = investedFund * (annualRate / 100) / 12;
        float totalInterest = monthlyDividend * numberOfMonths;

        DecimalFormat df = new DecimalFormat("#.00");
        resultTextView.setText("Monthly dividend is RM " + monthlyDividend +"\n" +
                "Total dividend is RM " + df.format(totalInterest));



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}