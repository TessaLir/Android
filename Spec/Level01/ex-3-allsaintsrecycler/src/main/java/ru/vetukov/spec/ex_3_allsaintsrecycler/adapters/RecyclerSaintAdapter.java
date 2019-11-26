package ru.vetukov.spec.ex_3_allsaintsrecycler.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import ru.vetukov.spec.ex_3_allsaintsrecycler.R;
import ru.vetukov.spec.ex_3_allsaintsrecycler.Saint;
import ru.vetukov.spec.ex_3_allsaintsrecycler.holders.SaintHolder;

public class RecyclerSaintAdapter extends RecyclerView.Adapter<SaintHolder> {

    private HashSet<Integer> selection = new HashSet<>();
    private List<Saint> saints;

    private OnItemClickListener clickListener;
    private OnItemLongClickListener longClickListener;

    public RecyclerSaintAdapter(List<Saint> saints) {
        this.saints = saints;
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View itemView, int position);
    }

    public void setOnClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }

    public void setOnLongClickListener(OnItemLongClickListener listener) {
        this.longClickListener = listener;
    }

    public void ratingChanged(int position, float newRating) {
        saints.get(position).setRating(newRating);
//        notifyDataSetChanged();
    }

    public void remove(int pos) {
        selection.remove(pos);
        saints.remove(pos);
        notifyDataSetChanged();
    }

    // Хелпер - выделен ли конкретный элемент
    public boolean hasSelected() {
        return !selection.isEmpty();
    }

    boolean isSelected(int position) {
        return selection.contains(position);
    }

    // Выделение - если элемент выделен, сделать не выделенным;
    // если не выделен - выделить.
    // В любом случае при изменении статуса уведомить об этом
    // адаптер.
    public void toggleSelection(int position) {
        if (!isSelected(position)) selection.remove(position);
        else selection.add(position);
        notifyDataSetChanged();
    }

    // Удалить выделенные элементы
    public void deleteSelected() {
        List<Integer> items = new ArrayList<>();

        // Вначале формируем List из сета.
        items.addAll(selection);

        // Обратно сортируем этот личт, чтобы вначале удалять элемент с самым большим номером
        Collections.sort(items);
        Collections.reverse(items);

        // Удаляем как сам элемент так и его признак выделенности
        for (int i : items) {
            selection.remove(i);
            saints.remove(i);
        }

        // Уведомляем об этом адаптер.
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public SaintHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = null;

        switch (viewType) {
            case 0 :
                view = inflater.inflate(R.layout.listviewitem, parent, false);
                break;
            case 1 :
                view = inflater.inflate(R.layout.listviewitemselected, parent, false);
                break;
        }

        return new SaintHolder(view, this, clickListener, longClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SaintHolder holder, int position) {
        Saint saint = saints.get(position);

        // Актуализируем данные View через ссылки ViewHolder
        holder.name.setText(saint.getName());
        holder.dob.setText(saint.getDob());
        holder.dod.setText(saint.getDod());
        holder.rating.setRating(saint.getRating());

    }

    @Override
    public int getItemViewType(int position) {
        if (isSelected(position)) return 1;
        else return 0;
    }

    @Override
    public int getItemCount() {
        return saints.size();
    }
}
