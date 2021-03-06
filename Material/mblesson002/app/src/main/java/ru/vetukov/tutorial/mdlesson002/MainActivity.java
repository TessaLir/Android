package ru.vetukov.tutorial.mdlesson002;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static final String EMPTY_STRING = "";

    private static final String TAG = "MainActivity";
    private Snackbar mSnackbar;

    @BindView(R.id.mainLayout) ConstraintLayout mConstraintLayout;
    @BindView(R.id.inputLayout1) TextInputLayout mTILFirst;
    @BindView(R.id.button) Button mButton;
    @BindView(R.id.editText1) EditText mETFirst;
    @BindView(R.id.editText2) EditText mETSecond;

    @BindString(R.string.main_activity_snackbar_visible) String mInservText;
    @BindString(R.string.main_activity_snackbar_invisible) String textSnackbarVisible;
    @BindString(R.string.main_activity_just_click) String textSnackbarInvisible;

    public static Snackbar getCustomSnackbar(View parentLayout, String message) {
        return Snackbar.make(parentLayout, message, Snackbar.LENGTH_LONG);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initUI();
        initSnackbar();
    }

    @OnClick(R.id.button)
    void  buttonClick() { mSnackbar.show(); }

    private void initSnackbar() {
        View.OnClickListener snackbarOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // здесь какое-то действия
                mETFirst.setText(mInservText);
            }
        };

        mSnackbar = getCustomSnackbar(mConstraintLayout, "Какое-то сообщение");
        mSnackbar.setAction("UNDO", snackbarOnClickListener);
        mSnackbar.setActionTextColor(Color.RED);

        mSnackbar.addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                super.onDismissed(transientBottomBar, event);
                mETSecond.setText(textSnackbarInvisible);
            }

            @Override
            public void onShown(Snackbar transientBottomBar) {
                super.onShown(transientBottomBar);
                mETSecond.setText(textSnackbarVisible);
            }
        });
    }

    private void initUI() {
        addEditTextListening();
    }

    private void addEditTextListening() {
        mETFirst.setOnEditorActionListener(new OurTextListener(new WeakReference<>(this)));
        mETFirst.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (isNeedShowError(editable.toString())) {
                    showError();
                } else {
                    hideError();
                }
                Log.e(TAG, "afterTextChanged: " + "afterTextChanged");
            }
        });
    }

    private void showError() {
        mTILFirst.setError("Not Email");
    }

    private void hideError() {
        mTILFirst.setError(EMPTY_STRING);
    }

    private boolean isNeedShowError(String s) {
        return TextUtils.isEmpty(s) || !s.contains("@");
    }

    private static class OurTextListener implements TextView.OnEditorActionListener {
        private final WeakReference<MainActivity> mainActivityWeakReference;

        private OurTextListener(WeakReference<MainActivity> mainActivityWeakReference) {
            this.mainActivityWeakReference = mainActivityWeakReference;
        }

        @Override
        public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
            MainActivity mainActivity = mainActivityWeakReference.get();
            if (mainActivity != null) {
                if (actionId == EditorInfo.IME_ACTION_NEXT && mainActivity.isNeedShowError(textView.getText().toString())) {
                    mainActivity.showError();
                } else {
                    mainActivity.hideError();
                }
            }
            return true;
        }
    }
}