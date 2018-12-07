package ru.vetukov.onyma.test.onyma.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ru.vetukov.onyma.test.onyma.R;

public class AuthFragment extends Fragment {


    private Listener mListener;

    private Button btnEntry;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (Listener) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frarment_auth, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnEntry = view.findViewById(R.id.button_enter);

        btnEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.btnClick(AuthFragment.this);
            }
        });

    }

}
