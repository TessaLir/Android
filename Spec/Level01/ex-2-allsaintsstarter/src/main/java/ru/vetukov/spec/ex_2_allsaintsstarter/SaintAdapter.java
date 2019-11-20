package ru.vetukov.spec.ex_2_allsaintsstarter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class SaintAdapter extends ArrayAdapter<Saint> {

    private List<Saint> saints;

    public SaintAdapter (@NonNull Context context, int resource, List<Saint> saints) {
        super(context, resource);
        this.saints = saints;
    }

    @Override
    public int getCount() {
        return saints.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.listviewitem, parent, false);
        Saint saint = saints.get(position);

        TextView name = view.findViewById(R.id.main_item_text);
        name.setText(saint.getName());

        return view;
    }

}
