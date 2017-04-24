package com.example.kate.calcforgrid;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView calcText;

    private double number1;
    private double number2;
    private String action;
    private boolean forNumbers;

    private final String ACTION_RESULT = "=";
    private final String ACTION_DIVIDE = "/";
    private final String ACTION_DELETE = "c";
    private final String ACTION_MULTIPLY = "*";
    private final String ACTION_DEDUCT = "-";
    private final String ACTION_ADD = "+";
    private final String INFINITY = "∞";
    private final String NUM_0 = "0";
    private final String NUM_1 = "1";
    private final String NUM_2 = "2";
    private final String NUM_3 = "3";
    private final String NUM_4 = "4";
    private final String NUM_5 = "5";
    private final String NUM_6 = "6";
    private final String NUM_7 = "7";
    private final String NUM_8 = "8";
    private final String NUM_9 = "9";
    private final String POINT = ".";

    private final String NUMBER_1 = "number1";
    private final String NUMBER_2 = "number2";
    private final String ACT = "action";
    private final String TEXT = "calcText";
    private final String FOR_NUM = "forNumbers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calcText = (TextView) findViewById(R.id.text);
        forNumbers = false;
        action = "";
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for (int i = 1; i < gridLayout.getChildCount(); i++) {
            View view = gridLayout.getChildAt(i);
            view.setOnClickListener(onClickListener);
        }
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String num = (String) calcText.getText();
            try {
                double number = Double.parseDouble(num);
                switch (v.getTag().toString()) {
                    case ACTION_RESULT:
                        if (action.equals("")) {
//                            Toast.makeText(MainActivity.this, R.string.exeptOperation, Toast.LENGTH_LONG).show();
                            calcText.setText(num);
                            return;
                        }
                        number2 = number;
                        calcText.setTextColor(Color.rgb(138, 43, 226));
                        calcText.setText(getResult());
                        action = ACTION_RESULT;
                        break;
                    case ACTION_DIVIDE:
                        calcText.setText(ACTION_DIVIDE);
                        action = ACTION_DIVIDE;
                        number1 = number;
                        break;
                    case ACTION_MULTIPLY:
                        calcText.setText(ACTION_MULTIPLY);
                        action = ACTION_MULTIPLY;
                        number1 = number;
                        break;
                    case ACTION_DEDUCT:
                        calcText.setText(ACTION_DEDUCT);
                        action = ACTION_DEDUCT;
                        number1 = number;
                        forNumbers = false;
                        break;
                    case ACTION_ADD:
                        calcText.setText(ACTION_ADD);
                        action = ACTION_ADD;
                        number1 = number;
                        break;
                    default:
                        break;
                }
            } catch (NumberFormatException e) {
                if (num.equals("")) {
                    if (v.getTag().toString().equals(ACTION_DEDUCT)) {
                        action = ACTION_DEDUCT;
                        calcText.setText(ACTION_DEDUCT);
                        forNumbers = true;
                    }
                }else if (calcText.getText().toString().equals(INFINITY)){
                    calcText.setText(INFINITY);
                    Toast.makeText(MainActivity.this, R.string.infinityNotAction, Toast.LENGTH_LONG).show();
                }
                else {
                    num = "";
                }
            }

            if (action.equals(ACTION_DEDUCT) && calcText.getText().equals(ACTION_DEDUCT) && forNumbers){
                num = ACTION_DEDUCT + num;
            } else if (calcText.getText().equals(POINT)){
                num = NUM_0 + POINT + num;
                calcText.setText(num);
            } else if (action.equals(ACTION_RESULT)){
                return;
            }
            switch (v.getTag().toString()) {
                case NUM_0:
                    num += NUM_0;
                    calcText.setText(num);
                    break;
                case NUM_1:
                    num += NUM_1;
                    calcText.setText(num);
                    break;
                case NUM_2:
                    num += NUM_2;
                    calcText.setText(num);
                    break;
                case NUM_3:
                    num += NUM_3;
                    calcText.setText(num);
                    break;
                case NUM_4:
                    num += NUM_4;
                    calcText.setText(num);
                    break;
                case NUM_5:
                    num += NUM_5;
                    calcText.setText(num);
                    break;
                case NUM_6:
                    num += NUM_6;
                    calcText.setText(num);
                    break;
                case NUM_7:
                    num += NUM_7;
                    calcText.setText(num);
                    break;
                case NUM_8:
                    num += NUM_8;
                    calcText.setText(num);
                    break;
                case NUM_9:
                    num += NUM_9;
                    calcText.setText(num);
                    break;
                case POINT:
                    if(calcText.getText().equals(NUM_0 + POINT)){return;}
                    num += POINT;
                    calcText.setText(num);
                    break;
                case ACTION_DELETE:
                    calcText.setText("");
                    action = "";
                    forNumbers = false;
                    break;
                default:
                    break;
            }
        }
    };

    private String getResult() {
        double result = 0;
        String resultToText = "";
        switch (action) {
            case ACTION_DIVIDE:
                if (number2 != 0) {
                    result = number1 / number2;
                    resultToText = "" + result;
                } else resultToText = INFINITY;
                break;
            case ACTION_MULTIPLY:
                result = number1 * number2;
                resultToText = "" + result;
                break;
            case ACTION_DEDUCT:
                result = number1 - number2;
                resultToText = "" + result;
                break;
            case ACTION_ADD:
                result = number1 + number2;
                resultToText = "" + result;
                break;
            default:
                Toast.makeText(MainActivity.this, R.string.exeptInResult, Toast.LENGTH_LONG).show();
                action = "";
                break;
        }
        number1 = 0;
        number2 = 0;
        return resultToText;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(ACT, action);
        outState.putString(TEXT, calcText.getText().toString());
        outState.putDouble(NUMBER_1, number1);
        outState.putDouble(NUMBER_2, number2);
        outState.putBoolean(FOR_NUM, forNumbers);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        action = savedInstanceState.getString(ACT);
        number1 = savedInstanceState.getDouble(NUMBER_1);
        number2 = savedInstanceState.getDouble(NUMBER_2);
        forNumbers = savedInstanceState.getBoolean(FOR_NUM);

        calcText.setText(savedInstanceState.getString(TEXT));
    }
}
