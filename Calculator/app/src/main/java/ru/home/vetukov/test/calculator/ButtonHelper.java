package ru.home.vetukov.test.calculator;

import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ButtonHelper
        extends Activity
        implements View.OnClickListener {

    private Button[] buttons;

    TextView resultView;
    TextView memberView;

    public ButtonHelper(CalculatorActivity view) {

        resultView = view.findViewById(R.id.result_basic);
        memberView = view.findViewById(R.id.result_memory);

        resultView.setText("");
        memberView.setText("");

        ConstraintLayout clView = view.findViewById(R.id.button_list);

        this.buttons = new Button[]{
                clView.findViewById(R.id.button_zero),
                clView.findViewById(R.id.button_one),
                clView.findViewById(R.id.button_two),
                clView.findViewById(R.id.button_three),
                clView.findViewById(R.id.button_four),
                clView.findViewById(R.id.button_five),
                clView.findViewById(R.id.button_six),
                clView.findViewById(R.id.button_seven),
                clView.findViewById(R.id.button_eight),
                clView.findViewById(R.id.button_nine)
        };

        for (Button bt : buttons) {
            bt.setOnClickListener(view);
        }
    }

    public Button[] getButtons() {
        return buttons;
    }

    public Button getButton(int position) {
        return buttons[position];
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_zero:
                Log.d("happy", "Click Button ZERO");
                memberView.append(String.format("%d", Integer.MAX_VALUE));
                resultView.append(String.format("%d", Long.MAX_VALUE));
                break;
            case R.id.button_one:
                Log.d("happy", "Click Button ONE");
                resultView.append("1");
                break;
            case R.id.button_two:
                Log.d("happy", "Click Button TWO");
                resultView.append("2");
                break;
            case R.id.button_three:
                Log.d("happy", "Click Button THREE");
                resultView.append("3");
                break;
            case R.id.button_four:
                Log.d("happy", "Click Button FOUR");
                resultView.append("4");
                break;
            case R.id.button_five:
                Log.d("happy", "Click Button FIVE");
                resultView.append("5");
                break;
            case R.id.button_six:
                Log.d("happy", "Click Button SIX");
                resultView.append("6");
                break;
            case R.id.button_seven:
                Log.d("happy", "Click Button SEVEN");
                resultView.append("7");
                break;
            case R.id.button_eight:
                Log.d("happy", "Click Button EIGHT");
                resultView.append("8");
                break;
            case R.id.button_nine:
                Log.d("happy", "Click Button NINE");
                resultView.append("9");
                break;
        }
    }

}
