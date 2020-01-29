package ru.spec.level_1.ex_5_apps;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

// Адаптер возвращает View, состоящий из ImageView и TextView
// Создает их по ApplicationInfo
public class ApplicationAdapter extends ArrayAdapter {

    // Контейнер ApplicationInfo
    private List<ApplicationInfo> info;
    private LayoutInflater inflater;
    private PackageManager manager;

    public ApplicationAdapter(Context context, int resource, List<ApplicationInfo> info) {
        super(context, resource);
        this.info = info;
        inflater = LayoutInflater.from(context);
        manager = context.getPackageManager();
    }

    // Для экономии времени -
    // стоило бы использовать шаблон ViewHolder, чтобы
    // не создавать View лишний раз
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Элемент контейнера, соответствующий информации о приложении
        ApplicationInfo i = info.get(position);

        // Создадим View
        View view = inflater.inflate(R.layout.item, parent, false );

        // Найдем ссылки на нужные виджеты по их идентификаторам
        TextView text = (TextView) view.findViewById(R.id.name);
        ImageView picture = (ImageView) view.findViewById(R.id.image);

        // Название приложения
        text.setText(i.loadLabel(manager).toString());

        // Иконка приложения
        Drawable appIcon = manager.getApplicationIcon(i);

        // Присвоим ее картинке
        picture.setImageDrawable(appIcon);

        // Возвратим
        return view;
    }

    // ListView должен знать, сколько всего элементов
    // в адаптере
    @Override
    public int getCount() {
        return info.size();
    }
}
