package com.example.assignment130;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

/*
Well Howdy there. Welcome to my Text Validator Class. Now you might be thinking to yourself: this looks suspiciously like
Something on Stack Overflow and Jacob may or may not have written this entirely on his own. And you would be right!

While preparing for this assignment I went on down to: https://stackoverflow.com/questions/2763022/android-how-can-i-validate-edittext-input
and was looking for options. I saw this and was like "Wow thats brilliant and allows me to modularly change the validation
conditions for each field! So I did what every CS person did and swiped it. Now, to avoid being sent to the good ole
Belmont Office of "We are kicking you out for plagiarism" I have done a couple things:

1. I Rewrote a lot of this in a style that I personally find cleaner
2. I cited my source
3. I can fully and 100% explain to you exactly how this works and WHY it works so I am not just mindlessly
copying and pasting code. This is a great solution and thank you for coming to my Ted Talk.
 */
public abstract class TextValidator implements TextWatcher {
    //The actual text block we will be watching
    private final TextView textView;

    public String errorMessage = "Input does not validate";

    //constructor
    public TextValidator(TextView textView) {
        this.textView = textView;
    }

    //this is a method that will be overwritten whenever we make a new instance of this class
    //to sit on one of our user input fields. Yay object oriented programming
    public abstract boolean validate(TextView textView, String text);


    @Override
    /*
    This code runs after a user has finished editing their stuff.
     */
    final public void afterTextChanged(Editable s) {
        String text = textView.getText().toString();
        if(!validate(textView, text)){
            textView.setError(errorMessage);
        }
    }

    @Override
    final public void beforeTextChanged(CharSequence s, int start, int count, int after) { /* Don't care */ }

    @Override
    final public void onTextChanged(CharSequence s, int start, int before, int count) { /* Don't care */ }

}
