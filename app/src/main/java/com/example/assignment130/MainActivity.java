package com.example.assignment130;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private boolean _ignoreTextWatcher;
    EditText nationalNumberInput;
    EditText nameInput;
    EditText speciesInput;

    RadioGroup radioGroup;
    RadioButton femaleButton;
    RadioButton maleButton;
    RadioButton unkButton;

    EditText heightInput;
    EditText weightInput;

    Spinner levelSpinner;

    EditText hpInput;
    EditText attackInput;
    EditText defenseInput;

    Button resetButton;
    Button submitButton;

    /**
     * This is the code that executes when the "Submit" button is clicked
     */
    View.OnClickListener submitListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if(nationalNumberInput.getError() != null){
                Toast.makeText(getApplicationContext(), "National Number does not validate: " + nationalNumberInput.getError() + "", Toast.LENGTH_LONG).show();
            }
            else if(nameInput.getError() != null){
                Toast.makeText(getApplicationContext(), "Name does not validate: " + nameInput.getError() + "", Toast.LENGTH_LONG).show();
            }
            else if(speciesInput.getError() != null){
                Toast.makeText(getApplicationContext(), "Species does not validate: " + speciesInput.getError() + "", Toast.LENGTH_LONG).show();
            }
            else if(!femaleButton.isChecked() && !maleButton.isChecked()){
                Toast.makeText(getApplicationContext(), "Gender does not validate: " + "either male or female must be selected" + "", Toast.LENGTH_LONG).show();
            }
            else if(heightInput.getError() != null){
                Toast.makeText(getApplicationContext(), "Height does not validate: " + heightInput.getError() + "", Toast.LENGTH_LONG).show();
            }
            else if(weightInput.getError() != null){
                Toast.makeText(getApplicationContext(), "Weight does not validate: " + weightInput.getError() + "", Toast.LENGTH_LONG).show();
            }
            else if(hpInput.getError() != null){
                Toast.makeText(getApplicationContext(), "HP does not validate: " + hpInput.getError() + "", Toast.LENGTH_LONG).show();
            }
            else if(attackInput.getError() != null){
                Toast.makeText(getApplicationContext(), "Attack does not validate: " + attackInput.getError() + "", Toast.LENGTH_LONG).show();
            }
            else if(defenseInput.getError() != null){
                Toast.makeText(getApplicationContext(), "Defense does not validate: " + defenseInput.getError() + "", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "Successfully Added to Database", Toast.LENGTH_LONG).show();
            }
        }
    };

    /**
     * Code Executed when "Reset" button is clicked
     */
    View.OnClickListener resetListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            setAllFieldsToDefault();


        }
    };

    AdapterView.OnItemSelectedListener spinListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }

    };


    /**
     * Runs when app starts
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.constraint);

        //region variable initialization
        nameInput = findViewById(R.id.nameInput);

        nationalNumberInput = findViewById(R.id.nationalNumberInput);

        speciesInput = findViewById(R.id.speciesInput);

        radioGroup = findViewById(R.id.radioGroup1);
        femaleButton = findViewById(R.id.femaleButton);
        maleButton = findViewById(R.id.maleButton);
        unkButton = findViewById(R.id.unkButton);

        heightInput = findViewById(R.id.heightInput);
        weightInput = findViewById(R.id.weightInput);

        levelSpinner = findViewById(R.id.levelDropdown);

        levelSpinner.setPrompt("Level:");

        LinkedList<Integer> levels = new LinkedList<Integer>();
        for(int i = 1; i <= 50; i++){
            levels.add(i);
        }

        ArrayAdapter<Integer> levelsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, levels);
        levelSpinner.setAdapter(levelsAdapter);

        hpInput = findViewById(R.id.hpInput);
        attackInput = findViewById(R.id.attackInput);
        defenseInput = findViewById(R.id.defenseInput);

        submitButton = findViewById(R.id.submitButton);
        resetButton = findViewById(R.id.resetButton);

        submitButton.setOnClickListener(submitListener);
        resetButton.setOnClickListener(resetListener);

        //endregion

        setValidationConditionsOnFields();



    }

    /**
     * This sets custom validation requirements on each input field in our UI, and an error will be shown if
     * they are invalid
     */
    public void setValidationConditionsOnFields(){

        nationalNumberInput.addTextChangedListener(new TextValidator(nationalNumberInput) {
            @Override
            public boolean validate(TextView textView, String text) {
                errorMessage = "Must be an Integer between 0 and 1010";
                if(text.length() > 0){
                    //if we find a character that is NOT numeric
                    Pattern pattern = Pattern.compile("[^0-9]");
                    Matcher matcher = pattern.matcher(text);
                    //if regex or first is not uppercase

                    if(matcher.find()){
                        return false;
                    }

                    if(Integer.parseInt(text) > 1010){
                        return false;
                    }
                    return true;
                }

                return false;
            }
        });

        nameInput.addTextChangedListener(new TextValidator(nameInput) {
            @Override
            public boolean validate(TextView textView, String text) {
                errorMessage = "Name must only contain letters, periods, and spaces. First letter must be capitalized. Must be between 3 and 12 characters";
                if(text.length() > 0){
                    //if we find a character that is NOT a-z, " ", or "."
                    Pattern pattern = Pattern.compile("[^A-Za-z .]");
                    Matcher matcher = pattern.matcher(text);
                    //if regex or first is not uppercase
                    if(matcher.find() || !Character.isUpperCase(text.charAt(0)) || text.length() > 12 || text.length() < 3 ){
                        return false;
                    }
                    return true;
                }

                return false;
            }
        });

        speciesInput.addTextChangedListener(new TextValidator(speciesInput) {
            @Override
            public boolean validate(TextView textView, String text) {
                errorMessage = "May contain only letters and spaces. Capitalize the first letter of each word";
                if(text.length() > 0){
                    //if we find a character that is NOT a-z or " "
                    Pattern pattern = Pattern.compile("[^a-zA-z ]");
                    Matcher matcher = pattern.matcher(text);
                    //if regex or first is not uppercase

                    if(matcher.find() || !Character.isUpperCase(text.charAt(0))){
                        return false;
                    }

                    return true;
                }

                return false;
            }
        });

        heightInput.addTextChangedListener(new TextValidator(heightInput) {
            @Override
            public boolean validate(TextView textView, String text) {
                errorMessage = "Decimal input with 2 decimal places in meters between 0.3 and 19.99";
                if(text.length() > 0){
                    //if not double or >= 2 decimal places
                    try
                    {
                        double height = Double.parseDouble(text);

                        if(height < 0.3 || height > 19.99){
                            return false;
                        }
                    }
                    catch(NumberFormatException e)
                    {
                        return false;
                    }

                    if(text.contains(".")){
                        int decimalPlaces = text.length() - text.indexOf(".") - 1;
                        if(decimalPlaces > 2){
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            }
        });

        weightInput.addTextChangedListener(new TextValidator(weightInput) {
            @Override
            public boolean validate(TextView textView, String text) {
                errorMessage = "Decimal input with 2 decimal places in kg between 0.1 and 820.0";
                if(text.length() > 0){
                    //if not double or >= 2 decimal places
                    try
                    {
                        double weight = Double.parseDouble(text);

                        if(weight < 0.1 || weight > 820.0){
                            return false;
                        }
                    }
                    catch(NumberFormatException e)
                    {
                        return false;
                    }

                    if(text.contains(".")){
                        int decimalPlaces = text.length() - text.indexOf(".") - 1;
                        if(decimalPlaces > 2){
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            }
        });

        hpInput.addTextChangedListener(new TextValidator(hpInput) {
            @Override
            public boolean validate(TextView textView, String text) {
                errorMessage = "Must be an Integer Input between 1 and 362";
                if(text.length() > 0){
                    //if not double or >= 2 decimal places
                    try
                    {
                        int hp = Integer.parseInt(text);
                        if(hp < 1 || hp > 362){
                            return false;
                        }
                    }
                    catch(NumberFormatException e)
                    {
                        return false;
                    }
                    return true;
                }
                return false;
            }
        });

        attackInput.addTextChangedListener(new TextValidator(attackInput) {
            @Override
            public boolean validate(TextView textView, String text) {
                errorMessage = "Must be an Integer Input between 5 and 526";
                if(text.length() > 0){
                    //if not double or >= 2 decimal places
                    try
                    {
                        int attack = Integer.parseInt(text);

                        if(attack < 5 || attack > 526){
                            return false;
                        }
                    }
                    catch(NumberFormatException e)
                    {
                        return false;
                    }
                    return true;
                }
                return false;
            }
        });

        defenseInput.addTextChangedListener(new TextValidator(defenseInput) {
            @Override
            public boolean validate(TextView textView, String text) {
                errorMessage = "Must be an Integer Input between 5 and 614";
                if(text.length() > 0){
                    //if not double or >= 2 decimal places
                    try
                    {
                        int defense = Integer.parseInt(text);

                        if(defense < 5 || defense > 614){
                            return false;
                        }
                    }
                    catch(NumberFormatException e)
                    {
                        return false;
                    }
                    return true;
                }
                return false;
            }
        });
    }

    public void setAllFieldsToDefault(){
        nationalNumberInput.setText("896");
        nameInput.setText("Glastrier");

        speciesInput.setText("Wild Horse Pokemon");
        heightInput.setText("2.2");
        weightInput.setText("800.0");
        hpInput.setText("1");
        attackInput.setText("5");
        defenseInput.setText("5");

        radioGroup.clearCheck();

        levelSpinner.setSelection(0);
    }

    public void clearOutAllFields(){
        nationalNumberInput.getText().clear();
        nameInput.getText().clear();

        speciesInput.getText().clear();
        heightInput.getText().clear();
        weightInput.getText().clear();
        hpInput.getText().clear();
        attackInput.getText().clear();
        defenseInput.getText().clear();

        radioGroup.clearCheck();

        levelSpinner.setSelection(0);
    }
}