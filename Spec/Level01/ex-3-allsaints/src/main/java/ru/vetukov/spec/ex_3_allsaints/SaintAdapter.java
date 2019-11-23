package ru.vetukov.spec.ex_3_allsaints;

import android.content.Context;
import android.media.Rating;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.SectionIndexer;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

public class SaintAdapter
        extends ArrayAdapter<Saint>
        implements SectionIndexer {

    private List<Saint> saints;
    // Список содержащий в себе все выделеные элементы
    private HashSet<Integer> selection = new HashSet<>();

    public SaintAdapter (@NonNull Context context, int resource, List<Saint> saints) {
        super(context, resource);
        this.saints = saints;
    }

    // Проверка на признак, имеется ли вообще выделеные  элементы
    boolean hasSelected() {
        return !selection.isEmpty();
    }

    // Проверяем, выдлен ли данный эллемент
    boolean isSelected(int pos) {
        return selection.contains(pos);
    }

    // Если элемент выделен, то снимаем выделение, в противном случае выделяем
    void toggleSelection(int pos) {
        if (isSelected(pos)) selection.remove(pos);
        else selection.add(pos);
        notifyDataSetChanged();
    }

    // Удаление выделеного элемента из выборки.
    void deleteSelected() {
        List<Integer> items = new ArrayList<>();
        items.addAll(selection);
        Collections.sort(items, Collections.reverseOrder());
        // Удалить святого
        // Удалить выделеный элемент
        for (int i : items) {
            selection.remove(i);
            saints.remove(i);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (isSelected(position)) return 1;
        else return 0;
    }

    @Override
    public int getCount() {
        return saints.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = convertView;
        Holder holder = null;

        if (view == null) {
            if (isSelected(position)) {
                view = inflater.inflate(R.layout.listviewitemselected, parent, false);
            } else {
                view = inflater.inflate(R.layout.listviewitem, parent, false);
            }

            holder = new Holder(
                    (TextView) view.findViewById(R.id.main_item_text),
                    (TextView) view.findViewById(R.id.main_item_dob),
                    (TextView) view.findViewById(R.id.main_item_dod),
                    (RatingBar) view.findViewById(R.id.main_item_rating)
            );

            view.setTag(holder);
        }

        holder = (Holder) view.getTag();

        Saint saint = saints.get(position);

        holder.name.setText(saint.getName());
        holder.dob.setText(saint.getDob());
        holder.dod.setText(saint.getDod());
        holder.rating.setRating(saint.getRating());

        return view;
    }

    private Object[] sections;

    // Вернуть в виде массива объектов все уникальные первые буквы имен
    @Override
    public Object[] getSections() {
        LinkedHashSet<String> set = new LinkedHashSet<>();
        for (Saint s : saints) {
            set.add(s.getName().substring(0,1));
        }
        sections = set.toArray();
        return sections;
    }

    // элементы
    // 0 Abo
    // 1 Az
    // 2 Ba
    // 3 Bari
    // секции
    // 0 A
    // 1 B
    // Для секции 1 (B) возвратить позицию с которой начинается - 2
    public int getPositionForSection(int sectionIndex) {
        String letter = (String) sections[sectionIndex];
        for(int i = 0; i < saints.size(); i++)
        {
            if(saints.get(i).getName().substring(0,1).equals(letter))
                return i;
        }
        return saints.size() - 1;
    }

    // для позиции 3 (Bari) возвратить номер секции  - 1
    public int getSectionForPosition(int position) {
        String letter = saints.get(position).getName().substring(0, 1);
        for (int i = 0; i < sections.length; i++)
        {
            if(letter.equals((String) sections[i]))
                return i;
        }
        return sections.length - 1;
    }


    class Holder {
        TextView name;
        TextView dob;
        TextView dod;
        RatingBar rating;

        public Holder(TextView name, TextView dob, TextView dod, RatingBar rating) {
            this.name = name;
            this.dob = dob;
            this.dod = dod;
            this.rating = rating;
        }
    }
}
