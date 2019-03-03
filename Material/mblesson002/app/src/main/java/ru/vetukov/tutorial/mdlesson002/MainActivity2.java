package ru.vetukov.tutorial.mdlesson002;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    private static final String TAG = "TestBSS";
    private EditText text;
    private BottomSheetBehavior sheetBehavior;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_main);

        initUI();

        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                printStatus(newState);
                if (newState == BottomSheetBehavior.STATE_HIDDEN
                        || newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    text.setEnabled(true);
                } else {
                    text.setEnabled(false);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

            }
        });
    }

    private void printStatus(int newState) {
        switch (newState) {
            case BottomSheetBehavior.STATE_COLLAPSED:
                Log.d(TAG, "STATE_COLLAPSED");
                break;
            case BottomSheetBehavior.STATE_DRAGGING:
                Log.d(TAG, "STATE_DRAGGING");
                break;
            case BottomSheetBehavior.STATE_EXPANDED:
                Log.d(TAG, "STATE_EXPANDED");
                break;
            case BottomSheetBehavior.STATE_HIDDEN:
                Log.d(TAG, "STATE_HIDDEN");
                break;
            case BottomSheetBehavior.STATE_SETTLING:
                Log.d(TAG, "STATE_SETTLING");
                break;
            default:
                Log.e(TAG, "unsupported status");
                break;
        }
    }

    private void initUI() {
        text = findViewById(R.id.editTextInNew);
        View view = findViewById(R.id.design_bottom_sheet);
        sheetBehavior = BottomSheetBehavior.from(view);
        sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        fab = findViewById(R.id.fab);
    }

}
