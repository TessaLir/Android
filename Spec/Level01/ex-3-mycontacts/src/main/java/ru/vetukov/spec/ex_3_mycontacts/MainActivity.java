package ru.vetukov.spec.ex_3_mycontacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CONTACTS = 341;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.main_list);
        showContacts();
    }

    private void showContacts() {
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
        && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED )
            // нужно запросить
            requestPermissions( new String[] { Manifest.permission.READ_CONTACTS},REQUEST_CONTACTS);
        else {
            // права есть
            Cursor contacts = getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    null,
                    null,
                    null
            );

            ListAdapter adapter = new SimpleCursorAdapter(
                    this,
                    android.R.layout.simple_list_item_2,
                    contacts,
                    new String[]{
                            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                    },
                    new int[] {
                            android.R.id.text1,
                            android.R.id.text2
                    },
                    0
            );
            list.setAdapter(adapter);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,@NonNull int[] grantResults) {
        if(requestCode == REQUEST_CONTACTS) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                showContacts();
            else
                Toast.makeText(this, "Не могу показать контакты", Toast.LENGTH_SHORT ).show();
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
