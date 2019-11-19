package ru.vetukov.spec.ex_2_menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {

    private PopupMenu popup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerForContextMenu(findViewById(R.id.main_tv_hello));

        popup = new PopupMenu(this, findViewById(R.id.main_tv_hello));
        popup.inflate(R.menu.popup);
        popup.setOnMenuItemClickListener(this);

        findViewById(R.id.main_tv_hello).setOnClickListener(this);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        if (v.getId() == R.id.main_tv_hello) {
            getMenuInflater().inflate(R.menu.context, menu);
        }

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

//        MenuItem item = menu.add(Menu.NONE, 345, Menu.NONE, "Buy");
//        item.setIcon(R.drawable.ic_arrow);
//        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_ALWAYS);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.menu_context_exit) {
            Toast.makeText(this, "Exit", Toast.LENGTH_SHORT).show();
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.main_add:
                Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_delete:
                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_quit:
                Toast.makeText(this, "Quit", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View v) {
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {

        if(menuItem.getItemId() == R.id.popup)
        {
            Toast.makeText(this, "Popup", Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }
}
