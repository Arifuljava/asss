package com.meass.calculator_apps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import es.dmoral.toasty.Toasty;

public class AgeCalculator extends AppCompatActivity  implements View.OnClickListener {


    //@BindView(R.id.textViewNextBirthdayMonths)
    TextView textViewNextBirthdayMonths;
    //@BindView(R.id.textViewNextBirthdayDays)
    TextView textViewNextBirthdayDays;
    // @BindView(R.id.textViewFinalYears)
    TextView textViewFinalYears;
    // @BindView(R.id.textViewFinalMonths)
    TextView textViewFinalMonths;
    //@BindView(R.id.textViewFinalDays)
    TextView textViewFinalDays;
    // @BindView(R.id.textViewCurrentDay)
    TextView textViewCurrentDay;
    //@BindView(R.id.textViewCalculate)
    TextView textViewCalculate;
    // @BindView(R.id.textViewClear)
    TextView textViewClear;
    //@BindView(R.id.imageViewCalenderFirst)
    ImageView imageViewCalenderFirst;
    //@BindView(R.id.imageViewCalenderSecond)
    ImageView imageViewCalenderSecond;
    //@BindView(R.id.editTextBirthDay)
    EditText editTextBirthDay;
    //@BindView(R.id.editTextBirthMonth)
    EditText editTextBirthMonth;
    //@BindView(R.id.editTextBirthYear)
    EditText editTextBirthYear;
    //@BindView(R.id.editTextCurrentDay)
    EditText editTextCurrentDay;
    //@BindView(R.id.editTextCurrentMonth)
    EditText editTextCurrentMonth;
    //@BindView(R.id.editTextCurrentYear)
    EditText editTextCurrentYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_calculator);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Age Calculator");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_myarrow);
        getSupportActionBar().setElevation(10.0f);
        getSupportActionBar().setElevation(10.0f);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_myarrow);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_myarrow);
        getSupportActionBar().setElevation(10.0f);
        getSupportActionBar().setElevation(10.0f);
        getSupportActionBar().setElevation(10.0f);
        editTextCurrentYear=findViewById(R.id.editTextCurrentYear);
        editTextCurrentMonth=findViewById(R.id.editTextCurrentMonth);
        textViewNextBirthdayMonths=findViewById(R.id.textViewNextBirthdayMonths);
        textViewNextBirthdayDays=findViewById(R.id.textViewNextBirthdayDays);
        textViewFinalYears=findViewById(R.id.textViewFinalYears);
        textViewFinalMonths=findViewById(R.id.textViewFinalMonths);
        textViewFinalDays=findViewById(R.id.textViewFinalDays);
        textViewCurrentDay=findViewById(R.id.textViewCurrentDay);
        textViewCalculate=findViewById(R.id.textViewCalculate);
        textViewClear=findViewById(R.id.textViewClear);
        imageViewCalenderFirst=findViewById(R.id.imageViewCalenderFirst);
        imageViewCalenderSecond=findViewById(R.id.imageViewCalenderSecond);
        editTextBirthDay=findViewById(R.id.editTextBirthDay);
        editTextBirthMonth=findViewById(R.id.editTextBirthMonth);
        editTextBirthYear=findViewById(R.id.editTextBirthYear);
        editTextCurrentDay=findViewById(R.id.editTextCurrentDay);
        setupCurrentDate(); // setup today's date

        textViewCalculate.setOnClickListener(this);
        textViewClear.setOnClickListener(this);
        imageViewCalenderSecond.setOnClickListener(this);
        imageViewCalenderFirst.setOnClickListener(this);

    }
    private void setupCurrentDate() {
        final Calendar c = Calendar.getInstance();
        editTextCurrentYear.setText(String.valueOf(c.get(Calendar.YEAR)));
        editTextCurrentMonth.setText(addZero(c.get(Calendar.MONTH) + 1));
        editTextCurrentDay.setText(addZero(c.get(Calendar.DAY_OF_MONTH)));

        SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE");
        Date date = new Date(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH) - 1);
        String dayOfWeek = simpledateformat.format(date);
        textViewCurrentDay.setText(dayOfWeek);
        textViewCurrentDay.setVisibility(View.VISIBLE);
    }
    private String addZero(int number) {
        String n;
        if (number < 10) {
            n = "0" + number;
        } else {
            n = String.valueOf(number);
        }
        return n;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imageViewCalenderSecond) {
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(AgeCalculator.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {

                    editTextBirthDay.setText(addZero(dayOfMonth));
                    editTextBirthMonth.setText(addZero(monthOfYear + 1));
                    editTextBirthYear.setText(String.valueOf(year));
                }
            }, mYear, mMonth, mDay);
            datePickerDialog.show();
        } else if (v == textViewCalculate) {
            if (!TextUtils.isEmpty(editTextBirthDay.getText()) && !TextUtils.isEmpty(editTextBirthMonth.getText()) && !TextUtils.isEmpty(editTextBirthYear.getText())) {
                calculateAge();
                nextBirthday();
            } else {
                Toasty.warning(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT, true).show();
            }
        } else if (v == textViewClear) {
            editTextBirthDay.setText("");
            editTextBirthMonth.setText("");
            editTextBirthYear.setText("");
            Toasty.success(getApplicationContext(), "Successfully reset", Toast.LENGTH_SHORT, true).show();
        }
    }
    private void calculateAge() {
        int currentDay = Integer.valueOf(editTextCurrentDay.getText().toString());
        int currentMonth = Integer.valueOf(editTextCurrentMonth.getText().toString());
        int currentYear = Integer.valueOf(editTextCurrentYear.getText().toString());

        Date now = new Date(currentYear, currentMonth, currentDay);

        int birthDay = Integer.valueOf(editTextBirthDay.getText().toString());
        int birthMonth = Integer.valueOf(editTextBirthMonth.getText().toString());
        int birthYear = Integer.valueOf(editTextBirthYear.getText().toString());

        Date dob = new Date(birthYear, birthMonth, birthDay);

        if (dob.after(now)) {
            Toasty.error(getApplicationContext(), "Birthday can't in future", Toast.LENGTH_SHORT, true).show();
            return;
        }
        // days of every month
        int month[] = {31, 28, 31, 30, 31, 30, 31,
                31, 30, 31, 30, 31};

        // if birth date is greater then current birth
        // month then do not count this month and add 30
        // to the date so as to subtract the date and
        // get the remaining days
        if (birthDay > currentDay) {
            currentDay = currentDay + month[birthMonth - 1];
            currentMonth = currentMonth - 1;
        }

        // if birth month exceeds current month, then do
        // not count this year and add 12 to the month so
        // that we can subtract and find out the difference
        if (birthMonth > currentMonth) {
            currentYear = currentYear - 1;
            currentMonth = currentMonth + 12;
        }

        // calculate date, month, year
        int calculated_date = currentDay - birthDay;
        int calculated_month = currentMonth - birthMonth;
        int calculated_year = currentYear - birthYear;

        textViewFinalDays.setText(String.valueOf(calculated_date));
        textViewFinalMonths.setText(String.valueOf(calculated_month));
        textViewFinalYears.setText(String.valueOf(calculated_year));
    }
    private void nextBirthday() {
        int currentDay = Integer.valueOf(editTextCurrentDay.getText().toString());
        int currentMonth = Integer.valueOf(editTextCurrentMonth.getText().toString());
        int currentYear = Integer.valueOf(editTextCurrentYear.getText().toString());

        Calendar current = Calendar.getInstance();
        current.set(currentYear, currentMonth, currentDay);

        int birthDay = Integer.valueOf(editTextBirthDay.getText().toString());
        int birthMonth = Integer.valueOf(editTextBirthMonth.getText().toString());
        int birthYear = Integer.valueOf(editTextBirthYear.getText().toString());

        Calendar birthday = Calendar.getInstance();
        birthday.set(birthYear, birthMonth, birthDay);

        long difference = birthday.getTimeInMillis() - current.getTimeInMillis();

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(difference);

        textViewNextBirthdayMonths.setText(String.valueOf(cal.get(Calendar.MONTH)));
        textViewNextBirthdayDays.setText(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
    }


    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(getApplicationContext(), UnitConverter.class));

        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), UnitConverter.class));
    }
}