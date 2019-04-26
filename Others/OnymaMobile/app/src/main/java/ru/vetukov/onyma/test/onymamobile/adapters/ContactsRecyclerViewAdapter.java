package ru.vetukov.onyma.test.onymamobile.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.vetukov.onyma.test.onymamobile.R;
import ru.vetukov.onyma.test.onymamobile.objects.Contact;

public class ContactsRecyclerViewAdapter extends RecyclerView.Adapter<ContactsRecyclerViewAdapter.ContactsHolder> {

    private List<Contact> mContactList;

    public ContactsRecyclerViewAdapter(List<Contact> list) {
        mContactList = list;
    }

    @NonNull
    @Override
    public ContactsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.contacts_item, viewGroup, false);
        return new ContactsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsHolder contactsHolder, int i) {
        Contact contact = mContactList.get(i);

        contactsHolder.mViewType.setText(contact.getContactType().getRuValue());
        contactsHolder.mViewValue.setText(contact.getValue());
    }

    @Override
    public int getItemCount() {
        return mContactList.size();
    }

    public class ContactsHolder extends RecyclerView.ViewHolder {

        private TextView mViewType;
        private TextView mViewValue;

        public ContactsHolder(@NonNull View itemView) {
            super(itemView);
            mViewType   = itemView.findViewById(R.id.item_text_type);
            mViewValue  = itemView.findViewById(R.id.item_text_value);
        }
    }
}
