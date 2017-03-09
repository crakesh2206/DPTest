package com.naruto.dptest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import static java.lang.Integer.parseInt;


public class BollwoodActivity extends Activity implements TextWatcher {

    // Progress Dialog
    private ProgressDialog pDialog;


    EditText etMdopen, etMdclose;
    EditText etKlopen, etKlclose;

    EditText etMNopen, etMNclose;
    EditText etMUMopen, etMUMclose;

    String stMdopen, stMdopendigit, stMdclose, stMdclosedigit, stKlopen, stKlopendigit, stKlclose, stKlclosedigit, stMNopen, stMNopendigit, stMNclose, stMNclosedigit, stMUMopen, stMUMopendigit, stMUMclose, stMUMclosedigit;

    // url to create new product
    private static String url_create_product = "http://narutofans.000webhostapp.com/rjc/ej.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private String dayofToday;
    private String todayDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insertdata);

        initViews();
        genRateTodayDateandDay();
        etMdopen.addTextChangedListener(this);
        etMdclose.addTextChangedListener(this);
        etKlopen.addTextChangedListener(this);
        etKlclose.addTextChangedListener(this);
        etMNopen.addTextChangedListener(this);
        etMNclose.addTextChangedListener(this);
        etMUMopen.addTextChangedListener(this);
        etMUMclose.addTextChangedListener(this);


        // Create button
        Button btnCreateProduct = (Button) findViewById(R.id.btnCreateProduct);

        // button click event
        btnCreateProduct.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // creating new product in background thread

                stMdopen = etMdopen.getText().toString();
                stMdopendigit = getDigit(stMdopen);
                stMdclose = etMdclose.getText().toString();
                stMdclosedigit = getDigit(stMdclose);

                stKlopen = etKlopen.getText().toString();
                stKlopendigit = getDigit(stKlopen);
                stKlclose = etKlclose.getText().toString();
                stKlclosedigit = getDigit(stKlclose);

                stMNopen = etMNopen.getText().toString();
                stMNopendigit = getDigit(stMNopen);
                stMNclose = etMNclose.getText().toString();
                stMNclosedigit = getDigit(stMNclose);

                stMUMopen = etMUMopen.getText().toString();
                stMUMopendigit = getDigit(stMUMopen);
                stMUMclose = etMUMclose.getText().toString();
                stMUMclosedigit = getDigit(stMUMclose);

                HashMap<String, String> queryValues = new HashMap<String, String>();
                queryValues.put(DBController.KEY_DATE, todayDate);
                queryValues.put(DBController.KEY_DAYOFMONTH,dayofToday);

                queryValues.put(DBController.KEY_MDOPEN, stMdopen);
                queryValues.put(DBController.KEY_MDOPEN_DIGIT, stMdopendigit);
                queryValues.put(DBController.KEY_MDCLOSE, stMdclose);
                queryValues.put(DBController.KEY_MDCLOSE_DIGIT, stMdclosedigit);

                queryValues.put(DBController.KEY_KL_OPEN, stKlopen);
                queryValues.put(DBController.KEY_KL_OPEN_DIGIT, stKlopendigit);
                queryValues.put(DBController.KEY_KL_CLOSE, stKlclose);
                queryValues.put(DBController.KEY_KL_CLOSE_DIGIT, stKlclosedigit);

                queryValues.put(DBController.KEY_MNOPEN, stMNopen);
                queryValues.put(DBController.KEY_MNOPEN_DIGIT, stMNopendigit);
                queryValues.put(DBController.KEY_MNCLOSE, stMNclose);
                queryValues.put(DBController.KEY_MNCLOSE_DIGIT, stMNclosedigit);

                queryValues.put(DBController.KEY_MUM_OPEN, stMUMopen);
                queryValues.put(DBController.KEY_MUM_OPEN_DIGIT, stMUMopendigit);
                queryValues.put(DBController.KEY_MUM_CLOSE, stMUMclose);
                queryValues.put(DBController.KEY_MUM_CLOSE_DIGIT, stMUMclosedigit);


                DBController controller = new DBController(getApplicationContext());

                controller.insertUser(queryValues);

                Toast.makeText(getApplicationContext(), "inserted", Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void genRateTodayDateandDay() {
        Date date = new Date();
        SimpleDateFormat inFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            date = inFormat.parse(String.valueOf(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        todayDate = inFormat.format(new Date());
        SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
        dayofToday = outFormat.format(date);
    }

    private void initViews() {
        etMdopen = (EditText) findViewById(R.id.mdopen);
        etMdclose = (EditText) findViewById(R.id.mdclose);
        etKlopen = (EditText) findViewById(R.id.klopen);
        etKlclose = (EditText) findViewById(R.id.klclose);
        etMNopen = (EditText) findViewById(R.id.mnopen);
        etMNclose = (EditText) findViewById(R.id.mnclose);
        etMUMopen = (EditText) findViewById(R.id.mumopen);
        etMUMclose = (EditText) findViewById(R.id.mumclose);

    }


    private String getDigit(String input) {

        char[] xy = input.toCharArray();
        int temp = 0;
        for (char d : xy) {
            temp = temp + parseInt(String.valueOf(d));
        }
        String twodigit = String.valueOf(temp);
        if (twodigit.length() == 2) {
            String lastdigit = twodigit.substring(1);
            temp = parseInt(lastdigit);
        }

        return String.valueOf(temp);
    }

    private void isSequence(int number) {

        int[] test = new int[]{4, 5, 7};

        Arrays.sort(test);
        for (int i = 0; i < test.length - 1; i++) {
            if (test[i] + 1 != test[i + 1]) {
                // Not sequential
            }
        }

    }

    public int ArraytoNumber(int[] numbtoarray) {
        //    int a[] = {60, 321, 5};

        int finalNumber = 0;
        for (int i = 0; i < numbtoarray.length; i++) {
            int num = numbtoarray[i];
            if (num != 0) {
                while (num > 0) {
                    finalNumber *= 10;
                    num /= 10;
                }
                finalNumber += numbtoarray[i];
            } else {
                finalNumber *= 10;
            }
        }
        return finalNumber;
    }

    public int[] intTOArray(int guess) {
        String temp = Integer.toString(guess);
        int[] newGuess = new int[temp.length()];
        for (int i = 0; i < temp.length(); i++) {
            newGuess[i] = temp.charAt(i) - '0';
        }
        return newGuess;
    }

    void pushZerosToEnd(int arr[], int n) {
        int count = 0;  // Count of non-zero elements

        // Traverse the array. If element encountered is non-
        // zero, then replace the element at index 'count'
        // with this element
        for (int i = 0; i < n; i++)
            if (arr[i] != 0)
                arr[count++] = arr[i]; // here count is
        // incremented

        // Now all non-zero elements have been shifted to
        // front and  'count' is set as index of first 0.
        // Make all elements 0 from count to end.
        while (count < n)
            arr[count++] = 0;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        if (etMdopen.getText().hashCode() == s.hashCode()) {
            methodforMultiTextwatcher(etMdopen, s);
        } else if (etMdclose.getText().hashCode() == s.hashCode()) {
            methodforMultiTextwatcher(etMdclose, s);
        } else if (etKlopen.getText().hashCode() == s.hashCode()) {
            methodforMultiTextwatcher(etKlopen, s);
        } else if (etKlclose.getText().hashCode() == s.hashCode()) {
            methodforMultiTextwatcher(etKlclose, s);
        } else if (etMNopen.getText().hashCode() == s.hashCode()) {
            methodforMultiTextwatcher(etMNopen, s);
        } else if (etMNclose.getText().hashCode() == s.hashCode()) {
            methodforMultiTextwatcher(etMNclose, s);
        } else if (etMUMopen.getText().hashCode() == s.hashCode()) {
            methodforMultiTextwatcher(etMUMopen, s);
        } else if (etMUMclose.getText().hashCode() == s.hashCode()) {
            methodforMultiTextwatcher(etMUMclose, s);
        }


    }

    private void methodforMultiTextwatcher(EditText etext, Editable s) {
        if (!s.toString().equals("")) {
            int before = Integer.parseInt(s.toString());
            int[] numbtoarray = intTOArray(before);

            Arrays.sort(numbtoarray);
            pushZerosToEnd(numbtoarray, numbtoarray.length);

            int after = ArraytoNumber(numbtoarray);

            if (before == after) {
                etext.setBackgroundResource(R.color.cardview_light_background);
            } else {
                etext.setBackgroundResource(R.color.colorAccent);

            }
        }

    }


}