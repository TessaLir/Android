package ru.vetukov.tutorial.mdlesson002;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity2 extends AppCompatActivity {

    private static final String TAG = "TestBSS";
    private BottomSheetBehavior sheetBehavior;

    @BindView(R.id.editTextInNew) EditText text;
    @BindView(R.id.design_bottom_sheet) View bottomSheetLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_main);
        ButterKnife.bind(this);

        initUI();

    }

    private void initUI() {
        sheetBehavior = BottomSheetBehavior.from(bottomSheetLayout);
        sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        addCallbackToSheet();
    }

    @OnClick({R.id.fab1, R.id.fab2})
    void showSheet() {
        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    private void addCallbackToSheet() {
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

}
