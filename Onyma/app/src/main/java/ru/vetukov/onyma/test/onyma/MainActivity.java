package ru.vetukov.onyma.test.onyma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import ru.vetukov.onyma.test.onyma.fragments.AuthFragment;
import ru.vetukov.onyma.test.onyma.fragments.Listener;
import ru.vetukov.onyma.test.onyma.object.User;

public class MainActivity extends AppCompatActivity implements Listener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        getSupportFragmentManager().beginTransaction()
                                   .replace(R.id.view_container, new AuthFragment())
                                   .commit();

    }

    @Override
    public void btnClick(Object fragment) {
        if (fragment instanceof AuthFragment) {

            EditText mETFName   = ((AuthFragment) fragment).getActivity().findViewById(R.id.edit_login);
            EditText mETLName   = ((AuthFragment) fragment).getActivity().findViewById(R.id.edit_login);
            EditText mETPatron  = ((AuthFragment) fragment).getActivity().findViewById(R.id.edit_login);
            EditText mETServer  = ((AuthFragment) fragment).getActivity().findViewById(R.id.edit_login);

            User user = new User(mETFName.getText().toString()
                                ,mETLName.getText().toString()
                                ,mETPatron.getText().toString()
                                ,mETServer.getText().toString());

            Toast.makeText(this, user.getLogin(), Toast.LENGTH_SHORT).show();
        }
    }
}
