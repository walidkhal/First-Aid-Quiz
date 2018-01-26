package com.example.android.testcardview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final static String BOOLEAN_ARRAY = "buttonsState";     // constant for save app state while rotation
    boolean submitButtonState = false;                      //check if submit button is pressed or not
    boolean trueButtonState = false;                        //check if Q4 true option button is pressed or not
    boolean falseButtonState = false;                       //check if Q4 false option button is pressed or not
    //question 1 variables
    RadioGroup questionOneOptionsGroup;
    RadioButton questionOneOptionTwo;
    //question 2 variables
    CheckBox questionTwoOptionOne;
    CheckBox questionTwoOptionTwo;
    CheckBox questionTwoOptionThree;
    CheckBox questionTwoOptionFour;
    //question 3 variables
    RadioGroup questionThreeOptionsGroup;
    RadioButton questionThreeOptionThree;
    //question 4 variables
    Button questionFourOptionTrue;
    Button questionFourOptionFalse;
    //question 5 variables
    RadioGroup questionFiveOptionsGroup;
    RadioButton questionFiveOptionTwo;
    //question 6 variables
    EditText questionSixTextField;
    //int questionSixInput;
    TextView questionSixCorrectAnswer;
    //question 7 variables
    RadioGroup questionSevenOptionsGroup;
    RadioButton questionSevenOptionOne;
    //views for submit answers, show results, scroll up and try again
    TextView scoreLabel;
    TextView scoreTextView;
    Button submit;
    Button tryAgain;
    Button scrollUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing all variables
        questionOneOptionsGroup = findViewById(R.id.question1_options_group);
        questionOneOptionTwo = findViewById(R.id.question_one_option_two);

        questionTwoOptionOne = findViewById(R.id.question_two_option_one);
        questionTwoOptionTwo = findViewById(R.id.question_two_option_two);
        questionTwoOptionThree = findViewById(R.id.question_two_option_three);
        questionTwoOptionFour = findViewById(R.id.question_two_option_four);

        questionThreeOptionsGroup = findViewById(R.id.question_three_options_group);
        questionThreeOptionThree = findViewById(R.id.question_three_option_three);

        questionFourOptionTrue = findViewById(R.id.question_four_option_true);
        questionFourOptionFalse = findViewById(R.id.question_four_option_false);

        questionFiveOptionsGroup = findViewById(R.id.question_five_options_group);
        questionFiveOptionTwo = findViewById(R.id.question_five_option_two);

        questionSixTextField = findViewById(R.id.question_six_text_field);
        questionSixCorrectAnswer = findViewById(R.id.question_six_correct_answer);

        questionSevenOptionsGroup = findViewById(R.id.question_seven_options_group);
        questionSevenOptionOne = findViewById(R.id.question_seven_option_one);

        scoreLabel = findViewById(R.id.score_label);
        scoreTextView = findViewById(R.id.score_text_view);
        submit = findViewById(R.id.submit_btn);
        tryAgain = findViewById(R.id.try_again_btn);
        scrollUp = findViewById(R.id.scroll_up_btn);

        //hide soft keyboard on orientation
        Window window = getWindow();
        if (window != null) {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        }


    }

    // save app state before rotation
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBooleanArray(BOOLEAN_ARRAY, new boolean[]{submitButtonState, trueButtonState, falseButtonState});
    }

    // restore app state after rotation
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            submitButtonState = savedInstanceState.getBooleanArray(BOOLEAN_ARRAY)[0];
            trueButtonState = savedInstanceState.getBooleanArray(BOOLEAN_ARRAY)[1];
            falseButtonState = savedInstanceState.getBooleanArray(BOOLEAN_ARRAY)[2];
            if (trueButtonState) checkTrueOrFalse(questionFourOptionTrue);
            if (falseButtonState) checkTrueOrFalse(questionFourOptionFalse);
            if (submitButtonState) checkAnswers(submit);
        }
    }

    // method for submit order button
    public void checkAnswers(View vew) {
        int score = 0;
        // check answer of question 1
        if (questionOneOptionsGroup.getCheckedRadioButtonId() == R.id.question_one_option_two) {
            score++;
        } else if (questionOneOptionsGroup.getCheckedRadioButtonId() != -1) {
            // highlight wrong answer with red background
            findViewById(questionOneOptionsGroup.getCheckedRadioButtonId()).setBackground(getResources().getDrawable(R.drawable.trans_red_bg));
        }

        // check answer of question 2
        if ((questionTwoOptionThree.isChecked() && questionTwoOptionFour.isChecked()) && !(questionTwoOptionOne.isChecked() || questionTwoOptionTwo.isChecked())) {
            score++;
        }

        // highlight wrong answer with red background
        if (questionTwoOptionOne.isChecked())
            questionTwoOptionOne.setBackground(getResources().getDrawable(R.drawable.trans_red_bg));
        if (questionTwoOptionTwo.isChecked())
            questionTwoOptionTwo.setBackground(getResources().getDrawable(R.drawable.trans_red_bg));


        // check answer of question 3
        if (questionThreeOptionsGroup.getCheckedRadioButtonId() == R.id.question_three_option_three) {
            score++;
        } else if (questionThreeOptionsGroup.getCheckedRadioButtonId() != -1) {
            // highlight wrong answer with red background
            findViewById(questionThreeOptionsGroup.getCheckedRadioButtonId()).setBackground(getResources().getDrawable(R.drawable.trans_red_bg));
        }
        // check answer of question 4
        if (!trueButtonState && falseButtonState) {
            score++;
        } else if (trueButtonState && !falseButtonState) {
            // highlight wrong answer with red background
            questionFourOptionTrue.setBackgroundResource(R.drawable.trans_red_bg);
        }
        // check answer of question 5
        if (questionFiveOptionsGroup.getCheckedRadioButtonId() == R.id.question_five_option_two) {
            score++;
        } else if (questionFiveOptionsGroup.getCheckedRadioButtonId() != -1) {
            // highlight wrong answer with red background
            findViewById(questionFiveOptionsGroup.getCheckedRadioButtonId()).setBackground(getResources().getDrawable(R.drawable.trans_red_bg));
        }

        // check answer of question 6
        if (Integer.parseInt("0" + questionSixTextField.getText().toString()) == 10) {
            score++;
            questionSixTextField.setBackground(getResources().getDrawable(R.drawable.trans_green_bg));
        } else {
            // highlight wrong answer with red background
            questionSixTextField.setBackground(getResources().getDrawable(R.drawable.trans_red_bg));
            questionSixCorrectAnswer.setText(getResources().getString(R.string.questsion6_correct_answer));
            questionSixCorrectAnswer.setBackground(getResources().getDrawable(R.drawable.trans_green_bg));
            questionSixCorrectAnswer.setPadding(8, 8, 8, 8);
        }

        // check answer of question 7
        if (questionSevenOptionsGroup.getCheckedRadioButtonId() == R.id.question_seven_option_one) {
            score++;
        } else if (questionSevenOptionsGroup.getCheckedRadioButtonId() != -1) {
            // highlight wrong answer with red background
            findViewById(questionSevenOptionsGroup.getCheckedRadioButtonId()).setBackground(getResources().getDrawable(R.drawable.trans_red_bg));
        }

        // highlight correct answers with green background
        questionOneOptionTwo.setBackground(getResources().getDrawable(R.drawable.trans_green_bg));
        questionTwoOptionThree.setBackground(getResources().getDrawable(R.drawable.trans_green_bg));
        questionTwoOptionFour.setBackground(getResources().getDrawable(R.drawable.trans_green_bg));
        questionThreeOptionThree.setBackground(getResources().getDrawable(R.drawable.trans_green_bg));
        questionFourOptionFalse.setBackground(getResources().getDrawable(R.drawable.trans_green_bg));
        questionFiveOptionTwo.setBackground(getResources().getDrawable(R.drawable.trans_green_bg));
        questionSevenOptionOne.setBackground(getResources().getDrawable(R.drawable.trans_green_bg));

        // show score after pressing submit button
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT, 1.3f);
        scoreLabel.setLayoutParams(params);
        scoreTextView.setLayoutParams(params);
        scoreTextView.setText("" + score + "/7");

        // adjust scroll up and try again buttons dimensions after pressing submit button
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT, 0.8f);
        params1.setMargins(16, 0, 0, 0);
        scrollUp.setLayoutParams(params1);
        tryAgain.setLayoutParams(params1);

        submitButtonState = true;
        submit.setClickable(false);
        // toast message
        String message = "";
        if (score < 7) {
            message = "Your score " + score + "/7 \nScroll up to see right answers or press try again for another attempt ";
        } else {
            message = "Perfect, all of your answers are correct. \nYour score 7/7";
        }
        Toast tst = Toast.makeText(this, message, Toast.LENGTH_LONG);
        tst.show();
    }

    // method for true or false buttons in question 6
    public void checkTrueOrFalse(View v) {
        switch (v.getId()) {
            case R.id.question_four_option_true:
                questionFourOptionTrue.setClickable(false);
                questionFourOptionTrue.setBackgroundResource(R.drawable.trans_green_bg);
                questionFourOptionFalse.setClickable(true);
                questionFourOptionFalse.setBackgroundResource(R.drawable.trans_black_bg);
                trueButtonState = true;
                falseButtonState = false;
                break;
            case R.id.question_four_option_false:
                questionFourOptionFalse.setClickable(false);
                questionFourOptionFalse.setBackgroundResource(R.drawable.trans_green_bg);
                questionFourOptionTrue.setClickable(true);
                questionFourOptionTrue.setBackgroundResource(R.drawable.trans_black_bg);
                trueButtonState = false;
                falseButtonState = true;
                break;
        }
    }

    // method for scroll up button
    public void scrollUp(View vew) {
        ScrollView mainScrollView = findViewById(R.id.hi);
        mainScrollView.fullScroll(ScrollView.FOCUS_UP);
    }

    // method for try again button
    public void tryAgain(View vew) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
